package View.Panels.Center.Ospite;

import DAO.Classi.ProdottoDAO;
import Model.Prodotto;
import View.AppFrame;
import View.Listener.CenterListeners.Ospite.BrowseProductListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BrowseProductPanel extends JPanel {
    private BrowseProductListener browseProductListener;
    private AppFrame appFrame;

    private JLabel labelTitle = new JLabel("Catalogo Prodotti");
    private JScrollPane currentScrollPane;
    private JTable currentTable;
    private ProdottiTableModel currentTableModel;

    private ArrayList<Prodotto> lista;

    public BrowseProductPanel(AppFrame appFrame) {

        lista = ProdottoDAO.getInstance().findAll();

        this.appFrame = appFrame;
        browseProductListener = new BrowseProductListener(this, this.appFrame);

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();
    }

    public void tableSetting(){
        currentTableModel = new ProdottiTableModel(lista);
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
