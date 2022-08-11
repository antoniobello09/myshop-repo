package View.Center.Amministratore.GestionePuntiVenditaPanels.Manager;

import DAO.Classi.ManagerDAO;
import Model.Manager;
import View.AppFrame;
import View.Listener.Amministratore.GestionePuntiVendita.Manager.ModifyManagerListener;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class ModifyManagerPanel extends JPanel {

    private AppFrame appFrame;
    private ModifyManagerListener modifyManagerListener;

    private JPanel cercaPanel = new JPanel();
        private JLabel inserisciManager = new JLabel("Cerca un manager:");
        private JTextField managerField = new JTextField();
        private JButton btnCerca = new JButton("Cerca");
        private JButton btnSfoglia = new JButton("Sfoglia");
    private JPanel operazioniPanel = new JPanel();
        private JButton btnElimina = new JButton("Elimina");
        private JButton btnModifica = new JButton("Modifica");
        private JButton btnAnnulla = new JButton("Annulla");
        private JButton btnSalva = new JButton("Salva");
    private JTable currentTable;
    private TableColumn etaColumn;
    private ManagerTableModel currentTableModel;
    private JScrollPane currentScrollPane;

    private ArrayList<Manager> listaBackup;
    private ArrayList<Manager> lista;
    private int selectedRow;

    public ModifyManagerPanel(AppFrame appFrame){
        this.appFrame = appFrame;
        this.modifyManagerListener = new ModifyManagerListener(this, this.appFrame);

        lista = ManagerDAO.getInstance().findAll();
        listaBackup = Manager.cloneList(lista);

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();
    }

    public void cerca(){
        if(!isCercaOK()) return;
        ArrayList<Manager> mList = new ArrayList<>();
        mList.add(currentTableModel.findManager(managerField.getText()));
        currentTableModel.setLista(mList);
        currentTableModel.fireTableDataChanged();
    }

    public boolean isCercaOK(){
        String message = "";
        int quantitaErrori = 0;
        if(managerField.getText().equals("")){
            message += "Il campo cerca è vuoto!";
            quantitaErrori++;
        }else if(currentTableModel.findManager(managerField.getText()) == null){
            message += "Il manager cercato non esiste!";
            quantitaErrori++;
        }
        if(quantitaErrori == 0) return true;
        JOptionPane.showMessageDialog(appFrame,
                message,
                "Search Manager ERROR",
                JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public void sfoglia(){
        currentTableModel.setLista(ManagerDAO.getInstance().findAll());
        listaBackup = Manager.cloneList(currentTableModel.getLista());
        currentTableModel.fireTableDataChanged();
    }



    public void elimina(){
        if(!isEliminaOK()) return;
        ManagerDAO.getInstance().delete(currentTableModel.getLista().get(currentTable.convertRowIndexToModel(currentTable.getSelectedRow())));
        currentTableModel.getLista().remove(currentTable.convertRowIndexToModel(currentTable.getSelectedRow()));
        currentTableModel.fireTableDataChanged();
    }

    public boolean isEliminaOK(){
        String message = "";
        int quantitaErrori = 0;
        if(currentTable.getSelectedRow() == -1){
            message += "Prima seleziona la riga da eliminare!";
            quantitaErrori++;
        }
        if(quantitaErrori == 0) return true;
        JOptionPane.showMessageDialog(appFrame,
                message,
                "Delete Manager ERROR",
                JOptionPane.ERROR_MESSAGE);
        return false;
    }


    public void modifica(){
        if(!isModificaOK()) return;
        selectedRow = currentTable.convertRowIndexToModel(currentTable.getSelectedRow());
        currentTableModel.setEditableRow(selectedRow);
        operazioniPanel.remove(btnModifica);
        operazioniPanel.add(btnAnnulla);
        operazioniPanel.add(btnSalva);
        managerField.setEditable(false);
        btnSfoglia.setEnabled(false);
        btnCerca.setEnabled(false);
        btnElimina.setEnabled(false);
        listaBackup = Manager.cloneList(currentTableModel.getLista());
        operazioniPanel.revalidate();
        operazioniPanel.repaint();
    }

    public boolean isModificaOK(){
        String message = "";
        int quantitaErrori = 0;
        if(currentTable.getSelectedRow() == -1){
            message += "Prima seleziona il manager da modificare!";
            quantitaErrori++;
        }
        if(quantitaErrori == 0) return true;
        JOptionPane.showMessageDialog(appFrame,
                message,
                "Modify Manager ERROR",
                JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public void salva(){
        if(!isSalvaOK()) return;
        operazioniPanel.add(btnModifica);
        operazioniPanel.remove(btnAnnulla);
        operazioniPanel.remove(btnSalva);
        managerField.setEditable(true);
        btnSfoglia.setEnabled(true);
        btnCerca.setEnabled(true);
        btnElimina.setEnabled(true);
        ManagerDAO.getInstance().update(currentTableModel.getLista().get(selectedRow));
        currentTableModel.setEditableRow(-1);
        currentTableModel.fireTableDataChanged();
        operazioniPanel.revalidate();
        operazioniPanel.repaint();
    }

    public boolean isSalvaOK(){
        String message = "";
        int quantitaErrori = 0;
        if(currentTableModel.getLista().get(selectedRow).getUsername().equals("")){
            message += "Il campo username";
            quantitaErrori++;
        }
        if(currentTableModel.getLista().get(selectedRow).getPassword().equals("")){
            if(quantitaErrori == 0) message += "Il campo password";
            else message += ", il campo password";
            quantitaErrori++;
        }
        if(currentTableModel.getLista().get(selectedRow).getSurname().equals("")){
            if(quantitaErrori == 0) message += "Il campo cognome";
            else message += ", il campo cognome";
            quantitaErrori++;
        }
        if(currentTableModel.getLista().get(selectedRow).getName().equals("")){
            if(quantitaErrori == 0) message += "Il campo nome";
            else message += ", il campo nome";
            quantitaErrori++;
        }
        if(currentTableModel.getLista().get(selectedRow).getEmail().equals("")){
            if(quantitaErrori == 0) message += "Il campo email";
            else message += ", il campo email";
            quantitaErrori++;
        }
        if(currentTableModel.getLista().get(selectedRow).getBirthdate().equals("")){
            if(quantitaErrori == 0) message += "Il campo data di nascita";
            else message += ", il campo data di nascita";
            quantitaErrori++;
        }
        if(currentTableModel.getLista().get(selectedRow).getAddress().equals("")){
            if(quantitaErrori == 0) message += "Il campo indirizzo";
            else message += ", il campo indirizzo";
            quantitaErrori++;
        }

        if(quantitaErrori == 1) message += " è vuoto!";
        else if(quantitaErrori == 0) return true;
        else message += " sono vuoti!";
        JOptionPane.showMessageDialog(appFrame,
                message,
                "Modify Manager ERROR",
                JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public void annulla(){
        operazioniPanel.add(btnModifica);
        operazioniPanel.remove(btnAnnulla);
        operazioniPanel.remove(btnSalva);
        managerField.setEditable(true);
        btnSfoglia.setEnabled(true);
        btnCerca.setEnabled(true);
        btnElimina.setEnabled(true);
        currentTableModel.setEditableRow(-1);
        currentTableModel.setLista(listaBackup);
        currentTableModel.fireTableDataChanged();
        operazioniPanel.revalidate();
        operazioniPanel.repaint();
    }

    public void tableSetting(){
        currentTableModel = new ManagerTableModel(lista);
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);
        etaColumn = currentTable.getColumnModel().getColumn(5);
        etaColumn.setCellEditor(new AgeEditor(appFrame, currentTable, currentTableModel));
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
            cercaPanel.setLayout(new FlowLayout());
            currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            operazioniPanel.setLayout(new GridLayout(8, 1, 15, 15));
    }

    public void componentsAdding(){
        add(cercaPanel, BorderLayout.NORTH);
            cercaPanel.add(inserisciManager);
            cercaPanel.add(managerField);
            cercaPanel.add(btnCerca);
            cercaPanel.add(btnSfoglia);
        add(operazioniPanel, BorderLayout.EAST);
            operazioniPanel.add(btnElimina);
            operazioniPanel.add(btnModifica);
        add(currentScrollPane, BorderLayout.CENTER);
    }

    public void componentsSizing(){
        managerField.setPreferredSize(new Dimension(200, 20));
        currentScrollPane.setPreferredSize(new Dimension(800,500));
    }

    public void listenerSettings(){
        btnCerca.addActionListener(modifyManagerListener);
        btnSfoglia.addActionListener(modifyManagerListener);
        btnElimina.addActionListener(modifyManagerListener);
        btnModifica.addActionListener(modifyManagerListener);
        btnAnnulla.addActionListener(modifyManagerListener);
        btnSalva.addActionListener(modifyManagerListener);

        btnCerca.setActionCommand("cerca");
        btnSfoglia.setActionCommand("sfoglia");
        btnElimina.setActionCommand("elimina");
        btnModifica.setActionCommand("modifica");
        btnAnnulla.setActionCommand("annulla");
        btnSalva.setActionCommand("salva");


    }
}
