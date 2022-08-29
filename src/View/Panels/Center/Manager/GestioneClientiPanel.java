package View.Panels.Center.Manager;

import View.AppFrame;
import View.Listener.CenterListeners.Manager.GestioneClientiListener;

import javax.swing.*;

public class GestioneClientiPanel extends JPanel {

    AppFrame appFrame;
    GestioneClientiListener gestioneClientiListener;

    public GestioneClientiPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
    }
}
