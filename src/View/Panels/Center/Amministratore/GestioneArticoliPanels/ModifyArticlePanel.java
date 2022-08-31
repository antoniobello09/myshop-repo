package View.Panels.Center.Amministratore.GestioneArticoliPanels;

import DAO.Classi.ArticoloDAO;
import DAO.Classi.ProdottoDAO;
import Model.Articolo;
import View.AppFrame;
import View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners.ModifyArticleListener;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.CompositeProductDialog;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.ModifyCompositeProductDialog;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.ModifyProductDialog;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.ModifyServiceDialog;

import javax.swing.*;
import java.awt.*;

public class ModifyArticlePanel extends JPanel {

    private AppFrame appFrame;
    private ModifyArticleListener modifyArticleListener;

    private JPanel formPanel = new JPanel();
        private JLabel inserisciArticolo = new JLabel("Articolo da modificare:");
        private JTextField articoloField = new JTextField();
        private JButton btnModifica = new JButton("Modifica");



    public ModifyArticlePanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        modifyArticleListener = new ModifyArticleListener(this, appFrame);



        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void modifica(){
        if(articoloField.getText().isEmpty()){
            JOptionPane.showMessageDialog(appFrame,
                    "Non hai inserito il nome dell'articolo!",
                    "Modify Article Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            Articolo articolo = ArticoloDAO.getInstance().findByName(articoloField.getText());
            if(articolo != null){
                if(ArticoloDAO.getInstance().isServizio(articolo)){
                    ModifyServiceDialog.showDialog(appFrame,
                            "Modifica servizio",
                            articolo);
                    articoloField.setText("");
                }else if(ArticoloDAO.getInstance().isProdottoComposito(articolo)){
                    ModifyCompositeProductDialog.showDialog(appFrame,
                            "Modifica prodotto composito",
                            articolo);
                    articoloField.setText("");
                }else if(ArticoloDAO.getInstance().isProdotto(articolo)){
                    ModifyProductDialog.showDialog(appFrame,
                            "Modifica prodotto",
                            articolo);
                    articoloField.setText("");
                }
            }else{
                JOptionPane.showMessageDialog(appFrame,
                        "Nome dell'articolo non corretto!",
                        "Modify Article Error",
                        JOptionPane.ERROR_MESSAGE);
                articoloField.setText("");
            }

        }
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
        btnModifica.setActionCommand("modifica");

        btnModifica.addActionListener(modifyArticleListener);
    }
}
