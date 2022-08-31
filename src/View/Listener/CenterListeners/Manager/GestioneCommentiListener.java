package View.Listener.CenterListeners.Manager;

import View.AppFrame;
import View.Panels.Center.Manager.GestioneCommentiPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestioneCommentiListener implements ActionListener {

    private GestioneCommentiPanel gestioneCommentiPanel;
    private AppFrame appFrame;

    public GestioneCommentiListener(GestioneCommentiPanel gestioneCommentiPanel, AppFrame appFrame) {
        this.appFrame = appFrame;
        this.gestioneCommentiPanel = gestioneCommentiPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("rispondi")){
            gestioneCommentiPanel.rispondi();
        }
    }
}
