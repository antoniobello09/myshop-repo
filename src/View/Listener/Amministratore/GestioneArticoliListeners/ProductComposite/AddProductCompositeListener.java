package View.Listener.Amministratore.GestioneArticoliListeners.ProductComposite;

import View.AppFrame;
import View.Center.Amministratore.GestioneArticoliPanels.ProductComposite.AddProductCompositePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductCompositeListener implements ActionListener {

    AddProductCompositePanel addProductCompositePanel;
    AppFrame appFrame;

    public AddProductCompositeListener(AddProductCompositePanel addProductCompositePanel, AppFrame appFrame) {
        this.addProductCompositePanel = addProductCompositePanel;
        this.appFrame = appFrame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("categoria")) {
            addProductCompositePanel.setComboBoxSottoCategorie();
        }else if(e.getActionCommand().equals("aggiungi")){
            addProductCompositePanel.aggiungi();
        } else if(e.getActionCommand().equals("crea")){
            addProductCompositePanel.crea();
        }else if(e.getActionCommand().equals("elimina")){
            addProductCompositePanel.elimina();
        }

    }
}
