package View.Listener.HeaderListeners;

import View.AppFrame;
import View.Panels.Center.Registration.RegisterPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterListener implements ActionListener {
    private static boolean year_chosen = false;
    private static boolean month_chosen = false;
    private static String month_chosenN;
    private static int year_chosenN;

    RegisterPanel registerPanel;
    AppFrame appFrame;

    public RegisterListener(RegisterPanel registerPanel, AppFrame appFrame){
        this.appFrame = appFrame;
        this.registerPanel = registerPanel;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("iscriviti")){
            registerPanel.iscriviti();
        }else if(e.getActionCommand().equals("month")){
            registerPanel.setMonthSelected();
        }else if(e.getActionCommand().equals("year")){
            registerPanel.setYearSelected();
        }

    }
}
