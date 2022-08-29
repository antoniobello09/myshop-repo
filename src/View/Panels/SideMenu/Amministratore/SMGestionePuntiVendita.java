package View.Panels.SideMenu.Amministratore;

import View.AppFrame;
import View.Listener.SideMenuListeners.Amministratore.SMGestionePuntiVenditaListener;

import javax.swing.*;
import java.awt.*;

//Pannello che contiene i pulsanti per la gestione dei punti vendita
public class SMGestionePuntiVendita extends JPanel {

    JButton btnCreateShop= new JButton("Crea punto vendita");
    JButton btnBindShopArticle = new JButton("Associa articoli - punto vendita");
    JButton indietro = new JButton("Indietro");

    SMGestionePuntiVenditaListener smGestionePuntiVenditaListener;

    public SMGestionePuntiVendita(AppFrame appFrame){
        setLayout(new GridLayout(10,1,0,15));
        btnCreateShop.setPreferredSize(new Dimension(255,40));
        smGestionePuntiVenditaListener = new SMGestionePuntiVenditaListener(appFrame);

        btnCreateShop.addActionListener(smGestionePuntiVenditaListener);
        btnBindShopArticle.addActionListener(smGestionePuntiVenditaListener);
        indietro.addActionListener(smGestionePuntiVenditaListener);

        btnCreateShop.setActionCommand("createShop");
        btnBindShopArticle.setActionCommand("bindShopArticle");
        indietro.setActionCommand("indietro");

        add(btnCreateShop);
        add(btnBindShopArticle);
        add(indietro);

    }
}
