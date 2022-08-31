package View.Panels.Center.Manager;

import Business.SessionManager;
import DAO.Classi.ClienteDAO;
import DAO.Classi.FeedbackDAO;
import DAO.Classi.PuntoVenditaDAO;
import Model.Cliente;
import Model.FeedBack;
import Model.Utente;
import View.AppFrame;
import View.Listener.CenterListeners.Manager.GestioneClientiListener;
import View.Panels.Center.Manager.Altro.ClientiTableModel;
import View.Panels.Center.Manager.Altro.FeedBackDialog;
import View.Panels.Center.Manager.Altro.FeedbackTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GestioneClientiPanel extends JPanel {

    private AppFrame appFrame;
    private GestioneClientiListener gestioneClientiListener;

    private JLabel labelTitle = new JLabel("Gestione Clienti");
    private JScrollPane currentScrollPane;
        private JTable currentTable;
        private ClientiTableModel currentTableModel;
    private JPanel sidePanel = new JPanel();
        private JButton btnEmail = new JButton("Invia email");
        private JButton btnAbilitaDisabilita = new JButton("Abilita/Disabilita");
        private JButton btnCancella = new JButton("Cancella");

    private ArrayList<Cliente> lista;
    private int idPuntoVenditaManager;

    public GestioneClientiPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        gestioneClientiListener = new GestioneClientiListener(this, appFrame);

        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");
        idPuntoVenditaManager = PuntoVenditaDAO.getInstance().findByManager(u.getIdUtente()).getIdPuntoVendita();
        lista = ClienteDAO.getInstance().findByPuntoVendita(idPuntoVenditaManager);

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();
    }

    public void inviaEmail(){
        int selectedRow = currentTable.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(appFrame,
                    "Seleziona il cliente a cui vuoi mandare una email!",
                    "Client Selection Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            selectedRow = currentTable.convertRowIndexToModel(selectedRow);
            Cliente c = currentTableModel.getLista().get(selectedRow);

            System.out.println("Si dovrebbe inviare una mail all'indirizzo: " + c.getEmail());
            //...

        }
    }

    public void abilita_disabilita(){
        int selectedRow = currentTable.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(appFrame,
                    "Prima seleziona il cliente da abilitare/disabilitare!",
                    "Client Selection Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            selectedRow = currentTable.convertRowIndexToModel(selectedRow);
            Cliente c = currentTableModel.getLista().get(selectedRow);
            if(c.isAbilitato()){
                c.setAbilitato(false);
            }else{
                c.setAbilitato(true);
            }
            ClienteDAO.getInstance().update(c);
            currentTableModel.setLista(ClienteDAO.getInstance().findByPuntoVendita(idPuntoVenditaManager));
            currentTableModel.fireTableDataChanged();
        }
    }

    public void cancella(){
        int selectedRow = currentTable.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(appFrame,
                    "Prima seleziona il cliente da cancellare!",
                    "Client Selection Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            selectedRow = currentTable.convertRowIndexToModel(selectedRow);
            Cliente c = currentTableModel.getLista().get(selectedRow);
            ClienteDAO.getInstance().delete(c);
            currentTableModel.setLista(ClienteDAO.getInstance().findByPuntoVendita(idPuntoVenditaManager));
            currentTableModel.fireTableDataChanged();
        }
    }

    public void tableSetting(){
        currentTableModel = new ClientiTableModel(lista);
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
        sidePanel.setLayout(new GridLayout(8,1,10,10));
        currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void componentsAdding(){
        add(labelTitle, BorderLayout.NORTH);
        add(currentScrollPane, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);
            sidePanel.add(btnEmail);
            sidePanel.add(btnAbilitaDisabilita);
            sidePanel.add(btnCancella);
    }

    public void componentsSizing(){
        currentScrollPane.setPreferredSize(new Dimension(1000,500));
    }

    public void listenerSettings(){
        btnAbilitaDisabilita.setActionCommand("abilita_disabilita");
        btnCancella.setActionCommand("cancella");
        btnEmail.setActionCommand("invioEmail");

        btnEmail.addActionListener(gestioneClientiListener);
        btnCancella.addActionListener(gestioneClientiListener);
        btnAbilitaDisabilita.addActionListener(gestioneClientiListener);
    }

}

