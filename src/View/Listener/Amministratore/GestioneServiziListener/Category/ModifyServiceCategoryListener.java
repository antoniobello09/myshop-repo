package View.Listener.Amministratore.GestioneServiziListener.Category;

import View.AppFrame;
import View.Center.Amministratore.GestioneServiziPanels.Category.ModifyServiceCategoryPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyServiceCategoryListener implements ActionListener {

    AppFrame appFrame;
    ModifyServiceCategoryPanel modifyServiceCategoryPanel;

    public ModifyServiceCategoryListener(ModifyServiceCategoryPanel modifyServiceCategoryPanel, AppFrame appFrame){
        this.appFrame = appFrame;
        this.modifyServiceCategoryPanel = modifyServiceCategoryPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("cerca")){
            modifyServiceCategoryPanel.cerca();
        }else if(e.getActionCommand().equals("sfoglia")){
            modifyServiceCategoryPanel.sfoglia();
        } else if(e.getActionCommand().equals("elimina")){
            modifyServiceCategoryPanel.elimina();
        }else if(e.getActionCommand().equals("modifica")){
            modifyServiceCategoryPanel.modifica();
        }else if(e.getActionCommand().equals("salva")){
            modifyServiceCategoryPanel.salva();
        }else if(e.getActionCommand().equals("annulla")){
            modifyServiceCategoryPanel.annulla();
        }else if(e.getActionCommand().equals("aggiungi")){
            modifyServiceCategoryPanel.aggiungi();
        }
    }
}
