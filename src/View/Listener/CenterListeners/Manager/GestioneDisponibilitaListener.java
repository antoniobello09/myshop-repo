package View.Listener.CenterListeners.Manager;

import View.AppFrame;
import View.Panels.Center.Manager.GestioneDisponibilitaPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestioneDisponibilitaListener implements ActionListener {

    GestioneDisponibilitaPanel gestioneDisponibilitaPanel;
    AppFrame appFrame;

    public GestioneDisponibilitaListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
