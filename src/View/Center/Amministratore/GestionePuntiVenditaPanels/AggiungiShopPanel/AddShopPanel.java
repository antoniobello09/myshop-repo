package View.Center.Amministratore.GestionePuntiVenditaPanels.AggiungiShopPanel;

import Business.HelpFunctions;
import DAO.Classi.ManagerDAO;
import DAO.Classi.PuntoVenditaDAO;
import Model.Manager;
import Model.PuntoVendita;
import View.AppFrame;
import View.Center.Amministratore.GestionePuntiVenditaPanels.AssociaArticoliPanel;
import View.Listener.Amministratore.GestionePuntiVendita.Shop.ModifyShopListener;
import View.Nameable;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class AddShopPanel extends JPanel {

    private AppFrame appFrame;
    private ModifyShopListener modifyShopListener;

    private JPanel addPanel = new JPanel();
        private JLabel inserisciCittaPuntoVendita = new JLabel("Citta del punto vendita:");
        private JTextField cittaField = new JTextField();
        private JLabel inserisciIndirizzoPuntoVendita = new JLabel("Indirizzo del punto vendita:");
        private JTextField indirizzoField = new JTextField();
        private JLabel inserisciManager = new JLabel("Associa un manager:");
        private JComboBox<String> managerField = new JComboBox<>();
        private JButton btnAggiungi = new JButton("Aggiungi");
    private PuntoVenditaTableModel currentTableModel;
    private JTable currentTable;
    private JScrollPane currentScrollPane;
    private TableColumn managerColumn;
    private JPanel operazioniPanel = new JPanel();
        private JButton btnElimina = new JButton("Elimina");
        private JButton btnModifica = new JButton("Modifica");
        private JButton btnSalva = new JButton("Salva");
        private JButton btnAnnulla = new JButton("Annulla");
        private JButton btnGestioneMagazzino = new JButton("Gestione magazzino");
        private JButton btnAssociaArticoli = new JButton("Associa articoli");
    private JPanel cercaPanel = new JPanel();
        private JLabel inserisciCerca = new JLabel("Cerca un punto vendita");
        private JTextField cercaField = new JTextField();
        private JButton btnCerca = new JButton("Cerca");
        private JButton btnSfoglia = new JButton("Sfoglia");

    private ArrayList<PuntoVendita> lista;
    private ArrayList<Manager> mList;
    private ArrayList<Manager> mFreeList;
    private ArrayList<Manager> mAvalaibleList;
    private JComboBox<Manager> managerTableField;

    private ArrayList<PuntoVendita> listaBackup;
    private ArrayList<Nameable> comboList;

    private ArrayList<Manager> mtList;
    private int selectedRow;

    public AddShopPanel(AppFrame appFrame){
        this.appFrame = appFrame;
        modifyShopListener = new ModifyShopListener(this, this.appFrame);

        mList = ManagerDAO.getInstance().findAll();
        lista = PuntoVenditaDAO.getInstance().findAll();
        mFreeList = new ArrayList<>();
        setManagerFree(mFreeList, mList, lista);
        comboList = (ArrayList<Nameable>) mFreeList.clone();
        HelpFunctions.setComboBox(managerField, HelpFunctions.fromArraytoString(comboList));

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void aggiungi(){
        PuntoVendita puntoVendita = new PuntoVendita();
        puntoVendita.setIndirizzo(indirizzoField.getText());
        puntoVendita.setCitta(cittaField.getText());
        PuntoVenditaDAO.getInstance().add(puntoVendita);
        currentTableModel.getLista().add(puntoVendita);
        currentTableModel.fireTableDataChanged();
    }




    public void associaArticoli(){
        selectedRow = currentTable.convertRowIndexToModel(currentTable.getSelectedRow());
        appFrame.getCenter().setCurrentPanel(new AssociaArticoliPanel(appFrame, currentTableModel.getLista().get(selectedRow)));
    }







    public void salva(){
        PuntoVendita p = currentTableModel.getLista().get(currentTableModel.getEditableRow());
        PuntoVenditaDAO.getInstance().update(p);
        currentTableModel.setEditableRow(-1);
        operazioniPanel.add(btnModifica);
        operazioniPanel.remove(btnSalva);
        operazioniPanel.remove(btnAnnulla);
        cercaField.setEditable(true);
        cittaField.setEditable(true);
        indirizzoField.setEditable(true);
        managerField.setEditable(true);
        btnAggiungi.setEnabled(true);
        btnCerca.setEnabled(true);
        btnGestioneMagazzino.setEnabled(true);
        btnElimina.setEnabled(true);
        btnSfoglia.setEnabled(true);

        boolean found = false;
        mFreeList = new ArrayList<>();
        for(int i=0;i<mList.size();i++){
            found = false;
            for(int j=0;j<currentTableModel.getLista().size() && !found;j++){
                if(mList.get(i).getIdUtente() == currentTableModel.getLista().get(j).getManager().getIdUtente()){
                    found = true;
                }
                if(!found)  mFreeList.add(mList.get(i));
            }
        }
        comboList = (ArrayList<Nameable>) mFreeList.clone();
        HelpFunctions.setComboBox(managerField, HelpFunctions.fromArraytoString(comboList));

        revalidate();
        repaint();
    }


    public void cerca(){
        ArrayList<PuntoVendita> lista = new ArrayList<>();
        lista.add(currentTableModel.findPuntoVendita(cercaField.getText()));
        currentTableModel.setLista(lista);
        listaBackup = (ArrayList<PuntoVendita>)lista.clone();
        currentTableModel.fireTableDataChanged();
    }

    public void sfoglia(){
        ArrayList<PuntoVendita> lista;
        lista = PuntoVenditaDAO.getInstance().findAll();
        currentTableModel.setLista(lista);
        listaBackup = (ArrayList<PuntoVendita>)lista.clone();
        currentTableModel.fireTableDataChanged();
    }

    public void tableSetting(){
        currentTableModel = new PuntoVenditaTableModel(lista);
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);
        managerColumn = currentTable.getColumnModel().getColumn(2);
    }

    public void  layoutSetting(){
        setLayout(new BorderLayout());
            addPanel.setLayout(new GridLayout(4,0,15,15));
            currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            operazioniPanel.setLayout(new GridLayout(8,1, 15, 15));
    }

    public void componentsAdding(){
        add(addPanel, BorderLayout.NORTH);
            addPanel.add(inserisciCittaPuntoVendita);
            addPanel.add(cittaField);
            addPanel.add(inserisciIndirizzoPuntoVendita);
            addPanel.add(indirizzoField);
            addPanel.add(inserisciManager);
            addPanel.add(managerField);
            addPanel.add(btnAggiungi);
        add(currentScrollPane, BorderLayout.CENTER);
        add(operazioniPanel, BorderLayout.EAST);
            operazioniPanel.add(btnElimina);
            operazioniPanel.add(btnGestioneMagazzino);
            operazioniPanel.add(btnAssociaArticoli);
            operazioniPanel.add(btnModifica);
        add(cercaPanel, BorderLayout.SOUTH);
            cercaPanel.add(inserisciCerca);
            cercaPanel.add(cercaField);
            cercaPanel.add(btnCerca);
            cercaPanel.add(btnSfoglia);
    }

    public void componentsSizing(){
        cercaField.setPreferredSize(new Dimension(200, 20));
        cittaField.setPreferredSize(new Dimension(200, 20));
        indirizzoField.setPreferredSize(new Dimension(200, 20));
    }

    public void listenerSettings(){
        btnAggiungi.setActionCommand("aggiungi");
        btnElimina.setActionCommand("elimina");
        btnGestioneMagazzino.setActionCommand("gestione");
        btnAssociaArticoli.setActionCommand("associaArticoli");
        btnModifica.setActionCommand("modifica");
        btnAnnulla.setActionCommand("annulla");
        btnSalva.setActionCommand("salva");
        btnCerca.setActionCommand("cerca");
        btnSfoglia.setActionCommand("sfoglia");

        btnAggiungi.addActionListener(modifyShopListener);
        btnElimina.addActionListener(modifyShopListener);
        btnGestioneMagazzino.addActionListener(modifyShopListener);
        btnAssociaArticoli.addActionListener(modifyShopListener);
        btnModifica.addActionListener(modifyShopListener);
        btnAnnulla.addActionListener(modifyShopListener);
        btnSalva.addActionListener(modifyShopListener);
        btnCerca.addActionListener(modifyShopListener);
        btnSfoglia.addActionListener(modifyShopListener);
    }
}
