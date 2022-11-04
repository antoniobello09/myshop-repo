package View.Panels.Center.Amministratore.GestioneArticoliPanels;

import Business.ModelBusiness.CategoriaBusiness;
import View.AppFrame;
import View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners.CreateCategoryListener;

import javax.swing.*;
import java.awt.*;

public class CreateCategoryPanel extends JPanel {

    AppFrame appFrame;
    CreateCategoryListener createCategoryListener;

    private JPanel formPanel = new JPanel();
        private JLabel inserisciCategoria = new JLabel("Inserire il nome della categoria da creare: ");
        private JTextField categoriaField = new JTextField();
        private JLabel inserisciSottocategoria = new JLabel("Sottocategoria: ");
        private JComboBox<String> sottocategoriaField;
        private JLabel inserisciCategoriaPadre = new JLabel("Inserire il nome della categoria padre: ");
        private JTextField categoriaPadreField = new JTextField();
        private JButton btnAggiungi = new JButton("Aggiungi");


    public CreateCategoryPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        createCategoryListener = new CreateCategoryListener(this, this.appFrame);

        String[] sottocategorie = {"Prodotto", "Servizio"};
        sottocategoriaField = new JComboBox<>(sottocategorie);


        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void aggiungi(){
        if(categoriaField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(appFrame,
                    "La compilazione è errata!",
                    "Create Product Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }else{
            //Aggiunge la categoria e restituisce un codice di errore
            int result = CategoriaBusiness.getInstance().aggiungi(categoriaField.getText(), sottocategoriaField.getSelectedItem().toString(), categoriaPadreField.getText());
            //In base al codice di errore fa vedere un messaggio
            switch (result) {
                case 1:
                    JOptionPane.showMessageDialog(appFrame,
                            "La categoria padre deve essere vuota!",
                            "Create Category Service Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(appFrame,
                            "La categoria è già esistente!",
                            "Create Product Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(appFrame,
                            "La categoria padre non esiste!",
                            "Create Product Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                case 0:
                    JOptionPane.showMessageDialog(appFrame,
                            "La categoria è stata aggiunta con successo",
                            "Create Product Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        }
        //Ripulisco i textField
        categoriaField.setText("");
        categoriaPadreField.setText("");

    }

    public void layoutSetting(){
        formPanel.setLayout(new GridLayout(0,2,0,10));
    }

    public void componentsAdding(){
        add(formPanel);
        formPanel.add(inserisciCategoria);
        formPanel.add(categoriaField);
        formPanel.add(inserisciSottocategoria);
        formPanel.add(sottocategoriaField);
        formPanel.add(inserisciCategoriaPadre);
        formPanel.add(categoriaPadreField);
        formPanel.add(btnAggiungi);

    }

    public void componentsSizing(){

    }

    public void listenerSettings(){
        btnAggiungi.setActionCommand("aggiungi");

        btnAggiungi.addActionListener(createCategoryListener);
    }
}
