package View.Panels.Center.Manager.Altro;

import DAO.Classi.CategoriaServizioDAO;
import DAO.Classi.FornitoreDAO;
import DAO.Classi.SchedaProdottoDAO;
import DAO.Classi.ServizioDAO;
import Model.*;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.ModifyServiceDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;

public class DisponibilitaDialog extends JDialog implements ActionListener {

    private static DisponibilitaDialog dialog;
    private Frame appFrame;
    private SchedaProdotto schedaProdotto;

    private JPanel grigliaPanel = new JPanel();
        private JLabel inserisciDisponibilita = new JLabel("Disponibilità prodotto: ");
        private JTextField disponibilitaField = new JTextField();
        private JButton btnRifornisci = new JButton("Rifornisci");



    private DisponibilitaDialog(Frame frame, String title, SchedaProdotto schedaProdotto) {

        super(frame, title, true);
        this.appFrame = frame;
        this.schedaProdotto = schedaProdotto;

        disponibilitaField.setText(String.valueOf(schedaProdotto.getDisponibilita()));



        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();


        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }

    public static Object showDialog(JFrame appFrame, String title, SchedaProdotto schedaProdotto) {
        Frame frame = JOptionPane.getFrameForComponent(appFrame);
        dialog = new DisponibilitaDialog(frame, title, schedaProdotto);
        dialog.setVisible(true);

        return null;
    }

    public void actionPerformed(ActionEvent e) {
        if ("rifornisci".equals(e.getActionCommand())) {
            if(disponibilitaField.getText().equals("")){
                JOptionPane.showMessageDialog(appFrame,
                        "Campo vuoto!",
                        "Supply Product Error",
                        JOptionPane.ERROR_MESSAGE);
            }else{
                SchedaProdottoDAO.getInstance().update(schedaProdotto.getIdSchedaProdotto(), Integer.parseInt(disponibilitaField.getText()));
                DisponibilitaDialog.dialog.setVisible(false);
            }
        }
    }

    public void layoutSetting(){
        grigliaPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        grigliaPanel.setLayout(new GridLayout(0,2,20,10));
    }

    public void componentsAdding(){
        add(grigliaPanel);
            grigliaPanel.add(inserisciDisponibilita);
            grigliaPanel.add(disponibilitaField);
            grigliaPanel.add(btnRifornisci);
    }

    public void componentsSizing(){

    }

    public void listenerSettings(){
        btnRifornisci.addActionListener(this);
        btnRifornisci.setActionCommand("rifornisci");

    }

}