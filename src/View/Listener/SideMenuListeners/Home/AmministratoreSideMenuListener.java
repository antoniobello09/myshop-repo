package View.Listener.SideMenuListeners.Home;

import View.*;
import View.SideMenuPanels.Amministratore.SMGestioneArticoli;
import View.SideMenuPanels.Amministratore.SMGestionePuntiVendita;
import View.SideMenuPanels.Ospite.SMSfoglia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AmministratoreSideMenuListener implements ActionListener {

    AppFrame appFrame;

    public AmministratoreSideMenuListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if("sfoglia".equals(cmd))                   {
            appFrame.getSideMenu().setCurrentPanel(new SMSfoglia(appFrame));
            appFrame.getCenter().setCurrentPanel(new WelcomePanel());
        }
        else if("gestioneArticoli".equals(cmd))     {
            appFrame.getSideMenu().setCurrentPanel(new SMGestioneArticoli(appFrame));
            appFrame.getCenter().setCurrentPanel(new WelcomePanel());
        }
        else if("gestionePuntiVendita".equals(cmd)) {
            appFrame.getSideMenu().setCurrentPanel(new SMGestionePuntiVendita(appFrame));
            appFrame.getCenter().setCurrentPanel(new WelcomePanel());
        }

        appFrame.invalidate();
        appFrame.validate();
        appFrame.repaint();
    }
}
