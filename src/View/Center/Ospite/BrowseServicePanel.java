package View.Center.Ospite;

import DAO.Classi.ServizioDAO;
import Model.Servizio;
import View.AppFrame;
import View.Center.Cliente.ServizioTableModel;
import View.Listener.Ospite.BrowseServiceListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BrowseServicePanel extends JPanel {

    private BrowseServiceListener browseServiceListener;
    private AppFrame appFrame;

    private JLabel labelTitle;

    private JScrollPane currentScrollPane;
    private JTable currentTable;
    private ServizioTableModel currentTableModel;

    private ArrayList<Servizio> lista;

    public BrowseServicePanel(AppFrame appFrame) {

        lista = ServizioDAO.getInstance().findAll();

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
        add(labelTitle, BorderLayout.NORTH);
        add(currentScrollPane, BorderLayout.CENTER);
    }

    public void componentsSizing(){
        currentScrollPane.setPreferredSize(new Dimension(1000,500));
    }

    public void listenerSettings(){
    }

}
