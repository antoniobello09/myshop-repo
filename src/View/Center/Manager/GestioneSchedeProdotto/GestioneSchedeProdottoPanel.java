package View.Center.Manager.GestioneSchedeProdotto;

import Business.SessionManager;
import DAO.Classi.ManagerDAO;
import DAO.Classi.PuntoVenditaDAO;
import DAO.Classi.SchedaProdottoDAO;
import Model.*;
import View.AppFrame;
import View.Listener.Manager.GestioneSchedeProdotto.GestioneSchedeProdottoListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GestioneSchedeProdottoPanel extends JPanel {

    private AppFrame appFrame;
    private GestioneSchedeProdottoListener gestioneSchedeProdottoListener;

    private JPanel cercaPanel = new JPanel();
        private JLabel inserisciScheda = new JLabel("Cerca prodotto: ");
        private JTextField schedaField = new JTextField();
        private JButton btnCerca = new JButton("Cerca");
    private SchedaProdottoModel currentTableModel;
    private JTable currentTable;
    private JScrollPane currentScrollPane;
    private JPanel operazioniPanel = new JPanel();
        private JButton btnDettagli = new JButton("Dettagli");

    private ArrayList<SchedaProdotto> lista;
    private int selectedRow;
    private Magazzino magazzino;
    private PuntoVendita puntoVendita;

    public GestioneSchedeProdottoPanel(AppFrame appFrame){

        this.appFrame = appFrame;
        gestioneSchedeProdottoListener = new GestioneSchedeProdottoListener(this, this.appFrame);
        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");
        puntoVendita = PuntoVenditaDAO.getInstance().findByManager(ManagerDAO.getInstance().findByUsername(u.getUsername()).getIdUtente());

        magazzino = puntoVendita.getMagazzino();

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void cerca(){
        ArrayList<SchedaProdotto> s = new ArrayList<>();
        s.add(SchedaProdottoDAO.getInstance().findByName(schedaField.getText()));
        currentTableModel.setLista(s);
        currentTableModel.fireTableDataChanged();
    }

    public void dettagli(){
        selectedRow = currentTable.convertRowIndexToModel(currentTable.getSelectedRow());
        appFrame.getCenter().setCurrentPanel(new DettagliSchedaProdottoPanel(appFrame, magazzino, currentTableModel.getLista().get(selectedRow)));
    }

    public void tableSetting(){
        currentTableModel = new SchedaProdottoModel(magazzino);
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);
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
        btnCerca.addActionListener(gestioneSchedeProdottoListener);
        btnDettagli.addActionListener(gestioneSchedeProdottoListener);

        btnCerca.setActionCommand("cerca");
        btnDettagli.setActionCommand("dettagli");
    }


}
