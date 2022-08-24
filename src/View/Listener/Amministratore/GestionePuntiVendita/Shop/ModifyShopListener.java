package View.Listener.Amministratore.GestionePuntiVendita.Shop;

import View.AppFrame;
import View.Center.Amministratore.GestionePuntiVenditaPanels.AggiungiShopPanel.AddShopPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyShopListener implements ActionListener {

    AppFrame appFrame;
    AddShopPanel modifyShopPanel;

    public ModifyShopListener(AddShopPanel modifyShopPanel, AppFrame appFrame){
        this.modifyShopPanel = modifyShopPanel;
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("aggiungi")){
            modifyShopPanel.aggiungi();
        }else if(e.getActionCommand().equals("elimina")){
            modifyShopPanel.elimina();
        }else if(e.getActionCommand().equals("gestione")){
            modifyShopPanel.gestioneMagazzino();
        }else if(e.getActionCommand().equals("associaArticoli")){
            modifyShopPanel.associaArticoli();
        }else if(e.getActionCommand().equals("modifica")){
            modifyShopPanel.modifica();
        }else if(e.getActionCommand().equals("annulla")){
            modifyShopPanel.annulla();
        }else if(e.getActionCommand().equals("salva")){
            modifyShopPanel.salva();
        }else if(e.getActionCommand().equals("cerca")){
            modifyShopPanel.cerca();
        }else if(e.getActionCommand().equals("sfoglia")){
            modifyShopPanel.sfoglia();
        }
    }
}
