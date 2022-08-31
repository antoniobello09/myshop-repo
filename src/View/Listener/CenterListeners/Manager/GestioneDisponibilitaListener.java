package View.Listener.CenterListeners.Manager;

import View.AppFrame;
import View.Panels.Center.Manager.GestioneDisponibilitaPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestioneDisponibilitaListener implements ActionListener {

    private GestioneDisponibilitaPanel gestioneDisponibilitaPanel;
    private AppFrame appFrame;

    public GestioneDisponibilitaListener(GestioneDisponibilitaPanel gestioneDisponibilitaPanel, AppFrame appFrame) {
        this.appFrame = appFrame;
        this.gestioneDisponibilitaPanel = gestioneDisponibilitaPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("rifornisci")){
            gestioneDisponibilitaPanel.rifornisci();
        }
    }
}
