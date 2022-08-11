package View.Listener.Amministratore.GestioneServiziListener.Service;

import View.AppFrame;
import View.Center.Amministratore.GestioneServiziPanels.Service.AddServicePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddServiceListener implements ActionListener {

    AppFrame appFrame;
    AddServicePanel addServicePanel;

    public AddServiceListener(AddServicePanel addServicePanel, AppFrame appFrame){
        this.appFrame = appFrame;
        this.addServicePanel = addServicePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("invia")){
            addServicePanel.invia();
        }
    }
}
