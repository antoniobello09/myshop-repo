package View.Listener.CenterListeners.Ospite;

import View.AppFrame;
import View.Panels.Center.Ospite.BrowseServicePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseServiceListener implements ActionListener {

    private AppFrame appFrame;
    private BrowseServicePanel browseServicePanel;

    public BrowseServiceListener(BrowseServicePanel browseServicePanel, AppFrame appFrame){
        this.appFrame = appFrame;
        this.browseServicePanel = browseServicePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
