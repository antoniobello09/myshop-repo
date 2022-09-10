package View.Listener.HeaderListeners;

import Business.SessionManager;
import Business.ModelBusiness.UtenteBusiness;
import Model.Other.LoginResponse;
import Model.Utente;
import View.AppFrame;
import View.Panels.Center.Registration.RegisterPanel;
import View.Panels.NullPanel;
import View.Panels.WelcomePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener {

    AppFrame appFrame;

    public LoginListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//-----------COMMAND---------------------------------------------------------------------//
        String cmd = e.getActionCommand();
//-----------PULSANTE LOGIN--------------------------------------------------------------//
        if("btnLogin".equals(cmd)) {

            LoginResponse res = UtenteBusiness.getInstance().login(appFrame.getHeader().getUsername(), appFrame.getHeader().getPassword());
            appFrame.getHeader().clearFields();
            appFrame.getCenter().setCurrentPanel(new WelcomePanel());
            Utente u = res.getUtente(); //potrebbe essere null in caso di login fallito

            if(u == null) {
                //login fallito: avvisare l'utente
                String reason = res.getMessage(); // da mostrare all'utente

                JOptionPane.showMessageDialog(appFrame,
                        reason,
                        "Login error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                //login ok
                SessionManager.getInstance().getSession().put("loggedUser", u);
                appFrame.getHeader().refresh();
                appFrame.getSideMenu().setCurrentPanel();
                appFrame.getCenter().setCurrentPanel(new NullPanel());
                /*
                if(u instanceof Manager) {
                    //metti i pulsanti per il manager
                }*/
            }
        }
        if("btnLogout".equals(cmd)) {
            // reset della view mostrando interfaccia guest
            SessionManager.getInstance().getSession().remove("loggedUser");
            appFrame.getHeader().refresh();
            appFrame.getSideMenu().setCurrentPanel();
            appFrame.getCenter().setCurrentPanel(new WelcomePanel());
        }
        if("btnRegister".equals(cmd)){
            appFrame.getCenter().setCurrentPanel(new RegisterPanel(appFrame));
        }

    }
}
