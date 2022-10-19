package View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners;

import View.AppFrame;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.CreateCategoryPanel;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.CreateCompositeProductPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateCompositeProductListener implements ActionListener {

    AppFrame appFrame;
    CreateCompositeProductPanel createCompositeProductPanel;

    public CreateCompositeProductListener(CreateCompositeProductPanel createCompositeProductPanel, AppFrame appFrame) {
        this.appFrame = appFrame;
        this.createCompositeProductPanel = createCompositeProductPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("aggiungiProdotti")){
            createCompositeProductPanel.aggiungiProdotti();
        }else if(e.getActionCommand().equals("fileChooser")){
            createCompositeProductPanel.scegliImmagine();
        }
    }

}
