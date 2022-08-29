package View.Panels.Center.Manager;

import View.AppFrame;
import View.Listener.CenterListeners.Manager.GestioneCommentiListener;

import javax.swing.*;

public class GestioneCommentiPanel extends JPanel {

    AppFrame appFrame;
    GestioneCommentiListener gestioneCommentiListener;


    public GestioneCommentiPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
    }
}
