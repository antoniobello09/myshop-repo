package View.SideMenuPanels;

import javax.swing.*;
import java.util.List;

public class AmministratoreMenuDecorator extends CustomMenuDecorator {

    public AmministratoreMenuDecorator(Menu menu) {
        this.menu = menu;
    }

    @Override
    public List<JButton> getPulsanti() {
        if(pulsanti.size() > 0) return pulsanti;
        pulsanti.addAll(this.menu.getPulsanti());

        JButton btnGestioneProdotti = new JButton("Gestione prodotti");
        btnGestioneProdotti.setActionCommand("gestioneProdotti");
        pulsanti.add(btnGestioneProdotti);

        JButton btnGestioneServizi = new JButton("Gestione servizi");
        btnGestioneServizi.setActionCommand("gestioneServizi");
        pulsanti.add(btnGestioneServizi);

        JButton btnGestionePuntiVendita = new JButton("Gestione punti vendita");
        btnGestionePuntiVendita.setActionCommand("gestionePuntiVendita");
        pulsanti.add(btnGestionePuntiVendita);


        return pulsanti;

    }
}
