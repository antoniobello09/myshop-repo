package View.SideMenuPanels.Ospite;

import View.AppFrame;
import View.Listener.SideMenuListeners.Amministratore.SMGestioneServiziListener;
import View.Listener.SideMenuListeners.Ospite.SMSfogliaListener;

import javax.swing.*;
import java.awt.*;

public class SMSfoglia extends JPanel {

    private JButton sfogliaProdotti = new JButton("Sfoglia prodotti");
    private JButton sfogliaServizi = new JButton("Sfoglia servizi");
    private JButton indietro = new JButton("Indietro");
    private SMSfogliaListener smSfogliaListener;


    public SMSfoglia(AppFrame appFrame) {
        setLayout(new GridLayout(8, 1, 0, 15));
        sfogliaServizi.setPreferredSize(new Dimension(255, 40));
        smSfogliaListener = new SMSfogliaListener(appFrame);

        sfogliaServizi.addActionListener(smSfogliaListener);
        sfogliaProdotti.addActionListener(smSfogliaListener);
        indietro.addActionListener(smSfogliaListener);

        sfogliaProdotti.setActionCommand("sfogliaProdotti");
        sfogliaServizi.setActionCommand("sfogliaServizi");
        indietro.setActionCommand("indietro");

        add(sfogliaProdotti);
        add(sfogliaServizi);
        add(indietro);

    }
}
