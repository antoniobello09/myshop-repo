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
import java.util.ArrayList;

public class FeedBackDialog extends JDialog implements ActionListener {

    private static FeedBackDialog dialog;
    private static Object value;        //Valore da ritornare
    private Frame appFrame;
    private FeedBack feedBack;

    private JPanel grigliaPanel = new JPanel();
    private JLabel inserisciCommento = new JLabel("Commento: ");
    private JTextField commentoField = new JTextField();
    private JLabel inserisciIndiceDiGradimento = new JLabel("Indice di gradimento: ");
    private JTextField indiceDiGradimentoField = new JTextField();
    private JButton btnSalva = new JButton("Salva");


    private FeedBackDialog(Frame frame, String title, int idAcquisto, int idArticolo) {

        super(frame, title, true);
        appFrame = frame;

        feedBack = FeedbackDAO.getInstance().findByIDAcquisto_Articolo(idAcquisto, idArticolo);






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


            FeedBackDialog.dialog.setVisible(false);
        }
    }

    public void layoutSetting(){
        grigliaPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        grigliaPanel.setLayout(new GridLayout(0,2,20,10));
    }

    public void componentsAdding(){
        add(grigliaPanel);

    }

    public void componentsSizing(){

    }

    public void listenerSettings(){
        btnSalva.addActionListener(this);
        btnSalva.setActionCommand("salva");

    }

}
