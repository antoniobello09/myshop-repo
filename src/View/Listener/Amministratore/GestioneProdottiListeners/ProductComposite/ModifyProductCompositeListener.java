package View.Listener.Amministratore.GestioneProdottiListeners.ProductComposite;

import View.AppFrame;
import View.Center.Amministratore.GestioneProdottiPanels.ProductComposite.ModifyProductCompositePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyProductCompositeListener implements ActionListener {

    ModifyProductCompositePanel modifyProductCompositePanel;
    AppFrame appFrame;

    public ModifyProductCompositeListener(ModifyProductCompositePanel modifyProductCompositePanel, AppFrame appFrame){
        this.modifyProductCompositePanel = modifyProductCompositePanel;
        this.appFrame = appFrame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("cerca")){
            modifyProductCompositePanel.cerca();
        }else if(e.getActionCommand().equals("sfoglia")){
            modifyProductCompositePanel.sfoglia();
        }else if(e.getActionCommand().equals("elimina")){
            modifyProductCompositePanel.elimina();
        }else if(e.getActionCommand().equals("modifica")){
            modifyProductCompositePanel.modifica();
        }
    }
}
