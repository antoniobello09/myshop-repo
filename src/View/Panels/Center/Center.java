package View.Panels.Center;

import Business.SessionManager;
import Model.Utente;
import View.AppFrame;
import View.Panels.ChangeablePanel;
import View.Panels.WelcomePanel;

import javax.swing.*;

public class Center extends ChangeablePanel {

    public Center(AppFrame appFrame){
        WelcomePanel welcomePanel = new WelcomePanel();
        currentPanel = welcomePanel;
        this.appFrame = appFrame;
        add(welcomePanel);
    }

    @Override
    public void setCurrentPanel(JPanel panel) {

        if(currentPanel!=null) remove(currentPanel);

        add(panel);

        currentPanel = panel;

        invalidate();
        validate();
        repaint();
    }

    @Override
    public void setCurrentPanel() {
        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");


    }
}
