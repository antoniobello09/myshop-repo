package View.Listener.Amministratore.GestioneArticoliListeners.Producer;

import View.AppFrame;
import View.Center.Amministratore.GestioneArticoliPanels.Producer.AddProducerPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProducerListener implements ActionListener {

    private AppFrame appFrame;
    private AddProducerPanel addProducerPanel;


    public AddProducerListener(AddProducerPanel addProducerPanel, AppFrame appFrame){
        this.addProducerPanel = addProducerPanel;
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("aggiungi")){
            addProducerPanel.aggiungi();
        }
    }
}
