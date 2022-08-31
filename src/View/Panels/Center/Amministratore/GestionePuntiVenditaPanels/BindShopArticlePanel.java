package View.Panels.Center.Amministratore.GestionePuntiVenditaPanels;

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

    private ArrayList<PuntoVendita> puntoVenditaList = new ArrayList<>();

    public BindShopArticlePanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        bindShopArticleListener = new BindShopArticleListener(this, appFrame);

        puntoVenditaList = PuntoVenditaDAO.getInstance().findAll();
        if(puntoVenditaList!=null) {
            for (int i=0;i<puntoVenditaList.size();i++){
                puntoVenditaField.addItem("" + puntoVenditaList.get(i).getIndirizzo() + ", " + puntoVenditaList.get(i).getCitta());
            }
        }


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
            if(ProdottoDAO.getInstance().findByName(prodottoField.getText())!=null){
                int idProdotto = ProdottoDAO.getInstance().findByName(prodottoField.getText()).getIdArticolo();
                String[] indirizzo = String.valueOf(puntoVenditaField.getSelectedItem()).split(", ");
                int idPuntoVendita = PuntoVenditaDAO.getInstance().findByName(indirizzo[0],indirizzo[1]).getIdPuntoVendita();
                SchedaProdottoDAO.getInstance().add(idProdotto, 0, idPuntoVendita);
            }else{
                JOptionPane.showMessageDialog(appFrame,
                        "Prodotto non esistente!",
                        "Wrong Product Field Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
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
