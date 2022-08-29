package View.Listener.SideMenuListeners.Amministratore;

import View.AppFrame;
import View.Panels.Center.Amministratore.GestionePuntiVenditaPanels.BindShopArticlePanel;
import View.Panels.Center.Amministratore.GestionePuntiVenditaPanels.CreateShopPanel;

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
        }else if("createShop".equals(cmd)) {
            appFrame.getCenter().setCurrentPanel(new CreateShopPanel(appFrame));
        }else if("bindShopArticle".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new BindShopArticlePanel(appFrame));
        }

    }
}
