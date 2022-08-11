package View.SideMenuPanels.Amministratore;

import View.AppFrame;
import View.Listener.SideMenuListeners.Amministratore.SMGestionePuntiVenditaListener;

import javax.swing.*;
import java.awt.*;

public class SMGestionePuntiVendita extends JPanel {

    JButton modificaPuntoVendita = new JButton("Gestisci punto vendita");
    JButton aggiungiManager = new JButton("Aggiungi un nuovo manager");
    JButton modificaManager = new JButton("Modifica manager");
    JButton indietro = new JButton("Indietro");

    SMGestionePuntiVenditaListener smGestionePuntiVenditaListener;

    public SMGestionePuntiVendita(AppFrame appFrame){
        setLayout(new GridLayout(10,1,0,15));
        modificaPuntoVendita.setPreferredSize(new Dimension(255,40));
        smGestionePuntiVenditaListener = new SMGestionePuntiVenditaListener(appFrame);

        modificaPuntoVendita.addActionListener(smGestionePuntiVenditaListener);
        aggiungiManager.addActionListener(smGestionePuntiVenditaListener);
        modificaManager.addActionListener(smGestionePuntiVenditaListener);
        indietro.addActionListener(smGestionePuntiVenditaListener);

        modificaPuntoVendita.setActionCommand("modifyPuntoVendita");
        aggiungiManager.setActionCommand("addManager");
        modificaManager.setActionCommand("modifyManager");
        indietro.setActionCommand("indietro");

        add(modificaPuntoVendita);
        add(aggiungiManager);
        add(modificaManager);
        add(indietro);

    }
}
