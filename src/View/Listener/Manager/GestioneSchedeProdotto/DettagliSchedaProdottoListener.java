package View.Listener.Manager.GestioneSchedeProdotto;

import View.AppFrame;
import View.Center.Manager.GestioneSchedeProdotto.DettagliSchedaProdottoPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DettagliSchedaProdottoListener implements ActionListener {

    AppFrame appFrame;
    DettagliSchedaProdottoPanel dettagliSchedaProdottoPanel;

    public DettagliSchedaProdottoListener(DettagliSchedaProdottoPanel dettagliSchedaProdottoPanel,AppFrame appFrame){
        this.appFrame = appFrame;
        this.dettagliSchedaProdottoPanel = dettagliSchedaProdottoPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("piano")){
            dettagliSchedaProdottoPanel.setCorsie();
        }else if(e.getActionCommand().equals("corsia")){
            dettagliSchedaProdottoPanel.setScaffali();
        }else if(e.getActionCommand().equals("salva")){
            dettagliSchedaProdottoPanel.salva();
        }
    }
}
