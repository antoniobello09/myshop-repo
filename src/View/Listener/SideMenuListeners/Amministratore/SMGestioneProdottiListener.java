package View.Listener.SideMenuListeners.Amministratore;

import View.*;
import View.Center.Amministratore.GestioneProdottiPanels.Category.AddProductCategoryPanel;
import View.Center.Amministratore.GestioneProdottiPanels.Category.ModifyProductCategoryPanel;
import View.Center.Amministratore.GestioneProdottiPanels.Producer.AddProducerPanel;
import View.Center.Amministratore.GestioneProdottiPanels.Producer.ModifyProducerPanel;
import View.Center.Amministratore.GestioneProdottiPanels.Product.AddProductPanel;
import View.Center.Amministratore.GestioneProdottiPanels.Product.ModifyProductPanel;
import View.Center.Amministratore.GestioneProdottiPanels.ProductComposite.AddProductCompositePanel;
import View.Center.Amministratore.GestioneProdottiPanels.ProductComposite.ModifyProductCompositePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SMGestioneProdottiListener implements ActionListener {

    AppFrame appFrame;

    public SMGestioneProdottiListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if("indietro".equals(cmd)){
            appFrame.getSideMenu().setPrecedentPanel();
        }else if ("addProduct".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new AddProductPanel(appFrame));
        }else if ("modifyProduct".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new ModifyProductPanel(appFrame));
        }else if ("addProductC".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new AddProductCompositePanel(appFrame));
        }else if ("modifyProductC".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new ModifyProductCompositePanel(appFrame));
        }else if ("addCategoryP".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new AddProductCategoryPanel(appFrame));
        }else if ("modifyCategoryP".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new ModifyProductCategoryPanel(appFrame));
        }else if ("addProduttore".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new AddProducerPanel(appFrame));
        }else if ("modifyProduttore".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new ModifyProducerPanel(appFrame));
        }

    }
}
