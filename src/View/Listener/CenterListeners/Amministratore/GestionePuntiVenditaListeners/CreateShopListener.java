package View.Listener.CenterListeners.Amministratore.GestionePuntiVenditaListeners;

import View.AppFrame;
import View.Panels.Center.Amministratore.GestionePuntiVenditaPanels.CreateShopPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateShopListener implements ActionListener {

    CreateShopPanel createShopPanel;
    AppFrame appFrame;

    public CreateShopListener(CreateShopPanel createShopPanel, AppFrame appFrame) {
        this.createShopPanel = createShopPanel;
        this.appFrame = appFrame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("crea")){
            createShopPanel.crea();
        }
    }
}
