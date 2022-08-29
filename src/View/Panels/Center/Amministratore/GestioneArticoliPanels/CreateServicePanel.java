package View.Panels.Center.Amministratore.GestioneArticoliPanels;

import DAO.Classi.*;
import Model.*;
import View.AppFrame;
import View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners.CreateServiceListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CreateServicePanel extends JPanel {

    AppFrame appFrame;
    CreateServiceListener createServiceListener;

    private JPanel formProductPanel = new JPanel();
    private JLabel inserisciNome = new JLabel("Nome");
    private JTextField nomeField = new JTextField();
    private JLabel inserisciDescrizione = new JLabel("Descrizione");
    private JTextField descrizioneField = new JTextField();
    private JLabel inserisciPrezzo = new JLabel("Prezzo                  €");
    private JTextField prezzoField = new JTextField();
    private JLabel immagineProdotto = new JLabel("Immagine");
    private JTextField immagineField = new JTextField();
    private JLabel inserisciCategoria = new JLabel("Categoria");
    private JComboBox<String> categoriaField;
    private JLabel inserisciFornitore = new JLabel("Fornitore");
    private JComboBox<String> fornitoreField;
    private JButton btnInvia = new JButton("Invia");

    private ArrayList<CategoriaServizio> categorieList;     //Lista di categorie
    private ArrayList<Fornitore> fornitoriList;            //Lista di fornitori

    public CreateServicePanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        createServiceListener = new CreateServiceListener(this, appFrame);

        categoriaField = new JComboBox<>();
        fornitoreField = new JComboBox<>();

        //Creo la lista di categorie da cui scegliere
        categorieList = CategoriaServizioDAO.getInstance().findAll();
        if(categorieList != null) {
            for (int i = 0; i < categorieList.size(); i++) {
                categoriaField.addItem(categorieList.get(i).getNome());
            }
        }

        //Creo la lista di produttori da cui scegliere
        fornitoriList = FornitoreDAO.getInstance().findAllServ();
        if(fornitoriList != null) {
            for (int i = 0; i < fornitoriList.size(); i++) {
                fornitoreField.addItem(fornitoriList.get(i).getNome());
            }
        }

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void invia(){
        if(nomeField.getText().isEmpty() || descrizioneField.getText().isEmpty() || prezzoField.getText().isEmpty() || fornitoreField.getSelectedItem() == null){
            JOptionPane.showMessageDialog(appFrame,
                    "La compilazione è errata!",
                    "Create Service Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }else{
            int idCategoria = CategoriaServizioDAO.getInstance().findByName(categoriaField.getSelectedItem().toString()).getIdCategoria();
            int idFornitore = FornitoreDAO.getInstance().findByName(fornitoreField.getSelectedItem().toString()).getIdFornitore();
            Servizio servizio = new Servizio(nomeField.getText(), descrizioneField.getText(), Float.parseFloat(prezzoField.getText()), idCategoria, idFornitore);
            ServizioDAO.getInstance().add(servizio);
        }
    }

    public void layoutSetting(){
        formProductPanel.setLayout(new GridLayout(0,2,0,10));
    }

    public void componentsAdding(){
        add(formProductPanel);
        formProductPanel.add(inserisciNome);
        formProductPanel.add(nomeField);
        formProductPanel.add(inserisciDescrizione);
        formProductPanel.add(descrizioneField);
        formProductPanel.add(inserisciPrezzo);
        formProductPanel.add(prezzoField);
        formProductPanel.add(inserisciCategoria);
        formProductPanel.add(categoriaField);
        formProductPanel.add(inserisciFornitore);
        formProductPanel.add(fornitoreField);
        formProductPanel.add(btnInvia);
    }

    public void componentsSizing(){

    }

    public void listenerSettings(){
        btnInvia.setActionCommand("invia");

        btnInvia.addActionListener(createServiceListener);
    }
}
