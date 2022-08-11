package View.Listener.SideMenuListeners;

import View.AppFrame;
import View.SideMenuPanels.Ospite.SMSfoglia;

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
        if("sfoglia".equals(cmd)) appFrame.getSideMenu().setCurrentPanel(new SMSfoglia(appFrame));

        appFrame.invalidate();
        appFrame.validate();
        appFrame.repaint();
    }

}