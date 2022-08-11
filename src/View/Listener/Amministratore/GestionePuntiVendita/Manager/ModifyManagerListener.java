package View.Listener.Amministratore.GestionePuntiVendita.Manager;

import View.AppFrame;
import View.Center.Amministratore.GestionePuntiVenditaPanels.Manager.ModifyManagerPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyManagerListener implements ActionListener {

    ModifyManagerPanel modifyManagerPanel;
    AppFrame appFrame;

    public ModifyManagerListener(ModifyManagerPanel modifyManagerPanel, AppFrame appFrame){
        this.appFrame = appFrame;
        this.modifyManagerPanel = modifyManagerPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("cerca")) {
            modifyManagerPanel.cerca();
        }else if(e.getActionCommand().equals("sfoglia")){
            modifyManagerPanel.sfoglia();
        }else if(e.getActionCommand().equals("elimina")){
            modifyManagerPanel.elimina();
        }else if(e.getActionCommand().equals("modifica")){
            modifyManagerPanel.modifica();
        }else if(e.getActionCommand().equals("salva")){
            modifyManagerPanel.salva();
        }else if(e.getActionCommand().equals("annulla")){
            modifyManagerPanel.annulla();
        }
    }
}
