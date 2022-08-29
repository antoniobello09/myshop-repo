package View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners;

import View.AppFrame;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.DeleteArticlePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteArticleListener implements ActionListener {

    DeleteArticlePanel deleteArticlePanel;
    AppFrame appFrame;

    public DeleteArticleListener(DeleteArticlePanel deleteArticlePanel, AppFrame appFrame) {
        this.deleteArticlePanel = deleteArticlePanel;
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
