package View.SideMenuPanels.Manager;

import View.AppFrame;
import View.Listener.SideMenuListeners.Amministratore.SMGestioneServiziListener;

import javax.swing.*;
import java.awt.*;

public class SMGestioneClienti extends JPanel {

    private JButton aggiungiServizio = new JButton("Aggiungi servizio");
    private JButton modificaServizio = new JButton("Modifica/Elimina servizio");
    private JButton modificaCategoriaS = new JButton("<html>Aggiungi/Modifica/Elimina<br> una categoria servizio</html>");
    private JButton aggiungiFornitoreServizi = new JButton("Aggiungi un fornitore di servizi");
    private JButton modificaFornitoreServizi = new JButton("Modifica/Elimina un fornitore di servizi");
    private JButton indietro = new JButton("Indietro");
    private SMGestioneServiziListener smGestioneServiziListener;


    public SMGestioneClienti(AppFrame appFrame) {
        setLayout(new GridLayout(8, 1, 0, 15));
        aggiungiServizio.setPreferredSize(new Dimension(255, 40));
        smGestioneServiziListener = new SMGestioneServiziListener(appFrame);

        aggiungiServizio.addActionListener(smGestioneServiziListener);
        modificaServizio.addActionListener(smGestioneServiziListener);
        modificaCategoriaS.addActionListener(smGestioneServiziListener);
        aggiungiFornitoreServizi.addActionListener(smGestioneServiziListener);
        modificaFornitoreServizi.addActionListener(smGestioneServiziListener);
        indietro.addActionListener(smGestioneServiziListener);

        aggiungiServizio.setActionCommand("addService");
        modificaServizio.setActionCommand("modifyService");
        modificaCategoriaS.setActionCommand("modifyCategoryS");
        aggiungiFornitoreServizi.setActionCommand("addServiceProvider");
        modificaFornitoreServizi.setActionCommand("modifyServiceProvider");
        indietro.setActionCommand("indietro");

        add(aggiungiServizio);
        add(modificaServizio);
        add(modificaCategoriaS);
        add(aggiungiFornitoreServizi);
        add(modificaFornitoreServizi);
        add(indietro);

    }
}
