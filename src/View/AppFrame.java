package View;

import Business.SessionManager;
import DAO.Classi.PuntoVenditaDAO;
import Model.PuntoVendita;
import View.Panels.Center.Center;
import View.Panels.Footer.Footer;
import View.Panels.Header.Header;
import View.Panels.SideMenu.SideMenu;

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

        //Mostra il Dialog per la scelta del punto vendita
        nomePuntoVendita = ShopDialog.showDialog(this,
                null,
                "Punto Vendita",
                "Punto Vendita",
                null,
                null,
                null);
        String[] indirizzo = nomePuntoVendita.split(", ");
        PuntoVendita puntoVendita = PuntoVenditaDAO.getInstance().findByName(indirizzo[0], indirizzo[1]);
        SessionManager.getInstance().getSession().put("idPuntoVendita", puntoVendita.getIdPuntoVendita()); //salvataggio dell'ID del punto vendita dove si sta accedendo

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