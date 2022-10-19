package View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro;

import Business.HelpFunctions;
import Business.ModelBusiness.CategoriaBusiness;
import Business.ModelBusiness.FornitoreBusiness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ModifyServiceDialog extends JDialog implements ActionListener {

    private static ModifyServiceDialog dialog;
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
        private JLabel inserisciCategoria = new JLabel("Categoria: ");
        private JComboBox categoriaField = new JComboBox();
        private JLabel inserisciFornitore = new JLabel("Fornitore: ");
        private JComboBox fornitoreField = new JComboBox();
        private JButton btnModifiche = new JButton("Salva modifiche");

    private JFileChooser fileChooser;

    private ModifyServiceDialog(Frame frame, String title,
                                String nomeServizio, String descrizioneServizio, String prezzoServizio,
                                String nomeCategoriaServizio, File immagine, String nomeFornitoreServizio) {

        super(frame, title, true);
        appFrame = frame;

        ModifyServiceDialog.immagine = immagine;

        value = new ArrayList<>();

        nomeField.setText(nomeServizio);
        descrizioneField.setText(descrizioneServizio);
        prezzoField.setText(String.valueOf(prezzoServizio));

        categoriaField = HelpFunctions.getFullComboBox(CategoriaBusiness.getInstance().getNomiCategorieServizio());
        categoriaField.setSelectedItem(nomeCategoriaServizio);


        fornitoreField = HelpFunctions.getFullComboBox(FornitoreBusiness.getInstance().getNomiFornitoriServizi());
        fornitoreField.setSelectedItem(nomeFornitoreServizio);

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
                                    String nomeServizio, String descrizioneServizio, String prezzoServizio,
                                    String nomeCategoriaServizio, File immagine, String nomeFornitoreServizio) {
        Frame frame = JOptionPane.getFrameForComponent(appFrame);
        dialog = new ModifyServiceDialog(frame, title, nomeServizio, descrizioneServizio, prezzoServizio,
                                        nomeCategoriaServizio, immagine, nomeFornitoreServizio);
        dialog.setVisible(true);
        return value;

    }

    public void actionPerformed(ActionEvent e) {
        if ("modifica".equals(e.getActionCommand())) {
            if(nomeField.getText().equals("")||
                    descrizioneField.getText().equals("")||
                    prezzoField.getText().equals("")){
                JOptionPane.showMessageDialog(appFrame,
                        "Form non valido!",
                        "Modify Service Error",
                        JOptionPane.ERROR_MESSAGE);
            }else{
                value.add(nomeField.getText());
                value.add(descrizioneField.getText());
                value.add(prezzoField.getText());
                value.add(categoriaField.getSelectedItem().toString());
                value.add(fornitoreField.getSelectedItem().toString());
                ModifyServiceDialog.dialog.setVisible(false);
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
            grigliaPanel.add(inserisciCategoria);
            grigliaPanel.add(categoriaField);
            grigliaPanel.add(inserisciPrezzo);
            grigliaPanel.add(prezzoField);
            grigliaPanel.add(inserisciImmagine);
            grigliaPanel.add(btnImmagine);
            grigliaPanel.add(inserisciFornitore);
            grigliaPanel.add(fornitoreField);
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