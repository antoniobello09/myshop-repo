package View.Listener.Manager.GestioneSchedeProdotto;

import View.AppFrame;
import View.Center.Manager.GestioneSchedeProdotto.GestioneSchedeProdottoPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestioneSchedeProdottoListener implements ActionListener {

    private AppFrame appFrame;
    private GestioneSchedeProdottoPanel gestioneSchedeProdottoPanel;

    public GestioneSchedeProdottoListener(GestioneSchedeProdottoPanel gestioneSchedeProdottoPanel, AppFrame appFrame){
        this.appFrame = appFrame;
        this.gestioneSchedeProdottoPanel = gestioneSchedeProdottoPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("cerca")){
            gestioneSchedeProdottoPanel.cerca();
        }else if(e.getActionCommand().equals("dettagli")){
            gestioneSchedeProdottoPanel.dettagli();
        }
    }
}
