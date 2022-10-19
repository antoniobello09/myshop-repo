package View.Panels.Center.Amministratore.GestioneArticoliPanels;

import Business.ModelBusiness.ArticoloBusiness;
import Business.ModelBusiness.ServizioBusiness;
import DAO.Classi.*;
import Model.Articolo;
import Model.Prodotto;
import Model.ProdottoComposito;
import Model.Servizio;
import View.AppFrame;
import View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners.DeleteArticleListener;
import View.Listener.CenterListeners.Amministratore.GestioneArticoliListeners.ModifyArticleListener;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.ModifyCompositeProductDialog;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.ModifyProductDialog;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.ModifyServiceDialog;

import javax.swing.*;
import java.awt.*;

public class DeleteArticlePanel extends JPanel {

    AppFrame appFrame;
    DeleteArticleListener deleteArticleListener;

    private JPanel formProductPanel = new JPanel();
        private JLabel inserisciNome = new JLabel("Articolo da eliminare: ");
        private JTextField nomeField = new JTextField();
        private JButton btnCancella = new JButton("Cancella");

    public DeleteArticlePanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        deleteArticleListener = new DeleteArticleListener(this, appFrame);

        layoutSetting();

        componentsAdding();

        listenerSettings();

    }

    public void cancella(){
        if(nomeField.getText().isEmpty() ){
            JOptionPane.showMessageDialog(appFrame,
                    "Non hai inserito il nome dell'articolo!",
                    "Delete Product Error",
                    JOptionPane.ERROR_MESSAGE);
        }else {
            int result = ArticoloBusiness.getInstance().cancella(nomeField.getText());
            switch (result){
                case 1:
                        JOptionPane.showMessageDialog(appFrame,
                                "Nome dell'articolo non corretto!",
                                "Delete Article Error",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                case 0:
                        JOptionPane.showMessageDialog(appFrame,
                                "Eliminazione avvenuta con successo",
                                "Delete Article Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        break;
            }
            nomeField.setText("");
        }

    }

    //------------------------------------------------------------------------------------------------------------------------

    public void layoutSetting(){
        formProductPanel.setLayout(new GridLayout(0,2,0,10));
    }

    public void componentsAdding() {
        add(formProductPanel);
        formProductPanel.add(inserisciNome);
        formProductPanel.add(nomeField);
        formProductPanel.add(btnCancella);
    }

    public void listenerSettings(){
        btnCancella.setActionCommand("cancella");

        btnCancella.addActionListener(deleteArticleListener);
    }

}
