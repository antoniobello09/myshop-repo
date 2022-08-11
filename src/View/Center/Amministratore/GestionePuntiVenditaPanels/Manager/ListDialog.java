package View.Center.Amministratore.GestionePuntiVenditaPanels.Manager;

import Business.HelpFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Use this modal dialog to let the user choose one string from a long
 * list.  See ListDialogRunner.java for an example of using ListDialog.
 * The basics:
 * <pre>
 String[] choices = {"A", "long", "array", "of", "strings"};
 String selectedName = ListDialog.showDialog(
 componentInControllingFrame,
 locatorComponent,
 "A description of the list:",
 "Dialog Title",
 choices,
 choices[0]);
 * </pre>
 */
public class ListDialog extends JDialog
        implements ActionListener {
    private static ListDialog dialog;
    private static String value = "";
    private JList list;
    private JComboBox<Integer> anno;
    private JComboBox<Integer> mese;
    private JComboBox<String> giorno;
    private String initialValue;
    private boolean yearChosen = false;
    private boolean monthChosen = false;


    public static String showDialog(Component frameComp,
                                    Component locationComp,
                                    String labelText,
                                    String title,
                                    String[] possibleValues,
                                    String initialValue,
                                    String longValue) {
        Frame frame = JOptionPane.getFrameForComponent(frameComp);
        dialog = new ListDialog(frame,
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

    private ListDialog(Frame frame,
                       Component locationComp,
                       String labelText,
                       String title,
                       Object[] data,
                       String initialValue,
                       String longValue) {
        super(frame, title, true);


        //Create and initialize the buttons.
        JButton cancelButton = new JButton("Annulla");
        cancelButton.addActionListener(this);
        //
        final JButton setButton = new JButton("Imposta");
        setButton.setActionCommand("Set");
        setButton.addActionListener(this);
        getRootPane().setDefaultButton(setButton);


        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));
        listScroller.setAlignmentX(LEFT_ALIGNMENT);


        JPanel titlePanel = new JPanel();
        JLabel label = new JLabel("Inserisci la nuova data:");
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new FlowLayout());

        titlePanel.add(label);
        anno = HelpFunctions.setYearsComboBox();
        anno.setActionCommand("anno");
        anno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("anno")){
                    JComboBox cb = (JComboBox)e.getSource();
                    if(!cb.getSelectedItem().equals("--"))  yearChosen = true;
                    else monthChosen = false;
                }
                if((monthChosen)&&(yearChosen)){
                    HelpFunctions.setDaysComboBox(giorno, mese.getSelectedItem().toString(), anno.getSelectedItem().toString());
                }
            }
        });
        mese = HelpFunctions.setMonthsComboBox();
        mese.setActionCommand("mese");
        mese.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("mese")){
                    JComboBox cb = (JComboBox)e.getSource();
                    if(!cb.getSelectedItem().equals("--"))  monthChosen = true;
                    else monthChosen = false;
                }
                if((monthChosen)&&(yearChosen)){
                    HelpFunctions.setDaysComboBox(giorno, mese.getSelectedItem().toString(), anno.getSelectedItem().toString());
                }
            }
        });
        giorno = new JComboBox<>();
        HelpFunctions.setDaysComboBox(giorno, anno.getSelectedItem().toString(), mese.getSelectedItem().toString());
        comboBoxPanel.add(anno);
        comboBoxPanel.add(mese);
        comboBoxPanel.add(giorno);

        anno.setSelectedItem(initialValue.substring(0,4));
        mese.setSelectedItem(HelpFunctions.convertMonth3(initialValue.substring(5,7)));
        giorno.setSelectedItem(initialValue.substring(8,10));

        //Lay out the buttons from left to right.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(cancelButton);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(setButton);

        //Put everything together, using the content pane's BorderLayout.
        Container contentPane = getContentPane();
        contentPane.add(titlePanel, BorderLayout.NORTH);
        contentPane.add(buttonPane, BorderLayout.SOUTH);
        contentPane.add(comboBoxPanel, BorderLayout.CENTER);

        //Initialize values.
        setValue(initialValue);
        pack();
        setLocationRelativeTo(locationComp);
    }

    //Handle clicks on the Set and Cancel buttons.
    public void actionPerformed(ActionEvent e) {
        if ("Set".equals(e.getActionCommand())) {
            if(anno.getSelectedItem().equals("--")||mese.getSelectedItem().equals("--")||giorno.getSelectedItem().equals("--")){
                JOptionPane.showMessageDialog(dialog,
                        "Inserimento errato",
                        "Modify Date ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            ListDialog.value = "" + anno.getSelectedItem().toString() + "-" + HelpFunctions.convertMonth(mese.getSelectedIndex()) + "-" + giorno.getSelectedItem().toString();
        }
        ListDialog.dialog.setVisible(false);
    }
}