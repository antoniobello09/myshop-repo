package View.Center.Amministratore.GestioneServiziPanels.Category;

import DAO.Classi.CategoriaServizioDAO;
import Model.CategoriaServizio;
import View.AppFrame;
import View.Listener.Amministratore.GestioneServiziListener.Category.ModifyServiceCategoryListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ModifyServiceCategoryPanel extends JPanel {

    AppFrame appFrame;
    ModifyServiceCategoryListener modifyServiceCategoryListener;

    private JPanel namePanel = new JPanel();
        private JLabel inserisciNome = new JLabel("Cerca una categoria:");
        private JTextField nomeField = new JTextField();
        private JButton btnCerca = new JButton("Cerca");
        private JButton btnSfoglia = new JButton("Sfoglia");
    private ServiceCategoryTableModel currentTableModel;
    private JTable currentTable;
    private JScrollPane currentScrollPane;
    private JPanel operationPanel = new JPanel();
        private JButton btnElimina = new JButton("Elimina");
        private JButton btnModifica = new JButton("Modifica");
        private JButton btnAnnulla = new JButton("Annulla");
        private JButton btnSalva = new JButton("Salva");
    private JPanel southPanel = new JPanel();
        private JPanel addPanel = new JPanel();
            private JLabel inserisciCategoria = new JLabel("Inserisci una nuova categoria:");
            private JTextField categoriaField = new JTextField();
            private JButton btnAggiungi = new JButton("Aggiungi");



    private ArrayList<CategoriaServizio> lista;
    private ArrayList<CategoriaServizio> listaBackup;
    private int selectedRow;
    private boolean isCercaAttive = false;

    public ModifyServiceCategoryPanel(AppFrame appFrame){

        this.appFrame = appFrame;

        lista = CategoriaServizioDAO.getInstance().findAll();

        listaBackup = CategoriaServizio.cloneList(lista);

        modifyServiceCategoryListener = new ModifyServiceCategoryListener(this, this.appFrame);

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();



    }

    public void cerca(){
        ArrayList<CategoriaServizio> categorie = new ArrayList<>();
        categorie.add(currentTableModel.findCategory(nomeField.getText()));
        currentTableModel.setLista(categorie);
        currentTableModel.fireTableDataChanged();
        isCercaAttive = true;
    }

    public void sfoglia(){
        lista = CategoriaServizioDAO.getInstance().findAll();
        listaBackup = CategoriaServizio.cloneList(lista);
        currentTableModel.setLista(lista);
        currentTableModel.fireTableDataChanged();
        nomeField.setText("");
        isCercaAttive = false;
    }

    public void elimina(){
        if(currentTable.getSelectedRow() == -1) return;
        CategoriaServizioDAO.getInstance().delete(currentTableModel.getLista().get(currentTable.convertRowIndexToModel(currentTable.getSelectedRow())));
        currentTableModel.getLista().remove(currentTable.convertRowIndexToModel(currentTable.getSelectedRow()));
        listaBackup = (ArrayList<CategoriaServizio>)currentTableModel.getLista().clone();
        currentTableModel.fireTableDataChanged();
    }

    public void aggiungi(){
        if(categoriaField.getText() == "") return;
        CategoriaServizio categoriaServizio = new CategoriaServizio(categoriaField.getText());
        CategoriaServizioDAO.getInstance().add(categoriaServizio);
        if(!isCercaAttive){
            currentTableModel.getLista().add(categoriaServizio);
            listaBackup = (ArrayList<CategoriaServizio>)currentTableModel.getLista().clone();
            currentTableModel.fireTableDataChanged();
        }

        categoriaField.setText("");
    }

    public void modifica(){
        selectedRow = currentTable.convertRowIndexToModel(currentTable.getSelectedRow());
        currentTableModel.setEditableRow(currentTable.getSelectedRow());
        operationPanel.remove(btnModifica);
        operationPanel.add(btnSalva);
        operationPanel.add(btnAnnulla);
        btnAggiungi.setEnabled(false);
        btnCerca.setEnabled(false);
        btnSfoglia.setEnabled(false);
        btnElimina.setEnabled(false);
        nomeField.setEditable(false);
        categoriaField.setEditable(false);
        revalidate(); repaint();
    }

    public void annulla(){
        currentTableModel.setLista((ArrayList<CategoriaServizio>) listaBackup.clone());
        operationPanel.add(btnModifica);
        operationPanel.remove(btnSalva);
        operationPanel.remove(btnAnnulla);
        btnAggiungi.setEnabled(true);
        btnCerca.setEnabled(true);
        btnSfoglia.setEnabled(true);
        btnElimina.setEnabled(true);
        nomeField.setEditable(true);
        categoriaField.setEditable(true);
        currentTableModel.setEditableRow(-1);
        currentTableModel.fireTableDataChanged();
        revalidate(); repaint();
    }

    public void salva(){
        if(currentTable.getCellEditor() != null) currentTable.getCellEditor().stopCellEditing();
        selectedRow = currentTable.convertRowIndexToModel(currentTable.getSelectedRow());
        CategoriaServizio categoriaUpdated = new CategoriaServizio();
        categoriaUpdated.setIdCategoria(currentTableModel.getLista().get(selectedRow).getIdCategoria());
        categoriaUpdated.setNome(currentTableModel.getLista().get(selectedRow).getNome());
        CategoriaServizioDAO.getInstance().update(categoriaUpdated);
        currentTableModel.setEditableRow(-1);
        currentTableModel.getLista().get(selectedRow).setNome(currentTableModel.getLista().get(selectedRow).getNome());
        operationPanel.add(btnModifica);
        operationPanel.remove(btnSalva);
        operationPanel.remove(btnAnnulla);
        btnAggiungi.setEnabled(true);
        btnCerca.setEnabled(true);
        btnSfoglia.setEnabled(true);
        btnElimina.setEnabled(true);
        nomeField.setEditable(true);
        categoriaField.setEditable(true);
        revalidate(); repaint();
    }


    public void tableSetting(){
        currentTableModel = new ServiceCategoryTableModel(lista);
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
            namePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
            currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            operationPanel.setLayout(new GridLayout(10,1,10,10));
            southPanel.setLayout(new GridLayout(2,0,10,10));
            addPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

    }

    public void componentsAdding(){
        add(namePanel,BorderLayout.NORTH);
        namePanel.add(inserisciNome);
        namePanel.add(nomeField);
        namePanel.add(btnCerca);
        namePanel.add(btnSfoglia);
        add(currentScrollPane, BorderLayout.CENTER);
        add(operationPanel, BorderLayout.EAST);
            operationPanel.add(btnElimina);
            operationPanel.add(btnModifica);
        add(southPanel, BorderLayout.SOUTH);
            southPanel.add(addPanel);
                addPanel.add(inserisciCategoria);
                addPanel.add(categoriaField);
                addPanel.add(btnAggiungi);
    }

    public void componentsSizing(){
        nomeField.setPreferredSize(new Dimension(200,20));
        categoriaField.setPreferredSize(new Dimension(200,20));
    }

    public void listenerSettings(){
        btnCerca.addActionListener(modifyServiceCategoryListener);
        btnSfoglia.addActionListener(modifyServiceCategoryListener);
        btnElimina.addActionListener(modifyServiceCategoryListener);
        btnAggiungi.addActionListener(modifyServiceCategoryListener);
        btnAnnulla.addActionListener(modifyServiceCategoryListener);
        btnModifica.addActionListener(modifyServiceCategoryListener);
        btnSalva.addActionListener(modifyServiceCategoryListener);


        btnCerca.setActionCommand("cerca");
        btnSfoglia.setActionCommand("sfoglia");
        btnElimina.setActionCommand("elimina");
        btnAggiungi.setActionCommand("aggiungi");
        btnAnnulla.setActionCommand("annulla");
        btnModifica.setActionCommand("modifica");
        btnSalva.setActionCommand("salva");
    }



}
