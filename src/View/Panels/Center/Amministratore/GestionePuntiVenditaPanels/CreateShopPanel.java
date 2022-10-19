package View.Panels.Center.Amministratore.GestionePuntiVenditaPanels;

import Business.ModelBusiness.PuntoVenditaBusiness;
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

    private AppFrame appFrame;
    private CreateShopListener createShopListener;

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
        if((emailField.getText().isEmpty()) || (usernameField.getText().isEmpty()) || (passwordField.getText().isEmpty()) || (cittaField.getText().isEmpty()) || (indirizzoField.getText().isEmpty()) ) {
            JOptionPane.showMessageDialog(appFrame,
                    "Riempire tutti i campi!",
                    "Create Product Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            int result = PuntoVenditaBusiness.getInstance().crea(
                    emailField.getText(), usernameField.getText(), passwordField.getText(),
                    cittaField.getText(), indirizzoField.getText());
            switch(result){
                case 0:
                    JOptionPane.showMessageDialog(appFrame,
                            "Punto Vendita creato con successo!",
                            "Create Shop Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(appFrame,
                            "Punto Vendita non creato!",
                            "Create Shop Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
        clearText();
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

    public void clearText(){
        emailField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        cittaField.setText("");
        indirizzoField.setText("");
    }

}
