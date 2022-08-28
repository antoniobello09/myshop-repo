package View.Center.Cliente;


import DAO.Classi.FeedbackDAO;
import DAO.Classi.PuntoVenditaDAO;
import Model.FeedBack;
import Model.PuntoVendita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FeedbackDialog extends JDialog
        implements ActionListener {

    private static FeedbackDialog dialog;
    private static FeedBack value;

    private Container contentPane;
        private JPanel titlePanel = new JPanel();
            private JLabel label = new JLabel("Dacci un tuo feedback su quest'articolo:");
        private JPanel comboBoxPanel = new JPanel();
            private JComboBox<String> comboStars;
            private JTextField feedbackField = new JTextField();
        private JPanel buttonPane = new JPanel();
            private JButton setButton = new JButton("Salva");

    private ArrayList<String> starList = new ArrayList<>();

    public static FeedBack showDialog(Component frameComp,
                                      Component locationComp,
                                      String labelText,
                                      String title,
                                      String[] possibleValues,
                                      String initialValue,
                                      String longValue) {
        Frame frame = JOptionPane.getFrameForComponent(frameComp);
        dialog = new FeedbackDialog(frame,
                locationComp,
                labelText,
                title,
                possibleValues,
                initialValue,
                longValue);
        dialog.setVisible(true);
        return value;
    }

    private void setValue(FeedBack newValue) {
        value = newValue;
    }

    private FeedbackDialog(Frame frame,
                           Component locationComp,
                           String labelText,
                           String title,
                           Object[] data,
                           String initialValue,
                           String longValue) {
        super(frame, title, true);

        contentPane = getContentPane();
        getRootPane().setDefaultButton(setButton);

        comboStars.addItem("1");
        comboStars.addItem("2");
        comboStars.addItem("3");
        comboStars.addItem("4");
        comboStars.addItem("5");

      //  feedbackField.setText(FeedbackDAO.getInstance().findByInfo(idAcquisto, idArticolo).getText());
       // comboStars.setSelectedIndex(FeedbackDAO.getInstance().findByInfo(idAcquisto, idArticolo).getText());

        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

        setValue(null);
        pack();
        setLocationRelativeTo(locationComp);
    }

    public void actionPerformed(ActionEvent e) {
        if ("imposta".equals(e.getActionCommand())) {
            value.setCommento(feedbackField.getText());
            value.setIndiceGradimento(Integer.parseInt(comboStars.getSelectedItem().toString()));
        }
        FeedbackDialog.dialog.setVisible(false);
    }

    public void layoutSetting(){
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
            comboBoxPanel.setLayout(new FlowLayout());
    }

    public void componentsAdding(){
        contentPane.add(titlePanel, BorderLayout.NORTH);
            titlePanel.add(label);
        contentPane.add(comboBoxPanel, BorderLayout.CENTER);
            comboBoxPanel.add(comboStars);
            comboBoxPanel.add(feedbackField);
        contentPane.add(buttonPane, BorderLayout.SOUTH);
            buttonPane.add(setButton);
    }

    public void componentsSizing(){

    }

    public void listenerSettings(){
        setButton.setActionCommand("imposta");
        setButton.addActionListener(this);
    }

}