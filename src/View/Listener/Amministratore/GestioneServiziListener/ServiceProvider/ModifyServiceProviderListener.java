package View.Listener.Amministratore.GestioneServiziListener.ServiceProvider;

import View.AppFrame;
import View.Center.Amministratore.GestioneServiziPanels.ServiceProvider.ModifyServiceProviderPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyServiceProviderListener implements ActionListener {

    AppFrame appFrame;
    ModifyServiceProviderPanel modifyServiceProviderPanel;

    public ModifyServiceProviderListener(ModifyServiceProviderPanel modifyServiceProviderPanel, AppFrame appFrame){
        this.appFrame = appFrame;
        this.modifyServiceProviderPanel = modifyServiceProviderPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("cerca")){
            modifyServiceProviderPanel.cerca();
        }else if(e.getActionCommand().equals("elimina")){
            modifyServiceProviderPanel.elimina();
        }else if(e.getActionCommand().equals("modifica")){
            modifyServiceProviderPanel.modifica();
        }else if(e.getActionCommand().equals("salva")){
            modifyServiceProviderPanel.salva();
        }else if(e.getActionCommand().equals("sfoglia")){
            modifyServiceProviderPanel.sfoglia();
        }else if(e.getActionCommand().equals("annulla")){
            modifyServiceProviderPanel.annulla();
        }
    }
}
