package View.Listener.Amministratore.GestioneServiziListener.Service;

import View.AppFrame;
import View.Center.Amministratore.GestioneServiziPanels.Service.ModifyServicePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyServiceListener implements ActionListener {

    AppFrame appFrame;
    ModifyServicePanel modifyServicePanel;

    public ModifyServiceListener(ModifyServicePanel modifyServicePanel, AppFrame appFrame){
        this.appFrame = appFrame;
        this.modifyServicePanel = modifyServicePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("cerca")){
            modifyServicePanel.cerca();
        }else if(e.getActionCommand().equals("sfoglia")){
            modifyServicePanel.sfoglia();
        }else if(e.getActionCommand().equals("elimina")){
            modifyServicePanel.elimina();
        }else if(e.getActionCommand().equals("modifica")){
            modifyServicePanel.modifica();
        }else if(e.getActionCommand().equals("salva")){
            modifyServicePanel.salva();
        }else if(e.getActionCommand().equals("annulla")){
            modifyServicePanel.annulla();
        }
    }
}
