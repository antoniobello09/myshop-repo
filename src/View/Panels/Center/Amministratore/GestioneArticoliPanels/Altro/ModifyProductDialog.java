package View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro;

import Business.HelpFunctions;
import Business.ModelBusiness.CategoriaBusiness;
import Business.ModelBusiness.FornitoreBusiness;
import Business.ModelBusiness.PosizioneBusiness;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ModifyProductDialog extends JDialog implements ActionListener {

    private static ModifyProductDialog dialog;
    private static ArrayList<String> value;        //Valore da ritornare
    private static File immagine;
    private Frame appFrame;

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
        private JLabel inserisciImmagine = new JLabel("Immagine: ");
        private JButton btnImmagine = new JButton("Cambia immagine");
        private JLabel inserisciProduttore = new JLabel("Produttore: ");
        private JComboBox produttoreField = new JComboBox();
        private JLabel inserisciPosizione = new JLabel("Posizione: ");
        private JComboBox posizioneField = new JComboBox();
        private JButton btnModifiche = new JButton("Salva modifiche");

    private JFileChooser fileChooser;



    private ModifyProductDialog(Frame frame, String title,
                                String nomeProdotto, String descrizioneProdotto, String prezzoProdotto,
                                String nomeCategoria,String nomeSottoCategoria, File immagine, String nomeProduttore, String nomePosizione) {

        super(frame, title, true);
        appFrame = frame;
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        ModifyProductDialog.immagine = immagine;

        value = new ArrayList<>();

        nomeField.setText(nomeProdotto);
        descrizioneField.setText(descrizioneProdotto);
        prezzoField.setText(String.valueOf(prezzoProdotto));

        categoriaField = HelpFunctions.getFullComboBox(CategoriaBusiness.getInstance().getNomiCategorieProdotto());
        categoriaField.setSelectedItem(nomeCategoria);

        sottocategoriaField = HelpFunctions.getFullComboBox(sottocategoriaField, CategoriaBusiness.getInstance().getNomiSottoCategorieProdotto(nomeCategoria));
        sottocategoriaField.setSelectedItem(nomeSottoCategoria);

        posizioneField = HelpFunctions.getFullComboBox(PosizioneBusiness.getInstance().getPosizioniDisponibili());
        posizioneField.addItem(nomePosizione);
        posizioneField.setSelectedItem(nomePosizione);

        produttoreField = HelpFunctions.getFullComboBox(FornitoreBusiness.getInstance().getNomiProduttori());
        produttoreField.setSelectedItem(nomeProduttore);

        fileChooserSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();


        pack();
        setLocationRelativeTo(null);

    }

    public static ArrayList<String> showDialog(JFrame appFrame, String title,
                                    String nomeProdotto, String descrizioneProdotto, String prezzoProdotto,
                                    String nomeCategoria,String nomeSottoCategoria, File immagine, String nomeProduttore, String nomePosizione) {
        Frame frame = JOptionPane.getFrameForComponent(appFrame);
        dialog = new ModifyProductDialog(frame, title, nomeProdotto, descrizioneProdotto, prezzoProdotto, nomeCategoria, nomeSottoCategoria, immagine, nomeProduttore, nomePosizione);
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
                        "Modify Product Error",
                        JOptionPane.ERROR_MESSAGE);
            }else{
                value.add(nomeField.getText());
                value.add(descrizioneField.getText());
                value.add(prezzoField.getText());
                value.add(sottocategoriaField.getSelectedItem().toString());
                value.add(produttoreField.getSelectedItem().toString());
                value.add(posizioneField.getSelectedItem().toString());
                ModifyProductDialog.dialog.setVisible(false);
            }
        } else if ("categoria".equals(e.getActionCommand())){
            String selectedItem = categoriaField.getSelectedItem().toString();
            sottocategoriaField.removeAllItems();
            sottocategoriaField = HelpFunctions.getFullComboBox(sottocategoriaField, CategoriaBusiness.getInstance().getNomiSottoCategorieProdotto(selectedItem));
        } else if("fileChooser".equals(e.getActionCommand())){

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
            grigliaPanel.add(inserisciCategoria);
            grigliaPanel.add(categoriaField);
            grigliaPanel.add(inserisciSottoCategoria);
            grigliaPanel.add(sottocategoriaField);
            grigliaPanel.add(inserisciPrezzo);
            grigliaPanel.add(prezzoField);
            grigliaPanel.add(inserisciImmagine);
            grigliaPanel.add(btnImmagine);
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

        btnImmagine.addActionListener(this);
        btnImmagine.setActionCommand("fileChooser");

        categoriaField.addActionListener(this);
        categoriaField.setActionCommand("categoria");

    }


}