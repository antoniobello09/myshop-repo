package View.Listener.SideMenuListeners.Ospite;

import View.AppFrame;
import View.Center.Ospite.BrowseProductPanel;
import View.Center.Ospite.BrowseServicePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SMSfogliaListener implements ActionListener {

    AppFrame appFrame;

    public SMSfogliaListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if ("indietro".equals(cmd)) {
            appFrame.getSideMenu().setPrecedentPanel();
        } else if ("sfogliaProdotti".equals(cmd)) {
            appFrame.getCenter().setCurrentPanel(new BrowseProductPanel(appFrame));
        } else if ("sfogliaServizi".equals(cmd)) {
            appFrame.getCenter().setCurrentPanel(new BrowseServicePanel(appFrame));
        }
    }
}