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
import View.Listener.Amministratore.GestioneProdottiListeners.Product.ModifyProductListener;
import View.Listener.Ospite.BrowseServiceListener;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BrowseServicePanel extends JPanel {

    private BrowseServiceListener browseServiceListener;
    private AppFrame appFrame;


        private JScrollPane currentScrollPane;
        private JTable currentTable;
        private ServizioTableModel currentTableModel;

    private ArrayList<Servizio> lista;

    public BrowseServicePanel(AppFrame appFrame) {

        PuntoVendita puntoVendita = (PuntoVendita)SessionManager.getInstance().getSession().get("loggedShop");
        lista = ServizioDAO.getInstance().findByShop(puntoVendita.getIdPuntoVendita());

        this.appFrame = appFrame;
         browseServiceListener = new BrowseServiceListener(this, this.appFrame);

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();
    }

    public void tableSetting(){
        currentTableModel = new ServizioTableModel(lista);
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
