package View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners;

import View.AppFrame;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.CreateServicePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateServiceListener implements ActionListener{

    CreateServicePanel createServicePanel;
    AppFrame appFrame;

    public CreateServiceListener(CreateServicePanel createServicePanel, AppFrame appFrame) {
        this.appFrame = appFrame;
        this.createServicePanel = createServicePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("invia")){
            createServicePanel.invia();
        }
    }
}
