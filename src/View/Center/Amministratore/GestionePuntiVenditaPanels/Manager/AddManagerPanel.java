package View.Center.Amministratore.GestionePuntiVenditaPanels.Manager;

import Business.HelpFunctions;
import DAO.Classi.ManagerDAO;
import Model.Manager;
import View.AppFrame;
import View.Listener.Amministratore.GestionePuntiVendita.Manager.AddManagerListener;

import javax.swing.*;
import java.awt.*;

public class AddManagerPanel extends JPanel {

    private AppFrame appFrame;
    private AddManagerListener addManagerListener;

    private JPanel formPanel = new JPanel();
        private JLabel inserisciNome = new JLabel("Inserisci nome: ");
        private JTextField nomeField = new JTextField();
        private JLabel inserisciCognome = new JLabel("Inserisci cognome: ");
        private JTextField cognomeField = new JTextField();
        private JLabel inserisciEmail = new JLabel("Inserisci email: ");
        private JTextField emailField = new JTextField();
        private JLabel inserisciTelefono = new JLabel("Inserisci telefono: ");
        private JTextField telefonoField = new JTextField();
        private JLabel inserisciResidenza = new JLabel("Inserisci residenza: ");
        private JTextField residenzaField = new JTextField();
        private JLabel inserisciProfessione = new JLabel("Inserisci professione: ");
        private JTextField professioneField = new JTextField();
        private JLabel inserisciUsername = new JLabel("Inserisci username: ");
        private JTextField usernameField = new JTextField();
        private JLabel inserisciPassword = new JLabel("Inserisci password: ");
        private JTextField passwordField = new JTextField();
        private JButton btnAggiungi = new JButton("Aggiungi");

        private JLabel inserisciAnno = new JLabel("Inserisci anno: ");
        private JLabel inserisciMese = new JLabel("Inserisci mese: ");
        private JLabel inserisciGiorno = new JLabel("Inserisci giorno: ");
        private JComboBox<String> yearField;
        private JComboBox<String> monthField;
        private JComboBox<String> dayField = new JComboBox<>();

    private boolean year_chosen = false;
    private boolean month_chosen = false;

    public AddManagerPanel(AppFrame appFrame){

        this.appFrame = appFrame;
        addManagerListener = new AddManagerListener(this, this.appFrame);

        yearField = HelpFunctions.setYearsComboBox();
        monthField = HelpFunctions.setMonthsComboBox();
        dayField.setEnabled(false);

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

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

    public void aggiungi(){
        if(!isAggiungiOK()) return;
        String dataCompleanno = yearField.getSelectedItem().toString() + "-" + HelpFunctions.convertMonth(monthField.getSelectedIndex()) + "-" + dayField.getSelectedItem().toString();
        ManagerDAO.getInstance().add(new Manager(
                usernameField.getText(), passwordField.getText(), nomeField.getText(), cognomeField.getText(), emailField.getText(), dataCompleanno, telefonoField.getText(), residenzaField.getText(), professioneField.getText()));
    }

    public boolean isAggiungiOK(){
        if( usernameField.getText().equals("")|| passwordField.getText().equals("")||
                nomeField.getText().equals("")||cognomeField.getText().equals("")||emailField.getText().equals("")||
                yearField.getSelectedItem().toString().equals("--")||monthField.getSelectedItem().toString().equals("--")||dayField.getSelectedItem().toString().equals("--")||
                telefonoField.getText().equals("")||residenzaField.getText().equals("--")||professioneField.getText().equals("--")){
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
        formPanel.setLayout(new GridLayout(0,2,0,10));
    }

    public void componentsAdding(){
        add(formPanel);
        formPanel.add(inserisciNome);
        formPanel.add(nomeField);
        formPanel.add(inserisciCognome);
        formPanel.add(cognomeField);
        formPanel.add(inserisciEmail);
        formPanel.add(emailField);
        formPanel.add(inserisciAnno);
        formPanel.add(yearField);
        formPanel.add(inserisciMese);
        formPanel.add(monthField);
        formPanel.add(inserisciGiorno);
        formPanel.add(dayField);
        formPanel.add(inserisciTelefono);
        formPanel.add(telefonoField);
        formPanel.add(inserisciResidenza);
        formPanel.add(residenzaField);
        formPanel.add(inserisciProfessione);
        formPanel.add(professioneField);
        formPanel.add(inserisciUsername);
        formPanel.add(usernameField);
        formPanel.add(inserisciPassword);
        formPanel.add(passwordField);
        formPanel.add(btnAggiungi);
    }

    public void componentsSizing(){
        cognomeField.setPreferredSize(new Dimension(300,30));
    }

    public void listenerSettings(){
        btnAggiungi.addActionListener(addManagerListener);
        btnAggiungi.setActionCommand("aggiungi");
        monthField.addActionListener(addManagerListener);
        monthField.setActionCommand("month");
        yearField.addActionListener(addManagerListener);
        yearField.setActionCommand("year");
    }



}
