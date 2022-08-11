package View.Center.Amministratore.GestioneServiziPanels.Service;

import Business.HelpFunctions;
import DAO.Classi.*;
import Model.*;
import View.AppFrame;
import View.Listener.Amministratore.GestioneServiziListener.Service.ModifyServiceListener;
import View.Nameable;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class ModifyServicePanel extends JPanel {

    AppFrame appFrame;
    ModifyServiceListener modifyServiceListener;

    private JPanel cercaPanel = new JPanel();
        private JLabel inserisciNomeServizio = new JLabel("Inserisci il nome del servizio");
        private JTextField nomeServizioField = new JTextField();
        private JButton btnCerca = new JButton("Cerca");
    private JScrollPane currentScrollPane;
    private JTable currentTable;
    private ServizioTableModel currentTableModel;
        private TableColumn categoriaColumn;
        private JComboBox<String> comboCategoria;
    private JPanel operationsPanel = new JPanel();
        private JButton btnModifica = new JButton("Modifica");
        private JButton btnElimina = new JButton("Elimina");
        private JButton btnSfoglia = new JButton("Sfoglia");
        private JButton btnSalva = new JButton("Salva modifiche");
        private JButton btnAnnulla = new JButton("Annulla");

    private boolean isCercaAttive = false;
    private ArrayList<CategoriaServizio> cList;
    private ArrayList<Nameable> comboList;
    private int selectedRow;
    private ArrayList<Servizio> listaBackup;

    private ArrayList<Servizio> lista;


    public ModifyServicePanel(AppFrame appFrame){

        lista = ServizioDAO.getInstance().findAll();
        listaBackup = Servizio.cloneList(lista);

        this.appFrame = appFrame;
        modifyServiceListener = new ModifyServiceListener(this, this.appFrame);

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void cerca(){
        ArrayList<Servizio> s = new ArrayList<>();
        s.add(ServizioDAO.getInstance().findByName(nomeServizioField.getText()));
        currentTableModel.setLista(s);
        currentTableModel.fireTableDataChanged();
        isCercaAttive = true;
    }

    public void sfoglia(){
        ArrayList<Servizio> s = new ArrayList<>(ServizioDAO.getInstance().findAll());
        currentTableModel.setLista(s);
        currentTableModel.fireTableDataChanged();
        isCercaAttive = false;
    }

    public void elimina(){
        selectedRow = currentTable.convertRowIndexToModel(currentTable.getSelectedRow());
        Servizio servizio = currentTableModel.getLista().get(selectedRow);
        ServizioDAO.getInstance().delete(servizio);
        currentTableModel.getLista().remove(selectedRow);
        currentTableModel.fireTableDataChanged();
    }

    public void modifica(){
        selectedRow = currentTable.convertRowIndexToModel(currentTable.getSelectedRow());
        currentTableModel.setRowEditable(selectedRow);
        categoriaColumn = currentTable.getColumnModel().getColumn(3);

        cList = CategoriaServizioDAO.getInstance().findAll();
        comboList = (ArrayList<Nameable>) cList.clone();
        comboCategoria = new JComboBox<>(HelpFunctions.fromArraytoString(comboList));

        categoriaColumn.setCellEditor(new DefaultCellEditor(comboCategoria));

        operationsPanel.remove(btnModifica);
        operationsPanel.add(btnSalva);
        operationsPanel.add(btnAnnulla);

        btnElimina.setEnabled(false);
        btnCerca.setEnabled(false);
        btnSfoglia.setEnabled(false);
        nomeServizioField.setEditable(false);

        operationsPanel.invalidate();
        operationsPanel.validate();
        operationsPanel.repaint();
    }

    public void salva(){
        currentTableModel.setRowEditable(-1);
        operationsPanel.remove(btnSalva);
        operationsPanel.remove(btnAnnulla);
        operationsPanel.add(btnModifica);

        btnElimina.setEnabled(true);
        btnCerca.setEnabled(true);
        btnSfoglia.setEnabled(true);
        nomeServizioField.setEditable(true);

        Servizio sUpdated = new Servizio();
        CategoriaServizio categoriaServizio = new CategoriaServizio();
        categoriaServizio.setNome(currentTableModel.getValueAt(selectedRow, 3).toString());
        sUpdated.setNome(currentTableModel.getValueAt(selectedRow, 0).toString());
        sUpdated.setPrezzo(Float.parseFloat(currentTableModel.getValueAt(selectedRow, 1).toString()));
        sUpdated.setDescrizione(currentTableModel.getValueAt(selectedRow, 2).toString());
        sUpdated.setCategoria(categoriaServizio);
        ServizioDAO.getInstance().update(sUpdated);
        listaBackup = Servizio.cloneList(currentTableModel.getLista());
    }

    public void annulla(){
        currentTableModel.setRowEditable(-1);
        operationsPanel.remove(btnSalva);
        operationsPanel.remove(btnAnnulla);
        operationsPanel.add(btnModifica);

        btnElimina.setEnabled(true);
        btnCerca.setEnabled(true);
        btnSfoglia.setEnabled(true);
        nomeServizioField.setEditable(true);

        currentTableModel.setLista(listaBackup);
        currentTableModel.fireTableDataChanged();
        operationsPanel.invalidate();
        operationsPanel.validate();
        operationsPanel.repaint();
    }


    public void tableSetting(){
        currentTableModel = new ServizioTableModel(lista);
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
            cercaPanel.add(inserisciNomeServizio);
            cercaPanel.add(nomeServizioField);
            cercaPanel.add(btnCerca);
        add(currentScrollPane, BorderLayout.CENTER);
        add(operationsPanel, BorderLayout.SOUTH);
            operationsPanel.add(btnSfoglia);
            operationsPanel.add(btnElimina);
            operationsPanel.add(btnModifica);
    }

    public void componentsSizing(){
        nomeServizioField.setPreferredSize(new Dimension(200,20));
        currentScrollPane.setPreferredSize(new Dimension(1000,500));
    }

    public void listenerSettings(){
        btnCerca.addActionListener(modifyServiceListener);
        btnSfoglia.addActionListener(modifyServiceListener);
        btnElimina.addActionListener(modifyServiceListener);
        btnModifica.addActionListener(modifyServiceListener);
        btnSalva.addActionListener(modifyServiceListener);
        btnAnnulla.addActionListener(modifyServiceListener);

        btnCerca.setActionCommand("cerca");
        btnSfoglia.setActionCommand("sfoglia");
        btnElimina.setActionCommand("elimina");
        btnModifica.setActionCommand("modifica");
        btnSalva.setActionCommand("salva");
        btnAnnulla.setActionCommand("annulla");
    }

}
