package View.Center.Amministratore.GestionePuntiVenditaPanels;

import DAO.Classi.*;
import Model.PuntoVendita;
import Model.SchedaProdotto;
import Model.SchedaServizio;
import View.AppFrame;
import View.Center.Amministratore.GestionePuntiVenditaPanels.AggiungiShopPanel.AddShopPanel;
import View.Listener.Amministratore.GestionePuntiVendita.Shop.AssociaArticoliListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AssociaArticoliPanel extends JPanel {

    private AppFrame appFrame;
    private PuntoVendita puntoVendita;

    private JPanel prodottoPanel = new JPanel();
        private JPanel associaPPanel = new JPanel();
            private JLabel inserisciProdotto = new JLabel("Associa un prodotto");
            private JTextField prodottoField = new JTextField();
            private JButton btnAssociaP = new JButton("Associa");
        private AssociaSchedaProdottoTableModel associaSchedaProdottoTableModel;
        private JTable associaSchedaProdottoTable;
        private JScrollPane associaSchedaProdottoScrollPane;
        private JPanel operazioniPPanel = new JPanel();
            private JButton btnEliminaP = new JButton("Elimina");

    private JPanel servizioPanel = new JPanel();
        private JPanel associaSPanel = new JPanel();
            private JLabel inserisciServizio = new JLabel("Associa un servizio");
            private JTextField servizioField = new JTextField();
            private JButton btnAssociaS = new JButton("Associa");
        private AssociaSchedaServizioTableModel associaSchedaServizioTableModel;
        private JTable associaSchedaServizioTable;
        private JScrollPane associaSchedaServizioScrollPane;
        private JPanel operazioniSPanel = new JPanel();
            private JButton btnEliminaS = new JButton("Elimina");
        private JPanel annullaPanel = new JPanel();
            private JButton btnAnnulla = new JButton("Annulla");


    private ArrayList<SchedaProdotto> listaP = new ArrayList<>();
    private ArrayList<SchedaServizio> listaS;
    private AssociaArticoliListener associaArticoliListener;
    private int selectedRow;

    public AssociaArticoliPanel(AppFrame appFrame, PuntoVendita puntoVendita){
        this.appFrame = appFrame;
        this.puntoVendita = puntoVendita;

        associaArticoliListener = new AssociaArticoliListener(this, this.appFrame);

        listaP.addAll(puntoVendita.getMagazzino().getProdottiSenzaPosizione());
        for(int i=0;i<puntoVendita.getMagazzino().getPiani().size();i++){
            for(int j=0;j<puntoVendita.getMagazzino().getPiani().get(i).getCorsie().size();j++){
                for(int k=0;k<puntoVendita.getMagazzino().getPiani().get(i).getCorsie().get(j).getScaffali().size();k++){
                    if(puntoVendita.getMagazzino().getPiani().get(i).getCorsie().get(j).getScaffali().get(k).getProdotto() != null) {
                        listaP.add(puntoVendita.getMagazzino().getPiani().get(i).getCorsie().get(j).getScaffali().get(k).getProdotto());
                    }
                }
            }
        }

        listaS = puntoVendita.getServizi();

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }



    public void associaProdotto(){
        SchedaProdotto schedaProdotto = new SchedaProdotto();
        schedaProdotto.setProdotto(ProdottoDAO.getInstance().findByName(prodottoField.getText()));
        MagazzinoDAO.getInstance().addSchedaProdotto(schedaProdotto, puntoVendita.getMagazzino().getIdMagazzino());
        associaSchedaProdottoTableModel.getLista().add(schedaProdotto);
        associaSchedaProdottoTableModel.fireTableDataChanged();
    }

    public void associaServizio(){
        SchedaServizio schedaServizio = new SchedaServizio();
        schedaServizio.setServizio(ServizioDAO.getInstance().findByName(servizioField.getText()));
        SchedaServizioDAO.getInstance().add(schedaServizio, puntoVendita.getIdPuntoVendita());
        associaSchedaServizioTableModel.getLista().add(schedaServizio);
        associaSchedaServizioTableModel.fireTableDataChanged();
    }

    public void eliminaProdotto(){
        selectedRow = associaSchedaProdottoTable.convertRowIndexToModel(associaSchedaProdottoTable.getSelectedRow());
        SchedaProdottoDAO.getInstance().delete(associaSchedaProdottoTableModel.getLista().get(selectedRow));
        associaSchedaProdottoTableModel.getLista().remove(selectedRow);
        associaSchedaProdottoTableModel.fireTableDataChanged();
    }

    public void eliminaServizio(){
        selectedRow = associaSchedaServizioTable.convertRowIndexToModel(associaSchedaServizioTable.getSelectedRow());
        SchedaServizioDAO.getInstance().delete(associaSchedaServizioTableModel.getLista().get(selectedRow));
        associaSchedaServizioTableModel.getLista().remove(selectedRow);
        associaSchedaServizioTableModel.fireTableDataChanged();
    }

    public void annulla(){
        appFrame.getCenter().setCurrentPanel(new AddShopPanel(appFrame));
    }

    public void tableSetting(){
        associaSchedaProdottoTableModel = new AssociaSchedaProdottoTableModel(listaP);
        associaSchedaProdottoTable = new JTable(associaSchedaProdottoTableModel);
        associaSchedaProdottoScrollPane = new JScrollPane(associaSchedaProdottoTable);

        associaSchedaServizioTableModel = new AssociaSchedaServizioTableModel(listaS);
        associaSchedaServizioTable = new JTable(associaSchedaServizioTableModel);
        associaSchedaServizioScrollPane = new JScrollPane(associaSchedaServizioTable);
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
            prodottoPanel.setLayout(new BorderLayout());
                associaPPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
                associaSchedaProdottoScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                associaSchedaProdottoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                operazioniPPanel.setLayout(new GridLayout(8,1,15,55));
            servizioPanel.setLayout(new BorderLayout());
                associaSPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
                associaSchedaServizioScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                associaSchedaServizioScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                operazioniSPanel.setLayout(new GridLayout(8,1,15,55));
            annullaPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
    }

    public void componentsAdding(){
        add(prodottoPanel, BorderLayout.EAST);
            prodottoPanel.add(associaPPanel, BorderLayout.NORTH);
                associaPPanel.add(inserisciProdotto);
                associaPPanel.add(prodottoField);
                associaPPanel.add(btnAssociaP);
            prodottoPanel.add(associaSchedaProdottoScrollPane, BorderLayout.CENTER);
            prodottoPanel.add(operazioniPPanel, BorderLayout.EAST);
                operazioniPPanel.add(btnEliminaP);
        add(servizioPanel, BorderLayout.WEST);
            servizioPanel.add(associaSPanel, BorderLayout.NORTH);
                associaSPanel.add(inserisciServizio);
                associaSPanel.add(servizioField);
                associaSPanel.add(btnAssociaS);
            servizioPanel.add(associaSchedaServizioScrollPane, BorderLayout.CENTER);
            servizioPanel.add(operazioniSPanel, BorderLayout.EAST);
                operazioniSPanel.add(btnEliminaS);
        add(annullaPanel, BorderLayout.SOUTH);
            annullaPanel.add(btnAnnulla);
    }

    public void componentsSizing(){
        prodottoField.setPreferredSize(new Dimension(200,20));
        servizioField.setPreferredSize(new Dimension(200,20));
    }

    public void listenerSettings(){
        btnAssociaP.addActionListener(associaArticoliListener);
        btnAssociaS.addActionListener(associaArticoliListener);
        btnEliminaP.addActionListener(associaArticoliListener);
        btnEliminaS.addActionListener(associaArticoliListener);
        btnAnnulla.addActionListener(associaArticoliListener);

        btnAssociaP.setActionCommand("associaProdotto");
        btnAssociaS.setActionCommand("associaServizio");
        btnEliminaP.setActionCommand("eliminaProdotto");
        btnEliminaS.setActionCommand("eliminaServizio");
        btnAnnulla.setActionCommand("annulla");
    }



}
