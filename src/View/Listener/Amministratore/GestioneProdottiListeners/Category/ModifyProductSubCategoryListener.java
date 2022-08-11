package View.Listener.Amministratore.GestioneProdottiListeners.Category;

import View.AppFrame;
import View.Center.Amministratore.GestioneProdottiPanels.Category.ModifyProductSubCategoryPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyProductSubCategoryListener implements ActionListener {

    ModifyProductSubCategoryPanel modifyProductSubCategoryPanel;
    AppFrame appFrame;

    public ModifyProductSubCategoryListener(ModifyProductSubCategoryPanel modifyProductSubCategoryPanel, AppFrame appFrame){
        this.modifyProductSubCategoryPanel = modifyProductSubCategoryPanel;
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("elimina")){
            modifyProductSubCategoryPanel.elimina();
        }else if(e.getActionCommand().equals("aggiungi")){
            modifyProductSubCategoryPanel.aggiungi();
        }else if(e.getActionCommand().equals("annulla")){
            modifyProductSubCategoryPanel.annulla();
        }else if(e.getActionCommand().equals("reset")){
            modifyProductSubCategoryPanel.reset();
        }else if(e.getActionCommand().equals("salva")){
            modifyProductSubCategoryPanel.salva();
        }
    }
}
