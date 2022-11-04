package View;

import Business.ModelBusiness.PuntoVenditaBusiness;
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

    private Header header;
    private SideMenu sideMenu;
    private Footer footer;
    private Center center;
//----------------COSTRUTTORE---------------------------------------------------------//
    public AppFrame() {
        super("MY SHOP");
        setLayout(new BorderLayout());

        //Dialog per scegliere il punto vendita dove mi trovo
        PuntoVenditaBusiness.getInstance().scegliPuntovenditaDialog(this);

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
