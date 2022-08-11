package View.Listener.SideMenuListeners;

import View.*;
import View.SideMenuPanels.Amministratore.SMGestioneProdotti;
import View.SideMenuPanels.Amministratore.SMGestionePuntiVendita;
import View.SideMenuPanels.Amministratore.SMGestioneServizi;
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
        if("sfoglia".equals(cmd))                    appFrame.getSideMenu().setCurrentPanel(new SMSfoglia(appFrame));
        else if("gestioneProdotti".equals(cmd))     appFrame.getSideMenu().setCurrentPanel(new SMGestioneProdotti(appFrame));
        else if("gestioneServizi".equals(cmd))      appFrame.getSideMenu().setCurrentPanel(new SMGestioneServizi(appFrame));
        else if("gestionePuntiVendita".equals(cmd)) appFrame.getSideMenu().setCurrentPanel(new SMGestionePuntiVendita(appFrame));

        appFrame.invalidate();
        appFrame.validate();
        appFrame.repaint();
    }
}
