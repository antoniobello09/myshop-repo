package View;


import Business.HelpFunctions;
import Business.ModelBusiness.PuntoVenditaBusiness;
import Business.SessionManager;
import DAO.Classi.PuntoVenditaDAO;
import Model.PuntoVendita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShopDialog extends JDialog
        implements ActionListener {

    private static ShopDialog dialog;
    private static String value = "";

    private Container contentPane;
        private JPanel titlePanel = new JPanel();
            private JLabel label = new JLabel("Punto vendita dove stai usando il PC:");
        private JPanel comboBoxPanel = new JPanel();
            private JComboBox<String> comboShops;
        private JPanel buttonPane = new JPanel();
            private JButton setButton = new JButton("Imposta");


    private ShopDialog(Frame frame,
                       Component locationComp,
                       String title,
                       ArrayList<String> shopsList) {
        super(frame, title, true);

        contentPane = getContentPane();
        getRootPane().setDefaultButton(setButton);


        comboShops = HelpFunctions.getFullComboBox(shopsList);

        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

        setValue("");
        pack();
        setLocationRelativeTo(locationComp);
    }


    public static String showDialog(Component frameComp,
                                    Component locationComp,
                                    String title,
                                    ArrayList<String> shopsList) {
        Frame frame = JOptionPane.getFrameForComponent(frameComp);
        dialog = new ShopDialog(frame,
                locationComp,
                title,
                shopsList);
        dialog.setVisible(true);
        return value;
    }

    private void setValue(String newValue) {
        value = newValue;
    }



    public void actionPerformed(ActionEvent e) {
        if ("imposta".equals(e.getActionCommand())) {
            value = "" + comboShops.getSelectedItem();
        }
        ShopDialog.dialog.setVisible(false);
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
            comboBoxPanel.add(comboShops);
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