package View.Panels.Center.Amministratore.GestioneArticoliPanels;

import View.AppFrame;
import View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners.CreateCategoryListener;
import View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners.CreateProductListener;

import javax.swing.*;
import java.awt.*;

public class CreateCategoryPanel extends JPanel {

    AppFrame appFrame;
    CreateCategoryListener createCategoryListener;

    private JPanel formPanel = new JPanel();
        private JLabel inserisciArticolo = new JLabel("Inserire il nome della categoria:");
        private JTextField articoloField = new JTextField();
        private JButton btnModifica = new JButton("Aggiungi");

    public CreateCategoryPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        createCategoryListener = new CreateCategoryListener(this, this.appFrame);

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void aggiungi(){

    }

    public void layoutSetting(){
        formPanel.setLayout(new GridLayout(0,2,0,10));
    }

    public void componentsAdding(){
        add(formPanel);
        formPanel.add(inserisciArticolo);
        formPanel.add(articoloField);
        formPanel.add(btnModifica);


    }

    public void componentsSizing(){

    }

    public void listenerSettings(){
        btnModifica.setActionCommand("aggiungi");

        btnModifica.addActionListener(createCategoryListener);
    }
}
