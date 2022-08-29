package View.Listener.CenterListeners.Manager;

import View.AppFrame;
import View.Panels.Center.Manager.GestioneClientiPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestioneClientiListener implements ActionListener {

    GestioneClientiPanel gestioneClientiPanel;
    AppFrame appFrame;

    public GestioneClientiListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
