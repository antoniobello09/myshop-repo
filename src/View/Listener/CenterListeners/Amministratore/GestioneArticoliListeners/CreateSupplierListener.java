package View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners;

import View.AppFrame;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.CreateSupplierPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateSupplierListener implements ActionListener {


    CreateSupplierPanel createSupplierPanel;
    AppFrame appFrame;

    public CreateSupplierListener(CreateSupplierPanel createSupplierPanel, AppFrame appFrame){
        this.createSupplierPanel = createSupplierPanel;
        this.appFrame = appFrame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("aggiungi")){
            createSupplierPanel.aggiungi();
        }
    }
}
