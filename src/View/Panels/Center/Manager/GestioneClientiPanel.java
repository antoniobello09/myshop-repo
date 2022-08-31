package View.Panels.Center.Manager;

import View.AppFrame;
import View.Listener.CenterListeners.Manager.GestioneClientiListener;
import View.Panels.Center.Manager.Altro.ClientiTableModel;
import View.Panels.Center.Manager.Altro.FeedbackTableModel;

import javax.swing.*;

public class GestioneClientiPanel extends JPanel {

    private AppFrame appFrame;
    private GestioneClientiListener gestioneClientiListener;

    private JLabel gestioneClientiLabel = new JLabel("Gestione Clienti");
    private JScrollPane currentScrollPane;
        private JTable currentTable;
        private ClientiTableModel currentTableModel;
    private JPanel sidePanel = new JPanel();
        private JButton btnEmail = new JButton("Invia email");
        private JButton btnAbilitaDisabilita = new JButton("Abilita/Disabilita");
        private JButton btnCancella = new JButton("Cancella");

    public GestioneClientiPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    public void inviaEmail(){

    }

    public void abilita_disabilita(){

    }

    public void cancella(){

    }

}

