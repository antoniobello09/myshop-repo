package View.Listener.Cliente;

import View.AppFrame;
import View.Center.Cliente.GestioneArticoliListaPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestioneArticoliListaListener implements ActionListener {

    private AppFrame appFrame;
    private GestioneArticoliListaPanel gestioneArticoliListaPanel;

    public GestioneArticoliListaListener(GestioneArticoliListaPanel gestioneArticoliListaPanel, AppFrame appFrame){
        this.appFrame = appFrame;
        this.gestioneArticoliListaPanel = gestioneArticoliListaPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("cercaSchedaProdotto")){
            gestioneArticoliListaPanel.cercaSchedaProdotto();
        }else if(e.getActionCommand().equals("sfogliaSchedeProdotto")){
            gestioneArticoliListaPanel.sfogliaSchedeProdotto();
        }else if (e.getActionCommand().equals("cercaSchedaServizio")){
            gestioneArticoliListaPanel.cercaSchedaServizio();
        }else if(e.getActionCommand().equals("sfogliaSchedeServizio")){
            gestioneArticoliListaPanel.sfogliaSchedeServizio();
        } else if(e.getActionCommand().equals("aggiungiProdotto")){
            gestioneArticoliListaPanel.aggiungiProdotto();
        }else if(e.getActionCommand().equals("aggiungiServizio")){
            gestioneArticoliListaPanel.aggiungiServizio();
        }else if(e.getActionCommand().equals("eliminaProdotto")){
            gestioneArticoliListaPanel.eliminaProdotto();
        }else if(e.getActionCommand().equals("eliminaServizio")){
            gestioneArticoliListaPanel.eliminaServizio();
        }else if(e.getActionCommand().equals("salva")){
            gestioneArticoliListaPanel.salva();
        }else if(e.getActionCommand().equals("annulla")){
            gestioneArticoliListaPanel.annulla();
        }
    }
}
