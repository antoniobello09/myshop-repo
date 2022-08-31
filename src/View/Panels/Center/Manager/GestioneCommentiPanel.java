package View.Panels.Center.Manager;

import Business.SessionManager;
import DAO.Classi.FeedbackDAO;
import DAO.Classi.PuntoVenditaDAO;
import Model.FeedBack;
import Model.Manager;
import Model.PuntoVendita;
import Model.Utente;
import View.AppFrame;
import View.Listener.CenterListeners.Manager.GestioneCommentiListener;
import View.Panels.Center.Manager.Altro.FeedBackDialog;
import View.Panels.Center.Manager.Altro.FeedbackTableModel;
import View.Panels.Center.Ospite.ProdottiTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GestioneCommentiPanel extends JPanel {

    private AppFrame appFrame;
    private GestioneCommentiListener gestioneCommentiListener;

    private JLabel labelTitle = new JLabel("gestione Commenti");
    private JScrollPane currentScrollPane;
        private JTable currentTable;
        private FeedbackTableModel currentTableModel;
    private JPanel sidePanel = new JPanel();
        private JButton btnRispondi = new JButton("Rispondi");


    private ArrayList<FeedBack> lista;
    private int idPuntoVenditaManager;

    public GestioneCommentiPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        gestioneCommentiListener = new GestioneCommentiListener(this, appFrame);

        Utente u = (Utente)SessionManager.getInstance().getSession().get("loggedUser");
        idPuntoVenditaManager = PuntoVenditaDAO.getInstance().findByManager(u.getIdUtente()).getIdPuntoVendita();
        lista = FeedbackDAO.getInstance().findByPuntoVendita(idPuntoVenditaManager);

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void rispondi(){
        int selectedRow = currentTable.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(appFrame,
                    "Seleziona il commento a cui rispondere!",
                    "Answer Comment Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            selectedRow = currentTable.convertRowIndexToModel(selectedRow);
            FeedBack f = currentTableModel.getLista().get(selectedRow);
            FeedBackDialog.showDialog(appFrame, "Rispondi al commento", f);
            currentTableModel.setLista(FeedbackDAO.getInstance().findByPuntoVendita(idPuntoVenditaManager));
            currentTableModel.fireTableDataChanged();
        }
    }

    public void tableSetting(){
        currentTableModel = new FeedbackTableModel(lista);
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
        currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void componentsAdding(){
        add(labelTitle, BorderLayout.NORTH);
        add(currentScrollPane, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);
            sidePanel.add(btnRispondi);
    }

    public void componentsSizing(){
        currentScrollPane.setPreferredSize(new Dimension(1000,500));
    }

    public void listenerSettings(){
        btnRispondi.setActionCommand("rispondi");
        btnRispondi.addActionListener(gestioneCommentiListener);
    }

}
