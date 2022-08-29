package View.Panels.SideMenu.Home.ButtonsDecorator;

import javax.swing.*;

public class GuestMenu extends Menu {

    public GuestMenu() {
    //--------------PULSANTE SFOGLIA-------------------------------//
        JButton btnBrowse = new JButton("Sfoglia catalogo");
        btnBrowse.setActionCommand("sfoglia");
        pulsanti.add(btnBrowse);
    //-------------------------------------------------------------//
    }
}
