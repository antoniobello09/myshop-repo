package View.Center.Amministratore.GestioneArticoliPanels.Category;

import DAO.Classi.CategoriaProdottoDAO;
import Model.CategoriaProdotto;
import View.AppFrame;
import View.Listener.Amministratore.GestioneArticoliListeners.Category.ModifyProductSubCategoryListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ModifyProductSubCategoryPanel extends JPanel {

    private JPanel namePanel = new JPanel();
        private JLabel inserisciNome = new JLabel("Nome della categoria:");
        private JTextField nomeField = new JTextField();
    private CategoryTableModel currentTableModel;
    private JTable currentTable;
    private JScrollPane currentScrollPane;
    private JPanel operationPanel = new JPanel();
        private JButton btnElimina = new JButton("Elimina");
    private JPanel southPanel = new JPanel();
        private JPanel addPanel = new JPanel();
            private JLabel inserisciSottoCategoria = new JLabel("Inserisci una nuova sottocategoria:");
            private JTextField sottoCategoriaField = new JTextField();
            private JButton btnAggiungi = new JButton("Aggiungi");
        private JPanel savePanel = new JPanel();
            private JButton btnAnnulla = new JButton("Annulla");
            private JButton btnReset = new JButton("Reset");
            private JButton btnSalva = new JButton("Salva modifiche");


    private AppFrame appFrame;
    private CategoriaProdotto categoriaProdotto;
    private ModifyProductSubCategoryListener modifyProductSubCategoryListener;
    private ArrayList<CategoriaProdotto> lista;

    public ModifyProductSubCategoryPanel(AppFrame appFrame, CategoriaProdotto categoriaProdotto){
        this.appFrame = appFrame;
        this.categoriaProdotto = categoriaProdotto;

        lista = categoriaProdotto.cloneList();

        modifyProductSubCategoryListener = new ModifyProductSubCategoryListener(this, appFrame);

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

        nomeField.setText(categoriaProdotto.getNome());


    }

    public void elimina(){
        if(currentTable.getSelectedRow() == -1) return;
        currentTableModel.getLista().remove(currentTable.convertRowIndexToModel(currentTable.getSelectedRow()));
        currentTableModel.fireTableDataChanged();
    }

    public void aggiungi(){
        if(sottoCategoriaField.getText() == "") return;
        currentTableModel.getLista().add(new CategoriaProdotto(sottoCategoriaField.getText()));
        currentTableModel.fireTableDataChanged();
        sottoCategoriaField.setText("");
    }

    public void annulla(){
        appFrame.getCenter().setCurrentPanel(new ModifyProductCategoryPanel(appFrame));
    }

    public void reset(){
        nomeField.setText(categoriaProdotto.getNome());
        currentTableModel.setLista((ArrayList<CategoriaProdotto>) categoriaProdotto.getSottocategorie());
        currentTableModel.fireTableDataChanged();
    }

    public void salva(){
        CategoriaProdotto categoriaUpdated = new CategoriaProdotto();
        categoriaUpdated.setIdCategoria(categoriaProdotto.getIdCategoria());
        categoriaUpdated.setNome(nomeField.getText());
        categoriaUpdated.setSottocategorie(currentTableModel.getLista());
        CategoriaProdottoDAO.getInstance().update(categoriaUpdated);
        categoriaProdotto = categoriaUpdated;
    }


    public void tableSetting(){
        currentTableModel = new CategoryTableModel(lista);
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);
        currentTableModel.setCellEditable(true);
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
            namePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
            currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            operationPanel.setLayout(new GridLayout(10,1,10,10));
            southPanel.setLayout(new GridLayout(2,0,10,10));
                addPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
                savePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
    }

    public void componentsAdding(){
        add(namePanel,BorderLayout.NORTH);
            namePanel.add(inserisciNome);
            namePanel.add(nomeField);
        add(currentScrollPane, BorderLayout.CENTER);
        add(operationPanel, BorderLayout.EAST);
            operationPanel.add(btnElimina);
        add(southPanel, BorderLayout.SOUTH);
            southPanel.add(addPanel);
                addPanel.add(inserisciSottoCategoria);
                addPanel.add(sottoCategoriaField);
                addPanel.add(btnAggiungi);
            southPanel.add(savePanel);
                savePanel.add(btnAnnulla);
                savePanel.add(btnReset);
                savePanel.add(btnSalva);

    }

    public void componentsSizing(){
        nomeField.setPreferredSize(new Dimension(200,20));
        sottoCategoriaField.setPreferredSize(new Dimension(200,20));
    }

    public void listenerSettings(){
        btnElimina.addActionListener(modifyProductSubCategoryListener);
        btnAggiungi.addActionListener(modifyProductSubCategoryListener);
        btnAnnulla.addActionListener(modifyProductSubCategoryListener);
        btnReset.addActionListener(modifyProductSubCategoryListener);
        btnSalva.addActionListener(modifyProductSubCategoryListener);

        btnElimina.setActionCommand("elimina");
        btnAggiungi.setActionCommand("aggiungi");
        btnAnnulla.setActionCommand("annulla");
        btnReset.setActionCommand("reset");
        btnSalva.setActionCommand("salva");
    }

}
