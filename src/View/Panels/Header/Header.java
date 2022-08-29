package View.Panels.Header;

import Business.SessionManager;
import Model.Utente;
import View.AppFrame;
import View.Listener.HeaderListeners.LoginListener;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {


    private JLabel welcome = new JLabel("Benvenuto _____");
    private JPanel loggedIn = new JPanel();
        private JLabel lblUsername = new JLabel("username: ");
        private JTextField username = new JTextField(20);
        private JLabel lblPassword = new JLabel("password: ");
        private JPasswordField password = new JPasswordField(20);
        private JButton btnLogin = new JButton("Login");
        private JButton btnRegister = new JButton("Registrati");
    private JPanel loggedOut = new JPanel();
        private JButton btnLogout = new JButton("Logout");


    private LoginListener loginListener;

    public Header(AppFrame appFrame) {

        loginListener = new LoginListener(appFrame);

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

        setLoggedOutStatus();

    }



    public void refresh() {

        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");

        if(u==null) setLoggedOutStatus();

        else {
            welcome.setText("Benvenuto, "+u.getName());
            setLoggedInStatus();
        }
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return new String(password.getPassword());
    }

    public void clearFields() {
        username.setText("");
        password.setText("");
    }

    public void setLoggedInStatus() {
        loggedIn.setVisible(true);
        loggedOut.setVisible(false);
    }
    public void setLoggedOutStatus() {
        loggedIn.setVisible(false);
        loggedOut.setVisible(true);
    }

    public void layoutSetting(){
        setLayout(new FlowLayout());
    }

    public void componentsAdding(){
        add(loggedIn);
            loggedIn.add(welcome);
            loggedIn.add(btnLogout);
        add(loggedOut);
            loggedOut.add(lblUsername);
            loggedOut.add(username);
            loggedOut.add(lblPassword);
            loggedOut.add(password);
            loggedOut.add(btnLogin);
            loggedOut.add(btnRegister);
    }

    public void componentsSizing(){

    }

    public void listenerSettings(){
        btnLogin.setActionCommand("btnLogin");
        btnLogin.addActionListener(loginListener);

        btnRegister.setActionCommand("btnRegister");
        btnRegister.addActionListener(loginListener);

        btnLogout.setActionCommand("btnLogout");
        btnLogout.addActionListener(loginListener);
    }



}
