package View.Panels.Center.Amministratore.GestioneArticoliPanels;

import Business.HelpFunctions;
import Business.ModelBusiness.CategoriaBusiness;
import Business.ModelBusiness.FornitoreBusiness;
import Business.ModelBusiness.ServizioBusiness;
import Model.*;
import View.AppFrame;
import View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners.CreateServiceListener;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.ImageFilter;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.ImagePreview;

import javax.swing.*;
import java.awt.*;
import java.io.File;
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
    private JLabel immagineServizio = new JLabel("Immagine");
    private JButton btnImmagine = new JButton("Scegli immagine");
    private JLabel inserisciCategoria = new JLabel("Categoria");
    private JComboBox<String> categoriaField;
    private JLabel inserisciFornitore = new JLabel("Fornitore");
    private JComboBox<String> fornitoreField;
    private JButton btnInvia = new JButton("Invia");

    private JFileChooser fileChooser;
    private File immagine = null;

    public CreateServicePanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        createServiceListener = new CreateServiceListener(this, appFrame);

        categoriaField = new JComboBox<>();
        fornitoreField = new JComboBox<>();

        //Creo la lista di categorie da cui scegliere
        categoriaField = HelpFunctions.getFullComboBox(CategoriaBusiness.getInstance().getNomiCategorieServizio());


        //Creo la lista di produttori da cui scegliere
        fornitoreField = HelpFunctions.getFullComboBox(FornitoreBusiness.getInstance().getNomiFornitoriServizi());

        fileChooserSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void scegliImmagine(){
        int returnVal = fileChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            immagine = fileChooser.getSelectedFile();
        }
    }

    public void invia(){
        if(immagine == null || nomeField.getText().isEmpty() || descrizioneField.getText().isEmpty() || prezzoField.getText().isEmpty() || fornitoreField.getSelectedItem() == null){
            JOptionPane.showMessageDialog(appFrame,
                    "La compilazione è errata!",
                    "Create Service Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            int result = ServizioBusiness.getInstance().aggiungi(nomeField.getText(), descrizioneField.getText(), prezzoField.getText(), categoriaField.getSelectedItem().toString(), immagine, fornitoreField.getSelectedItem().toString());
            switch (result){
                case 0:
                    JOptionPane.showMessageDialog(appFrame,
                            "Servizio aggiunto con successo!",
                            "Create Service Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(appFrame,
                            "Il servizio non è stato aggiunto!",
                            "Create Service Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }

    }

    public void fileChooserSetting(){
        fileChooser = new JFileChooser();

        fileChooser.addChoosableFileFilter(new ImageFilter());
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setAccessory(new ImagePreview(fileChooser));
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
        formProductPanel.add(immagineServizio);
        formProductPanel.add(btnImmagine);
        formProductPanel.add(inserisciFornitore);
        formProductPanel.add(fornitoreField);
        formProductPanel.add(btnInvia);
    }

    public void componentsSizing(){

    }

    public void listenerSettings(){
        btnInvia.setActionCommand("invia");
        btnImmagine.setActionCommand("fileChooser");

        btnInvia.addActionListener(createServiceListener);
        btnImmagine.addActionListener(createServiceListener);
    }
}
