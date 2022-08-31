package View.Panels.Center.Manager.Altro;

import DAO.Classi.*;
import Model.*;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.ModifyProductDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FeedBackDialog extends JDialog implements ActionListener {

    private static FeedBackDialog dialog;
    private static Object value;        //Valore da ritornare
    private Frame appFrame;
    private FeedBack feedBack;

    private JPanel grigliaPanel = new JPanel();
        private JPanel formPanel = new JPanel();
            private JLabel inserisciRisposta = new JLabel("Risposta: ");
            private JTextArea rispostaField = new JTextArea();
        private JPanel buttonPanel = new JPanel();
            private JButton btnRisposta = new JButton("Rispondi");


    private FeedBackDialog(Frame frame, String title, FeedBack f) {

        super(frame, title, true);
        appFrame = frame;
        this.feedBack = f;


        rispostaField.setText(f.getRisposta());

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();


        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }

    public static Object showDialog(JFrame appFrame, String title, FeedBack f) {
        Frame frame = JOptionPane.getFrameForComponent(appFrame);
        dialog = new FeedBackDialog(frame, title, f);
        dialog.setVisible(true);
        return null;

    }

    public void actionPerformed(ActionEvent e) {
        if ("rispondi".equals(e.getActionCommand())) {
            feedBack.setRisposta(rispostaField.getText());
            FeedbackDAO.getInstance().update(feedBack);
            FeedBackDialog.dialog.setVisible(false);
        }

    }

    public void layoutSetting(){
        grigliaPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        grigliaPanel.setLayout(new BorderLayout());
        formPanel.setLayout(new FlowLayout());
        rispostaField.setLineWrap(true);
        rispostaField.setWrapStyleWord(true);
    }

    public void componentsAdding(){
        add(grigliaPanel);
        grigliaPanel.add(formPanel, BorderLayout.NORTH);
            formPanel.add(inserisciRisposta);
            formPanel.add(rispostaField);
        grigliaPanel.add(buttonPanel, BorderLayout.SOUTH);
            buttonPanel.add(btnRisposta);
    }

    public void componentsSizing(){
        rispostaField.setPreferredSize(new Dimension(200,100));
    }

    public void listenerSettings(){
        btnRisposta.addActionListener(this);
        btnRisposta.setActionCommand("rispondi");
    }


}