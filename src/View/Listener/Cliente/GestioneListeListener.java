package View.Listener.Cliente;

import View.AppFrame;
import View.Center.Cliente.GestioneListePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestioneListeListener implements ActionListener {

    private AppFrame appFrame;
    private GestioneListePanel gestioneListePanel;

    public GestioneListeListener(GestioneListePanel gestioneListePanel, AppFrame appFrame){
        this.gestioneListePanel = gestioneListePanel;
        this.appFrame = appFrame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("cerca")){
            gestioneListePanel.cerca();
        }else if(e.getActionCommand().equals("crea")){
            gestioneListePanel.crea();
        }else if(e.getActionCommand().equals("dettagli")){
            gestioneListePanel.dettagli();
        }else if(e.getActionCommand().equals("elimina")){
            gestioneListePanel.elimina();
        }else if(e.getActionCommand().equals("acquista")){
            gestioneListePanel.acquista();
        }

    }
}
