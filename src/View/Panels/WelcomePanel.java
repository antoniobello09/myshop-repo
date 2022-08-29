package View.Panels;

import Business.SessionManager;
import DAO.Classi.PuntoVenditaDAO;
import Model.PuntoVendita;

import javax.swing.*;
//Pannello di benvenuto
public class WelcomePanel extends JPanel {

    public WelcomePanel() {
        PuntoVendita puntoVendita = PuntoVenditaDAO.getInstance().findByID((Integer) SessionManager.getInstance().getSession().get("idPuntoVendita"));
        JLabel benvenuto = new JLabel(
                "<html>" +
                        "<h1><center><b>MY SHOP</b></center></h1><br>" +
                        "<h2><center><b>Benvenuto nel punto vendita di " + puntoVendita.getCitta() + ", " + puntoVendita.getIndirizzo() + "</b></center><br>" +
                        "Ciao, effettua il login per iniziare, oppure sfoglia il catalogo usando il pulsante a lato!</h2>" +
                        "</html>" );
        add(benvenuto);
    }
}
