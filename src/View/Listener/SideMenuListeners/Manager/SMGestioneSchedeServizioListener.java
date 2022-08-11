package View.Listener.SideMenuListeners.Manager;

import View.AppFrame;
import View.SideMenuPanels.Manager.SMGestioneClienti;
import View.SideMenuPanels.Manager.SMGestioneSchedeServizio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SMGestioneSchedeServizioListener implements ActionListener {

    AppFrame appFrame;
    SMGestioneSchedeServizio smGestioneSchedeServizio;

    public SMGestioneSchedeServizioListener(AppFrame appFrame, SMGestioneSchedeServizio smGestioneSchedeServizio){
        this.appFrame = appFrame;
        this.smGestioneSchedeServizio = smGestioneSchedeServizio;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
