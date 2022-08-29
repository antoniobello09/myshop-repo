package View.Panels.Center.Manager;

import View.AppFrame;
import View.Listener.CenterListeners.Manager.GestioneDisponibilitaListener;

import javax.swing.*;

public class GestioneDisponibilitaPanel extends JPanel {

    AppFrame appFrame;
    GestioneDisponibilitaListener gestioneDisponibilitaListener;

    public GestioneDisponibilitaPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
    }
}
