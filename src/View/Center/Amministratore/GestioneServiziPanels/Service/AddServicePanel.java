package View.Center.Amministratore.GestioneServiziPanels.Service;

import Business.HelpFunctions;
import DAO.Classi.CategoriaServizioDAO;
import DAO.Classi.ServizioDAO;
import Model.*;
import View.AppFrame;
import View.Listener.Amministratore.GestioneServiziListener.Service.AddServiceListener;
import View.Nameable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddServicePanel extends JPanel {

    private AddServiceListener addServiceListener;
    private AppFrame appFrame;

    private JPanel containerPanel = new JPanel();
        private JPanel formServicePanel = new JPanel();
            private JLabel inserisciNome = new JLabel("Nome del servizio");
            private JTextField nomeField = new JTextField();
            private JLabel inserisciDescrizione = new JLabel("Descrizione del servizio");
            private JTextField descrizioneField = new JTextField();
            private JLabel inserisciPrezzo = new JLabel("Prezzo del servizio                     €");
            private JTextField prezzoField = new JTextField();
            private JLabel immagineProdotto = new JLabel("Immagine del servizio");
            private JTextField immagineField = new JTextField();
            private JLabel inserisciCategoria = new JLabel("Categoria del servizio");
            private JComboBox<String> categoriaField;
            private JButton invia = new JButton("Invia");

    private ArrayList<Nameable> comboList;
    private ArrayList<CategoriaServizio> cList;     //Lista di categorie


    public AddServicePanel(AppFrame appFrame){

        this.appFrame = appFrame;
        addServiceListener = new AddServiceListener(this, this.appFrame);

        cList = CategoriaServizioDAO.getInstance().findAll();
        comboList = (ArrayList<Nameable>)cList.clone();
        categoriaField = new JComboBox<>(HelpFunctions.fromArraytoString(comboList));

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void invia(){
        Servizio servizio = new Servizio();
        servizio.setNome(nomeField.getText());
        servizio.setCategoria(new CategoriaServizio(categoriaField.getSelectedItem().toString()));
        servizio.setDescrizione(descrizioneField.getText().replace("\'","\\\'"));
        servizio.setPrezzo(Float.parseFloat(prezzoField.getText()));
        ServizioDAO.getInstance().add(servizio);
    }

    public void tableSetting(){

    }

    public void layoutSetting(){
        setLayout(new FlowLayout());
            containerPanel.setLayout(new BorderLayout());
                formServicePanel.setLayout(new GridLayout(0,2,0,10));
    }

    public void componentsAdding(){
        add(containerPanel);
            containerPanel.add(formServicePanel, BorderLayout.NORTH);
                formServicePanel.add(inserisciNome);
                formServicePanel.add(nomeField);
                formServicePanel.add(inserisciCategoria);
                formServicePanel.add(categoriaField);
                formServicePanel.add(inserisciDescrizione);
                formServicePanel.add(descrizioneField);
                formServicePanel.add(inserisciPrezzo);
                formServicePanel.add(prezzoField);
                formServicePanel.add(invia);
    }

    public void componentsSizing(){
        containerPanel.setPreferredSize(new Dimension(500,450));
    }

    public void listenerSettings(){
        invia.addActionListener(addServiceListener);

        invia.setActionCommand("invia");
    }

}
