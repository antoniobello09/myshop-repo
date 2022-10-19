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
        if(e.getActionCommand().equals("crea")){
            listsPanel.crea();
        }else if(e.getActionCommand().equals("modifica")){
            listsPanel.modifica();
        }else if(e.getActionCommand().equals("acquista")){
            listsPanel.acquista();
        }else if(e.getActionCommand().equals("pdf")){
            listsPanel.inviaPDF();
        }
    }
}
