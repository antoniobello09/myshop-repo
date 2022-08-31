package View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro;

import DAO.Classi.*;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModifyProductDialog extends JDialog implements ActionListener {

    private static ModifyProductDialog dialog;
    private static Object value;        //Valore da ritornare
    private Frame appFrame;
    private Prodotto prodotto;

    private JPanel grigliaPanel = new JPanel();
        private JLabel inserisciNome = new JLabel("Nome: ");
        private JTextField nomeField = new JTextField();
        private JLabel inserisciDescrizione = new JLabel("Descrizione: ");
        private JTextField descrizioneField = new JTextField();
        private JLabel inserisciCategoria = new JLabel("Categoria: ");
        private JComboBox categoriaField = new JComboBox();
        private JLabel inserisciSottoCategoria = new JLabel("SottoCategoria: ");
        private JComboBox sottocategoriaField = new JComboBox();
        private JLabel inserisciPrezzo = new JLabel("Prezzo: ");
        private JTextField prezzoField = new JTextField();
        private JLabel inserisciProduttore = new JLabel("Produttore: ");
        private JComboBox produttoreField = new JComboBox();
        private JLabel inserisciPosizione = new JLabel("Posizione: ");
        private JComboBox posizioneField = new JComboBox();
        private JButton btnModifiche = new JButton("Salva modifiche");

    private ArrayList<CategoriaProdotto> categorieList;
    private ArrayList<CategoriaProdotto> sottocategorieList;
    private ArrayList<Posizione> posizioniList = new ArrayList<>();
    private ArrayList<Fornitore> produttoriList;

    private ModifyProductDialog(Frame frame, String title, Articolo articolo) {

        super(frame, title, true);
        appFrame = frame;
        prodotto = ProdottoDAO.getInstance().findByID(articolo.getIdArticolo());
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        nomeField.setText(prodotto.getNome());
        descrizioneField.setText(prodotto.getDescrizione());
        prezzoField.setText(String.valueOf(prodotto.getPrezzo()));

        int idCategoria = CategoriaProdottoDAO.getInstance().findByID(prodotto.getIdCategoria()).getIdCategoriaPadre();
        String nomeCategoria = CategoriaProdottoDAO.getInstance().findByID(idCategoria).getNome();

        //Creo la lista di categorie da cui scegliere
        categorieList = CategoriaProdottoDAO.getInstance().findAll();
        if(categorieList != null) {
            for (int i = 0; i < categorieList.size(); i++) {
                if (categorieList.get(i).getIdCategoriaPadre() == 0) {
                    categoriaField.addItem(categorieList.get(i).getNome());
                }
            }
        }
        categoriaField.setSelectedItem(nomeCategoria);

        sottocategorieList = CategoriaProdottoDAO.getInstance().findAllSons(idCategoria);
        if(sottocategorieList != null) {
            for (int i = 0; i < sottocategorieList.size(); i++) {
                sottocategoriaField.addItem(sottocategorieList.get(i).getNome());
            }
        }

        //Creo la lista di posizioni da cui scegliere
        posizioniList.add(PosizioneDAO.getInstance().findByID(prodotto.getIdPosizione()));
        posizioneField.addItem("" + posizioniList.get(0).getPiano() + " piano " + posizioniList.get(0).getCorsia() + " corsia "+ posizioniList.get(0).getScaffale() + " scaffale");
        posizioniList = PosizioneDAO.getInstance().findAllEmpty();
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
        produttoreField.setSelectedItem(FornitoreDAO.getInstance().findByID(prodotto.getIdProduttore()).getNome());



        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();


        pack();
        setLocationRelativeTo(null);

    }

    public static Object showDialog(JFrame appFrame, String title, Articolo articolo) {
        Frame frame = JOptionPane.getFrameForComponent(appFrame);
        dialog = new ModifyProductDialog(frame, title, articolo);
        dialog.setVisible(true);
        return null;

    }

    public void actionPerformed(ActionEvent e) {
        if ("modifica".equals(e.getActionCommand())) {
            if( nomeField.getText().equals("")||
                descrizioneField.getText().equals("")||
                prezzoField.getText().equals("")){
                JOptionPane.showMessageDialog(appFrame,
                        "Form non valido!",
                        "Modify Product Error",
                        JOptionPane.ERROR_MESSAGE);
            }else{
                String[] posizioneArray = posizioneField.getSelectedItem().toString().split(" ");
                int idCategoria = CategoriaProdottoDAO.getInstance().findByName(String.valueOf(sottocategoriaField.getSelectedItem())).getIdCategoria();
                int idProduttore = FornitoreDAO.getInstance().findByName(String.valueOf(produttoreField.getSelectedItem())).getIdFornitore();
                int idPosizione = PosizioneDAO.getInstance().findByNumbers(Integer.parseInt(posizioneArray[0]), Integer.parseInt(posizioneArray[2]), Integer.parseInt(posizioneArray[4])).getIdPosizione();
                Prodotto p = new Prodotto(
                        prodotto.getIdArticolo(),
                        nomeField.getText(),
                        descrizioneField.getText(),
                        Float.parseFloat(prezzoField.getText()),
                        idCategoria,
                        idProduttore,
                        idPosizione);
                ProdottoDAO.getInstance().update(p);
                ModifyProductDialog.dialog.setVisible(false);
            }
        } else if ("categoria".equals(e.getActionCommand())){
            int idCategoriaPadre = CategoriaProdottoDAO.getInstance().findByName(String.valueOf(categoriaField.getSelectedItem())).getIdCategoria();
            sottocategorieList = CategoriaProdottoDAO.getInstance().findAllSons(idCategoriaPadre);
            sottocategoriaField.removeAllItems();
            if(sottocategorieList != null) {
                for (int i = 0; i < sottocategorieList.size(); i++) {
                    sottocategoriaField.addItem(sottocategorieList.get(i).getNome());
                }
            }

        }

    }

    public void layoutSetting(){
        grigliaPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        grigliaPanel.setLayout(new GridLayout(0,2,20,10));
    }

    public void componentsAdding(){
        add(grigliaPanel);
            grigliaPanel.add(inserisciNome);
            grigliaPanel.add(nomeField);
            grigliaPanel.add(inserisciDescrizione);
            grigliaPanel.add(descrizioneField);
            grigliaPanel.add(inserisciCategoria);
            grigliaPanel.add(categoriaField);
            grigliaPanel.add(inserisciSottoCategoria);
            grigliaPanel.add(sottocategoriaField);
            grigliaPanel.add(inserisciPrezzo);
            grigliaPanel.add(prezzoField);
            grigliaPanel.add(inserisciProduttore);
            grigliaPanel.add(produttoreField);
            grigliaPanel.add(inserisciPosizione);
            grigliaPanel.add(posizioneField);
            grigliaPanel.add(btnModifiche);
    }

    public void componentsSizing(){

    }

    public void listenerSettings(){
        btnModifiche.addActionListener(this);
        btnModifiche.setActionCommand("modifica");

        categoriaField.addActionListener(this);
        categoriaField.setActionCommand("categoria");

    }


}