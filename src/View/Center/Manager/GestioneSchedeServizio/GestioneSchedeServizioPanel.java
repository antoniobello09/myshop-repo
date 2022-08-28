package View.Center.Manager.GestioneSchedeServizio;

import Business.SessionManager;
import DAO.Classi.ManagerDAO;
import DAO.Classi.PuntoVenditaDAO;
import Model.*;
import View.AppFrame;
import View.Center.Manager.GestioneSchedeProdotto.DettagliSchedaProdottoPanel;
import View.Center.Manager.GestioneSchedeProdotto.SchedaProdottoModel;
import View.Listener.Manager.GestioneSchedeProdotto.GestioneSchedeProdottoListener;
import View.Listener.Manager.GestioneSchedeServizio.GestioneSchedeServizioListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GestioneSchedeServizioPanel extends JPanel {

    AppFrame appFrame;
    GestioneSchedeServizioListener gestioneSchedeServizioListener;


    private JPanel cercaPanel = new JPanel();
    private JLabel inserisciScheda = new JLabel("Cerca servizio: ");
    private JTextField schedaField = new JTextField();
    private JButton btnCerca = new JButton("Cerca");
    private SchedaServizioModel currentTableModel;
    private JTable currentTable;
    private JScrollPane currentScrollPane;
    private JPanel operazioniPanel = new JPanel();
    private JButton btnDettagli = new JButton("Dettagli");

    private int selectedRow;
    private PuntoVendita puntoVendita;

    public GestioneSchedeServizioPanel(AppFrame appFrame){

        this.appFrame = appFrame;
        gestioneSchedeServizioListener = new GestioneSchedeServizioListener(this, this.appFrame);

        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");
        puntoVendita = PuntoVenditaDAO.getInstance().findByManager(ManagerDAO.getInstance().findByUsername(u.getUsername()).getIdUtente());


        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void cerca(){
        /*ArrayList<SchedaServizio> s = new ArrayList<>();
        s.add(SchedaServizioDAO.getInstance().findByName(schedaField.getText()));
        currentTableModel.setLista(s);
        currentTableModel.fireTableDataChanged();*/
    }

    public void dettagli(){
        selectedRow = currentTable.convertRowIndexToModel(currentTable.getSelectedRow());
        appFrame.getCenter().setCurrentPanel(new DettagliSchedaServizioPanel(appFrame, currentTableModel.getLista().get(selectedRow)));
    }

    public void tableSetting(){
        /*currentTableModel = new SchedaServizioModel(puntoVendita.getServizi());
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);*/
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
        cercaPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        operazioniPanel.setLayout(new GridLayout(8,1,15,15));
    }

    public  void componentsAdding(){
        add(cercaPanel, BorderLayout.NORTH);
            cercaPanel.add(inserisciScheda);
            cercaPanel.add(schedaField);
            cercaPanel.add(btnCerca);
        add(currentScrollPane, BorderLayout.CENTER);
        add(operazioniPanel, BorderLayout.EAST);
            operazioniPanel.add(btnDettagli);
    }

    public void componentsSizing(){
        schedaField.setPreferredSize(new Dimension(200, 20));
    }

    public void listenerSettings(){
        btnCerca.addActionListener(gestioneSchedeServizioListener);
        btnDettagli.addActionListener(gestioneSchedeServizioListener);

        btnCerca.setActionCommand("cerca");
        btnDettagli.setActionCommand("dettagli");
    }


}
