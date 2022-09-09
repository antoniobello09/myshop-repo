package View.Panels.Center.Amministratore.GestioneArticoliPanels;

import DAO.Classi.CategoriaProdottoDAO;
import DAO.Classi.CategoriaServizioDAO;
import DAO.Classi.ProdottoDAO;
import Model.*;
import View.AppFrame;
import View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners.CreateCategoryListener;
import View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners.CreateProductListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CreateCategoryPanel extends JPanel {

    AppFrame appFrame;
    CreateCategoryListener createCategoryListener;

    private JPanel formPanel = new JPanel();
        private JLabel inserisciCategoria = new JLabel("Inserire il nome della categoria da creare:");
        private JTextField categoriaField = new JTextField();
        private JLabel inserisciSottocategoria = new JLabel("Sottocategoria");
        private JComboBox<String> sottocategoriaField;
        private JLabel inserisciCategoriaPadre = new JLabel("Inserire il nome della categoria padre:");
        private JTextField categoriaPadreField = new JTextField();
        private JButton btnModifica = new JButton("Aggiungi");

    private String selectedItem;
    private ArrayList<CategoriaProdotto> categorieList;     //Lista di categorie

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
            if(sottocategoriaField.getSelectedItem().equals("Prodotto")){
                if(categoriaPadreField.getText().isEmpty()){
                    CategoriaProdotto categoriaProdotto = new CategoriaProdotto(categoriaField.getText());
                    CategoriaProdottoDAO.getInstance().add(categoriaProdotto);
                    if(CategoriaProdottoDAO.getInstance().add(categoriaProdotto) == 0){
                        JOptionPane.showMessageDialog(appFrame,
                                "La categoria è già esistente!",
                                "Create Product Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }else{
                    CategoriaProdotto categoriaPadreProdotto = new CategoriaProdotto(categoriaPadreField.getText());
                    if(CategoriaProdottoDAO.getInstance().isCategory(categoriaPadreProdotto.getIdCategoria()) == false){
                        System.out.println("Categoria Padre non esiste");
                    }
                }

            }else if(sottocategoriaField.getSelectedItem().equals("Servizio")){
                CategoriaServizio categoriaServizio = new CategoriaServizio(categoriaField.getText());
                CategoriaServizioDAO.getInstance().add(categoriaServizio);
            }

        }
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
        formPanel.add(btnModifica);

    }

    public void componentsSizing(){

    }

    public void listenerSettings(){
        btnModifica.setActionCommand("aggiungi");

        btnModifica.addActionListener(createCategoryListener);
    }
}
