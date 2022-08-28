package View.Center.Amministratore.GestioneArticoliPanels.Product;


import Business.HelpFunctions;
import DAO.Classi.CategoriaProdottoDAO;
import DAO.Classi.ProdottoDAO;
import DAO.Classi.FornitoreDAO;
import Model.CategoriaProdotto;
import Model.Fornitore;
import Model.Prodotto;
import View.AppFrame;
import View.Listener.Amministratore.GestioneArticoliListeners.Product.ModifyProductListener;
import View.Nameable;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class ModifyProductPanel extends JPanel {

    private ModifyProductListener modifyProductListener;
    private AppFrame appFrame;

    private JPanel cercaPanel = new JPanel();
        private JLabel inserisciNomeProdotto = new JLabel("Inserisci l'id del prodotto");
        private JTextField nomeProdottoField = new JTextField();
        private JButton btnCerca = new JButton("Cerca");
    private JScrollPane currentScrollPane;
        private JTable currentTable;
        private ProdottoTableModel currentTableModel;
        private TableColumn produttoreColumn;
        private TableColumn categoriaColumn;
        private TableColumn sottocategoriaColumn;
        private JComboBox<String> comboProduttori;
        private JComboBox<String> comboCategoria;
        private JComboBox<String> comboSottocategoria;
    private JPanel operationsPanel = new JPanel();
        private JButton btnModifica = new JButton("Modifica");
        private JButton btnElimina = new JButton("Elimina");
        private JButton btnSfoglia = new JButton("Sfoglia");
        private JButton btnSalva = new JButton("Salva modifiche");
        private JButton btnAnnulla = new JButton("Annulla");


    private int tableChosen = 1; //0 se la tabella mostra un solo prodotto, 1 se mostra tutti i prodotti
    private ArrayList<CategoriaProdotto> cList;
    private ArrayList<Fornitore> pList;
    private ArrayList<Nameable> comboList;
    private int selectedRow;
    private int indexModel;
    private String selectedProduct;
    private String searchedProduct;
    private String selectedCategory;

    private java.util.List<Prodotto> lista;


    public ModifyProductPanel(AppFrame appFrame){

        /*lista = ProdottoDAO.getInstance().findAllProducts();

        this.appFrame = appFrame;
        modifyProductListener = new ModifyProductListener(this, this.appFrame);

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();*/

    }



//--------------------------------------------------------------------------------------------------//
    public void tableSetting(){
        currentTableModel = new ProdottoTableModel(lista);
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
            currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void componentsAdding(){
        add(cercaPanel,BorderLayout.NORTH);
            cercaPanel.add(inserisciNomeProdotto);
            cercaPanel.add(nomeProdottoField);
            cercaPanel.add(btnCerca);
        add(currentScrollPane, BorderLayout.CENTER);
        add(operationsPanel, BorderLayout.SOUTH);
            operationsPanel.add(btnSfoglia);
            operationsPanel.add(btnElimina);
            operationsPanel.add(btnModifica);
    }

    public void componentsSizing(){
        nomeProdottoField.setPreferredSize(new Dimension(200,20));
        currentScrollPane.setPreferredSize(new Dimension(1000,500));
    }

    public void listenerSettings(){
        btnCerca.addActionListener(modifyProductListener);
        btnSfoglia.addActionListener(modifyProductListener);
        btnElimina.addActionListener(modifyProductListener);
        btnModifica.addActionListener(modifyProductListener);
        btnSalva.addActionListener(modifyProductListener);
        btnAnnulla.addActionListener(modifyProductListener);

        btnCerca.setActionCommand("cerca");
        btnSfoglia.setActionCommand("sfoglia");
        btnElimina.setActionCommand("elimina");
        btnModifica.setActionCommand("modifica");
        btnSalva.setActionCommand("salva");
        btnAnnulla.setActionCommand("annulla");
    }
//---------------------------------------------------------------------------------------------//
    public void cerca(){
        ArrayList<Prodotto> p = new ArrayList<>();
        searchedProduct = nomeProdottoField.getText();
        p.add(ProdottoDAO.getInstance().findByName(searchedProduct));
        setCurrentTable(p);
        tableChosen = 0;

    }

    public void cerca2(){
        ArrayList<Prodotto> p = new ArrayList<>();
        p.add(ProdottoDAO.getInstance().findByName(searchedProduct));
        setCurrentTable(p);
        tableChosen = 0;
    }

    public void sfoglia(){
    }

    public void elimina(){
        updateSelectedProdotto();
        Prodotto p = ProdottoDAO.getInstance().findByName(selectedProduct);
        ProdottoDAO.getInstance().delete(p);
        currentTableModel.getLista().remove(selectedRow);
        currentTableModel.fireTableDataChanged();
    }

    public void modifica(){
        /*updateSelectedProdotto();
        currentTableModel.setRowEditable(selectedRow);
        categoriaColumn = currentTable.getColumnModel().getColumn(3);
        sottocategoriaColumn = currentTable.getColumnModel().getColumn(4);
        produttoreColumn = currentTable.getColumnModel().getColumn(5);

        cList = CategoriaProdottoDAO.getInstance().findAll();
        comboList = (ArrayList<Nameable>) cList.clone();
        comboCategoria = new JComboBox<>(HelpFunctions.fromArraytoString(comboList));

        comboSottocategoria = new JComboBox<>();

        pList = FornitoreDAO.getInstance().findAll();
        comboList = (ArrayList<Nameable>) pList.clone();
        comboProduttori = new JComboBox<>(HelpFunctions.fromArraytoString(comboList));


        comboCategoria.addActionListener(modifyProductListener);
        comboCategoria.setActionCommand("categoria");

        produttoreColumn.setCellEditor(new DefaultCellEditor(comboProduttori));
        categoriaColumn.setCellEditor(new DefaultCellEditor(comboCategoria));
        selectedCategory = p;
        setComboBoxSottoCategorie();

        operationsPanel.remove(btnModifica);
        operationsPanel.add(btnSalva);
        operationsPanel.add(btnAnnulla);

        operationsPanel.invalidate();
        operationsPanel.validate();
        operationsPanel.repaint();*/
    }

    public void saveCompleted(){
        /*currentTableModel.setRowEditable(-1);
        operationsPanel.remove(btnSalva);
        operationsPanel.remove(btnAnnulla);
        operationsPanel.add(btnModifica);
        Prodotto pUpdated = new Prodotto();
        CategoriaProdotto categoriaProdotto = new CategoriaProdotto();
        categoriaProdotto.setNome(currentTableModel.getValueAt(indexModel, 3).toString());
        categoriaProdotto.getSottocategorie().add(new CategoriaProdotto());
        categoriaProdotto.getSottocategorie().get(0).setNome(currentTableModel.getValueAt(indexModel, 4).toString());
        Fornitore produttore = new Fornitore();
        produttore.setNome(currentTableModel.getValueAt(indexModel, 5).toString());
        pUpdated.setNome(currentTableModel.getValueAt(indexModel, 0).toString());
        pUpdated.setPrezzo(Float.parseFloat(currentTableModel.getValueAt(indexModel, 1).toString()));
        pUpdated.setDescrizione(currentTableModel.getValueAt(indexModel, 2).toString());
        pUpdated.setCategoria(categoriaProdotto);
        pUpdated.setProduttore(produttore);
        ProdottoDAO.getInstance().update(pUpdated);*/
    }

    public void annulla(){
        currentTableModel.setRowEditable(-1);
        operationsPanel.remove(btnSalva);
        operationsPanel.remove(btnAnnulla);
        operationsPanel.add(btnModifica);
        currentTableModel.fireTableDataChanged();
        System.out.println("Noooo");
        operationsPanel.invalidate();
        operationsPanel.validate();
        operationsPanel.repaint();
        if(tableChosen == 0){
            cerca2();
        }else if (tableChosen == 1){
            sfoglia();
        }
    }

    //Imposta le sottocategoria nel secondo menù a tendina in base alla categoria scelta
    public void setComboBoxSottoCategorie(){
        selectedCategory = comboCategoria.getSelectedItem().toString();
        if(selectedCategory == "--"){
            return;
        }
        comboSottocategoria.removeAllItems();
        comboList = (ArrayList<Nameable>)getArraySottoCategorie(selectedCategory).clone();
        HelpFunctions.setComboBox(comboSottocategoria, HelpFunctions.fromArraytoString(comboList));
        sottocategoriaColumn.setCellEditor(new DefaultCellEditor(comboSottocategoria));
    }

//-----------------------------------------------------------------------------------------------//
    public void setCurrentTable(ArrayList<Prodotto> p){

        remove(currentScrollPane);
        currentTableModel = new ProdottoTableModel(p);
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);
        currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        currentScrollPane.setPreferredSize(new Dimension(1000,500));

        add(currentScrollPane, BorderLayout.CENTER);

        invalidate();
        validate();
        repaint();
    }

    //Trova il nome della riga selezionata
    public void updateSelectedProdotto(){
        selectedRow = currentTable.getSelectedRow();
        indexModel = currentTable.convertRowIndexToModel(selectedRow);
        selectedProduct = currentTable.getValueAt(indexModel,0).toString();
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

}
