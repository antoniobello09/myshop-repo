package View.Center.Amministratore.GestioneProdottiPanels.Product;

import Business.HelpFunctions;
import DAO.Classi.CategoriaProdottoDAO;
import DAO.Classi.ProdottoDAO;
import DAO.Classi.ProduttoreDAO;
import Model.CategoriaProdotto;
import Model.Prodotto;
import Model.Produttore;
import View.AppFrame;
import View.Listener.Amministratore.GestioneProdottiListeners.Product.AddProductListener;
import View.Nameable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class AddProductPanel extends JPanel {

    private AddProductListener addProductListener;
    private AppFrame appFrame;

    private JPanel containerPanel = new JPanel();
        private JPanel formProductPanel = new JPanel();
            private JLabel inserisciNome = new JLabel("Nome del prodotto");
            private JTextField nomeField = new JTextField();
            private JLabel inserisciDescrizione = new JLabel("Descrizione del prodotto");
            private JTextField descrizioneField = new JTextField();
            private JLabel inserisciPrezzo = new JLabel("Prezzo del prodotto                     €");
            private JTextField prezzoField = new JTextField();
            private JLabel immagineProdotto = new JLabel("Immagine del prodotto");
            private JTextField immagineField = new JTextField();
            private JLabel inserisciCategoria = new JLabel("Categoria principale del prodotto");
            private JComboBox<String> categoriaField;
            private JLabel inserisciSottoCategoria = new JLabel("Sottocategoria del prodotto");
            private JComboBox<String> sottocategoriaField;
            private JButton btnProduttoreEsiste = new JButton("Produttore già esistente");
            private JButton btnProduttoreNuovo = new JButton("Produttore nuovo");
        private JPanel formatPanel = new JPanel();  //Serve per formattare la parte sud del pannello
            private JPanel addProductorPanel = new JPanel();
                private JLabel inserisciNomeProduttore = new JLabel("Nome del produttore");
                private JTextField nomeProduttoreField = new JTextField();
                private JLabel inserisciSitoweb = new JLabel("Sito web del produttore");
                private JTextField sitowebField = new JTextField();
                private JLabel inserisciCittaProduttore = new JLabel("Citta del produttore");
                private JTextField cittaField = new JTextField();
                private JLabel inserisciNazione = new JLabel("Nazione del produttore");
                private JTextField nazioneField = new JTextField();
                private JButton invia = new JButton("Invia");
            private JPanel getProductorPanel = new JPanel();
                private JLabel inserisciNomeProduttore2 = new JLabel("Nome del produttore");
                private JTextField nomeProduttoreField2 = new JTextField();
                private JButton invia2 = new JButton("Invia");

    private ArrayList<Nameable> comboList;
    private String selectedItem;
    private ArrayList<CategoriaProdotto> cList;     //Lista di categorie
    private Prodotto outputProdotto;

    private Dimension dContainerPanel = new Dimension(500,450);
    private Dimension dProductorPanel = new Dimension(500,70);

    public AddProductPanel(AppFrame appFrame){

        this.appFrame = appFrame;
        addProductListener = new AddProductListener(this, this.appFrame);

        cList = CategoriaProdottoDAO.getInstance().findAll();
        comboList = (ArrayList<Nameable>)cList.clone();
        categoriaField = new JComboBox<>(HelpFunctions.fromArraytoString(comboList));
        sottocategoriaField = new JComboBox<>();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }


    //Imposta le sottocategoria nel secondo menù a tendina in base alla categoria scelta
    public void setComboBoxSottoCategorie(){
        selectedItem = categoriaField.getSelectedItem().toString();
        if(selectedItem == "--"){
            return;
        }
        sottocategoriaField.removeAllItems();
        comboList = (ArrayList<Nameable>)getArraySottoCategorie(selectedItem).clone();
        HelpFunctions.setComboBox(sottocategoriaField, HelpFunctions.fromArraytoString(comboList));
        sottocategoriaField.setEnabled(true);
    }

    //Imposta il pannello per inserire un produttore già esistente
    public void setProduttoreEsistePanel(){
        formatPanel.remove(addProductorPanel);
        formatPanel.add(getProductorPanel, BorderLayout.NORTH);
        invalidate();
        validate();
        repaint();
    }

    //Imposta il pannello per inserire un produttore nuovo
    public void setProduttoreNuovoPanel(){
        formatPanel.remove(getProductorPanel);
        formatPanel.add(addProductorPanel, BorderLayout.NORTH);
        invalidate();
        validate();
        repaint();
    }

    public void inviaN(){
        outputProdotto = getFormData2();
        ProduttoreDAO.getInstance().add(outputProdotto.getProduttore());
        ProdottoDAO.getInstance().add(outputProdotto);
    }

    public void inviaE(){
        outputProdotto = getFormData();
        ProdottoDAO.getInstance().add(outputProdotto);
    }
    

//-------------------------------------------------------------------------------------------------//
    //Form con produttore già esistente
    public Prodotto getFormData(){
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(nomeField.getText());
        prodotto.setCategoria(new CategoriaProdotto(categoriaField.getSelectedItem().toString(),sottocategoriaField.getSelectedItem().toString()));
        prodotto.setDescrizione(descrizioneField.getText().replace("\'","\\\'"));
        prodotto.setPrezzo(Float.parseFloat(prezzoField.getText()));
        prodotto.setProduttore(new Produttore(nomeProduttoreField2.getText()));
        return prodotto;
    }

    //Form con produttore nuovo
    public Prodotto getFormData2(){
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(nomeField.getText());
        prodotto.setCategoria(new CategoriaProdotto(categoriaField.getSelectedItem().toString(),sottocategoriaField.getSelectedItem().toString()));
        prodotto.setDescrizione(descrizioneField.getText().replace("\'","\\\'"));
        prodotto.setPrezzo(Float.parseFloat(prezzoField.getText()));
        prodotto.setProduttore(new Produttore(nomeProduttoreField.getText(),sitowebField.getText(),cittaField.getText(),nazioneField.getText()));
        return prodotto;
    }

//--------------------------------------------------------------------------------------------------
    //Restituisce un array di sottocategorie dato il nome di una categoria
    public ArrayList<CategoriaProdotto> getArraySottoCategorie(String nomeCategoria){
        ArrayList<CategoriaProdotto> scList = new ArrayList<>();    //array di sottocategorie

        for(int i=0;i<cList.size();i++){
            if(cList.get(i).getNome().equals(nomeCategoria))
                scList.addAll(cList.get(i).getSottocategorie());
        }

        return scList;
    }


    public void layoutSetting(){
        setLayout(new FlowLayout());
            containerPanel.setLayout(new BorderLayout());
                formProductPanel.setLayout(new GridLayout(0,2,0,10));
                formatPanel.setLayout(new BorderLayout());
                addProductorPanel.setLayout(new GridLayout(0,2,0,10));
                getProductorPanel.setLayout(new GridLayout(0,2,0,10));
    }

    public void componentsAdding(){
        add(containerPanel);
        containerPanel.add(formProductPanel, BorderLayout.NORTH);
        formProductPanel.add(inserisciNome);
        formProductPanel.add(nomeField);
        formProductPanel.add(inserisciCategoria);
        formProductPanel.add(categoriaField);
        formProductPanel.add(inserisciSottoCategoria);
        formProductPanel.add(sottocategoriaField);
        sottocategoriaField.setEnabled(false);
        formProductPanel.add(inserisciDescrizione);
        formProductPanel.add(descrizioneField);
        formProductPanel.add(inserisciPrezzo);
        formProductPanel.add(prezzoField);
        formProductPanel.add(btnProduttoreEsiste);
        formProductPanel.add(btnProduttoreNuovo);
        containerPanel.add(formatPanel, BorderLayout.CENTER);
        formatPanel.add(getProductorPanel, BorderLayout.NORTH);
        getProductorPanel.add(inserisciNomeProduttore2);
        getProductorPanel.add(nomeProduttoreField2);
        getProductorPanel.add(invia2);

        addProductorPanel.add(inserisciNomeProduttore);
        addProductorPanel.add(nomeProduttoreField);
        addProductorPanel.add(inserisciSitoweb);
        addProductorPanel.add(sitowebField);
        addProductorPanel.add(inserisciCittaProduttore);
        addProductorPanel.add(cittaField);
        addProductorPanel.add(inserisciNazione);
        addProductorPanel.add(nazioneField);
        addProductorPanel.add(invia);
    }

    public void componentsSizing(){
        containerPanel.setPreferredSize(dContainerPanel);
        getProductorPanel.setPreferredSize(dProductorPanel);
    }

    public void listenerSettings(){
        categoriaField.addActionListener(addProductListener);
        btnProduttoreNuovo.addActionListener(addProductListener);
        btnProduttoreEsiste.addActionListener(addProductListener);
        invia.addActionListener(addProductListener);
        invia2.addActionListener(addProductListener);

        categoriaField.setActionCommand("categoria");
        btnProduttoreEsiste.setActionCommand("produttoreEsiste");
        btnProduttoreNuovo.setActionCommand("produttoreNuovo");
        invia.setActionCommand("inviaN");//Invia con nuovo produttore
        invia2.setActionCommand("inviaE");//Invia con produttore già esistente
    }
}
