package View.Listener.SideMenuListeners.Amministratore;

import View.*;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.*;
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
            appFrame.getCenter().setCurrentPanel(new CreateCompositeProductPanel(appFrame));
        }else if ("createService".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new CreateServicePanel(appFrame));
        }else if ("deleteArticle".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new DeleteArticlePanel(appFrame));
        }else if ("modifyArticle".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new ModifyArticlePanel(appFrame));
        }else if ("createSupplier".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new CreateSupplierPanel(appFrame));
        }else if ("createCategory".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new CreateCategoryPanel(appFrame));
        }
    }
}
