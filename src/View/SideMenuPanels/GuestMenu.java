package View.SideMenuPanels;

import javax.swing.*;

public class GuestMenu extends Menu {

    public GuestMenu() {
    //--------------PULSANTE SFOGLIA-------------------------------//
        JButton browse = new JButton("Sfoglia catalogo");
        browse.setActionCommand("sfoglia");
        pulsanti.add(browse);
    //-------------------------------------------------------------//
    }
}
