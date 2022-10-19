package View.Panels.Center.Amministratore.GestioneArticoliPanels;

import Business.HelpFunctions;
import Business.ModelBusiness.CategoriaBusiness;
import Business.ModelBusiness.FornitoreBusiness;
import Business.ModelBusiness.PosizioneBusiness;
import Business.ModelBusiness.ProdottoBusiness;
import View.AppFrame;
import View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners.CreateProductListener;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.ImageFilter;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.ImagePreview;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;


public class CreateProductPanel extends JPanel {

    private CreateProductListener createProductListener;
    private AppFrame appFrame;

    private JPanel formProductPanel = new JPanel();
        private JLabel inserisciNome = new JLabel("Nome");
        private JTextField nomeField = new JTextField();
        private JLabel inserisciDescrizione = new JLabel("Descrizione");
        private JTextField descrizioneField = new JTextField();
        private JLabel inserisciPrezzo = new JLabel("Prezzo                         €");
        private JTextField prezzoField = new JTextField();
        private JLabel immagineProdotto = new JLabel("Immagine");
        private JButton btnImmagine = new JButton("Scegli immagine");
        private JLabel inserisciCategoria = new JLabel("Categoria");
        private JComboBox<String> categoriaField;
        private JLabel inserisciSottoCategoria = new JLabel("Sottocategoria");
        private JComboBox<String> sottocategoriaField;
        private JLabel inserisciProduttore = new JLabel("Produttore");
        private JComboBox<String> produttoreField;
        private JLabel inserisciPosizione = new JLabel("Posizione nel magazzino");
        private JComboBox<String> posizioneField;
        private JButton btnInvia = new JButton("Invia");


    private JFileChooser fileChooser;
    private File immagine = null;
    private String selectedItem;

    public CreateProductPanel(AppFrame appFrame){

        this.appFrame = appFrame;
        createProductListener = new CreateProductListener(this, this.appFrame);

        sottocategoriaField = new JComboBox<>();
        categoriaField = new JComboBox<>();
        posizioneField = new JComboBox<>();
        produttoreField = new JComboBox<>();

        //Creo la lista di categorie da cui scegliere
        categoriaField = HelpFunctions.getFullComboBox(CategoriaBusiness.getInstance().getNomiCategorieProdotto());

        //Creo la lista di posizioni da cui scegliere
        posizioneField = HelpFunctions.getFullComboBox(PosizioneBusiness.getInstance().getPosizioniDisponibili());

        //Creo la lista di produttori da cui scegliere
        produttoreField = HelpFunctions.getFullComboBox(FornitoreBusiness.getInstance().getNomiProduttori());

        fileChooserSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }


    //Imposta le sottocategoria nel secondo menù a tendina in base alla categoria scelta
    public void setComboBoxSottoCategorie(){
        selectedItem = categoriaField.getSelectedItem().toString();
        sottocategoriaField.removeAllItems();
        sottocategoriaField = HelpFunctions.getFullComboBox(sottocategoriaField, CategoriaBusiness.getInstance().getNomiSottoCategorieProdotto(selectedItem));
        sottocategoriaField.setEnabled(true);
    }

    public void invia(){
        if(immagine == null || nomeField.getText().isEmpty() || descrizioneField.getText().isEmpty() || prezzoField.getText().isEmpty() || sottocategoriaField.getSelectedItem() == null || produttoreField.getSelectedItem() == null || posizioneField.getSelectedItem() == null){
            JOptionPane.showMessageDialog(appFrame,
                    "La compilazione è errata!",
                    "Create Product Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{

            int result = ProdottoBusiness.getInstance().aggiungi(
                    nomeField.getText(), descrizioneField.getText(), prezzoField.getText(),
                    sottocategoriaField.getSelectedItem().toString(), immagine, produttoreField.getSelectedItem().toString(),
                    posizioneField.getSelectedItem().toString());

            switch (result){
                case 0:
                    JOptionPane.showMessageDialog(appFrame,
                            "Prodotto creato con successo",
                            "Create Product Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(appFrame,
                            "Prodotto non inserito!",
                            "Create Product Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
        nomeField.setText("");
        descrizioneField.setText("");
        prezzoField.setText("");
        posizioneField.removeAllItems();
        posizioneField = HelpFunctions.getFullComboBox(posizioneField, PosizioneBusiness.getInstance().getPosizioniDisponibili());
    }

    public void scegliImmagine(){
        int returnVal = fileChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            immagine = fileChooser.getSelectedFile();
        }
    }

//--------------------------------------------------------------------------------------------------


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
        formProductPanel.add(inserisciSottoCategoria);
        formProductPanel.add(sottocategoriaField);
        formProductPanel.add(immagineProdotto);
        formProductPanel.add(btnImmagine);
        formProductPanel.add(inserisciProduttore);
        formProductPanel.add(produttoreField);
        formProductPanel.add(inserisciPosizione);
        formProductPanel.add(posizioneField);
        formProductPanel.add(btnInvia);

        sottocategoriaField.setEnabled(false);

    }

    public void componentsSizing(){

    }

    public void listenerSettings(){
        btnInvia.setActionCommand("invia");
        categoriaField.setActionCommand("categoria");
        btnImmagine.setActionCommand("fileChooser");

        btnInvia.addActionListener(createProductListener);
        categoriaField.addActionListener(createProductListener);
        btnImmagine.addActionListener(createProductListener);
    }
}
