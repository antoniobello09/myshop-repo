package View.Listener.SideMenuListeners.Home;

import View.AppFrame;
import View.Center.Ospite.BrowseProductPanel;
import View.Center.Cliente.GestioneListePanel;
import View.SideMenuPanels.Ospite.SMSfoglia;
import View.WelcomePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteSideMenuListener implements ActionListener {

    AppFrame appFrame;

    public ClienteSideMenuListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if("sfoglia".equals(cmd))                            {
            appFrame.getSideMenu().setCurrentPanel(new SMSfoglia(appFrame));
            appFrame.getCenter().setCurrentPanel(new WelcomePanel());
        }
        else if("gestioneListe".equals(cmd))                 appFrame.getCenter().setCurrentPanel(new GestioneListePanel(appFrame));

        appFrame.invalidate();
        appFrame.validate();
        appFrame.repaint();
    }

}
