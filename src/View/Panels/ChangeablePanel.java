package View.Panels;

import View.AppFrame;

import javax.swing.*;
//Un pannello è "changeable" se aggiunge e toglie pannelli al suo interno dinamicamente (Lo sono il pannello Center e il pannello SideMenu,
//responsabili del cambiamento dei pannelli al centro e al lato quando si premono i pulsanti del menu)
public abstract class ChangeablePanel extends JPanel {

    public JPanel currentPanel;
    public AppFrame appFrame;

    public abstract void setCurrentPanel(JPanel panel);
    public abstract void setCurrentPanel();

}
