package View.Listener.Manager.GestioneSchedeServizio;

import View.AppFrame;
import View.Center.Manager.GestioneSchedeServizio.DettagliSchedaServizioPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DettagliSchedaServizioListener implements ActionListener {

    private AppFrame appFrame;
    private DettagliSchedaServizioPanel dettagliSchedaServizioPanel;

    public DettagliSchedaServizioListener(DettagliSchedaServizioPanel dettagliSchedaServizioPanel, AppFrame appFrame){
        this.appFrame = appFrame;
        this.dettagliSchedaServizioPanel = dettagliSchedaServizioPanel;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("salva")){
            dettagliSchedaServizioPanel.salva();
        }
    }
}
