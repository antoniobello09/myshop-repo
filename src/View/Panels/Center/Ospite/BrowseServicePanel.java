package View.Panels.Center.Ospite;

import Business.ModelBusiness.ServizioBusiness;
import DAO.Classi.ServizioDAO;
import Model.Servizio;
import View.AppFrame;
import View.Listener.CenterListeners.Ospite.BrowseServiceListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BrowseServicePanel extends JPanel {

    private BrowseServiceListener browseServiceListener;
    private AppFrame appFrame;

    private JLabel labelTitle = new JLabel("Catalogo Servizi");

    private JScrollPane currentScrollPane;
    private JTable currentTable;

    public BrowseServicePanel(AppFrame appFrame) {

        this.appFrame = appFrame;
        browseServiceListener = new BrowseServiceListener(this, this.appFrame);

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();
    }

    public void tableSetting(){
        //La JTable che mi serve la recupero da ServizioBusiness
        //E' compito del Business associare la tableModel alla JTable
        currentTable = ServizioBusiness.getInstance().getTabellaServizi();
        currentScrollPane = new JScrollPane(currentTable);
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
        currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void componentsAdding(){
        add(labelTitle, BorderLayout.NORTH);
        add(currentScrollPane, BorderLayout.CENTER);
    }

    public void componentsSizing(){
        currentTable.setRowHeight(60);
        currentScrollPane.setPreferredSize(new Dimension(1000,500));
    }

    public void listenerSettings(){
    }

}
