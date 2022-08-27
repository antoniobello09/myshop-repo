package View.Center.Amministratore.GestioneProdottiPanels.ProductComposite;


import DAO.Classi.ProdottoCompositoDAO;
import Model.CategoriaProdotto;
import Model.ProdottoComposito;
import Model.Fornitore;
import View.AppFrame;
import View.Listener.Amministratore.GestioneProdottiListeners.ProductComposite.ModifyProductCompositeListener;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class ModifyProductCompositePanel extends JPanel {

    private ModifyProductCompositeListener modifyProductCompositeListener;

    private JPanel cercaPanel = new JPanel();
        private JLabel inserisciNomeProdotto = new JLabel("Nome del prodotto composito");
        private JTextField nomeProdottoField = new JTextField();
        private JButton btnCerca = new JButton("Cerca");
    private JPanel operazioni = new JPanel();
        private JButton btnModifica = new JButton("Modifica");
        private JButton btnElimina = new JButton("Elimina");
        private JButton btnSfoglia = new JButton("Sfoglia");
        private JButton btnSalva = new JButton("Salva modifiche");
        private JButton btnAnnulla = new JButton("Annulla");

        private JComboBox<String> comboProduttori;
        private JComboBox<String> comboCategoria;
        private JComboBox<String> comboSottocategoria;
        private TableColumn produttoreColumn;
        private TableColumn categoriaColumn;
        private TableColumn sottocategoriaColumn;

    private ArrayList<CategoriaProdotto> cList;
    private ArrayList<Fornitore> pList;
    private int selectedRow;
    private int indexModel;
    private String selectedProductC;
    private String searchedProductC;
    private String selectedCategory;
    private int tableChosen = 1; //0 se la tabella mostra un solo prodotto, 1 se mostra tutti i prodotti

    private JScrollPane currentScrollPane;
    private JTable currentTable;
    private ProdottoCompositoTableModel currentTableModel;

    private AppFrame appFrame;
    private java.util.List<ProdottoComposito> lista;
    private ProdottoComposito selectedProdottoC;


    public ModifyProductCompositePanel(AppFrame appFrame){

        this.appFrame = appFrame;
        lista = ProdottoCompositoDAO.getInstance().findAll();

        modifyProductCompositeListener = new ModifyProductCompositeListener(this, appFrame);

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void cerca(){
        ArrayList<ProdottoComposito> p = new ArrayList<>();
        searchedProductC = nomeProdottoField.getText();
        p.add(ProdottoCompositoDAO.getInstance().findByName(searchedProductC));
        setCurrentTable(p);
        tableChosen = 0;
    }

    public void sfoglia(){
        ArrayList<ProdottoComposito> p = new ArrayList<>(ProdottoCompositoDAO.getInstance().findAll());
        setCurrentTable(p);
        tableChosen = 1;
    }

    public void elimina(){
        if(currentTable.getSelectedRow() == -1) return;
        updateSelectedProdotto();
        ProdottoComposito p = ProdottoCompositoDAO.getInstance().findByName(selectedProductC);
        ProdottoCompositoDAO.getInstance().delete(p);
        currentTableModel.getLista().remove(selectedRow);
        currentTableModel.fireTableDataChanged();
    }

    public void modifica(){
        if(currentTable.getSelectedRow() == -1) return;
        appFrame.getCenter().setCurrentPanel(new ModifyProductQuantityPanel(appFrame,getSelectedProduct()));

    }

    public void updateSelectedProdotto(){
        selectedRow = currentTable.getSelectedRow();
        indexModel = currentTable.convertRowIndexToModel(selectedRow);
        selectedProductC = currentTable.getValueAt(indexModel,0).toString();
    }


    public void setCurrentTable(ArrayList<ProdottoComposito> p){
        remove(currentScrollPane);
        currentTableModel = new ProdottoCompositoTableModel(p);
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

    public ProdottoComposito getSelectedProduct(){
        int index = currentTable.getSelectedRow();
        index = currentTable.convertColumnIndexToModel(index);
        return currentTableModel.getLista().get(index);

    }
//------------------------------------------------------------------------------------------//
    public void tableSetting(){
        currentTableModel = new ProdottoCompositoTableModel(lista);
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);
        currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
    }

    public void componentsAdding(){
        add(cercaPanel,BorderLayout.NORTH);
            cercaPanel.add(inserisciNomeProdotto);
            cercaPanel.add(nomeProdottoField);
            cercaPanel.add(btnCerca);
        add(currentScrollPane, BorderLayout.CENTER);
        add(operazioni, BorderLayout.SOUTH);
            operazioni.add(btnSfoglia);
            operazioni.add(btnElimina);
            operazioni.add(btnModifica);
    }

    public void componentsSizing(){
        nomeProdottoField.setPreferredSize(new Dimension(200,20));
        currentScrollPane.setPreferredSize(new Dimension(1000,500));
    }

    public void listenerSettings(){
        btnCerca.addActionListener(modifyProductCompositeListener);
        btnSfoglia.addActionListener(modifyProductCompositeListener);
        btnElimina.addActionListener(modifyProductCompositeListener);
        btnModifica.addActionListener(modifyProductCompositeListener);
        btnSalva.addActionListener(modifyProductCompositeListener);

        btnCerca.setActionCommand("cerca");
        btnSfoglia.setActionCommand("sfoglia");
        btnElimina.setActionCommand("elimina");
        btnModifica.setActionCommand("modifica");
        btnSalva.setActionCommand("salva");
    }

}
