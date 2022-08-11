package View.Listener.Amministratore.GestionePuntiVendita.Shop;

import View.AppFrame;
import View.Center.Amministratore.GestionePuntiVenditaPanels.Shop.AssociaArticoliPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssociaArticoliListener implements ActionListener {

    AssociaArticoliPanel associaArticoliPanel;
    AppFrame appFrame;

    public AssociaArticoliListener(AssociaArticoliPanel associaArticoliPanel, AppFrame appFrame){
        this.appFrame = appFrame;
        this.associaArticoliPanel = associaArticoliPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("associaProdotto")){
            associaArticoliPanel.associaProdotto();
        }else if(e.getActionCommand().equals("associaServizio")){
            associaArticoliPanel.associaServizio();
        }else if(e.getActionCommand().equals("eliminaProdotto")){
            associaArticoliPanel.eliminaProdotto();
        }else if(e.getActionCommand().equals("eliminaServizio")){
            associaArticoliPanel.eliminaServizio();
        }else if(e.getActionCommand().equals("annulla")){
            associaArticoliPanel.annulla();
        }
    }
}
