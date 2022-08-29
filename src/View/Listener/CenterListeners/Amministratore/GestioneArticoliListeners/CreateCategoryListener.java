package View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners;

import View.AppFrame;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.CreateCategoryPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateCategoryListener implements ActionListener {

    AppFrame appFrame;
    CreateCategoryPanel createCategoryPanel;

    public CreateCategoryListener(CreateCategoryPanel createCategoryPanel, AppFrame appFrame) {
        this.appFrame = appFrame;
        this.createCategoryPanel = createCategoryPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
