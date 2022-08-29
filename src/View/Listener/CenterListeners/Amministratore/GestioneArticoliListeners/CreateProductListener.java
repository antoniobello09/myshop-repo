package View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners;

import View.AppFrame;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.CreateProductPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateProductListener implements ActionListener {

    CreateProductPanel createProductPanel;
    AppFrame appframe;

    public CreateProductListener(CreateProductPanel createProductPanel, AppFrame appframe) {
        this.createProductPanel = createProductPanel;
        this.appframe = appframe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("categoria")){
            createProductPanel.setComboBoxSottoCategorie();
        }else if(e.getActionCommand().equals("invia")){
            createProductPanel.invia();
        }
    }
}
