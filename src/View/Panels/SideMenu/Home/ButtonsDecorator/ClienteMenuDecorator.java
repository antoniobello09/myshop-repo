package View.Panels.SideMenu.Home.ButtonsDecorator;

import Business.SessionManager;
import DAO.Classi.ClienteDAO;
import Model.Utente;

import javax.swing.*;
import java.util.List;

public class ClienteMenuDecorator extends CustomMenuDecorator {

    public ClienteMenuDecorator(Menu menu) {
        this.menu = menu;
    }

    @Override
    public List<JButton> getPulsanti() {
        if(pulsanti.size() > 0) return pulsanti;
        pulsanti.addAll(this.menu.getPulsanti());

        JButton btnLists = new JButton("Gestione liste");
        btnLists.setActionCommand("gestioneListe");
        Utente u = (Utente)SessionManager.getInstance().getSession().get("loggedUser");
        int idCliente = u.getIdUtente();
        if(!ClienteDAO.getInstance().isAbilitato(idCliente)){
            btnLists.setEnabled(false);
        }
        pulsanti.add(btnLists);


        return pulsanti;
    }
}
