package View.Listener.Ospite;

import View.AppFrame;
import View.Center.Ospite.BrowseProductPanel;
import View.Center.Ospite.BrowseServicePanel;

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
