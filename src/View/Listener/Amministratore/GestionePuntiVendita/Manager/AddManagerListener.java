package View.Listener.Amministratore.GestionePuntiVendita.Manager;


import View.AppFrame;
import View.Center.Amministratore.GestionePuntiVenditaPanels.Manager.AddManagerPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddManagerListener implements ActionListener {

    private AppFrame appFrame;
    private AddManagerPanel addManagerPanel;

    public AddManagerListener(AddManagerPanel addManagerPanel, AppFrame appFrame){
        this.appFrame = appFrame;
        this.addManagerPanel = addManagerPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("aggiungi")){
            addManagerPanel.aggiungi();
        }else if(e.getActionCommand().equals("month")){
            addManagerPanel.setMonthSelected();
        }else if(e.getActionCommand().equals("year")){
            addManagerPanel.setYearSelected();
        }

    }
}
