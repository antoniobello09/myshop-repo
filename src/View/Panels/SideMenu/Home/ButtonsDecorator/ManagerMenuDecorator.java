package View.Panels.SideMenu.Home.ButtonsDecorator;

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
        JButton btnUsersList = new JButton("Lista utenti");
        btnUsersList.setActionCommand("gestioneClienti");
        pulsanti.add(btnUsersList);

        JButton btnSchedeProdotto = new JButton("Gestione Disponibilità Prodotti");
        btnSchedeProdotto.setActionCommand("gestioneSchedeProdotto");
        pulsanti.add(btnSchedeProdotto);

        JButton btnFeedbackList = new JButton("Feedbacks");
        btnFeedbackList.setActionCommand("feedbacks");
        pulsanti.add(btnFeedbackList);
        //-------------------------------------------------------------------//

        return pulsanti;
    }
}
