package View.Panels.SideMenu.Home;

import Business.SessionManager;
import Business.UtenteBusiness;
import Model.Utente;
import View.AppFrame;
import View.Listener.SideMenuListeners.Home.AmministratoreSideMenuListener;
import View.Listener.SideMenuListeners.Home.ClienteSideMenuListener;
import View.Listener.SideMenuListeners.Home.GuestSideMenuListener;
import View.Listener.SideMenuListeners.Home.ManagerSideMenuListener;
import View.Panels.SideMenu.Home.ButtonsDecorator.AmministratoreMenuDecorator;
import View.Panels.SideMenu.Home.ButtonsDecorator.ClienteMenuDecorator;
import View.Panels.SideMenu.Home.ButtonsDecorator.GuestMenu;
import View.Panels.SideMenu.Home.ButtonsDecorator.ManagerMenuDecorator;
import View.Panels.SideMenu.Home.ButtonsDecorator.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


//Pannello che aggiunge i pulsanti dinamicamente in base all'utente registrato
//Utilizza il design pattern Decorator
public class SideMenuLogin extends JPanel{

    ActionListener listener;
    AppFrame appFrame;

    public SideMenuLogin(AppFrame appFrame) {
        this.appFrame = appFrame;
        setLayout(new GridLayout(10,1,0,15));
        refresh();
    }

    public void refresh() {

        removeAll();
        Menu menu = new GuestMenu();
        listener = new GuestSideMenuListener(appFrame);
        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");

        if(u != null) {

            if(UtenteBusiness.getInstance().userCan(u, UtenteBusiness.Privilegio.CLIENT)) { //cliente
                menu = new ClienteMenuDecorator(menu);
                listener = new ClienteSideMenuListener(appFrame);
            }

            if(UtenteBusiness.getInstance().userCan(u, UtenteBusiness.Privilegio.MANAGE_SHOP)) { //manager
                menu = new ManagerMenuDecorator(menu);
                listener = new ManagerSideMenuListener(appFrame);
            }
            if(UtenteBusiness.getInstance().userCan(u, UtenteBusiness.Privilegio.ADMIN_SYSTEM)) { //amministratore
                menu = new AmministratoreMenuDecorator(menu);
                listener = new AmministratoreSideMenuListener(appFrame);
            }

        }

        for(JButton btn: menu.getPulsanti()) {
            btn.addActionListener(listener);

            btn.setPreferredSize(new Dimension(255,40));
            add(btn);
        }
    }

}
