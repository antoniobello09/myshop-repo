package View.Panels.Center.Ospite;

import Business.ModelBusiness.ProdottoBusiness;
import View.AppFrame;
import View.Listener.CenterListeners.Ospite.BrowseProductListener;

import javax.swing.*;
import java.awt.*;

public class BrowseProductPanel extends JPanel {
    private BrowseProductListener browseProductListener;
    private AppFrame appFrame;

    private JLabel labelTitle = new JLabel("Catalogo Prodotti");
    private JScrollPane currentScrollPane;
    private JTable currentTable;

    public BrowseProductPanel(AppFrame appFrame) {


        this.appFrame = appFrame;
        browseProductListener = new BrowseProductListener(this, this.appFrame);

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();
    }

    public void tableSetting(){
        currentTable = ProdottoBusiness.getInstance().getTabellaProdotti();
        currentTable.setRowHeight(60);
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
