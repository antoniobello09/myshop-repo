package View.Center.Registration;

import Business.HelpFunctions;
import Business.SessionManager;
import DAO.Classi.ClienteDAO;
import DAO.Classi.ManagerDAO;
import Model.Cliente;
import Model.Manager;
import Model.PuntoVendita;
import Model.Utente;
import View.AppFrame;
import View.Listener.HeaderListeners.RegisterListener;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegisterPanel extends JPanel {

    private RegisterListener registerListener;
    private AppFrame appFrame;

    private JPanel containerPanel = new JPanel();
        private JPanel formRegistrazionePanel = new JPanel();
            private JLabel inserisciNome = new JLabel("Inserisci nome: ");
            private JTextField nomeField = new JTextField();
            private JLabel inserisciCognome = new JLabel("Inserisci cognome: ");
            private JTextField cognomeField = new JTextField();
            private JLabel inserisciEmail = new JLabel("Inserisci email: ");
            private JTextField emailField = new JTextField();
            private JLabel inserisciTelefono = new JLabel("Inserisci telefono: ");
            private JTextField telefonoField = new JTextField();
            private JLabel inserisciResidenza = new JLabel("Inserisci indirizzo: ");
            private JTextField residenzaField = new JTextField();
            private JLabel inserisciCitta = new JLabel("Inserisci città di residenza: ");
            private JTextField cittaField = new JTextField();
            private JLabel inserisciProfessione = new JLabel("Inserisci professione: ");
            private JTextField professioneField = new JTextField();
            private JLabel inserisciCanalePreferito = new JLabel("Inserisci il tuo canale preferito: ");
            private JComboBox<String> canaleField = new JComboBox<>();
            private JLabel inserisciUsername = new JLabel("Inserisci username: ");
            private JTextField usernameField = new JTextField();
            private JLabel inserisciPassword = new JLabel("Inserisci password: ");
            private JTextField passwordField = new JTextField();
        private JPanel operazioniPanel = new JPanel();
            private JButton btnSend = new JButton("Iscriviti");

    private JLabel inserisciAnno = new JLabel("Inserisci anno: ");
    private JLabel inserisciMese = new JLabel("Inserisci mese: ");
    private JLabel inserisciGiorno = new JLabel("Inserisci giorno: ");
    private JComboBox<String> yearField;
    private JComboBox<String> monthField;
    private JComboBox<String> dayField = new JComboBox<>();


    private boolean year_chosen = false;
    private boolean month_chosen = false;

    public RegisterPanel(AppFrame appFrame) {

        this.appFrame = appFrame;
        registerListener = new RegisterListener(this, this.appFrame);

        yearField = HelpFunctions.setYearsComboBox();
        monthField = HelpFunctions.setMonthsComboBox();
        dayField.setEnabled(false);

        canaleField.addItem("Email");
        canaleField.addItem("Telefono");

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();


    }





    public void iscriviti(){
        if(!isIscrivitiOK()) return;
        String dataCompleanno = yearField.getSelectedItem().toString() + "-" + HelpFunctions.convertMonth(monthField.getSelectedIndex()) + "-" + dayField.getSelectedItem().toString();
        ClienteDAO.getInstance().add(new Cliente(
                usernameField.getText(), passwordField.getText(), nomeField.getText(), cognomeField.getText(), emailField.getText(), dataCompleanno, telefonoField.getText(), residenzaField.getText(), professioneField.getText(), String.valueOf(canaleField.getSelectedItem()), true), (PuntoVendita)SessionManager.getInstance().getSession().get("loggedShop"));
    }

    public boolean isIscrivitiOK(){
        if( usernameField.getText().equals("")|| passwordField.getText().equals("")||
                nomeField.getText().equals("")||cognomeField.getText().equals("")||emailField.getText().equals("")||
                yearField.getSelectedItem().toString().equals("--")||monthField.getSelectedItem().toString().equals("--")||dayField.getSelectedItem().toString().equals("--")||
                telefonoField.getText().equals("")||residenzaField.getText().equals("")||residenzaField.getText().equals("")||professioneField.getText().equals("")){
            JOptionPane.showMessageDialog(appFrame,
                    "Riempi tutte le caselle!",
                    "Register error",
                    JOptionPane.ERROR_MESSAGE);
            return false;

        }else if(telefonoField.getText().length() != 10){
            JOptionPane.showMessageDialog(appFrame,
                    "Il numero di telefono deve avere 10 numeri!",
                    "Register error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void ricalcolaGiorni(){
        if((!month_chosen)||(!year_chosen)) return;
        HelpFunctions.setDaysComboBox(dayField, monthField.getSelectedItem().toString(), yearField.getSelectedItem().toString());
        dayField.setEnabled(true);
    }
    public void layoutSetting(){
        setLayout(new FlowLayout());
            containerPanel.setLayout(new GridLayout(2,0,0,20));
                formRegistrazionePanel.setLayout(new GridLayout(0,2,0,10));
                operazioniPanel.setLayout(new FlowLayout());
    }

    public void componentsAdding(){
        add(containerPanel);
        containerPanel.add(formRegistrazionePanel);
        formRegistrazionePanel.add(inserisciNome);
        formRegistrazionePanel.add(nomeField);
        formRegistrazionePanel.add(inserisciCognome);
        formRegistrazionePanel.add(cognomeField);
        formRegistrazionePanel.add(inserisciEmail);
        formRegistrazionePanel.add(emailField);
        formRegistrazionePanel.add(inserisciAnno);
        formRegistrazionePanel.add(yearField);
        formRegistrazionePanel.add(inserisciMese);
        formRegistrazionePanel.add(monthField);
        formRegistrazionePanel.add(inserisciGiorno);
        formRegistrazionePanel.add(dayField);
        formRegistrazionePanel.add(inserisciTelefono);
        formRegistrazionePanel.add(telefonoField);
        formRegistrazionePanel.add(inserisciResidenza);
        formRegistrazionePanel.add(residenzaField);
        formRegistrazionePanel.add(inserisciProfessione);
        formRegistrazionePanel.add(professioneField);
        formRegistrazionePanel.add(inserisciCanalePreferito);
        formRegistrazionePanel.add(canaleField);
        formRegistrazionePanel.add(inserisciUsername);
        formRegistrazionePanel.add(usernameField);
        formRegistrazionePanel.add(inserisciPassword);
        formRegistrazionePanel.add(passwordField);
        containerPanel.add(operazioniPanel);
        operazioniPanel.add(btnSend);
    }

    public void componentsSizing(){
        cognomeField.setPreferredSize(new Dimension(300,30));
    }

    public void listenerSettings(){
        btnSend.addActionListener(registerListener);
        btnSend.setActionCommand("iscriviti");
        monthField.addActionListener(registerListener);
        monthField.setActionCommand("month");
        yearField.addActionListener(registerListener);
        yearField.setActionCommand("year");
    }


    public void setMonthSelected(){
        if(monthField.getSelectedItem().toString().equals("--")) {
            month_chosen = false;
            return;
        }
        month_chosen = true;
        ricalcolaGiorni();
    }

    public void setYearSelected(){
        if(yearField.getSelectedItem().toString().equals("--")) {
            year_chosen = false;
            return;
        }
        year_chosen = true;
        ricalcolaGiorni();
    }

}
