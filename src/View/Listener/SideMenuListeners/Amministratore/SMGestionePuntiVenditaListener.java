package View.Listener.SideMenuListeners.Amministratore;

import View.AppFrame;
import View.Center.Amministratore.GestionePuntiVenditaPanels.Manager.AddManagerPanel;
import View.Center.Amministratore.GestionePuntiVenditaPanels.Manager.ModifyManagerPanel;
import View.Center.Amministratore.GestionePuntiVenditaPanels.Shop.ModifyShopPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SMGestionePuntiVenditaListener implements ActionListener {

    AppFrame appFrame;

    public SMGestionePuntiVenditaListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if("indietro".equals(cmd)){
            appFrame.getSideMenu().setPrecedentPanel();
        }else if("modifyPuntoVendita".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new ModifyShopPanel(appFrame));
        }else if("addManager".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new AddManagerPanel(appFrame));
        }else if("modifyManager".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new ModifyManagerPanel(appFrame));
        }

    }
}
