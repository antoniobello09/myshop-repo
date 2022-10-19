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

    private JPanel grigliaPanel = new JPanel();
        private JPanel formPanel = new JPanel();
            private JLabel inserisciRisposta = new JLabel("Risposta: ");
            private JTextArea rispostaField = new JTextArea();
        private JPanel buttonPanel = new JPanel();
            private JButton btnRisposta = new JButton("Rispondi");

    private static String rispostaOUT;

    private FeedBackDialog(Frame frame, String title, String rispostaIN) {

        super(frame, title, true);



        rispostaField.setText(rispostaIN);

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();


        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }

    public static String showDialog(JFrame appFrame, String title, String rispostaIN) {
        Frame frame = JOptionPane.getFrameForComponent(appFrame);
        dialog = new FeedBackDialog(frame, title, rispostaIN);
        dialog.setVisible(true);
        return rispostaOUT;

    }

    public void actionPerformed(ActionEvent e) {
        if ("rispondi".equals(e.getActionCommand())) {
            rispostaOUT = rispostaField.getText();
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