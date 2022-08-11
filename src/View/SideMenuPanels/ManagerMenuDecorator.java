package View.SideMenuPanels;

import javax.swing.*;
import java.util.List;

public class ManagerMenuDecorator extends CustomMenuDecorator {

    public ManagerMenuDecorator(Menu menu) {
        this.menu = menu;
    }

    @Override
    public List<JButton> getPulsanti() {

        if(pulsanti.size() > 0) return pulsanti;
        pulsanti.addAll(this.menu.getPulsanti());//Pulsanti precedenti

        //--------------PULSANTE LISTA UTENTI-------------------------------//
        JButton utenti = new JButton("Lista utenti");
        utenti.setActionCommand("gestioneClienti");
        pulsanti.add(utenti);
        JButton btnSchedeProdotto = new JButton("Gestione Prodotti");
        btnSchedeProdotto.setActionCommand("gestioneSchedeProdotto");
        pulsanti.add(btnSchedeProdotto);
        JButton btnSchedeServizio = new JButton("Gestione Servizi");
        btnSchedeServizio.setActionCommand("gestioneSchedeServizio");
        pulsanti.add(btnSchedeServizio);
        //-------------------------------------------------------------------//

        return pulsanti;
    }
}
