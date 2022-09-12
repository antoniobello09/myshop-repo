package View.Panels.Center.Amministratore.GestionePuntiVenditaPanels;

import DAO.Classi.FornitoreDAO;
import DAO.Classi.ManagerDAO;
import DAO.Classi.PuntoVenditaDAO;
import Model.Fornitore;
import Model.Manager;
import Model.Utente;
import View.AppFrame;
import View.Listener.CenterListeners.Amministratore.GestionePuntiVenditaListeners.CreateShopListener;

import javax.swing.*;
import java.awt.*;

public class CreateShopPanel extends JPanel {

    AppFrame appFrame;
    CreateShopListener createShopListener;

    private JPanel formPanel = new JPanel();
        private JLabel inserisciEmail = new JLabel("Inserire l'e-mail: ");
        private JTextField emailField = new JTextField();
        private JLabel inserisciUsername = new JLabel("Inserire l'username: ");
        private JTextField usernameField = new JTextField();
        private JLabel inserisciPassword = new JLabel("Inserire la password: ");
        private JTextField passwordField = new JTextField();
        private JLabel inserisciCitta = new JLabel("Inserire la città del punto vendita: ");
        private JTextField cittaField = new JTextField();
        private JLabel inserisciIndirizzo = new JLabel("Scegliere l'indirizzo del punto vendita: ");
        private JTextField indirizzoField = new JTextField();
        private JButton btnCrea = new JButton("Aggiungi");

    public CreateShopPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        createShopListener = new CreateShopListener(this, this.appFrame);

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();
    }

    public void crea(){
        if( (emailField.getText().isEmpty()) || (usernameField.getText().isEmpty()) || (passwordField.getText().isEmpty()) || (cittaField.getText().isEmpty()) || (indirizzoField.getText().isEmpty()) ) {
            JOptionPane.showMessageDialog(appFrame,
                    "Riempire tutti i campi!",
                    "Create Product Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
            Manager manager = new Manager(usernameField.getText(), passwordField.getText(), emailField.getText());
            ManagerDAO.getInstance().add(manager);
            PuntoVenditaDAO.getInstance().add(ManagerDAO.getInstance().findByUsername(usernameField.getText(),1).getIdUtente(), cittaField.getText(), indirizzoField.getText());
        }
    }

    public void layoutSetting(){
        formPanel.setLayout(new GridLayout(0,2,0,10));
    }

    public void componentsAdding(){
        add(formPanel);
        formPanel.add(inserisciEmail);
        formPanel.add(emailField);
        formPanel.add(inserisciUsername);
        formPanel.add(usernameField);
        formPanel.add(inserisciPassword);
        formPanel.add(passwordField);
        formPanel.add(inserisciCitta);
        formPanel.add(cittaField);
        formPanel.add(inserisciIndirizzo);
        formPanel.add(indirizzoField);
        formPanel.add(btnCrea);

    }

    public void componentsSizing(){

    }

    public void listenerSettings(){
        btnCrea.setActionCommand("crea");

        btnCrea.addActionListener(createShopListener);
    }

}
