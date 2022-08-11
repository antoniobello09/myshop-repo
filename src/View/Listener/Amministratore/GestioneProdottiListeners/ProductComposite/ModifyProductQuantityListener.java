package View.Listener.Amministratore.GestioneProdottiListeners.ProductComposite;

import View.AppFrame;
import View.Center.Amministratore.GestioneProdottiPanels.ProductComposite.ModifyProductCompositePanel;
import View.Center.Amministratore.GestioneProdottiPanels.ProductComposite.ModifyProductQuantityPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyProductQuantityListener implements ActionListener {

    ModifyProductQuantityPanel modifyProductQuantityPanel;
    AppFrame appFrame;

    public ModifyProductQuantityListener(ModifyProductQuantityPanel modifyProductQuantityPanel, AppFrame appFrame){
        this.modifyProductQuantityPanel = modifyProductQuantityPanel;
        this.appFrame = appFrame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("elimina")){
            modifyProductQuantityPanel.elimina();
        }else if(e.getActionCommand().equals("salva")){
            modifyProductQuantityPanel.saveCompleted();
        }else if(e.getActionCommand().equals("annulla")){
            appFrame.getCenter().setCurrentPanel(new ModifyProductCompositePanel(appFrame));
        }else if(e.getActionCommand().equals("reset")){
            modifyProductQuantityPanel.reset();
        }else if(e.getActionCommand().equals("categoria")){
            modifyProductQuantityPanel.setComboBoxSottoCategorie();
        }else if(e.getActionCommand().equals("aggiungi")){
            modifyProductQuantityPanel.aggiungi();
        }
    }

}
