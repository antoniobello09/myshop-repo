package View.Panels.Center.Amministratore.GestioneArticoliPanels;

import View.AppFrame;
import View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners.CreateProductListener;

import javax.swing.*;

public class CreateCategoryPanel extends JPanel {

    AppFrame appFrame;
    CreateProductListener createProductListener;

    public CreateCategoryPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
    }
}
