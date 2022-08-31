package View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners;

import View.AppFrame;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.ModifyArticlePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyArticleListener implements ActionListener {

    ModifyArticlePanel modifyArticlePanel;
    AppFrame appFrame;

    public ModifyArticleListener(ModifyArticlePanel modifyArticlePanel, AppFrame appFrame) {
        this.modifyArticlePanel = modifyArticlePanel;
        this.appFrame = appFrame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("modifica")){
            modifyArticlePanel.modifica();
        }
    }
}
