package View.Center.Amministratore.GestioneProdottiPanels.Category;

import DAO.Classi.CategoriaProdottoDAO;
import Model.CategoriaProdotto;
import View.AppFrame;
import View.Listener.Amministratore.GestioneProdottiListeners.Category.AddProductCategoryListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddProductCategoryPanel extends JPanel {

    private AddProductCategoryListener addProductCategoryListener;
    private AppFrame appFrame;

    private JPanel addPanel = new JPanel();
        private JPanel categoryPanel = new JPanel();
            private JLabel inserisciCategoria = new JLabel("Inserisci il nome della nuova categoria:");
            private JTextField categoriaField = new JTextField();
        private JPanel subCategoryPanel = new JPanel();
            private JLabel inserisciSottoCategoria = new JLabel("Inserisci il nome di una sottocategoria:");
            private JTextField sottoCategoriaField = new JTextField();
            private JButton btnAggiungi = new JButton("Aggiungi");
    private CategoryTableModel currentTableModel;
    private JTable currentTable;
    private JScrollPane currentScrollPane;
    private JPanel operationPanel = new JPanel();
        private JButton btnElimina = new JButton("Elimina");
    private JPanel createPanel = new JPanel();
        private JButton btnCrea = new JButton("Crea");



    public AddProductCategoryPanel(AppFrame appFrame){

        this.appFrame = appFrame;

        addProductCategoryListener = new AddProductCategoryListener(this, this.appFrame);

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();
    }

    public void crea(){
        CategoriaProdotto categoriaProdotto = new CategoriaProdotto();
        categoriaProdotto.setNome(categoriaField.getText());
        categoriaProdotto.setSottocategorie(currentTableModel.getLista());
        CategoriaProdottoDAO.getInstance().add(categoriaProdotto);
    }

    public void aggiungiSottoCategoria(){
        if(currentTableModel.find(sottoCategoriaField.getText())) return;
        currentTableModel.add(new CategoriaProdotto(sottoCategoriaField.getText()));
        currentTableModel.fireTableDataChanged();
        sottoCategoriaField.setText("");
    }

    public void cancellaSottoCategoria(){
        currentTableModel.remove(currentTable.convertRowIndexToModel(currentTable.getSelectedRow()));
        currentTableModel.fireTableDataChanged();
    }

    public void tableSetting(){
       currentTableModel = new CategoryTableModel(new ArrayList<>());
       currentTable = new JTable(currentTableModel);
       currentScrollPane = new JScrollPane(currentTable);
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
            addPanel.setLayout(new GridLayout(2,0,10,10));
            currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            categoryPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
            subCategoryPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
    }

    public void componentsAdding(){
        add(addPanel, BorderLayout.NORTH);
            addPanel.add(categoryPanel);
                categoryPanel.add(inserisciCategoria);
                categoryPanel.add(categoriaField);
            addPanel.add(subCategoryPanel);
                subCategoryPanel.add(inserisciSottoCategoria);
                subCategoryPanel.add(sottoCategoriaField);
                subCategoryPanel.add(btnAggiungi);
        add(currentScrollPane, BorderLayout.CENTER);
        add(operationPanel, BorderLayout.EAST);
            operationPanel.add(btnElimina);
        add(createPanel, BorderLayout.SOUTH);
            createPanel.add(btnCrea);

    }

    public void componentsSizing(){
        categoriaField.setPreferredSize(new Dimension(200,25));
        sottoCategoriaField.setPreferredSize(new Dimension(200, 25));
    }

    public void listenerSettings(){
        btnCrea.addActionListener(addProductCategoryListener);
        btnAggiungi.addActionListener(addProductCategoryListener);
        btnElimina.addActionListener(addProductCategoryListener);
        btnCrea.setActionCommand("create");
        btnAggiungi.setActionCommand("add");
        btnElimina.setActionCommand("delete");
    }

}
