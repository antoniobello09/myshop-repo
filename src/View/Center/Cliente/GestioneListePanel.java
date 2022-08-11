package View.Center.Cliente;

import Business.SessionManager;
import DAO.Classi.AcquistoDAO;
import DAO.Classi.ClienteDAO;
import DAO.Classi.ListaDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.*;
import View.AppFrame;
import View.Listener.Cliente.GestioneListeListener;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GestioneListePanel extends JPanel {

    private AppFrame appFrame;
    private GestioneListeListener gestioneListeListener;

    private JPanel cercaPanel = new JPanel();
        private JLabel inserisciCerca = new JLabel("Cerca lista: ");
        private JTextField cercaField = new JTextField();
        private JButton btnCerca = new JButton("Cerca");
    private JTable currentTable;
    private ListaTableModel currentTableModel;
    private JScrollPane currentScrollPane;
    private JPanel operazioniPanel = new JPanel();
        private JButton btnElimina = new JButton("Elimina");
        private JButton btnDettagli = new JButton("Dettagli");
        private JButton btnAcquista = new JButton("Acquista");
    private JPanel creaPanel = new JPanel();
        private JLabel creaLista = new JLabel("Crea nuova lista:");
        private JButton btnCrea = new JButton("Crea");


    private ArrayList<Lista> lista = new ArrayList<Lista>();
    private Cliente cliente;
    private Utente utente;

    public GestioneListePanel(AppFrame appFrame){
        this.appFrame = appFrame;
        gestioneListeListener = new GestioneListeListener(this, this.appFrame);

        utente = (Utente)SessionManager.getInstance().getSession().get("loggedUser");


        lista = ListaDAO.getInstance().findAll(utente.getIdUtente());

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void cerca(){
        ArrayList<Lista> newList = new ArrayList<>();
        newList.add(findByName(cercaField.getText()));
        currentTableModel.setLista(newList);
        currentTableModel.fireTableDataChanged();
    }

    public void crea(){
        appFrame.getCenter().setCurrentPanel(new GestioneArticoliListaPanel(appFrame));
    }

    public void elimina(){
        int selectedIndex = currentTable.convertRowIndexToModel(currentTable.getSelectedRow());
        ListaDAO.getInstance().delete(currentTableModel.getLista().get(selectedIndex));
        currentTableModel.getLista().remove(currentTableModel.getLista().get(selectedIndex));
        currentTableModel.fireTableDataChanged();
    }

    public void dettagli(){
        appFrame.getCenter().setCurrentPanel(new DettagliListaPanel(appFrame, currentTableModel.getLista().get(currentTable.convertRowIndexToModel(currentTable.getSelectedRow()))));
    }

    public void acquista(){
        if(currentTableModel.getLista().get(currentTable.convertRowIndexToModel(currentTable.getSelectedRow())).isAcquistato()){

        }else{
            Acquisto acquisto = new Acquisto();
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            acquisto.setData(formatter.format(date));
            AcquistoDAO.getInstance().add(acquisto, currentTableModel.getLista().get(currentTable.convertRowIndexToModel(currentTable.getSelectedRow())));
            currentTableModel.setLista(ListaDAO.getInstance().findAll());
            currentTableModel.fireTableDataChanged();
        }
    }

    public void tableSetting(){
        currentTableModel = new ListaTableModel(lista);
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
            cercaPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
            currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            operazioniPanel.setLayout(new GridLayout(8,1,15,15));
            creaPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
    }


    public void componentsAdding(){
        add(cercaPanel, BorderLayout.NORTH);
            cercaPanel.add(inserisciCerca);
            cercaPanel.add(cercaField);
            cercaPanel.add(btnCerca);
        add(currentScrollPane, BorderLayout.CENTER);
        add(operazioniPanel, BorderLayout.EAST);
            operazioniPanel.add(btnElimina);
            operazioniPanel.add(btnDettagli);
            operazioniPanel.add(btnAcquista);
        add(creaPanel, BorderLayout.SOUTH);
            creaPanel.add(creaLista);
            creaPanel.add(btnCrea);
    }

    public void componentsSizing(){
        cercaField.setPreferredSize(new Dimension(200,20));
    }

    public void listenerSettings(){
        btnCerca.addActionListener(gestioneListeListener);
        btnCrea.addActionListener(gestioneListeListener);
        btnDettagli.addActionListener(gestioneListeListener);
        btnElimina.addActionListener(gestioneListeListener);
        btnAcquista.addActionListener(gestioneListeListener);

        btnCerca.setActionCommand("cerca");
        btnCrea.setActionCommand("crea");
        btnDettagli.setActionCommand("dettagli");
        btnElimina.setActionCommand("elimina");
        btnAcquista.setActionCommand("acquista");
    }

    public Lista findByName(String nomeLista){
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).getNome().equals(nomeLista)){
                return lista.get(i);
            }
        }
        return null;
    }

}
