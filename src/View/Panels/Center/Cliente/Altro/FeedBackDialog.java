package View.Panels.Center.Cliente.Altro;

import DAO.Classi.CategoriaServizioDAO;
import DAO.Classi.FeedbackDAO;
import DAO.Classi.FornitoreDAO;
import DAO.Classi.ServizioDAO;
import Model.*;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.ModifyServiceDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class FeedBackDialog extends JDialog implements ActionListener {

    private static FeedBackDialog dialog;
    private static Object value;        //Valore da ritornare
    private Frame appFrame;
    private FeedBack feedBack;

    private JPanel grigliaPanel = new JPanel();
    private JLabel inserisciCommento = new JLabel("Commento: ");
    private JTextArea commentoField = new JTextArea();
    private JLabel inserisciIndiceDiGradimento = new JLabel("Indice di gradimento: ");
    private JTextField indiceDiGradimentoField = new JTextField();
    private JLabel inserisciRisposta = new JLabel("Risposta: ");
    private JTextArea rispostaField = new JTextArea();
    private JButton btnSalva = new JButton("Salva");

    private int idAcquisto;
    private int idArticolo;
    private int idFeedBack;

    private FeedBackDialog(Frame frame, String title, int idAcquisto, int idArticolo) {

        super(frame, title, true);
        appFrame = frame;

        feedBack = FeedbackDAO.getInstance().findByIDAcquisto_Articolo(idAcquisto, idArticolo);
        this.idAcquisto = idAcquisto;
        this.idArticolo = idArticolo;

        if (FeedbackDAO.getInstance().findByIDAcquisto_Articolo(idAcquisto, idArticolo) != null){
            FeedBack f = FeedbackDAO.getInstance().findByIDAcquisto_Articolo(idAcquisto, idArticolo);
            this.idFeedBack = f.getIdFeedBack();
            commentoField.setText(f.getCommento());
            indiceDiGradimentoField.setText(String.valueOf(f.getIndiceGradimento()));
            rispostaField.setText(f.getRisposta());
        }



        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();


        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }

    public static Object showDialog(JFrame appFrame, String title, int idAcquisto, int idArticolo) {
        Frame frame = JOptionPane.getFrameForComponent(appFrame);
        dialog = new FeedBackDialog(frame, title, idAcquisto, idArticolo);
        dialog.setVisible(true);
        return null;

    }

    public void actionPerformed(ActionEvent e) {
        if ("salva".equals(e.getActionCommand())) {
            if(commentoField.getText().isEmpty()||indiceDiGradimentoField.getText().isEmpty()){
                JOptionPane.showMessageDialog(appFrame,
                        "Riempi i campi!",
                        "Empty Field Error",
                        JOptionPane.ERROR_MESSAGE);
            }else{
                int indiceDiGradimento = 0;
                try {
                    indiceDiGradimento = Integer.parseInt(indiceDiGradimentoField.getText());
                    if((indiceDiGradimento<0)||(indiceDiGradimento>10)){
                        JOptionPane.showMessageDialog(appFrame,
                                "Inserire un numero da 1 a 10!",
                                "Feedback Error",
                                JOptionPane.ERROR_MESSAGE);
                    }else if (FeedbackDAO.getInstance().findByIDAcquisto_Articolo(idAcquisto, idArticolo) == null) {
                        FeedBack f = new FeedBack(idAcquisto, idArticolo, commentoField.getText(), indiceDiGradimento, Date.valueOf(LocalDate.now()));
                        FeedbackDAO.getInstance().add(f);
                        FeedBackDialog.dialog.setVisible(false);
                    }else{
                        FeedBack f = new FeedBack(idFeedBack, idAcquisto, idArticolo, commentoField.getText(), indiceDiGradimento, Date.valueOf(LocalDate.now()));
                        FeedbackDAO.getInstance().update(f);
                        FeedBackDialog.dialog.setVisible(false);
                    }
                }catch(NumberFormatException er){
                    JOptionPane.showMessageDialog(appFrame,
                            "Indice di gradimento non valido!",
                            "Feedback Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        }
    }

    public void layoutSetting(){
        grigliaPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        grigliaPanel.setLayout(new GridLayout(0,2,20,10));
        commentoField.setLineWrap(true);
        commentoField.setWrapStyleWord(true);
        rispostaField.setLineWrap(true);
        rispostaField.setWrapStyleWord(true);
        rispostaField.setEditable(false);
    }

    public void componentsAdding(){
        add(grigliaPanel);
            grigliaPanel.add(inserisciCommento);
            grigliaPanel.add(commentoField);
            grigliaPanel.add(inserisciIndiceDiGradimento);
            grigliaPanel.add(indiceDiGradimentoField);
            grigliaPanel.add(inserisciRisposta);
            grigliaPanel.add(rispostaField);
            grigliaPanel.add(btnSalva);

    }

    public void componentsSizing(){
        commentoField.setPreferredSize(new Dimension(100,50));
        indiceDiGradimentoField.setPreferredSize(new Dimension(50,50));
    }

    public void listenerSettings(){
        btnSalva.addActionListener(this);
        btnSalva.setActionCommand("salva");

    }

}
