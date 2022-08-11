package View.SideMenuPanels;

import Business.SessionManager;
import Business.UtenteBusiness;
import Model.Utente;
import View.AppFrame;
import View.Listener.SideMenuListeners.AmministratoreSideMenuListener;
import View.Listener.SideMenuListeners.ClienteSideMenuListener;
import View.Listener.SideMenuListeners.GuestSideMenuListener;
import View.Listener.SideMenuListeners.ManagerSideMenuListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SideMenuDefault extends JPanel{

    ActionListener listener;
    AppFrame appFrame;

    public SideMenuDefault(AppFrame appFrame) {
        this.appFrame = appFrame;
        Dimension d = new Dimension(300,800);
        setLayout(new GridLayout(10,1,0,15));
        //setPreferredSize(d);
        //listener = new AmministratoreSideMenuListener(appFrame);
        //setLayout(new GridLayout(20,1));
        refresh();

    }

    public void refresh() {

        removeAll();
        Menu menu = new GuestMenu();
        listener = new GuestSideMenuListener(appFrame);
        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");

        if(u != null) {
            menu = new ClienteMenuDecorator(menu);
            listener = new ClienteSideMenuListener(appFrame);

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
