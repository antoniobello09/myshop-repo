package View.Listener.SideMenuListeners.Home;

import View.AppFrame;
//import View.Center.Manager.GestioneClienti.GestioneClientiPanel;
import View.Center.Ospite.BrowseProductPanel;
import View.Center.Manager.GestioneSchedeProdotto.GestioneSchedeProdottoPanel;
import View.Center.Manager.GestioneSchedeServizio.GestioneSchedeServizioPanel;
import View.SideMenuPanels.Ospite.SMSfoglia;
import View.WelcomePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerSideMenuListener implements ActionListener {

    AppFrame appFrame;

    public ManagerSideMenuListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if("sfoglia".equals(cmd))                            {
            appFrame.getSideMenu().setCurrentPanel(new SMSfoglia(appFrame));
            appFrame.getCenter().setCurrentPanel(new WelcomePanel());
        }
       else if("gestioneClienti".equals(cmd))               appFrame.getCenter().setCurrentPanel(new GestioneClientiPanel(appFrame));
        else if("gestioneSchedeProdotto".equals(cmd))       appFrame.getCenter().setCurrentPanel(new GestioneSchedeProdottoPanel(appFrame));
        else if("feedbacks".equals(cmd))       appFrame.getCenter().setCurrentPanel(new GestioneSchedeServizioPanel(appFrame));

        appFrame.invalidate();
        appFrame.validate();
        appFrame.repaint();
    }

}
