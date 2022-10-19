package View.Panels;

import Business.ModelBusiness.PuntoVenditaBusiness;
import Business.SessionManager;
import DAO.Classi.PuntoVenditaDAO;
import Model.PuntoVendita;

import javax.swing.*;
//Pannello di benvenuto
public class WelcomePanel extends JPanel {

    public WelcomePanel() {
        JLabel benvenuto = new JLabel(PuntoVenditaBusiness.getInstance().creaMessaggioHTMLBenvenuto());
        add(benvenuto);
    }
}
