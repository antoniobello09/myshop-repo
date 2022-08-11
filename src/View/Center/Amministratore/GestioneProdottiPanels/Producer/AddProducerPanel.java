package View.Center.Amministratore.GestioneProdottiPanels.Producer;

import DAO.Classi.ProduttoreDAO;
import Model.Produttore;
import View.AppFrame;
import View.Listener.Amministratore.GestioneProdottiListeners.Producer.AddProducerListener;

import javax.swing.*;
import java.awt.*;

public class AddProducerPanel extends JPanel {

    private AppFrame appFrame;
    private AddProducerListener addProducerListener;

    private JPanel formPanel = new JPanel();
        private JLabel inserisciNome = new JLabel("Nome:");
        private JTextField nomeField = new JTextField();
        private JLabel inserisciSitoWeb = new JLabel("Sito web:");
        private JTextField sitowebField = new JTextField();
        private JLabel inserisciCitta = new JLabel("Città:");
        private JTextField cittaField = new JTextField();
        private JLabel inserisciNazione = new JLabel("Nazione: ");
        private JTextField nazioneField = new JTextField();
        private JButton btnAggiungi = new JButton("Aggiungi");


    public AddProducerPanel(AppFrame appFrame){

        this.appFrame = appFrame;
        addProducerListener = new AddProducerListener(this, this.appFrame);

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void aggiungi(){
        if(!isFormOK()) return;
        Produttore produttore = new Produttore();
        produttore.setNome(nomeField.getText());
        produttore.setSitoweb(sitowebField.getText());
        produttore.setCitta(cittaField.getText());
        produttore.setNazione(nazioneField.getText());
        ProduttoreDAO.getInstance().add(produttore);
        nomeField.setText("");  sitowebField.setText("");   cittaField.setText(""); nazioneField.setText("");
    }

    public boolean isFormOK(){
        String message = "";
        int quantitaErrori = 0;
        if(nomeField.getText().equals("")){
            message += "Il campo nome";
            quantitaErrori++;
        }
        if(sitowebField.getText().equals("")){
            if(quantitaErrori == 0) message += "Il campo sitoweb";
            else message += ", il campo sitoweb";
            quantitaErrori++;
        }
        if(cittaField.getText().equals("")){
            if(quantitaErrori == 0) message += "Il campo citta";
            else message += ", il campo citta";
            quantitaErrori++;
        }
        if(nazioneField.getText().equals("")){
            if(quantitaErrori == 0) message += "Il campo nazione";
            else message += ", il campo nazione";
            quantitaErrori++;
        }
        if(quantitaErrori == 1) message += " è vuoto!";
        else if(quantitaErrori == 0) return true;
        else message += " sono vuoti!";
        JOptionPane.showMessageDialog(appFrame,
                message,
                "Add Producer ERROR",
                JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public void tableSetting(){

    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
            formPanel.setLayout(new GridLayout(0,2,10,10));
    }

    public void componentsAdding(){
        add(formPanel);
            formPanel.add(inserisciNome);
            formPanel.add(nomeField);
            formPanel.add(inserisciSitoWeb);
            formPanel.add(sitowebField);
            formPanel.add(inserisciCitta);
            formPanel.add(cittaField);
            formPanel.add(inserisciNazione);
            formPanel.add(nazioneField);
            formPanel.add(btnAggiungi);
    }

    public  void componentsSizing(){
        inserisciNome.setPreferredSize(new Dimension(200,20));
    }

    public void listenerSettings(){
        btnAggiungi.addActionListener(addProducerListener);

        btnAggiungi.setActionCommand("aggiungi");
    }


}
