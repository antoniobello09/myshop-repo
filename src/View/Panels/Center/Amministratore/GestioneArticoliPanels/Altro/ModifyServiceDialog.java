package View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro;

import DAO.Classi.*;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModifyServiceDialog extends JDialog implements ActionListener {

    private static ModifyServiceDialog dialog;
    private static Object value;        //Valore da ritornare
    private Frame appFrame;
    private Servizio servizio;

    private JPanel grigliaPanel = new JPanel();
        private JLabel inserisciNome = new JLabel("Nome: ");
        private JTextField nomeField = new JTextField();
        private JLabel inserisciDescrizione = new JLabel("Descrizione: ");
        private JTextField descrizioneField = new JTextField();
        private JLabel inserisciPrezzo = new JLabel("Prezzo: ");
        private JTextField prezzoField = new JTextField();
        private JLabel inserisciCategoria = new JLabel("Categoria: ");
        private JComboBox categoriaField = new JComboBox();
        private JLabel inserisciFornitore = new JLabel("Fornitore: ");
        private JComboBox fornitoreField = new JComboBox();
        private JButton btnModifiche = new JButton("Salva modifiche");

    private ArrayList<CategoriaServizio> categorieList;
    private ArrayList<Fornitore> fornitoriList;


    private ModifyServiceDialog(Frame frame, String title, Articolo articolo) {

        super(frame, title, true);
        appFrame = frame;
        servizio = ServizioDAO.getInstance().findByID(articolo.getIdArticolo());


        nomeField.setText(servizio.getNome());
        descrizioneField.setText(servizio.getDescrizione());
        prezzoField.setText(String.valueOf(servizio.getPrezzo()));

        int idCategoria = CategoriaServizioDAO.getInstance().findByID(servizio.getIdCategoria()).getIdCategoria();
        String nomeCategoria = CategoriaServizioDAO.getInstance().findByID(idCategoria).getNome();

        //Creo la lista di categorie da cui scegliere
        categorieList = CategoriaServizioDAO.getInstance().findAll();
        if(categorieList != null) {
            for (int i = 0; i < categorieList.size(); i++) {
                categoriaField.addItem(categorieList.get(i).getNome());
            }
        }
        categoriaField.setSelectedItem(nomeCategoria);



        //Creo la lista di produttori da cui scegliere
        fornitoriList = FornitoreDAO.getInstance().findAllServ();
        if(fornitoriList != null) {
            for (int i = 0; i < fornitoriList.size(); i++) {
                fornitoreField.addItem(fornitoriList.get(i).getNome());
            }
        }
        fornitoreField.setSelectedItem(FornitoreDAO.getInstance().findByID(servizio.getIdFornitoreServizio()).getNome());



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
        dialog = new ModifyServiceDialog(frame, title, articolo);
        dialog.setVisible(true);
        return null;

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
                int idCategoria = CategoriaServizioDAO.getInstance().findByName(String.valueOf(categoriaField.getSelectedItem())).getIdCategoria();
                int idFornitore = FornitoreDAO.getInstance().findByName(String.valueOf(fornitoreField.getSelectedItem())).getIdFornitore();
                Servizio s = new Servizio(
                        servizio.getIdArticolo(),
                        nomeField.getText(),
                        descrizioneField.getText(),
                        Float.parseFloat(prezzoField.getText()),
                        idCategoria,
                        idFornitore);
                ServizioDAO.getInstance().update(s);
                ModifyServiceDialog.dialog.setVisible(false);
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
            grigliaPanel.add(inserisciPrezzo);
            grigliaPanel.add(prezzoField);
            grigliaPanel.add(inserisciFornitore);
            grigliaPanel.add(fornitoreField);
            grigliaPanel.add(btnModifiche);
    }

    public void componentsSizing(){

    }

    public void listenerSettings(){
        btnModifiche.addActionListener(this);
        btnModifiche.setActionCommand("modifica");

    }

}