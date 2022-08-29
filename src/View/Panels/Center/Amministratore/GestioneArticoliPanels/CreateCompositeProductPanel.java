package View.Panels.Center.Amministratore.GestioneArticoliPanels;

import DAO.Classi.PosizioneDAO;
import DAO.Classi.ProdottoDAO;
import Model.Posizione;
import Model.Prodotto;
import View.AppFrame;
import View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners.CreateCompositeProductListener;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.CompositeProductDialog;

import javax.swing.*;
import java.awt.*;
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
    private JLabel inserisciPosizione = new JLabel("Posizione nel magazzino");
    private JComboBox<String> posizioneField;
    private JButton btnAggiungiProdotti = new JButton("Aggiungi prodotti");

    private ArrayList<Posizione> posizioniList;             //Lista di posizioni

    public CreateCompositeProductPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        createCompositeProductListener = new CreateCompositeProductListener(this, this.appFrame);


        posizioneField = new JComboBox<>();
        //Creo la lista di posizioni da cui scegliere
        posizioniList = PosizioneDAO.getInstance().findAllEmpty();
        if(posizioniList != null) {
            for (int i = 0; i < posizioniList.size(); i++) {
                posizioneField.addItem("" + posizioniList.get(i).getPiano() + " piano " + posizioniList.get(i).getCorsia() + " corsia " + posizioniList.get(i).getScaffale() + " scaffale");
            }
        }

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();


    }

    public void aggiungiProdotti(){
        if(nomeField.getText().isEmpty() || descrizioneField.getText().isEmpty() || prezzoField.getText().isEmpty() || posizioneField.getSelectedItem() == null){
            JOptionPane.showMessageDialog(appFrame,
                    "La compilazione è errata!",
                    "Create Product Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }else{
            String[] posizioneArray = posizioneField.getSelectedItem().toString().split(" ");
            int idPosizione = PosizioneDAO.getInstance().findByNumbers(Integer.parseInt(posizioneArray[0]),Integer.parseInt(posizioneArray[2]),Integer.parseInt(posizioneArray[4])).getIdPosizione();
            Prodotto prodotto = new Prodotto(nomeField.getText(), descrizioneField.getText(), Float.parseFloat(prezzoField.getText()), 0,0, idPosizione);

            ProdottoDAO.getInstance().add(prodotto);
            if(ProdottoDAO.getInstance().findByName(prodotto.getNome())!=null) {
                CompositeProductDialog.showDialog(appFrame, "Aggiungi prodotto", ProdottoDAO.getInstance().findByName(prodotto.getNome()).getIdArticolo());
            }
            clearText();
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
        formProductPanel.add(inserisciPosizione);
        formProductPanel.add(posizioneField);
        formProductPanel.add(btnAggiungiProdotti);

    }

    public void componentsSizing(){

    }

    public void listenerSettings(){
        btnAggiungiProdotti.setActionCommand("aggiungiProdotti");

        btnAggiungiProdotti.addActionListener(createCompositeProductListener);
    }

    public void clearText(){
        nomeField.setText("");
        descrizioneField.setText("");
        prezzoField.setText("");

        posizioniList = PosizioneDAO.getInstance().findAllEmpty();
        posizioneField.removeAllItems();
        if(posizioniList != null) {
            for (int i = 0; i < posizioniList.size(); i++) {
                posizioneField.addItem("" + posizioniList.get(i).getPiano() + " piano " + posizioniList.get(i).getCorsia() + " corsia " + posizioniList.get(i).getScaffale() + " scaffale");
            }
        }
    }
}
