package View.Listener.Amministratore.GestioneProdottiListeners.Category;

import View.AppFrame;
import View.Center.Amministratore.GestioneProdottiPanels.Category.ModifyProductCategoryPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyProductCategoryListener implements ActionListener {

    ModifyProductCategoryPanel modifyProductCategoryPanel;
    AppFrame appFrame;

    public ModifyProductCategoryListener(ModifyProductCategoryPanel modifyProductCategoryPanel, AppFrame appFrame){
        this.modifyProductCategoryPanel = modifyProductCategoryPanel;
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("elimina")){
            modifyProductCategoryPanel.elimina();
        }else if(e.getActionCommand().equals("modifica")){
            modifyProductCategoryPanel.modifica();
        }else if(e.getActionCommand().equals("cerca")){
            modifyProductCategoryPanel.cerca();
        }else if(e.getActionCommand().equals("sfoglia")){
            modifyProductCategoryPanel.sfoglia();
        }
    }
}
