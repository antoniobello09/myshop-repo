package View;


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
            private JComboBox<String> comboShops = new JComboBox<>();
        private JPanel buttonPane = new JPanel();
            private JButton setButton = new JButton("Imposta");

    private ArrayList<PuntoVendita> shopsList;

    private ShopDialog(Frame frame,
                       Component locationComp,
                       String labelText,
                       String title,
                       Object[] data,
                       String initialValue,
                       String longValue) {
        super(frame, title, true);

        contentPane = getContentPane();
        getRootPane().setDefaultButton(setButton);


        shopsList = PuntoVenditaDAO.getInstance().findAll();
        for(int i=0;i<shopsList.size();i++){
            String shopAddress = shopsList.get(i).getIndirizzo() + ", " + shopsList.get(i).getCitta();
            comboShops.addItem(shopAddress);
        }

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
                                    String labelText,
                                    String title,
                                    String[] possibleValues,
                                    String initialValue,
                                    String longValue) {
        Frame frame = JOptionPane.getFrameForComponent(frameComp);
        dialog = new ShopDialog(frame,
                locationComp,
                labelText,
                title,
                possibleValues,
                initialValue,
                longValue);
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