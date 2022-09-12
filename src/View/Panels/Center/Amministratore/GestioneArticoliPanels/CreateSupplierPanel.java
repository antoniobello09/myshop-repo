package View.Panels.Center.Amministratore.GestioneArticoliPanels;

import DAO.Classi.FornitoreDAO;
import Model.Fornitore;
import View.AppFrame;
import View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners.CreateSupplierListener;

import javax.swing.*;
import java.awt.*;

public class CreateSupplierPanel extends JPanel {

    AppFrame appFrame;
    CreateSupplierListener createSupplierListener;

    private JPanel formPanel = new JPanel();
        private JLabel inserisciSupplier = new JLabel("Inserire il nome del fornitore da creare: ");
        private JTextField supplierField = new JTextField();
        private JLabel inserisciWebSite = new JLabel("Inserire il sito web: ");
        private JTextField websiteField = new JTextField();
        private JLabel inserisciCitta = new JLabel("Inserire la città: ");
        private JTextField cittaField = new JTextField();
        private JLabel inserisciNazione = new JLabel("Inserire la nazione: ");
        private JTextField nazioneField = new JTextField();
        private JLabel inserisciTipo = new JLabel("Scegliere il tipo di fornitore: ");
        private JComboBox<String> tipoField;
        private JButton btnAggiungi = new JButton("Aggiungi");

    public CreateSupplierPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        createSupplierListener = new CreateSupplierListener(this, this.appFrame);

        String[] tipo = {"prodotto", "servizio"};
        tipoField = new JComboBox<>(tipo);


        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();
    }

    public void aggiungi(){
        if( (supplierField.getText().isEmpty()) || (websiteField.getText().isEmpty()) || (cittaField.getText().isEmpty()) || (nazioneField.getText().isEmpty()) ) {
            JOptionPane.showMessageDialog(appFrame,
                    "La compilazione è errata!",
                    "Create Product Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
            Fornitore fornitore = new Fornitore(supplierField.getText(), websiteField.getText(), cittaField.getText(), nazioneField.getText(), tipoField.getSelectedItem().toString().toLowerCase().substring(0,1));
            FornitoreDAO.getInstance().add(fornitore);
        }
    }

    public void layoutSetting(){
        formPanel.setLayout(new GridLayout(0,2,0,10));
    }

    public void componentsAdding(){
        add(formPanel);
        formPanel.add(inserisciSupplier);
        formPanel.add(supplierField);
        formPanel.add(inserisciWebSite);
        formPanel.add(websiteField);
        formPanel.add(inserisciCitta);
        formPanel.add(cittaField);
        formPanel.add(inserisciNazione);
        formPanel.add(nazioneField);
        formPanel.add(inserisciTipo);
        formPanel.add(tipoField);
        formPanel.add(btnAggiungi);

    }

    public void componentsSizing(){

    }

    public void listenerSettings(){
        btnAggiungi.setActionCommand("aggiungi");

        btnAggiungi.addActionListener(createSupplierListener);
    }
}
