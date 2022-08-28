package View.Listener.Amministratore;

import View.AppFrame;
import View.Center.Amministratore.GestioneArticoliPanels.CreateProductPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateProductListener implements ActionListener {

    private AppFrame appFrame;
    private CreateProductPanel createProductPanel;


    public CreateProductListener(CreateProductPanel createProductPanel, AppFrame appFrame){
        this.createProductPanel = createProductPanel;
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("categoria")){
            createProductPanel.setComboBoxSottoCategorie();
        }else if(e.getActionCommand().equals("invia")){
            createProductPanel.invia();
        }
    }
}
