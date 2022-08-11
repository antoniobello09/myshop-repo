package View.Listener.SideMenuListeners;

import View.AppFrame;
//import View.Center.Manager.GestioneClienti.GestioneClientiPanel;
import View.Center.Ospite.BrowseProductPanel;
import View.Center.Manager.GestioneSchedeProdotto.GestioneSchedeProdottoPanel;
import View.Center.Manager.GestioneSchedeServizio.GestioneSchedeServizioPanel;

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
        if("browse".equals(cmd))                            appFrame.getCenter().setCurrentPanel(new BrowseProductPanel(appFrame));
       // else if("gestioneClienti".equals(cmd))          //appFrame.getCenter().setCurrentPanel(new GestioneClientiPanel(appFrame));
        else if("gestioneSchedeProdotto".equals(cmd))       appFrame.getCenter().setCurrentPanel(new GestioneSchedeProdottoPanel(appFrame));
        else if("gestioneSchedeServizio".equals(cmd))       appFrame.getCenter().setCurrentPanel(new GestioneSchedeServizioPanel(appFrame));

        appFrame.invalidate();
        appFrame.validate();
        appFrame.repaint();
    }

}
