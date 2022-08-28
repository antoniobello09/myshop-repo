package View.Listener.Amministratore.GestioneArticoliListeners.Category;

import View.AppFrame;
import View.Center.Amministratore.GestioneArticoliPanels.Category.AddProductCategoryPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductCategoryListener implements ActionListener {

    AddProductCategoryPanel addProductCategoryPanel;
    AppFrame appFrame;

    public AddProductCategoryListener(AddProductCategoryPanel addProductCategoryPanel, AppFrame appFrame){
        this.addProductCategoryPanel = addProductCategoryPanel;
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("create")){
            addProductCategoryPanel.crea();
        }else if(e.getActionCommand().equals("add")){
            addProductCategoryPanel.aggiungiSottoCategoria();
        }else if(e.getActionCommand().equals("delete")){
            addProductCategoryPanel.cancellaSottoCategoria();
        }
    }
}
