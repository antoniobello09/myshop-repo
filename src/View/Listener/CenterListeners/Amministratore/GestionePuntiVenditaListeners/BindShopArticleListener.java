package View.Listener.CenterListeners.Amministratore.GestionePuntiVenditaListeners;

import View.AppFrame;
import View.Panels.Center.Amministratore.GestionePuntiVenditaPanels.BindShopArticlePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BindShopArticleListener implements ActionListener {

    BindShopArticlePanel bindShopArticlePanel;
    AppFrame appFrame;

    public BindShopArticleListener(BindShopArticlePanel bindShopArticlePanel, AppFrame appFrame) {
        this.bindShopArticlePanel = bindShopArticlePanel;
        this.appFrame = appFrame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("associa")){
            bindShopArticlePanel.associa();
        }
    }
}
