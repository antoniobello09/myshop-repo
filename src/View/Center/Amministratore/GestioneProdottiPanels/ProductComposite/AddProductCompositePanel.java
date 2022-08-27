package View.Center.Amministratore.GestioneProdottiPanels.ProductComposite;

import Business.HelpFunctions;
import DAO.Classi.CategoriaProdottoDAO;
import DAO.Classi.ProdottoCompositoDAO;
import DAO.Classi.ProdottoDAO;
import Model.*;
import View.AppFrame;
import View.Listener.Amministratore.GestioneProdottiListeners.ProductComposite.AddProductCompositeListener;
import View.Nameable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class AddProductCompositePanel extends JPanel{

    private AddProductCompositeListener addProductCompositeListener;

    private JPanel containerPanel = new JPanel();
        private JPanel formProductPanel = new JPanel();
            private JLabel inserisciNome = new JLabel("Nome del prodotto composito");
            private JTextField nomeField = new JTextField();
            private JLabel inserisciDescrizione = new JLabel("Descrizione del prodotto");
            private JTextField descrizioneField = new JTextField();
            private JLabel inserisciPrezzo = new JLabel("Prezzo del prodotto                     €");
            private JTextField prezzoField = new JTextField();
            private JLabel inserisciCategoria = new JLabel("Categoria principale del prodotto");
            private JComboBox<String> categoriaField;
            private JLabel inserisciSottoCategoria = new JLabel("Sottocategoria del prodotto");
            private JComboBox<String> sottocategoriaField;
        private JPanel tablePanel = new JPanel();
            private JPanel addProductPanel = new JPanel();
                private JLabel inserisciProdotto = new JLabel("Aggiungi un prodotto: ");
                private JTextField prodottoField = new JTextField();
                private JLabel inserisciQuantita = new JLabel("Quantita: ");
                private JSpinner quantitaField = new JSpinner();
                private JButton btnAggiungi = new JButton("Aggiungi");
            private JScrollPane currentScrollPane;
                private JTable currentTable;
                private Prodotto_QuantitaTableModel currentTableModel;
            private JPanel operationPanel = new JPanel();
                private JButton btnCrea = new JButton("Crea");
                private JButton btnElimina = new JButton("Elimina");

    private Dimension dContainerPanel = new Dimension(1000,500);
    private Dimension dScrollPanePanel = new Dimension(1000,500);

    private ProdottoComposito outputProdottoComposito;
    private java.util.List<Prodotto_Quantita> lista;

    private String selectedItem;
    private ArrayList<CategoriaProdotto> cList;
    private ArrayList<Nameable> comboList;


    public AddProductCompositePanel(AppFrame appFrame){

        addProductCompositeListener = new AddProductCompositeListener(this, appFrame);

        cList = CategoriaProdottoDAO.getInstance().findAll();
        comboList = (ArrayList<Nameable>)cList.clone();
        categoriaField = new JComboBox<>(HelpFunctions.fromArraytoString(comboList));

        sottocategoriaField = new JComboBox<>();
        sottocategoriaField.setEnabled(false);

        lista = new ArrayList<>();


        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();




        SpinnerModel model = new SpinnerNumberModel(1, 1, 100, 1);
        quantitaField.setModel(model);

    }



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

    public void crea(){
        ProdottoComposito p = new ProdottoComposito();
        p.setNome(nomeField.getText());
        p.setDescrizione(descrizioneField.getText());
        p.setCategoria(CategoriaProdottoDAO.getInstance().findTopCategoria(sottocategoriaField.getSelectedItem().toString()));
        p.setPrezzo(Float.parseFloat(prezzoField.getText()));
        p.setProduttore(new Fornitore("Vario"));
        p.setSottoprodotti(currentTableModel.getLista());
        ProdottoCompositoDAO.getInstance().add(p);
    }

    public void elimina(){
        int selectedRow = currentTable.getSelectedRow();
        selectedRow = currentTable.convertRowIndexToModel(selectedRow);
        currentTableModel.remove(selectedRow);
        currentTableModel.fireTableDataChanged();
    }

    public void aggiungi(){
        Prodotto_Quantita p = new Prodotto_Quantita(ProdottoDAO.getInstance().findByName(prodottoField.getText()),Integer.parseInt(quantitaField.getValue().toString()));
        currentTableModel.add(p);
        currentTableModel.fireTableDataChanged();
    }

    public void layoutSetting(){
        setLayout(new FlowLayout());
        containerPanel.setLayout(new BorderLayout());

        formProductPanel.setLayout(new GridLayout(0,4,10,10));
        tablePanel.setLayout(new BorderLayout());
        addProductPanel.setLayout(new GridLayout(0,5,5,0));
        addProductPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        currentTableModel = new Prodotto_QuantitaTableModel(lista);
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);
        currentScrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        operationPanel.setLayout(new GridLayout(10,0,0,5));
    }

    public void componentsAdding(){
        add(containerPanel);
            containerPanel.add(formProductPanel, BorderLayout.NORTH);
                formProductPanel.add(inserisciNome);
                formProductPanel.add(nomeField);
                formProductPanel.add(inserisciDescrizione);
                formProductPanel.add(descrizioneField);
                formProductPanel.add(inserisciCategoria);
                formProductPanel.add(categoriaField);
                formProductPanel.add(inserisciSottoCategoria);
                formProductPanel.add(sottocategoriaField);
                formProductPanel.add(inserisciPrezzo);
                formProductPanel.add(prezzoField);
            containerPanel.add(tablePanel, BorderLayout.CENTER);
                tablePanel.add(addProductPanel,BorderLayout.NORTH);
                    addProductPanel.add(inserisciProdotto);
                    addProductPanel.add(prodottoField);
                    addProductPanel.add(inserisciQuantita);
                    addProductPanel.add(quantitaField);
                    addProductPanel.add(btnAggiungi);
                tablePanel.add(currentScrollPane,BorderLayout.CENTER);
                    currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                tablePanel.add(operationPanel,BorderLayout.EAST);
                    operationPanel.add(btnCrea);
                    operationPanel.add(btnElimina);
    }

    public void componentsSizing(){
        containerPanel.setPreferredSize(dContainerPanel);
        currentScrollPane.setPreferredSize(dScrollPanePanel);
    }

    public void listenerSettings(){
        categoriaField.addActionListener(addProductCompositeListener);
        categoriaField.setActionCommand("categoria");
        btnAggiungi.addActionListener(addProductCompositeListener);
        btnAggiungi.setActionCommand("aggiungi");
        btnCrea.addActionListener(addProductCompositeListener);
        btnCrea.setActionCommand("crea");
        btnElimina.addActionListener(addProductCompositeListener);
        btnElimina.setActionCommand("elimina");
    }

}
