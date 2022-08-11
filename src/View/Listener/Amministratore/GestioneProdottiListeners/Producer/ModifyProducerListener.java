package View.Listener.Amministratore.GestioneProdottiListeners.Producer;

import View.AppFrame;
import View.Center.Amministratore.GestioneProdottiPanels.Producer.ModifyProducerPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyProducerListener implements ActionListener {

    AppFrame appFrame;
    ModifyProducerPanel modifyProducerPanel;

    public ModifyProducerListener(ModifyProducerPanel modifyProducerPanel, AppFrame appFrame){
        this.modifyProducerPanel = modifyProducerPanel;
        this.appFrame = appFrame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("cerca")){
            modifyProducerPanel.cerca();
        }else if(e.getActionCommand().equals("elimina")){
            modifyProducerPanel.elimina();
        }else if(e.getActionCommand().equals("modifica")){
            modifyProducerPanel.modifica();
        }else if(e.getActionCommand().equals("salva")){
            modifyProducerPanel.salva();
        }else if(e.getActionCommand().equals("sfoglia")){
            modifyProducerPanel.sfoglia();
        }else if(e.getActionCommand().equals("annulla")){
            modifyProducerPanel.annulla();
        }
    }
}
