package View;

import View.AppFrame;

import javax.swing.*;

public abstract class ChangeablePanel extends JPanel {

    public JPanel currentPanel;
    public AppFrame appFrame;

    public abstract void setCurrentPanel(JPanel panel);
    public abstract void setCurrentPanel();

}
