package View.Center.Amministratore.GestioneArticoliPanels.Category;

import DAO.Classi.CategoriaProdottoDAO;
import Model.CategoriaProdotto;
import View.AppFrame;
import View.Listener.Amministratore.GestioneArticoliListeners.Category.ModifyProductCategoryListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ModifyProductCategoryPanel extends JPanel {

    private ModifyProductCategoryListener modifyProductCategoryListener;

    private JPanel searchPanel = new JPanel();
        private JLabel inserisciCategoria = new JLabel("Cerca categoria: ");
        private JTextField categoriaField = new JTextField();
        private JButton btnCerca = new JButton("Cerca");
    private CategoryTableModel currentTableModel;
    private JTable currentTable;
    private JScrollPane currentScrollPane;
    private JPanel operationPanel = new JPanel();
        private JButton btnElimina = new JButton("Elimina");
        private JButton btnModifica = new JButton("Modifica");
        private JButton btnSfoglia = new JButton("Sfoglia");


    private ArrayList<CategoriaProdotto> lista;
    private AppFrame appFrame;

    public ModifyProductCategoryPanel(AppFrame appFrame){

        this.appFrame = appFrame;

        lista = CategoriaProdottoDAO.getInstance().findAll();

        modifyProductCategoryListener = new ModifyProductCategoryListener(this, appFrame);

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();
    }

    public void cerca(){
        ArrayList<CategoriaProdotto> categorie = new ArrayList<>();
        categorie.add(currentTableModel.findCategory(categoriaField.getText()));
        currentTableModel.setLista(categorie);
        currentTableModel.fireTableDataChanged();
    }

    public void elimina(){
        if(currentTable.getSelectedRow() == -1) return;
        CategoriaProdotto categoriaProdotto = currentTableModel.getLista().get(currentTable.convertRowIndexToModel(currentTable.getSelectedRow()));
        CategoriaProdottoDAO.getInstance().delete(categoriaProdotto);
        currentTableModel.getLista().remove(categoriaProdotto);
        currentTableModel.fireTableDataChanged();
    }

    public void modifica(){
        if(currentTable.getSelectedRow() == -1) return;
        appFrame.getCenter().setCurrentPanel(new ModifyProductSubCategoryPanel(appFrame, getSelectedCategoria()));
    }

    public void sfoglia(){
        currentTableModel.setLista(CategoriaProdottoDAO.getInstance().findAll());
        currentTableModel.fireTableDataChanged();
    }

    public CategoriaProdotto getSelectedCategoria(){
        return currentTableModel.getLista().get(currentTable.convertRowIndexToModel(currentTable.getSelectedRow()));
    }

    public void tableSetting(){
        currentTableModel = new CategoryTableModel(lista);
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
            searchPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
            currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            operationPanel.setLayout(new GridLayout(10,0,10,10));
    }

    public void componentsAdding(){
        add(searchPanel, BorderLayout.NORTH);
            searchPanel.add(inserisciCategoria);
            searchPanel.add(categoriaField);
            searchPanel.add(btnCerca);
        add(currentScrollPane, BorderLayout.CENTER);
        add(operationPanel, BorderLayout.EAST);
            operationPanel.add(btnElimina);
            operationPanel.add(btnModifica);
            operationPanel.add(btnSfoglia);
    }

    public void componentsSizing(){
        categoriaField.setPreferredSize(new Dimension(200,25));
    }

    public void listenerSettings(){
        btnCerca.addActionListener(modifyProductCategoryListener);
        btnElimina.addActionListener(modifyProductCategoryListener);
        btnModifica.addActionListener(modifyProductCategoryListener);
        btnSfoglia.addActionListener(modifyProductCategoryListener);

        btnCerca.setActionCommand("cerca");
        btnElimina.setActionCommand("elimina");
        btnModifica.setActionCommand("modifica");
        btnSfoglia.setActionCommand("sfoglia");
    }

}
