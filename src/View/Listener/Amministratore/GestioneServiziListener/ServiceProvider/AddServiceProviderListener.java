package View.Listener.Amministratore.GestioneServiziListener.ServiceProvider;

import View.AppFrame;
import View.Center.Amministratore.GestioneServiziPanels.ServiceProvider.AddServiceProviderPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddServiceProviderListener implements ActionListener {

    AppFrame appFrame;
    AddServiceProviderPanel addServiceProviderPanel;

    public AddServiceProviderListener(AddServiceProviderPanel addServiceProviderPanel, AppFrame appFrame){
        this.appFrame = appFrame;
        this.addServiceProviderPanel = addServiceProviderPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("aggiungi")){
            addServiceProviderPanel.aggiungi();
        }
    }
}
