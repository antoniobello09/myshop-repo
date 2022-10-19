package View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro;

import Business.HelpFunctions;
import Business.ModelBusiness.PosizioneBusiness;
import DAO.Classi.*;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ModifyCompositeProductDialog extends JDialog implements ActionListener {

    private static ModifyCompositeProductDialog dialog;
    private static ArrayList<String> value;        //Valore da ritornare
    private static File immagine;
    private Frame appFrame;

    private JPanel grigliaPanel = new JPanel();
        private JLabel inserisciNome = new JLabel("Nome: ");
        private JTextField nomeField = new JTextField();
        private JLabel inserisciDescrizione = new JLabel("Descrizione: ");
        private JTextField descrizioneField = new JTextField();
        private JLabel inserisciPrezzo = new JLabel("Prezzo: ");
        private JTextField prezzoField = new JTextField();
        private JLabel inserisciImmagine = new JLabel("Immagine: ");
        private JButton btnImmagine = new JButton("Cambia immagine");
        private JLabel inserisciPosizione = new JLabel("Posizione: ");
        private JComboBox posizioneField;
        private JButton btnModifiche = new JButton("Salva modifiche");

    private JFileChooser fileChooser;

    private ModifyCompositeProductDialog(Frame frame, String title,
                                         String nomeProdottoComposito, String descrizioneProdottoComposito, String prezzoProdottoComposito, File immagine, String nomePosizione) {

        super(frame, title, true);
        appFrame = frame;

        ModifyCompositeProductDialog.immagine = immagine;

        value = new ArrayList<>();

        nomeField.setText(nomeProdottoComposito);
        descrizioneField.setText(descrizioneProdottoComposito);
        prezzoField.setText(prezzoProdottoComposito);

        //Creo la lista di posizioni da cui scegliere
        posizioneField = HelpFunctions.getFullComboBox(PosizioneBusiness.getInstance().getPosizioniDisponibili());
        posizioneField.addItem(nomePosizione);
        posizioneField.setSelectedItem(nomePosizione);

        fileChooserSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

    }

    public static ArrayList<String> showDialog(JFrame appFrame, String title,
                                    String nomeProdottoComposito, String descrizioneProdottoComposito, String prezzoProdottoComposito, File immagine, String nomePosizione) {
        Frame frame = JOptionPane.getFrameForComponent(appFrame);
        dialog = new ModifyCompositeProductDialog(frame, title, nomeProdottoComposito, descrizioneProdottoComposito, prezzoProdottoComposito, immagine, nomePosizione);
        dialog.setVisible(true);
        return value;

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
                value.add(nomeField.getText());
                value.add(descrizioneField.getText());
                value.add(prezzoField.getText());
                value.add(posizioneField.getSelectedItem().toString());
                ModifyCompositeProductDialog.dialog.setVisible(false);
            }
        }else if("fileChooser".equals(e.getActionCommand())){

            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                immagine = fileChooser.getSelectedFile();
            }
        }
    }

    public static File getImmagine(){
        return immagine;
    }

    public void fileChooserSetting(){
        fileChooser = new JFileChooser();

        fileChooser.addChoosableFileFilter(new ImageFilter());
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setAccessory(new ImagePreview(fileChooser));
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
            grigliaPanel.add(inserisciImmagine);
            grigliaPanel.add(btnImmagine);
            grigliaPanel.add(inserisciPosizione);
            grigliaPanel.add(posizioneField);
            grigliaPanel.add(btnModifiche);
    }

    public void componentsSizing(){

    }

    public void listenerSettings(){
        btnModifiche.addActionListener(this);
        btnModifiche.setActionCommand("modifica");

        btnImmagine.addActionListener(this);
        btnImmagine.setActionCommand("fileChooser");
    }

}