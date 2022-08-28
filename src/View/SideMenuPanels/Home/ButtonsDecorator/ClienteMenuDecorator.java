package View.SideMenuPanels.Home.ButtonsDecorator;

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
        pulsanti.add(btnLists);


        return pulsanti;
    }
}
