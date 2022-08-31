package View.Panels.Center.Manager;

import Business.SessionManager;
import DAO.Classi.ManagerDAO;
import DAO.Classi.PuntoVenditaDAO;
import DAO.Classi.SchedaProdottoDAO;
import Model.SchedaProdotto;
import Model.Utente;
import View.AppFrame;
import View.Listener.CenterListeners.Manager.GestioneDisponibilitaListener;
import View.Panels.Center.Manager.Altro.DisponibilitaDialog;
import View.Panels.Center.Manager.Altro.DisponibilitaTableModel;
import View.Panels.Center.Ospite.ProdottiTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GestioneDisponibilitaPanel extends JPanel {

    private AppFrame appFrame;
    private GestioneDisponibilitaListener gestioneDisponibilitaListener;

    private JLabel labelTitle = new JLabel("Diponibilita Articoli");
    private JScrollPane currentScrollPane;
        private JTable currentTable;
        private DisponibilitaTableModel currentTableModel;
    private JPanel sidePanel = new JPanel();
        private JButton btnRifornisci = new JButton("Rifornisci");

    private ArrayList<SchedaProdotto> disponibilitaList;
    private int idPuntoVenditaManager;

    public GestioneDisponibilitaPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        gestioneDisponibilitaListener = new GestioneDisponibilitaListener(this, appFrame);
        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");
        idPuntoVenditaManager = PuntoVenditaDAO.getInstance().findByManager(u.getIdUtente()).getIdPuntoVendita();
        disponibilitaList = SchedaProdottoDAO.getInstance().findAllByShop(idPuntoVenditaManager);

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void rifornisci(){
        int selectedRow = currentTable.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(appFrame,
                    "Per favore seleziona la riga dell'articolo da rifornire",
                    "Supply Product Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            selectedRow = currentTable.convertRowIndexToModel(selectedRow);
            SchedaProdotto sp = currentTableModel.getLista().get(selectedRow);
            DisponibilitaDialog.showDialog(appFrame, "Rifornisci Punto Vendita", sp);
            currentTableModel.setLista(SchedaProdottoDAO.getInstance().findAllByShop(idPuntoVenditaManager));
            currentTableModel.fireTableDataChanged();
        }
    }

    public void tableSetting(){
        currentTableModel = new DisponibilitaTableModel(disponibilitaList);
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
        sidePanel.setLayout(new FlowLayout());
        currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void componentsAdding(){
        add(labelTitle, BorderLayout.NORTH);
        add(currentScrollPane, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);
        sidePanel.add(btnRifornisci);
    }

    public void componentsSizing(){
        currentScrollPane.setPreferredSize(new Dimension(1000,500));
    }

    public void listenerSettings(){
        btnRifornisci.setActionCommand("rifornisci");
        btnRifornisci.addActionListener(gestioneDisponibilitaListener);
    }
}
