package View.SideMenuPanels;

import View.AppFrame;
import View.ChangeablePanel;
import View.SideMenuPanels.Home.SideMenuLogin;
import View.WelcomePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SideMenu extends ChangeablePanel {

    ArrayList<JPanel> sideMenuCronologia = new ArrayList<>();

    public SideMenu(AppFrame appFrame){
        Dimension d = new Dimension(300,500);
        setPreferredSize(d);
        this.appFrame = appFrame;
        setCurrentPanel();

    }

    @Override
    public void setCurrentPanel(JPanel panel) {

        if(currentPanel!=null) remove(currentPanel);
        add(panel);

        currentPanel = panel;
        sideMenuCronologia.add(panel);
        invalidate();
        validate();
        repaint();
    }

    @Override
    public void setCurrentPanel() {
        if(currentPanel!=null) remove(currentPanel);
        SideMenuLogin sideMenuDefaultPanel = new SideMenuLogin(appFrame);
        add(sideMenuDefaultPanel);

        currentPanel = sideMenuDefaultPanel;
        sideMenuCronologia.add(sideMenuDefaultPanel);
        invalidate();
        validate();
        repaint();

    }

    public void setPrecedentPanel(){
        if(currentPanel!=null) remove(currentPanel);
        sideMenuCronologia.remove(sideMenuCronologia.size()-1);
        currentPanel = sideMenuCronologia.get(sideMenuCronologia.size()-1);
        add(currentPanel);
        appFrame.getCenter().setCurrentPanel(new WelcomePanel());

        invalidate();
        validate();
        repaint();
    }
}
