package View.Panels.Center.Amministratore.GestioneArticoliPanels;

import View.AppFrame;
import View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners.ModifyArticleListener;

import javax.swing.*;

public class ModifyArticlePanel extends JPanel {

    AppFrame appFrame;
    ModifyArticleListener modifyArticleListener;

    public ModifyArticlePanel(AppFrame appFrame) {
        this.appFrame = appFrame;
    }
}
