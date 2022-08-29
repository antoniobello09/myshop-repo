package View.Listener.CenterListeners.Ospite;

import View.AppFrame;
import View.Panels.Center.Ospite.BrowseProductPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseProductListener implements ActionListener {
    private AppFrame appFrame;
    private BrowseProductPanel browseProductPanel;

    public BrowseProductListener(BrowseProductPanel browseProductPanel, AppFrame appFrame){
        this.appFrame = appFrame;
        this.browseProductPanel = browseProductPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
