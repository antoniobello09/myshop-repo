package View.Listener.SideMenuListeners.Amministratore;

import View.*;
import View.Center.Amministratore.GestioneArticoliPanels.Category.AddProductCategoryPanel;
import View.Center.Amministratore.GestioneArticoliPanels.Category.ModifyProductCategoryPanel;
import View.Center.Amministratore.GestioneArticoliPanels.Producer.AddProducerPanel;
import View.Center.Amministratore.GestioneArticoliPanels.CreateProductPanel;
import View.Center.Amministratore.GestioneArticoliPanels.Product.ModifyProductPanel;
import View.Center.Amministratore.GestioneArticoliPanels.ProductComposite.AddProductCompositePanel;
import View.Center.Amministratore.GestioneArticoliPanels.ProductComposite.ModifyProductCompositePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SMGestioneArticoliListener implements ActionListener {

    AppFrame appFrame;

    public SMGestioneArticoliListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if("indietro".equals(cmd)){
            appFrame.getSideMenu().setPrecedentPanel();
        }else if ("createProduct".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new CreateProductPanel(appFrame));
        }else if ("createCompositeProduct".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new ModifyProductPanel(appFrame));
        }else if ("createService".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new AddProductCompositePanel(appFrame));
        }else if ("deleteArticle".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new ModifyProductCompositePanel(appFrame));
        }else if ("modifyArticle".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new AddProductCategoryPanel(appFrame));
        }else if ("createSupplier".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new ModifyProductCategoryPanel(appFrame));
        }else if ("createCategory".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new AddProducerPanel(appFrame));
        }
    }
}
