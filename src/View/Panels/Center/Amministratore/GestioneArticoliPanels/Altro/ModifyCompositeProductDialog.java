package View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro;

import DAO.Classi.*;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModifyCompositeProductDialog extends JDialog implements ActionListener {

    private static ModifyCompositeProductDialog dialog;
    private static Object value;        //Valore da ritornare
    private Frame appFrame;
    private ProdottoComposito prodottoComposito;

    private JPanel grigliaPanel = new JPanel();
        private JLabel inserisciNome = new JLabel("Nome: ");
        private JTextField nomeField = new JTextField();
        private JLabel inserisciDescrizione = new JLabel("Descrizione: ");
        private JTextField descrizioneField = new JTextField();
        private JLabel inserisciPrezzo = new JLabel("Prezzo: ");
        private JTextField prezzoField = new JTextField();
        private JLabel inserisciPosizione = new JLabel("Posizione: ");
        private JComboBox posizioneField = new JComboBox();
        private JButton btnModifiche = new JButton("Salva modifiche");

    private ArrayList<Posizione> posizioniList = new ArrayList<>();

    private ModifyCompositeProductDialog(Frame frame, String title, Articolo articolo) {

        super(frame, title, true);
        appFrame = frame;


        prodottoComposito = ProdottoCompositoDAO.getInstance().findByID(articolo.getIdArticolo());
        nomeField.setText(prodottoComposito.getNome());
        descrizioneField.setText(prodottoComposito.getDescrizione());
        prezzoField.setText(String.valueOf(prodottoComposito.getPrezzo()));

        //Creo la lista di posizioni da cui scegliere
        posizioniList.add(PosizioneDAO.getInstance().findByID(prodottoComposito.getIdPosizione()));
        posizioneField.addItem("" + posizioniList.get(0).getPiano() + " piano " + posizioniList.get(0).getCorsia() + " corsia "+ posizioniList.get(0).getScaffale() + " scaffale");
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

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

    }

    public static Object showDialog(JFrame appFrame, String title, Articolo articolo) {
        Frame frame = JOptionPane.getFrameForComponent(appFrame);
        dialog = new ModifyCompositeProductDialog(frame, title, articolo);
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
                        "Modify Composite Product Error",
                        JOptionPane.ERROR_MESSAGE);
            }else{
                String[] posizioneArray = posizioneField.getSelectedItem().toString().split(" ");
                int idPosizione = PosizioneDAO.getInstance().findByNumbers(Integer.parseInt(posizioneArray[0]), Integer.parseInt(posizioneArray[2]), Integer.parseInt(posizioneArray[4])).getIdPosizione();
                ProdottoComposito p = new ProdottoComposito(
                        prodottoComposito.getIdArticolo(),
                        nomeField.getText(),
                        descrizioneField.getText(),
                        Float.parseFloat(prezzoField.getText()),
                        idPosizione);
                ProdottoDAO.getInstance().update(p);
                ModifyCompositeProductDialog.dialog.setVisible(false);
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
            grigliaPanel.add(inserisciPrezzo);
            grigliaPanel.add(prezzoField);
            grigliaPanel.add(inserisciPosizione);
            grigliaPanel.add(posizioneField);
            grigliaPanel.add(btnModifiche);
    }

    public void componentsSizing(){

    }

    public void listenerSettings(){
        btnModifiche.addActionListener(this);
        btnModifiche.setActionCommand("modifica");

    }

}