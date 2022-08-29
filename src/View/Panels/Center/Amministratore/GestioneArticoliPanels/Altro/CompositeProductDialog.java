package View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro;

import DAO.Classi.ProdottoCompositoDAO;
import DAO.Classi.ProdottoDAO;
import Model.Prodotto;
import Model.Prodotto_Quantita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompositeProductDialog extends JDialog implements ActionListener {

    private static CompositeProductDialog dialog;
    private static Object value;        //Valore da ritornare
    private Frame appFrame;
    private int idProdottoComposito;

    private JPanel grigliaPanel = new JPanel();
    private JLabel inserisciProdotto = new JLabel("Aggiungi un prodotto");
    private JTextField prodottoField = new JTextField();
    private JLabel inserisciQuantita = new JLabel("Inserisci la quantita");
    private JTextField quantitaField = new JTextField();
    private JButton btnAggiungi = new JButton("Aggiungi");
    private JButton btnTermina = new JButton("Termina");


    private CompositeProductDialog(Frame frame, String title, int idProdottoComposito) {

        super(frame, title, true);
        appFrame = frame;
        this.idProdottoComposito = idProdottoComposito;
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();


        pack();
        setLocationRelativeTo(null);

    }

    public static Object showDialog(JFrame appFrame, String title, int idProdottoComposito) {
        Frame frame = JOptionPane.getFrameForComponent(appFrame);
        dialog = new CompositeProductDialog(frame, title, idProdottoComposito);
        dialog.setVisible(true);
        return null;

    }

    public void actionPerformed(ActionEvent e) {
        if ("aggiungi".equals(e.getActionCommand())) {
            if(prodottoField.getText().equals("")){
                JOptionPane.showMessageDialog(appFrame,
                        "Il campo è vuoto!",
                        "Add Product Error",
                        JOptionPane.ERROR_MESSAGE);
            }else{
                Prodotto p;
                p = ProdottoDAO.getInstance().findByName(prodottoField.getText());
                if(p != null){
                    Prodotto_Quantita pq = new Prodotto_Quantita(p, Integer.parseInt(quantitaField.getText()));
                    ProdottoCompositoDAO.getInstance().add(idProdottoComposito, pq);
                }else{
                    JOptionPane.showMessageDialog(appFrame,
                            "Il prodotto non esiste!",
                            "Add Product Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                clearText();
            }
        } else if ("termina".equals(e.getActionCommand())){
            CompositeProductDialog.dialog.setVisible(false);
        }

    }

    public void layoutSetting(){
        grigliaPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        grigliaPanel.setLayout(new GridLayout(0,2,20,10));
    }

    public void componentsAdding(){
        add(grigliaPanel);
        grigliaPanel.add(inserisciProdotto);
        grigliaPanel.add(prodottoField);
        grigliaPanel.add(inserisciQuantita);
        grigliaPanel.add(quantitaField);
        grigliaPanel.add(btnAggiungi);
        grigliaPanel.add(btnTermina);
    }

    public void componentsSizing(){

    }

    public void listenerSettings(){
        btnAggiungi.addActionListener(this);
        btnAggiungi.setActionCommand("aggiungi");

        btnTermina.addActionListener(this);
        btnTermina.setActionCommand("termina");
    }

    public void clearText(){
        prodottoField.setText("");
        quantitaField.setText("");
    }
}