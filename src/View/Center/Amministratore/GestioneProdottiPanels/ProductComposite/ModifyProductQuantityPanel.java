package View.Center.Amministratore.GestioneProdottiPanels.ProductComposite;

import Business.CategoriaProdottoBusiness;
import Business.HelpFunctions;
import DAO.Classi.CategoriaProdottoDAO;
import DAO.Classi.ProdottoCompositoDAO;
import DAO.Classi.ProdottoDAO;
import Model.*;
import View.AppFrame;
import View.Listener.Amministratore.GestioneProdottiListeners.ProductComposite.ModifyProductQuantityListener;
import View.Nameable;
import View.SpinnerEditor;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class ModifyProductQuantityPanel extends JPanel {

    private ModifyProductQuantityListener modifyProductQuantityListener;

    private JPanel modifyPanel = new JPanel();
        private JLabel inserisciNomeProdotto = new JLabel("Nome:");
        private JTextField nomeProdottoField = new JTextField();
        private JLabel inserisciDescrizione = new JLabel("Descrizione:");
        private JTextField descrizioneField = new JTextField();
        private JLabel inserisciCategoria = new JLabel("Categoria principale: ");
        private JComboBox<String> categoriaField;
        private JLabel inserisciPrezzo = new JLabel("Prezzo:                     €");
        private JTextField prezzoField = new JTextField();
        private JLabel inserisciSottoCategoria = new JLabel("Sottocategoria: ");
        private JComboBox<String> sottocategoriaField = null;
    private JScrollPane currentScrollPane;
        private JTable currentTable;
        private Prodotto_QuantitaTableModel currentTableModel;
        private TableColumn quantityColumn;
        private JSpinner quantityField = new JSpinner();
    private JPanel eastPanel = new JPanel();
        private JPanel operationsPanel = new JPanel();
            private JButton btnElimina = new JButton("Elimina");
    private JPanel southPanel = new JPanel();
        private JPanel addProductPanel = new JPanel();
            private JLabel inserisciProdotto = new JLabel("Inserisci un nuovo prodotto: ");
            private JTextField prodottoField = new JTextField();
            private JLabel inserisciNuovaQuantita = new JLabel("Quantità:");
            private JSpinner nuovaQuantitaField = new JSpinner();
            private JButton btnAggiungi = new JButton("Aggiungi");
        private JPanel savePanel = new JPanel();
            private JButton btnSalva = new JButton("Salva modifiche");
            private JButton btnAnnulla = new JButton("Annulla");
            private JButton btnReset = new JButton("Reset");


    ArrayList<Prodotto_Quantita> lista;
    private ArrayList<Nameable> comboList;
    private ArrayList<CategoriaProdotto> cList;     //Lista di categorie
    private ArrayList<CategoriaProdotto> scList;    //Lista di sottocategorie
    private ProdottoComposito prodottoComposito;
    private ProdottoComposito initialProdottoComposito;

    private String selectedItem;
    private int selectedRow;
    private int indexModel;

    public ModifyProductQuantityPanel(AppFrame appFrame, ProdottoComposito prodottoCompositoN){

        modifyProductQuantityListener = new ModifyProductQuantityListener(this, appFrame);
        prodottoComposito = prodottoCompositoN;
        initialProdottoComposito = prodottoCompositoN;


        cList = CategoriaProdottoDAO.getInstance().findAll();
        comboList = (ArrayList<Nameable>) cList.clone();
        categoriaField = new JComboBox<>(HelpFunctions.fromArraytoString(comboList));
        sottocategoriaField = new JComboBox<>();


        SpinnerModel model = new SpinnerNumberModel(1, 1, 100, 1);
        quantityField.setModel(model);
        nuovaQuantitaField.setModel(model);


        fillForm();


        currentScrollPane = new JScrollPane(currentTable);
        quantityColumn = currentTable.getColumnModel().getColumn(6);
        quantityColumn.setCellEditor(new SpinnerEditor());

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void aggiungi(){
        Prodotto_Quantita p = new Prodotto_Quantita(ProdottoDAO.getInstance().findByName(prodottoField.getText()),Integer.parseInt(nuovaQuantitaField.getValue().toString()));
        currentTableModel.add(p);
        currentTableModel.fireTableDataChanged();
    }

    public void saveCompleted(){
        currentTableModel.setRowEditable(-1);
        operationsPanel.remove(btnSalva);
        operationsPanel.remove(btnAnnulla);
        ProdottoComposito pUpdated = new ProdottoComposito();
        CategoriaProdotto categoriaProdotto = new CategoriaProdotto();
        categoriaProdotto.setNome(currentTableModel.getValueAt(indexModel, 3).toString());
        categoriaProdotto.getSottocategorie().add(new CategoriaProdotto());
        categoriaProdotto.getSottocategorie().get(0).setNome(currentTableModel.getValueAt(indexModel, 4).toString());
        Produttore produttore = new Produttore("Vario");
        pUpdated.setId(prodottoComposito.getId());
        pUpdated.setNome(currentTableModel.getValueAt(indexModel, 0).toString());
        pUpdated.setPrezzo(Float.parseFloat(currentTableModel.getValueAt(indexModel, 1).toString()));
        pUpdated.setDescrizione(currentTableModel.getValueAt(indexModel, 2).toString());
        pUpdated.setCategoria(categoriaProdotto);
        pUpdated.setProduttore(produttore);
        pUpdated.setSottoprodotti(currentTableModel.getLista());
        ProdottoCompositoDAO.getInstance().update(pUpdated);
        prodottoComposito = pUpdated;
    }

    public void elimina(){
        int selectedRow = currentTable.getSelectedRow();
        selectedRow = currentTable.convertRowIndexToModel(selectedRow);
        currentTableModel.remove(selectedRow);
        currentTableModel.fireTableDataChanged();
    }

    public void reset(){
        fillForm();
        nuovaQuantitaField.setValue(1);
        prodottoField.setText("");
    }

    //Riempie il form automaticamente
    public void fillForm(){
        lista = prodottoComposito.cloneList();
        categoriaField.setSelectedItem(prodottoComposito.getCategoria().getNome());

        scList = CategoriaProdottoBusiness.getInstance().findSubCategories(prodottoComposito.getCategoria().getNome(),cList);
        setComboBoxSottoCategorie();
        sottocategoriaField.setSelectedItem(prodottoComposito.getCategoria().getSottocategorie().get(0).getNome());
        if(currentTableModel == null){
            currentTableModel = new Prodotto_QuantitaTableModel(lista);
            currentTable = new JTable(currentTableModel);
        }else{
            currentTableModel.copyList(lista);
        }


        nomeProdottoField.setText(prodottoComposito.getNome());
        descrizioneField.setText(prodottoComposito.getDescrizione());
        prezzoField.setText(prodottoComposito.getPrezzo().toString());

        currentTableModel.fireTableDataChanged();

    }

    //--------------------------------------------------------------------------------//
    //Imposta le sottocategoria nel secondo menù a tendina in base alla categoria scelta
    public void setComboBoxSottoCategorie(){
        selectedItem = categoriaField.getSelectedItem().toString();
        if(selectedItem == "--"){
            return;
        }
        sottocategoriaField.removeAllItems();
        comboList = (ArrayList<Nameable>)getArraySottoCategorie(selectedItem).clone();
        HelpFunctions.setComboBox(sottocategoriaField, HelpFunctions.fromArraytoString(comboList));
        sottocategoriaField.setEnabled(true);
    }

    //Restituisce un array di sottocategorie dato il nome di una categoria
    public ArrayList<CategoriaProdotto> getArraySottoCategorie(String nomeCategoria){
        ArrayList<CategoriaProdotto> scList = new ArrayList<>();    //array di sottocategorie

        for(int i=0;i<cList.size();i++){
            if(cList.get(i).getNome().equals(nomeCategoria))
                scList.addAll(cList.get(i).getSottocategorie());
        }

        return scList;
    }


    //-------------------------------------------------------------------------------//

    public void tableSetting(){

    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
            modifyPanel.setLayout(new GridLayout(0,4,10,10));
            currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            eastPanel.setLayout(new FlowLayout());
            operationsPanel.setLayout(new GridLayout(0,1,10,10));
            southPanel.setLayout(new GridLayout(2,0,10,10));
            addProductPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
            savePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
    }

    public void componentsAdding(){
        add(modifyPanel,BorderLayout.NORTH);
            modifyPanel.add(inserisciNomeProdotto);
            modifyPanel.add(nomeProdottoField);
            modifyPanel.add(inserisciDescrizione);
            modifyPanel.add(descrizioneField);
            modifyPanel.add(inserisciCategoria);
            modifyPanel.add(categoriaField);
            modifyPanel.add(inserisciPrezzo);
            modifyPanel.add(prezzoField);
            modifyPanel.add(inserisciSottoCategoria);
            modifyPanel.add(sottocategoriaField);
        add(currentScrollPane,BorderLayout.CENTER);
        add(eastPanel, BorderLayout.EAST);
            eastPanel.add(operationsPanel);
                operationsPanel.add(btnElimina);
        add(southPanel,BorderLayout.SOUTH);
            southPanel.add(addProductPanel);
                addProductPanel.add(inserisciProdotto);
                addProductPanel.add(prodottoField);
                addProductPanel.add(inserisciNuovaQuantita);
                addProductPanel.add(nuovaQuantitaField);
                addProductPanel.add(btnAggiungi);
            southPanel.add(savePanel);
                savePanel.add(btnAnnulla);
                savePanel.add(btnReset);
                savePanel.add(btnSalva);

    }

    public void componentsSizing(){
        currentScrollPane.setPreferredSize(new Dimension(1000,300));
        nomeProdottoField.setPreferredSize(new Dimension(200,20));
        prodottoField.setPreferredSize(new Dimension(200,20));
        currentTable.setRowHeight(30);
    }

    public void listenerSettings(){
        btnElimina.addActionListener(modifyProductQuantityListener);
        btnSalva.addActionListener(modifyProductQuantityListener);
        btnReset.addActionListener(modifyProductQuantityListener);
        btnAnnulla.addActionListener(modifyProductQuantityListener);
        btnAggiungi.addActionListener(modifyProductQuantityListener);
        categoriaField.addActionListener(modifyProductQuantityListener);

        btnElimina.setActionCommand("elimina");
        btnSalva.setActionCommand("salva");
        btnReset.setActionCommand("reset");
        btnAnnulla.setActionCommand("annulla");
        btnAggiungi.setActionCommand("aggiungi");
        categoriaField.setActionCommand("categoria");
    }

}

