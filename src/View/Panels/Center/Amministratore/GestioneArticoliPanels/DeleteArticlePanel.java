package View.Panels.Center.Amministratore.GestioneArticoliPanels;

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
        private JLabel inserisciNome = new JLabel("Articolo da eliminare");
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
        }else{
            Articolo articolo = ArticoloDAO.getInstance().findByName(nomeField.getText());

            if (articolo != null) {
                Prodotto prodotto = ProdottoDAO.getInstance().findByID(articolo.getIdArticolo());
                Servizio servizio = ServizioBusiness.getInstance().cercaIDServizio(articolo.getIdArticolo());
                if (ArticoloDAO.getInstance().isServizio(articolo)) {
                    ServizioBusiness.getInstance().cancella(servizio);
                } else if (ArticoloDAO.getInstance().isProdottoComposito(articolo)) {
                    ProdottoCompositoDAO.getInstance().delete(articolo.getIdArticolo());
                    ArticoloDAO.getInstance().delete(articolo);
                } else if (ArticoloDAO.getInstance().isProdotto(articolo)) {
                    ProdottoDAO.getInstance().delete(prodotto);
                    ArticoloDAO.getInstance().delete(articolo);
                }
            }
            else {
                JOptionPane.showMessageDialog(appFrame,
                        "Nome dell'articolo non corretto!",
                        "Delete Article Error",
                        JOptionPane.ERROR_MESSAGE);
                nomeField.setText("");
            }
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
