package View.Listener.CenterListeners.Cliente;

import View.AppFrame;
import View.Panels.Center.Cliente.ListsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListsListener implements ActionListener {

    ListsPanel listsPanel;
    AppFrame appFrame;

    public ListsListener(ListsPanel listsPanel, AppFrame appFrame) {
        this.listsPanel = listsPanel;
        this.appFrame = appFrame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
