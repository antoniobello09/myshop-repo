package View.Listener.CenterListeners.Manager;

import View.AppFrame;
import View.Panels.Center.Manager.GestioneClientiPanel;
import View.Panels.Center.Manager.GestioneCommentiPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestioneClientiListener implements ActionListener {

    private GestioneClientiPanel gestioneClientiPanel;
    private AppFrame appFrame;

    public GestioneClientiListener(GestioneClientiPanel gestioneClientiPanel, AppFrame appFrame) {
        this.appFrame = appFrame;
        this.gestioneClientiPanel = gestioneClientiPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("invioEmail")){
            gestioneClientiPanel.inviaEmail();
        }else if(e.getActionCommand().equals("abilita_disabilita")){
            gestioneClientiPanel.abilita_disabilita();
        }else if(e.getActionCommand().equals("cancella")){
            gestioneClientiPanel.cancella();
        }
    }
}
