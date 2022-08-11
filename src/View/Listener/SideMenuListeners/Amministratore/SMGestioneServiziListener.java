package View.Listener.SideMenuListeners.Amministratore;

import View.AppFrame;
import View.Center.Amministratore.GestioneServiziPanels.Category.ModifyServiceCategoryPanel;
import View.Center.Amministratore.GestioneServiziPanels.Service.AddServicePanel;
import View.Center.Amministratore.GestioneServiziPanels.Service.ModifyServicePanel;
import View.Center.Amministratore.GestioneServiziPanels.ServiceProvider.AddServiceProviderPanel;
import View.Center.Amministratore.GestioneServiziPanels.ServiceProvider.ModifyServiceProviderPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SMGestioneServiziListener implements ActionListener {

    AppFrame appFrame;

    public SMGestioneServiziListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if("indietro".equals(cmd)){
            appFrame.getSideMenu().setPrecedentPanel();
        }else if ("addService".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new AddServicePanel(appFrame));
        }else if ("modifyService".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new ModifyServicePanel(appFrame));
        }else if ("modifyCategoryS".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new ModifyServiceCategoryPanel(appFrame));
        }else if ("addServiceProvider".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new AddServiceProviderPanel(appFrame));
        }else if ("modifyServiceProvider".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new ModifyServiceProviderPanel(appFrame));
        }

    }
}
