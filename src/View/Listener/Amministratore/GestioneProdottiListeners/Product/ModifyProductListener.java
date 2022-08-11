package View.Listener.Amministratore.GestioneProdottiListeners.Product;

import View.AppFrame;
import View.Center.Amministratore.GestioneProdottiPanels.Product.ModifyProductPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyProductListener implements ActionListener {
    ModifyProductPanel modifyProductPanel;
    AppFrame appFrame;

    public ModifyProductListener(ModifyProductPanel modifyProductPanel, AppFrame appFrame){
        this.modifyProductPanel = modifyProductPanel;
        this.appFrame = appFrame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("cerca")){
            modifyProductPanel.cerca();
        }else if(e.getActionCommand().equals("sfoglia")){
            modifyProductPanel.sfoglia();
        }else if(e.getActionCommand().equals("elimina")){
            modifyProductPanel.elimina();
        }else if(e.getActionCommand().equals("modifica")){
            modifyProductPanel.modifica();
        }else if(e.getActionCommand().equals("salva")){
            modifyProductPanel.saveCompleted();
        }else if(e.getActionCommand().equals("annulla")){
            modifyProductPanel.annulla();
        }else if(e.getActionCommand().equals("categoria")){
            modifyProductPanel.setComboBoxSottoCategorie();
        }
    }
}
