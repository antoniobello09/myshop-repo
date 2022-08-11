package View.Listener.Amministratore.GestioneProdottiListeners.Product;

import View.Center.Amministratore.GestioneProdottiPanels.Product.AddProductPanel;
import View.AppFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductListener implements ActionListener {

    AddProductPanel addProductPanel;
    AppFrame appFrame;

    public AddProductListener(AddProductPanel addProductPanel, AppFrame appFrame){
        this.addProductPanel = addProductPanel;
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("categoria")){
            addProductPanel.setComboBoxSottoCategorie();
        }else if(e.getActionCommand().equals("produttoreNuovo")){
            addProductPanel.setProduttoreNuovoPanel();
        }else if(e.getActionCommand().equals("produttoreEsiste")){
            addProductPanel.setProduttoreEsistePanel();
        }else if(e.getActionCommand().equals("inviaN")){
            addProductPanel.inviaN();
        }else if(e.getActionCommand().equals("inviaE")){
            addProductPanel.inviaE();
        }
    }
}
