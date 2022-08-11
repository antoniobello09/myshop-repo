package View.Center.Amministratore.GestioneProdottiPanels.Producer;

import DAO.Classi.ProduttoreDAO;
import Model.Produttore;
import View.AppFrame;
import View.Listener.Amministratore.GestioneProdottiListeners.Producer.ModifyProducerListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ModifyProducerPanel extends JPanel {

    AppFrame appFrame;
    ModifyProducerListener modifyProducerListener;

    private JPanel searchPanel = new JPanel();
        private JLabel inserisciProduttore = new JLabel("Inserisci il produttore da cercare:");
        private JTextField produttoreField = new JTextField();
        private JButton btnCerca = new JButton("Cerca");
    private ProducerTableModel currentTableModel;
    private JTable currentTable;
    private JScrollPane currentScrollPane;
    private JPanel operationPanel = new JPanel();
        private JButton btnElimina = new JButton("Elimina");
        private JButton btnModifica = new JButton("Modifica");
        private JButton btnSalva = new JButton("Salva");
        private JButton btnSfoglia = new JButton("Sfoglia");
        private JButton btnAnnulla = new JButton("Annulla");

    private ArrayList<Produttore> lista;
    private int selectedRow;
    private ArrayList<Produttore> listaBackup;

    public ModifyProducerPanel(AppFrame appFrame){

        this.appFrame = appFrame;
        modifyProducerListener = new ModifyProducerListener(this, this.appFrame);
        lista = ProduttoreDAO.getInstance().findAll();

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void cerca(){
        if(!isCercaOK()) return;
        ArrayList<Produttore> p = new ArrayList<>();
        p.add(currentTableModel.searchProduttore(produttoreField.getText()));
        currentTableModel.setLista(p);
        currentTableModel.fireTableDataChanged();
        produttoreField.setText("");
    }

    public void elimina(){
        if(!isEliminaOK()) return;
        Produttore p = currentTableModel.getLista().get(currentTable.convertRowIndexToModel(currentTable.getSelectedRow()));
        ProduttoreDAO.getInstance().delete(p);
        currentTableModel.getLista().remove(p);
        currentTableModel.fireTableDataChanged();
    }

    public void modifica(){
        if(!isModificaOK()) return;
        selectedRow = currentTable.convertRowIndexToModel(currentTable.getSelectedRow());
        currentTableModel.setEditableRow(currentTable.convertRowIndexToModel(currentTable.getSelectedRow()));
        operationPanel.remove(btnElimina);
        operationPanel.remove(btnSfoglia);
        operationPanel.remove(btnModifica);
        operationPanel.add(btnAnnulla);
        operationPanel.add(btnSalva);
        listaBackup = currentTableModel.cloneList();

        revalidate(); repaint();
    }

    public void salva() {
        if(currentTable.getCellEditor() != null) currentTable.getCellEditor().stopCellEditing();
        ProduttoreDAO.getInstance().update(currentTableModel.getLista().get(selectedRow));
        operationPanel.remove(btnAnnulla);
        operationPanel.remove(btnSalva);
        operationPanel.add(btnElimina);
        operationPanel.add(btnSfoglia);
        operationPanel.add(btnModifica);
        currentTableModel.setEditableRow(-1);
        revalidate(); repaint();
    }

    public void sfoglia(){
        ArrayList<Produttore> p = new ArrayList<>(ProduttoreDAO.getInstance().findAll());
        currentTableModel.setLista(p);
        currentTableModel.fireTableDataChanged();
    }

    public void annulla(){
        currentTableModel.setLista(listaBackup);
        operationPanel.remove(btnAnnulla);
        operationPanel.remove(btnSalva);
        operationPanel.add(btnElimina);
        operationPanel.add(btnSfoglia);
        operationPanel.add(btnModifica);
        currentTableModel.fireTableDataChanged();
        currentTableModel.setEditableRow(-1);
        revalidate(); repaint();
    }

    public boolean isCercaOK(){
        String message = "";
        int quantitaErrori = 0;
        if(produttoreField.getText().equals("")){
            message += "Il campo cerca è vuoto!";
            quantitaErrori++;
        }else if(currentTableModel.searchProduttore(produttoreField.getText()) == null){
            message += "Il produttore cercato non esiste!";
            quantitaErrori++;
        }
        if(quantitaErrori == 0) return true;
        JOptionPane.showMessageDialog(appFrame,
                message,
                "Search Producer ERROR",
                JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public boolean isEliminaOK(){
        String message = "";
        int quantitaErrori = 0;
        if(currentTable.getSelectedRow() == -1){
            message += "Devi selezionare una riga!";
            quantitaErrori++;
        }
        if(quantitaErrori == 0) return true;
        JOptionPane.showMessageDialog(appFrame,
                message,
                "Delete Producer ERROR",
                JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public boolean isModificaOK(){
        String message = "";
        int quantitaErrori = 0;
        if(currentTable.getSelectedRow() == -1){
            message += "Devi selezionare una riga!";
            quantitaErrori++;
        }
        if(quantitaErrori == 0) return true;
        JOptionPane.showMessageDialog(appFrame,
                message,
                "Modify Producer ERROR",
                JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public void tableSetting(){
        currentTableModel = new ProducerTableModel(lista);
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
            searchPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
            currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            operationPanel.setLayout(new GridLayout(10,1,10,10));
    }

    public void componentsAdding(){
        add(searchPanel,BorderLayout.NORTH);
            searchPanel.add(inserisciProduttore);
            searchPanel.add(produttoreField);
            searchPanel.add(btnCerca);
        add(currentScrollPane, BorderLayout.CENTER);
        add(operationPanel, BorderLayout.EAST);
            operationPanel.add(btnElimina);
            operationPanel.add(btnModifica);
            operationPanel.add(btnSfoglia);
    }

    public void componentsSizing(){
        produttoreField.setPreferredSize(new Dimension(200,20));
    }

    public void listenerSettings(){
        btnCerca.addActionListener(modifyProducerListener);
        btnElimina.addActionListener(modifyProducerListener);
        btnModifica.addActionListener(modifyProducerListener);
        btnSalva.addActionListener(modifyProducerListener);
        btnSfoglia.addActionListener(modifyProducerListener);
        btnAnnulla.addActionListener(modifyProducerListener);

        btnCerca.setActionCommand("cerca");
        btnElimina.setActionCommand("elimina");
        btnModifica.setActionCommand("modifica");
        btnSalva.setActionCommand("salva");
        btnSfoglia.setActionCommand("sfoglia");
        btnAnnulla.setActionCommand("annulla");
    }

}
