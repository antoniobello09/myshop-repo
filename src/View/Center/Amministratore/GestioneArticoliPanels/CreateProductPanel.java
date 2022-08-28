package View.Center.Amministratore.GestioneArticoliPanels;

import Business.HelpFunctions;
import DAO.Classi.CategoriaProdottoDAO;
import DAO.Classi.PosizioneDAO;
import DAO.Classi.ProdottoDAO;
import DAO.Classi.FornitoreDAO;
import Model.CategoriaProdotto;
import Model.Fornitore;
import Model.Posizione;
import Model.Prodotto;
import View.AppFrame;
import View.Listener.Amministratore.CreateProductListener;
import View.Listener.Amministratore.GestioneArticoliListeners.Product.AddProductListener;
import View.Nameable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class CreateProductPanel extends JPanel {

    private CreateProductListener createProductListener;
    private AppFrame appFrame;

    private JPanel formProductPanel = new JPanel();
        private JLabel labelTitle = new JLabel("Crea un prodotto");
        private JLabel inserisciNome = new JLabel("Nome");
        private JTextField nomeField = new JTextField();
        private JLabel inserisciDescrizione = new JLabel("Descrizione");
        private JTextField descrizioneField = new JTextField();
        private JLabel inserisciPrezzo = new JLabel("Prezzo                     €");
        private JTextField prezzoField = new JTextField();
        private JLabel immagineProdotto = new JLabel("Immagine");
        private JTextField immagineField = new JTextField();
        private JLabel inserisciCategoria = new JLabel("Categoria");
        private JComboBox<String> categoriaField;
        private JLabel inserisciSottoCategoria = new JLabel("Sottocategoria");
        private JComboBox<String> sottocategoriaField;
        private JLabel inserisciProduttore = new JLabel("Produttore");
        private JComboBox<String> produttoreField;
        private JLabel inserisciPosizione = new JLabel("Posizione nel magazzino");
        private JComboBox<String> posizioneField;
        private JButton btnInvia = new JButton("Invia");


    private ArrayList<Nameable> comboList;
    private String selectedItem;
    private ArrayList<CategoriaProdotto> categorieList;     //Lista di categorie
    private ArrayList<Posizione> posizioniList;             //Lista di posizioni
    private ArrayList<Fornitore> produttoriList;            //Lista di produttori

    private Prodotto outputProdotto;


    public CreateProductPanel(AppFrame appFrame){

        this.appFrame = appFrame;
        createProductListener = new CreateProductListener(this, this.appFrame);

        categoriaField = new JComboBox<>();
        sottocategoriaField = new JComboBox<>();

        //Creo la lista di categorie da cui scegliere
        categorieList = CategoriaProdottoDAO.getInstance().findAll();
        if(categorieList != null) {
            for (int i = 0; i < categorieList.size(); i++) {
                if (categorieList.get(i).getIdCategoriaPadre() == 0) {
                    categoriaField.addItem(categorieList.get(i).getNome());
                }
            }
        }

        //Creo la lista di posizioni da cui scegliere
        posizioniList = PosizioneDAO.getInstance().findAll();
        if(posizioniList != null) {
            for (int i = 0; i < posizioniList.size(); i++) {
                posizioneField.addItem("" + posizioniList.get(i).getPiano() + " piano " + posizioniList.get(i).getCorsia() + " corsia " + posizioniList.get(i).getScaffale() + " scaffale");
            }
        }

        //Creo la lista di produttori da cui scegliere
        produttoriList = FornitoreDAO.getInstance().findAllProd();
        if(produttoriList != null) {
            for (int i = 0; i < produttoriList.size(); i++) {
                produttoreField.addItem(produttoriList.get(i).getNome());
            }
        }

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }


    //Imposta le sottocategoria nel secondo menù a tendina in base alla categoria scelta
    public void setComboBoxSottoCategorie(){
        selectedItem = categoriaField.getSelectedItem().toString();
        sottocategoriaField.removeAllItems();
        ArrayList<CategoriaProdotto> sottocategorieList = CategoriaProdottoDAO.getInstance().findAllSons(CategoriaProdottoDAO.getInstance().findByName(selectedItem).getIdCategoria());
        if(sottocategorieList != null) {
            for (int i = 0; i < sottocategorieList.size(); i++) {
                sottocategoriaField.addItem(sottocategorieList.get(i).getNome());
            }
        }
        sottocategoriaField.setEnabled(true);
    }

    public void invia(){
        if(nomeField.getText().isEmpty() || descrizioneField.getText().isEmpty() || prezzoField.getText().isEmpty() || sottocategoriaField.getSelectedItem() == null || produttoreField.getSelectedItem() == null || posizioneField.getSelectedItem() == null){
            JOptionPane.showMessageDialog(appFrame,
                    "La compilazione è errata!",
                    "Create Product Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }else{
            String[] posizioneArray = posizioneField.getSelectedItem().toString().split(" ");
            int idCategoria = CategoriaProdottoDAO.getInstance().findByName(sottocategoriaField.getSelectedItem().toString()).getIdCategoria();
            int idProduttore = FornitoreDAO.getInstance().findByName(produttoreField.getSelectedItem().toString()).getIdFornitore();
            int idPosizione = PosizioneDAO.getInstance().findByNumbers(Integer.parseInt(posizioneArray[0]),Integer.parseInt(posizioneArray[1]),Integer.parseInt(posizioneArray[2])).getIdPosizione();
            Prodotto prodotto = new Prodotto(nomeField.getText(), descrizioneField.getText(), Float.parseFloat(prezzoField.getText()), idCategoria, idProduttore, idPosizione);
            ProdottoDAO.getInstance().add(prodotto);
        }
    }

//--------------------------------------------------------------------------------------------------


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

        btnInvia.addActionListener(createProductListener);
        categoriaField.addActionListener(createProductListener);
    }
}
