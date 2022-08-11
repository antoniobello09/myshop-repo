package View.Listener.SideMenuListeners.Manager;

import View.AppFrame;
import View.SideMenuPanels.Manager.SMGestioneClienti;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SMGestioneClientiListener implements ActionListener {

    AppFrame appFrame;
    SMGestioneClienti smGestioneClienti;

    public SMGestioneClientiListener(AppFrame appFrame, SMGestioneClienti smGestioneClienti){
        this.appFrame = appFrame;
        this.smGestioneClienti = smGestioneClienti;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
