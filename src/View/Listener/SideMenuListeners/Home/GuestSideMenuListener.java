package View.Listener.SideMenuListeners.Home;

import View.AppFrame;
import View.Panels.NullPanel;
import View.Panels.SideMenu.Ospite.SMSfoglia;
import View.Panels.WelcomePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestSideMenuListener implements ActionListener {

    AppFrame appFrame;

    public GuestSideMenuListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if("sfoglia".equals(cmd)) {
            appFrame.getSideMenu().setCurrentPanel(new SMSfoglia(appFrame));
            appFrame.getCenter().setCurrentPanel(new NullPanel());
        }

        appFrame.invalidate();
        appFrame.validate();
        appFrame.repaint();
    }

}