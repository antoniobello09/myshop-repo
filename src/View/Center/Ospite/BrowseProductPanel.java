package View.Center.Ospite;

import Business.SessionManager;
import DAO.Classi.ProdottoDAO;
import DAO.Classi.ServizioDAO;
import Model.Prodotto;
import Model.PuntoVendita;
import Model.Servizio;
import View.AppFrame;
import View.Center.Amministratore.GestioneProdottiPanels.Product.ProdottoTableModel;
import View.Center.Cliente.ServizioTableModel;
import View.Listener.Ospite.BrowseProductListener;
import View.Listener.Ospite.BrowseServiceListener;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BrowseProductPanel extends JPanel {
    private BrowseProductListener browseProductListener;
    private AppFrame appFrame;


    private JScrollPane currentScrollPane;
    private JTable currentTable;
    private ProdottoTableModel currentTableModel;

    private ArrayList<Prodotto> lista;

    public BrowseProductPanel(AppFrame appFrame) {

        PuntoVendita puntoVendita = (PuntoVendita) SessionManager.getInstance().getSession().get("loggedShop");
        lista = ProdottoDAO.getInstance().finByShop(puntoVendita.getIdPuntoVendita());

        this.appFrame = appFrame;
        browseProductListener = new BrowseProductListener(this, this.appFrame);

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();
    }

    public void tableSetting(){
        currentTableModel = new ProdottoTableModel(lista);
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
        currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void componentsAdding(){
        add(currentScrollPane, BorderLayout.CENTER);
    }

    public void componentsSizing(){
        currentScrollPane.setPreferredSize(new Dimension(1000,500));
    }

    public void listenerSettings(){
    }

}
