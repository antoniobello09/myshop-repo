package View.Panels.SideMenu.Home.ButtonsDecorator;

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

        JButton btnGestioneProdotti = new JButton("Gestione articoli");
        btnGestioneProdotti.setActionCommand("gestioneArticoli");
        pulsanti.add(btnGestioneProdotti);

        JButton btnGestionePuntiVendita = new JButton("Gestione punti vendita");
        btnGestionePuntiVendita.setActionCommand("gestionePuntiVendita");
        pulsanti.add(btnGestionePuntiVendita);

        return pulsanti;
    }
}
