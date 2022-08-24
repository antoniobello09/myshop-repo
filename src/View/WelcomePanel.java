package View;

import Business.SessionManager;
import Model.PuntoVendita;

import javax.swing.*;

public class WelcomePanel extends JPanel {

    public WelcomePanel() {

        JLabel benvenuto = new JLabel(
                "<html>" +
                        "<h1><center><b>MY SHOP</b></center></h1><br>" +
                        "<h2><center><b>Benvenuto nel punto vendita di " + SessionManager.getInstance().getSession().get("loggedShopAddress") + ", " + SessionManager.getInstance().getSession().get("loggedShopCity") + "</b></center><br>" +
                        "Ciao, effettua il login per iniziare, oppure sfoglia il catalogo usando il pulsante a lato!</h2>" +
                        "</html>" );
        add(benvenuto);
    }
}
