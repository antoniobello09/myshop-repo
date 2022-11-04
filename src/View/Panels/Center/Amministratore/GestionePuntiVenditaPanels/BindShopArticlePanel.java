package View.Panels.Center.Amministratore.GestionePuntiVenditaPanels;

import Business.HelpFunctions;
import Business.ModelBusiness.PuntoVenditaBusiness;
import Business.ModelBusiness.SchedaProdottoBusiness;
import DAO.Classi.ProdottoDAO;
import DAO.Classi.PuntoVenditaDAO;
import DAO.Classi.SchedaProdottoDAO;
import Model.PuntoVendita;
import Model.SchedaProdotto;
import View.AppFrame;
import View.Listener.CenterListeners.Amministratore.GestionePuntiVenditaListeners.BindShopArticleListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BindShopArticlePanel extends JPanel {

    private AppFrame appFrame;
    private BindShopArticleListener bindShopArticleListener;

    private JPanel grigliaPanel = new JPanel();
        private JLabel inserisciPuntoVendita = new JLabel("Punto Vendita: ");
        private JComboBox puntoVenditaField = new JComboBox();
        private JLabel inserisciProdotto = new JLabel("Prodotto: ");
        private JTextField prodottoField = new JTextField();
        private JButton btnAssocia = new JButton("Associa");

    public BindShopArticlePanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        bindShopArticleListener = new BindShopArticleListener(this, appFrame);

        puntoVenditaField = HelpFunctions.getFullComboBox(PuntoVenditaBusiness.getInstance().getAllShopsAddresses());

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void associa(){
        if(prodottoField.getText().equals("")){
            JOptionPane.showMessageDialog(appFrame,
                    "Il campo prodotto è vuoto!",
                    "Empty Product Field Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{

            int result = SchedaProdottoBusiness.getInstance().associa(puntoVenditaField.getSelectedItem().toString(), prodottoField.getText());

            switch (result) {
                case 1:
                    JOptionPane.showMessageDialog(appFrame,
                        "Prodotto non esistente!",
                        "Wrong Product Field Error",
                        JOptionPane.ERROR_MESSAGE);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(appFrame,
                            "L'associazione non è riuscita!",
                            "Association Product Shop Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(appFrame,
                            "Associazione già fatta!",
                            "Association Product Shop Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                case 0:
                    JOptionPane.showMessageDialog(appFrame,
                        "Associazione creata con successo!",
                        "Association Product Shop Success",
                        JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        }
        prodottoField.setText("");
    }

    public void layoutSetting(){
        grigliaPanel.setLayout(new GridLayout(0,2,0,10));
    }

    public void componentsAdding(){
        add(grigliaPanel);
            grigliaPanel.add(inserisciPuntoVendita);
            grigliaPanel.add(puntoVenditaField);
            grigliaPanel.add(inserisciProdotto);
            grigliaPanel.add(prodottoField);
            grigliaPanel.add(btnAssocia);


    }

    public void componentsSizing(){

    }

    public void listenerSettings(){
        btnAssocia.setActionCommand("associa");
        btnAssocia.addActionListener(bindShopArticleListener);

    }
}
