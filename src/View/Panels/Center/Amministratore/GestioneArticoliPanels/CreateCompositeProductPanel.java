package View.Panels.Center.Amministratore.GestioneArticoliPanels;

import Business.HelpFunctions;
import Business.ModelBusiness.PosizioneBusiness;
import Business.ModelBusiness.ProdottoCompositoBusiness;
import DAO.Classi.PosizioneDAO;
import DAO.Classi.ProdottoDAO;
import Model.Posizione;
import Model.Prodotto;
import View.AppFrame;
import View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners.CreateCompositeProductListener;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.CompositeProductDialog;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.ImageFilter;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.ImagePreview;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class CreateCompositeProductPanel extends JPanel {

    private AppFrame appFrame;
    private CreateCompositeProductListener createCompositeProductListener;

    private JPanel formProductPanel = new JPanel();
    private JLabel inserisciNome = new JLabel("Nome");
    private JTextField nomeField = new JTextField();
    private JLabel inserisciDescrizione = new JLabel("Descrizione");
    private JTextField descrizioneField = new JTextField();
    private JLabel inserisciPrezzo = new JLabel("Prezzo                     €");
    private JTextField prezzoField = new JTextField();
    private JLabel immagineProdotto = new JLabel("Immagine");
    private JButton btnImmagine = new JButton("Scegli immagine");
    private JLabel inserisciPosizione = new JLabel("Posizione nel magazzino");
    private JComboBox<String> posizioneField;
    private JButton btnAggiungiProdotti = new JButton("Aggiungi prodotti");

    private JFileChooser fileChooser;
    private File immagine = null;

    public CreateCompositeProductPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        createCompositeProductListener = new CreateCompositeProductListener(this, this.appFrame);

        fileChooserSetting();

        //Da un array di stringhe di posizioni HelpFunctions mi dà un ComboBox pronta
        posizioneField = HelpFunctions.getFullComboBox(PosizioneBusiness.getInstance().getPosizioniDisponibili());

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

    public void aggiungiProdotti(){
        if(immagine == null || nomeField.getText().isEmpty() || descrizioneField.getText().isEmpty() || prezzoField.getText().isEmpty() || posizioneField.getSelectedItem() == null){
            JOptionPane.showMessageDialog(appFrame,
                    "La compilazione è errata!",
                    "Create Product Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            int result = ProdottoCompositoBusiness.getInstance().aggiungi(
                    appFrame, nomeField.getText(), descrizioneField.getText(),
                    prezzoField.getText(), immagine, posizioneField.getSelectedItem().toString());
            switch (result){
                case 0:
                    JOptionPane.showMessageDialog(appFrame,
                            "Prodotto Composito creato con successo!",
                            "Create Composite Product Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(appFrame,
                            "Prodotto Composito non creato!",
                            "Create Composite Product Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }
            clearText();
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
        formProductPanel.add(immagineProdotto);
        formProductPanel.add(btnImmagine);
        formProductPanel.add(inserisciPosizione);
        formProductPanel.add(posizioneField);
        formProductPanel.add(btnAggiungiProdotti);

    }

    public void componentsSizing(){

    }

    public void listenerSettings(){
        btnAggiungiProdotti.setActionCommand("aggiungiProdotti");
        btnImmagine.setActionCommand("fileChooser");

        btnAggiungiProdotti.addActionListener(createCompositeProductListener);
        btnImmagine.addActionListener(createCompositeProductListener);
    }

    public void clearText(){
        nomeField.setText("");
        descrizioneField.setText("");
        prezzoField.setText("");

        posizioneField.removeAllItems();
        //Aggiorno la combo box con le nuove prosizioni disponibili
        posizioneField = HelpFunctions.getFullComboBox(posizioneField, PosizioneBusiness.getInstance().getPosizioniDisponibili());
        immagineProdotto = null;
    }
}
