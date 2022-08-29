package View.Listener.SideMenuListeners.Home;

import View.*;
import View.Panels.NullPanel;
import View.Panels.SideMenu.Amministratore.SMGestioneArticoli;
import View.Panels.SideMenu.Amministratore.SMGestionePuntiVendita;
import View.Panels.SideMenu.Ospite.SMSfoglia;
import View.Panels.WelcomePanel;

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
            appFrame.getCenter().setCurrentPanel(new NullPanel());
        }
        else if("gestioneArticoli".equals(cmd))     {
            appFrame.getSideMenu().setCurrentPanel(new SMGestioneArticoli(appFrame));
            appFrame.getCenter().setCurrentPanel(new NullPanel());
        }
        else if("gestionePuntiVendita".equals(cmd)) {
            appFrame.getSideMenu().setCurrentPanel(new SMGestionePuntiVendita(appFrame));
            appFrame.getCenter().setCurrentPanel(new NullPanel());
        }

        appFrame.invalidate();
        appFrame.validate();
        appFrame.repaint();
    }
}
