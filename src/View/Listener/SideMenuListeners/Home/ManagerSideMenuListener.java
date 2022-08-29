package View.Listener.SideMenuListeners.Home;

import View.AppFrame;
//import View.Panels.Center.Manager.GestioneClienti.GestioneClientiPanel;
import View.Panels.Center.Manager.GestioneClientiPanel;
import View.Panels.Center.Manager.GestioneCommentiPanel;
import View.Panels.Center.Manager.GestioneDisponibilitaPanel;
import View.Panels.NullPanel;
import View.Panels.SideMenu.Ospite.SMSfoglia;
import View.Panels.WelcomePanel;

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
            appFrame.getCenter().setCurrentPanel(new NullPanel());
        }
       else if("gestioneClienti".equals(cmd))               appFrame.getCenter().setCurrentPanel(new GestioneClientiPanel(appFrame));
        else if("gestioneSchedeProdotto".equals(cmd))       appFrame.getCenter().setCurrentPanel(new GestioneDisponibilitaPanel(appFrame));
        else if("feedbacks".equals(cmd))       appFrame.getCenter().setCurrentPanel(new GestioneCommentiPanel(appFrame));

        appFrame.invalidate();
        appFrame.validate();
        appFrame.repaint();
    }

}
