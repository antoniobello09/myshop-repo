package View.Listener.Amministratore.GestionePuntiVendita.Shop;

import View.AppFrame;
import View.Center.Amministratore.GestionePuntiVenditaPanels.Shop.ModifyWareHousePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyWareHouseListener implements ActionListener {

    AppFrame appFrame;
    ModifyWareHousePanel modifyWareHousePanel;

    public ModifyWareHouseListener(ModifyWareHousePanel modifyWareHousePanel, AppFrame appFrame) {
        this.modifyWareHousePanel = modifyWareHousePanel;
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("annulla")) {
            modifyWareHousePanel.annulla();
        } else if (e.getActionCommand().equals("reset")) {
            modifyWareHousePanel.reset();
        } else if (e.getActionCommand().equals("salva")) {
            modifyWareHousePanel.salva();
        } else if (e.getActionCommand().equals("aggiungiPiano")) {
            modifyWareHousePanel.aggiungiPiano();
        } else if (e.getActionCommand().equals("aggiungiCorsia")) {
            modifyWareHousePanel.aggiungiCorsia();
        } else if (e.getActionCommand().equals("aggiungiScaffale")) {
            modifyWareHousePanel.aggiungiScaffale();
        } else if (e.getActionCommand().equals("togliPiano")) {
            modifyWareHousePanel.togliPiano();
        } else if (e.getActionCommand().equals("togliCorsia")) {
            modifyWareHousePanel.togliCorsia();
        } else if (e.getActionCommand().equals("togliScaffale")) {
            modifyWareHousePanel.togliScaffale();
        }
    }
}
