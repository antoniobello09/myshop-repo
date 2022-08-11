package View.SideMenuPanels;

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
        JButton lists = new JButton("Le tue liste");
        lists.setActionCommand("gestioneListe");
        pulsanti.add(lists);
        return pulsanti;
    }
}
