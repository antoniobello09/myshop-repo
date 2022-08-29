package View.Panels;

import javax.swing.*;

//Semplice pannello vuoto
public class NullPanel extends JPanel {

    public NullPanel() {
        JLabel nullLabel = new JLabel("" );
        add(nullLabel);
    }
}
