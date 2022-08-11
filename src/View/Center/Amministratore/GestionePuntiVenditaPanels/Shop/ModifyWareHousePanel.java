package View.Center.Amministratore.GestionePuntiVenditaPanels.Shop;

import DAO.Classi.MagazzinoDAO;
import Model.Corsia;
import Model.Piano;
import Model.PuntoVendita;
import Model.Scaffale;
import View.AppFrame;
import View.Listener.Amministratore.GestionePuntiVendita.Shop.ModifyWareHouseListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class ModifyWareHousePanel extends JPanel {

    private AppFrame appFrame;
    private ModifyWareHouseListener modifyWareHouseListener;

    private JPanel westPanel = new JPanel();
        private PianoTableModel pianoTableModel;
        private ListSelectionModel pianoListSelectionModel;
        private JTable pianoTable;
        private JScrollPane pianoScrollPane;
        private JPanel pianoButtonsPanel = new JPanel();
            private JButton btnAggiungiPiano = new JButton("+");
            private JButton btnTogliPiano = new JButton("-");
    private JPanel centerPanel = new JPanel();
        private CorsiaTableModel corsiaTableModel;
        private ListSelectionModel corsiaListSelectionModel;
        private JTable corsiaTable;
        private JScrollPane corsiaScrollPane;
        private JPanel corsiaButtonsPanel = new JPanel();
            private JButton btnAggiungiCorsia = new JButton("+");
            private JButton btnTogliCorsia = new JButton("-");
    private JPanel eastPanel = new JPanel();
        private ScaffaleTableModel scaffaleTableModel;
        private ListSelectionModel scaffaleListSelectionModel;
        private JTable scaffaleTable;
        private JScrollPane scaffaleScrollPane;
        private JPanel scaffaleButtonsPanel = new JPanel();
            private JButton btnAggiungiScaffale = new JButton("+");
            private JButton btnTogliScaffale = new JButton("-");
    private JPanel operationPanel = new JPanel();
        private JButton btnSalva = new JButton("Salva");
        private JButton btnReset = new JButton("Reset");
        private JButton btnAnnulla = new JButton("Annulla");

    private PuntoVendita puntoVendita;
    private int pianoSelected;
    private int corsiaSelected;
    private int scaffaleSelected;


    public ModifyWareHousePanel(AppFrame appFrame, PuntoVendita puntoVendita){

        this.appFrame = appFrame;
        modifyWareHouseListener = new ModifyWareHouseListener(this, this.appFrame);

        this.puntoVendita = puntoVendita;

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }


    public void annulla(){
        appFrame.getCenter().setCurrentPanel(new ModifyShopPanel(appFrame));
    }

    public void reset(){

    }

    public void salva(){
        MagazzinoDAO.getInstance().update(puntoVendita.getMagazzino());
    }

    public void aggiungiPiano(){
        Piano p = new Piano();
        ArrayList<Scaffale> scaffali = new ArrayList<>();
        Scaffale scaffale = new Scaffale();
        scaffale.setNumero(1);
        scaffali.add(scaffale);
        ArrayList<Corsia> corsie = new ArrayList<>();
        Corsia corsia = new Corsia();
        corsia.setNumero(1);
        corsia.setScaffali(scaffali);
        corsie.add(corsia);
        p.setNumero(pianoTableModel.getLista().size() + 1);
        p.setCorsie(corsie);
        pianoTableModel.getLista().add(p);
        pianoTableModel.fireTableDataChanged();
    }

    public void aggiungiCorsia(){
        ArrayList<Scaffale> scaffali = new ArrayList<>();
        Scaffale scaffale = new Scaffale();
        scaffale.setNumero(1);
        scaffali.add(scaffale);
        Corsia c = new Corsia();
        c.setNumero(corsiaTableModel.getLista().size() + 1);
        c.setScaffali(scaffali);
        corsiaTableModel.getLista().add(c);
        corsiaTableModel.fireTableDataChanged();
    }

    public void aggiungiScaffale(){
        Scaffale s = new Scaffale();
        s.setNumero(scaffaleTableModel.getLista().size() + 1);
        scaffaleTableModel.getLista().add(s);
        scaffaleTableModel.fireTableDataChanged();
    }

    public void togliPiano(){
        if(pianoTableModel.getLista().size()>1) {
            pianoTableModel.getLista().remove(pianoTableModel.getLista().size());
            pianoTableModel.fireTableDataChanged();
        } else return;
    }

    public void togliCorsia(){
        if(corsiaTableModel.getLista().size()>1) {
            corsiaTableModel.getLista().remove(corsiaTableModel.getLista().size());
            corsiaTableModel.fireTableDataChanged();
        }else return;
    }

    public void togliScaffale(){
        if(scaffaleTableModel.getLista().size()>1) {
            scaffaleTableModel.getLista().remove(scaffaleTableModel.getLista().size());
            scaffaleTableModel.fireTableDataChanged();
        }else return;
    }

    public void tableSetting(){
        pianoTableModel = new PianoTableModel(puntoVendita.getMagazzino().getPiani());
        pianoTable = new JTable(pianoTableModel);
        pianoListSelectionModel = pianoTable.getSelectionModel();
        pianoListSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pianoScrollPane = new JScrollPane(pianoTable);

        corsiaTableModel = new CorsiaTableModel(new ArrayList<>());
        corsiaTable = new JTable(corsiaTableModel);
        corsiaListSelectionModel = corsiaTable.getSelectionModel();
        corsiaListSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        corsiaScrollPane = new JScrollPane(corsiaTable);

        scaffaleTableModel = new ScaffaleTableModel(new ArrayList<>());
        scaffaleTable = new JTable(scaffaleTableModel);
        scaffaleListSelectionModel = scaffaleTable.getSelectionModel();
        scaffaleListSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scaffaleScrollPane = new JScrollPane(scaffaleTable);

    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
            westPanel.setLayout(new GridLayout(0,2,5,15));
                pianoButtonsPanel.setLayout(new GridLayout(8, 1, 5, 15));
            centerPanel.setLayout(new GridLayout(0, 2, 5, 15));
                corsiaButtonsPanel.setLayout(new GridLayout(8, 1, 5, 15));
            eastPanel.setLayout(new GridLayout(0, 2, 5, 15));
                scaffaleButtonsPanel.setLayout(new GridLayout(8, 1, 5, 15));
    }

    public void componentsAdding(){
        add(westPanel, BorderLayout.WEST);
            westPanel.add(pianoScrollPane);
            westPanel.add(pianoButtonsPanel);
                pianoButtonsPanel.add(btnAggiungiPiano);
                pianoButtonsPanel.add(btnTogliPiano);
        add(centerPanel, BorderLayout.CENTER);
            centerPanel.add(corsiaScrollPane);
            centerPanel.add(corsiaButtonsPanel);
                corsiaButtonsPanel.add(btnAggiungiCorsia);
                corsiaButtonsPanel.add(btnTogliCorsia);
        add(eastPanel, BorderLayout.EAST);
            eastPanel.add(scaffaleScrollPane);
            eastPanel.add(scaffaleButtonsPanel);
                scaffaleButtonsPanel.add(btnAggiungiScaffale);
                scaffaleButtonsPanel.add(btnTogliScaffale);
        add(operationPanel, BorderLayout.SOUTH);
            operationPanel.add(btnAnnulla);
            operationPanel.add(btnReset);
            operationPanel.add(btnSalva);
    }

    public void componentsSizing(){
        setPreferredSize(new Dimension(600,500));
        westPanel.setPreferredSize(new Dimension(200, 500));
        centerPanel.setPreferredSize(new Dimension(200, 500));
        eastPanel.setPreferredSize(new Dimension(200, 500));
        westPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        eastPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    }

    public void listenerSettings(){
        btnAnnulla.addActionListener(modifyWareHouseListener);
        btnReset.addActionListener(modifyWareHouseListener);
        btnSalva.addActionListener(modifyWareHouseListener);

        pianoListSelectionModel.addListSelectionListener(new PianoSelectionHandler());
        corsiaListSelectionModel.addListSelectionListener(new CorsiaSelectionHandler());

        btnAggiungiPiano.addActionListener(modifyWareHouseListener);
        btnTogliPiano.addActionListener(modifyWareHouseListener);
        btnAggiungiCorsia.addActionListener(modifyWareHouseListener);
        btnTogliCorsia.addActionListener(modifyWareHouseListener);
        btnAggiungiScaffale.addActionListener(modifyWareHouseListener);
        btnTogliScaffale.addActionListener(modifyWareHouseListener);

        btnAnnulla.setActionCommand("annulla");
        btnReset.setActionCommand("reset");
        btnSalva.setActionCommand("salva");
        btnAggiungiPiano.setActionCommand("aggiungiPiano");
        btnTogliPiano.setActionCommand("togliPiano");
        btnAggiungiCorsia.setActionCommand("aggiungiCorsia");
        btnTogliCorsia.setActionCommand("togliCorsia");
        btnAggiungiScaffale.setActionCommand("aggiungiScaffale");
        btnTogliScaffale.setActionCommand("togliScaffale");

    }

    class PianoSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            pianoSelected = lsm.getMaxSelectionIndex();
            if(pianoSelected != -1)
                corsiaTableModel.setLista(puntoVendita.getMagazzino().getPiani().get(pianoSelected).getCorsie());
            else
                corsiaTableModel.setLista(new ArrayList<>());
            scaffaleTableModel.setLista(new ArrayList<>());
            corsiaListSelectionModel.setLeadSelectionIndex(corsiaSelected);
            corsiaTableModel.fireTableDataChanged();
        }
    }

    class CorsiaSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            corsiaSelected = lsm.getMaxSelectionIndex();
            if(corsiaSelected != -1)
                scaffaleTableModel.setLista(puntoVendita.getMagazzino().getPiani().get(pianoSelected).getCorsie().get(corsiaSelected).getScaffali());
            else
                scaffaleTableModel.setLista(new ArrayList<>());
            scaffaleTableModel.fireTableDataChanged();
        }
    }

}
