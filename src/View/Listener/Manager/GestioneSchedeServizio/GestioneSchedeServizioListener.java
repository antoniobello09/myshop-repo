package View.Listener.Manager.GestioneSchedeServizio;

import View.AppFrame;
import View.Center.Manager.GestioneSchedeServizio.GestioneSchedeServizioPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestioneSchedeServizioListener implements ActionListener {

    AppFrame appFrame;
    GestioneSchedeServizioPanel gestioneSchedeServizioPanel;

    public GestioneSchedeServizioListener(GestioneSchedeServizioPanel gestioneSchedeServizioPanel, AppFrame appFrame){
        this.appFrame = appFrame;
        this.gestioneSchedeServizioPanel = gestioneSchedeServizioPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("cerca")){
            gestioneSchedeServizioPanel.cerca();
        }else if(e.getActionCommand().equals("dettagli")){
            gestioneSchedeServizioPanel.dettagli();
        }
    }
}
