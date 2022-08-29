package View.Panels.Center.Amministratore.GestioneArticoliPanels;

import View.AppFrame;
import View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners.DeleteArticleListener;

import javax.swing.*;

public class DeleteArticlePanel extends JPanel {

    AppFrame appFrame;
    DeleteArticleListener deleteArticleListener;

    public DeleteArticlePanel(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

}
