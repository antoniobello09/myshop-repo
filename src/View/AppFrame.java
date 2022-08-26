package View;

import Business.SessionManager;
import View.Center.Center;
import View.Footer.Footer;
import View.Header.Header;
import View.SideMenuPanels.SideMenu;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {

    private String nomePuntoVendita;

    Header header;
    SideMenu sideMenu;
    Footer footer;
    Center center;
//----------------COSTRUTTORE---------------------------------------------------------//
    public AppFrame() {
        super("MY SHOP");
        setLayout(new BorderLayout());

        nomePuntoVendita = ShopDialog.showDialog(this,
                null,
                "Punto Vendita",
                "Punto Vendita",
                null,
                null,
                null);
        String[] indirizzo = nomePuntoVendita.split(", ");
        //PuntoVendita puntoVendita = PuntoVenditaDAO.getInstance().findByName(indirizzo[0], indirizzo[1]);
        SessionManager.getInstance().getSession().put("loggedShopAddress", indirizzo[0]);
        SessionManager.getInstance().getSession().put("loggedShopCity", indirizzo[1]);

    //----------------HEADER--------------------------------------------------------------//
        header = new Header(this);
        add(header, BorderLayout.NORTH);
    //----------------LEFT SIDE-----------------------------------------------------------//
        sideMenu = new SideMenu(this);
        add(sideMenu, BorderLayout.WEST);
    //----------------CENTER--------------------------------------------------------------//
        center = new Center(this);
        add(center, BorderLayout.CENTER);
    //----------------FOOTER--------------------------------------------------------------//
        footer = new Footer();
        add(footer, BorderLayout.SOUTH);
    //----------------DEFAULT SETTINGS----------------------------------------------------//
        setSize(1500,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    public Header getHeader() {
        return this.header;
    }

    public SideMenu getSideMenu() {
        return this.sideMenu;
    }

    public Center getCenter() {
        return this.center;
    }

    public Footer getFooter() {
        return this.footer;
    }
}
/*
        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        JMenu operazioni = new JMenu("Operazioni");
        JMenuItem browseMenu= new JMenuItem("Catalogo");
        operazioni.add(browseMenu);
        browseMenu.setActionCommand("browse");
        browseMenu.addActionListener(list);
        bar.add(operazioni);
 */