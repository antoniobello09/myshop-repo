package View.Center.Cliente;

import Business.SessionManager;
import DAO.Classi.ListaDAO;
import DAO.Classi.MagazzinoDAO;
import DAO.Classi.SchedaProdottoDAO;
import Model.*;
import View.AppFrame;
import View.Center.Manager.GestioneSchedeProdotto.SchedaProdottoModel;
import View.Center.Manager.GestioneSchedeServizio.SchedaServizioModel;
import View.Listener.Cliente.GestioneArticoliListaListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GestioneArticoliListaPanel extends JPanel {

    private AppFrame appFrame;
    private GestioneArticoliListaListener gestioneArticoliListaListener;

    private JPanel nomePanel = new JPanel();
        private JLabel inserisciNome = new JLabel("Nome della lista: ");
        private JTextField nomeField = new JTextField();
    private JPanel grigliaPanel = new JPanel();

        private JPanel schedeProdottoPanel = new JPanel();
            private JPanel cercaSchedaProdottoPanel = new JPanel();
                private JTextField cercaSchedaProdottoField = new JTextField();
                private JButton btnCercaSchedaProdotto = new JButton("Cerca");
            private JScrollPane schedaProdottoScrollPane;
                private SchedaProdottoModel schedaProdottoTableModel;
                private JTable schedaProdottoTable;
            private JPanel operazioniSchedaProdottoPanel = new JPanel();
                private JButton btnAggiungiProdotto = new JButton("Aggiungi");
                private JButton btnSfogliaSchedeProdotto = new JButton("Sfoglia");

        private JPanel schedeServizioPanel = new JPanel();
            private JPanel cercaSchedaServizioPanel = new JPanel();
                private JTextField cercaSchedaServizioField = new JTextField();
                private JButton btnCercaSchedaServizio = new JButton("Cerca");
            private JScrollPane schedaServizioScrollPane;
                private SchedaServizioModel schedaServizioTableModel;
                private JTable schedaServizioTable;
            private JPanel operazioniSchedaServizioPanel = new JPanel();
                private JButton btnAggiungiServizio = new JButton("Aggiungi");
                private JButton btnSfogliaSchedeServizio = new JButton("Sfoglia");

        private JPanel prodottiPanel = new JPanel();
            private JScrollPane prodottiScrollPane;
                private ProdottoQuantitaModel prodottiModel;
                private JTable prodottiTable;
            private JPanel operazioniProdottiPanel = new JPanel();
                private JButton btnEliminaProdotto = new JButton("Elimina");

        private JPanel serviziPanel = new JPanel();
            private JScrollPane serviziScrollPane;
                private ServizioTableModel serviziModel;
                private JTable serviziTable;
        private JPanel operazioniServiziPanel = new JPanel();
            private JButton btnEliminaServizio = new JButton("Elimina");
    private JPanel salvaPanel = new JPanel();
        private JButton btnSalva = new JButton("Salva");
        private JButton btnAnnulla = new JButton("Annulla");

    private ArrayList<Prodotto_Quantita> listaProdotti;
    private ArrayList<Servizio> listaServizi;
    private Magazzino magazzino;
    private PuntoVendita p;
    private Utente cliente;
    private ArrayList<SchedaServizio> listaSchedeServizio;

    public GestioneArticoliListaPanel(AppFrame appFrame){
        this.appFrame = appFrame;
        this.gestioneArticoliListaListener = new GestioneArticoliListaListener(this, this.appFrame);

        listaServizi = new ArrayList<>();
        listaProdotti = new ArrayList<>();

        p = (PuntoVendita) SessionManager.getInstance().getSession().get("loggedShop");
        cliente = (Utente) SessionManager.getInstance().getSession().get("loggedUser");
        magazzino = p.getMagazzino();

        listaSchedeServizio = p.getServizi();

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

/*
    public GestioneArticoliListaPanel(AppFrame appFrame, Lista lista, boolean isEditable){
        this.appFrame = appFrame;
        this.gestioneArticoliListaListener = new GestioneArticoliListaListener(this, this.appFrame);

        listaProdotti = lista.getProdotti();
        listaServizi = lista.getServizi();

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

        if(!isEditable){

        }
    }
*/
   public void aggiungiProdotto(){
        int selectedIndex = schedaProdottoTable.convertRowIndexToModel(schedaProdottoTable.getSelectedRow());
        prodottiModel.add(schedaProdottoTableModel.getLista().get(selectedIndex));
        prodottiModel.fireTableDataChanged();
   }

   public void aggiungiServizio(){
       int selectedIndex = schedaServizioTable.convertRowIndexToModel(schedaServizioTable.getSelectedRow());
       serviziModel.add(schedaServizioTableModel.getLista().get(selectedIndex));
       serviziModel.fireTableDataChanged();
   }

   public void eliminaProdotto(){
       int selectedIndex = prodottiTable.convertRowIndexToModel(prodottiTable.getSelectedRow());
       prodottiModel.remove(prodottiModel.getLista().get(selectedIndex));
       prodottiModel.fireTableDataChanged();
   }

   public void eliminaServizio(){
       int selectedIndex = serviziTable.convertRowIndexToModel(serviziTable.getSelectedRow());
       serviziModel.remove(serviziModel.getLista().get(selectedIndex));
       serviziModel.fireTableDataChanged();
   }

   public void annulla(){
        appFrame.getCenter().setCurrentPanel(new GestioneListePanel(appFrame));
   }

   public void salva(){
        Lista lista = new Lista();
        lista.setNome(nomeField.getText());
        lista.setIdPuntoVendita(p.getIdPuntoVendita());
        lista.setIdCliente(cliente.getIdUtente());
        lista.setProdotti(prodottiModel.getLista());
        lista.setServizi(serviziModel.getLista());
        ListaDAO.getInstance().add(lista);
        appFrame.getCenter().setCurrentPanel(new GestioneListePanel(appFrame));
   }


    public void tableSetting(){
        schedaProdottoTableModel = new SchedaProdottoModel(magazzino);
        schedaProdottoTable = new JTable(schedaProdottoTableModel);
        schedaProdottoScrollPane = new JScrollPane(schedaProdottoTable);

        schedaServizioTableModel = new SchedaServizioModel(listaSchedeServizio);
        schedaServizioTable = new JTable(schedaServizioTableModel);
        schedaServizioScrollPane = new JScrollPane(schedaServizioTable);

        prodottiModel = new ProdottoQuantitaModel(listaProdotti);
        prodottiTable = new JTable(prodottiModel);
        prodottiScrollPane = new JScrollPane(prodottiTable);

        serviziModel = new ServizioTableModel(listaServizi);
        serviziTable = new JTable(serviziModel);
        serviziScrollPane = new JScrollPane(serviziTable);

    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
            nomePanel.setLayout(new FlowLayout());
            grigliaPanel.setLayout(new GridLayout(2,0,15,15));
                schedeProdottoPanel.setLayout(new BorderLayout());
                    cercaSchedaProdottoPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
                    schedaProdottoScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    schedaProdottoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    operazioniSchedaProdottoPanel.setLayout(new GridLayout(5,1,15,15));
                schedeServizioPanel.setLayout(new BorderLayout());
                    cercaSchedaServizioPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
                    schedaServizioScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    schedaServizioScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    operazioniSchedaServizioPanel.setLayout(new GridLayout(5,1,15,15));
                prodottiPanel.setLayout(new BorderLayout());
                    prodottiScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    prodottiScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    operazioniProdottiPanel.setLayout(new GridLayout(5,1,15,15));
                serviziPanel.setLayout(new BorderLayout());
                    serviziScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    serviziScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    operazioniServiziPanel.setLayout(new GridLayout(5,1,15,15));
            salvaPanel.setLayout(new FlowLayout());
    }

    public void componentsAdding(){
        add(nomePanel, BorderLayout.NORTH);
            nomePanel.add(inserisciNome);
            nomePanel.add(nomeField);
        add(grigliaPanel, BorderLayout.CENTER);
            grigliaPanel.add(schedeProdottoPanel);
                schedeProdottoPanel.add(cercaSchedaProdottoPanel, BorderLayout.NORTH);
                    cercaSchedaProdottoPanel.add(cercaSchedaProdottoField);
                    cercaSchedaProdottoPanel.add(btnCercaSchedaProdotto);
                schedeProdottoPanel.add(schedaProdottoScrollPane, BorderLayout.CENTER);
                schedeProdottoPanel.add(operazioniSchedaProdottoPanel, BorderLayout.EAST);
                    operazioniSchedaProdottoPanel.add(btnAggiungiProdotto);
                    operazioniSchedaProdottoPanel.add(btnSfogliaSchedeProdotto);
            grigliaPanel.add(prodottiPanel);
                prodottiPanel.add(prodottiScrollPane, BorderLayout.CENTER);
                prodottiPanel.add(operazioniProdottiPanel, BorderLayout.EAST);
                    operazioniProdottiPanel.add(btnEliminaProdotto);
            grigliaPanel.add(schedeServizioPanel);
                schedeServizioPanel.add(cercaSchedaServizioPanel, BorderLayout.NORTH);
                    cercaSchedaServizioPanel.add(cercaSchedaServizioField);
                    cercaSchedaServizioPanel.add(btnCercaSchedaServizio);
                schedeServizioPanel.add(schedaServizioScrollPane, BorderLayout.CENTER);
                schedeServizioPanel.add(operazioniSchedaServizioPanel, BorderLayout.EAST);
                    operazioniSchedaServizioPanel.add(btnAggiungiServizio);
                    operazioniSchedaServizioPanel.add(btnSfogliaSchedeServizio);
            grigliaPanel.add(serviziPanel);
                serviziPanel.add(serviziScrollPane, BorderLayout.CENTER);
                serviziPanel.add(operazioniServiziPanel, BorderLayout.EAST);
                    operazioniServiziPanel.add(btnEliminaServizio);
        add(salvaPanel, BorderLayout.SOUTH);
            salvaPanel.add(btnSalva);
            salvaPanel.add(btnAnnulla);
    }

    public void componentsSizing(){
        nomeField.setPreferredSize(new Dimension(200,20));
        grigliaPanel.setPreferredSize(new Dimension(800,500));
        schedaProdottoScrollPane.setPreferredSize(new Dimension(100,20));
        schedaServizioScrollPane.setPreferredSize(new Dimension(300,20));
        prodottiScrollPane.setPreferredSize(new Dimension(300,20));
        serviziScrollPane.setPreferredSize(new Dimension(300,20));
        cercaSchedaProdottoField.setPreferredSize(new Dimension(200,20));
        cercaSchedaServizioField.setPreferredSize(new Dimension(200,20));
    }

    public void listenerSettings(){
        btnCercaSchedaProdotto.addActionListener(gestioneArticoliListaListener);
        btnSfogliaSchedeProdotto.addActionListener(gestioneArticoliListaListener);
        btnCercaSchedaServizio.addActionListener(gestioneArticoliListaListener);
        btnSfogliaSchedeServizio.addActionListener(gestioneArticoliListaListener);
        btnAggiungiProdotto.addActionListener(gestioneArticoliListaListener);
        btnAggiungiServizio.addActionListener(gestioneArticoliListaListener);
        btnEliminaProdotto.addActionListener(gestioneArticoliListaListener);
        btnEliminaServizio.addActionListener(gestioneArticoliListaListener);
        btnAnnulla.addActionListener(gestioneArticoliListaListener);
        btnSalva.addActionListener(gestioneArticoliListaListener);


        btnCercaSchedaProdotto.setActionCommand("cercaSchedaProdotto");
        btnSfogliaSchedeProdotto.setActionCommand("sfogliaSchedeProdotto");
        btnCercaSchedaServizio.setActionCommand("cercaSchedaServizio");
        btnSfogliaSchedeServizio.setActionCommand("sfogliaSchedeServizio");
        btnAggiungiProdotto.setActionCommand("aggiungiProdotto");
        btnAggiungiServizio.setActionCommand("aggiungiServizio");
        btnEliminaProdotto.setActionCommand("eliminaProdotto");
        btnEliminaServizio.setActionCommand("eliminaServizio");
        btnSalva.setActionCommand("salva");
        btnAnnulla.setActionCommand("annulla");
    }

    public SchedaProdotto findProdottoByName(String nomeProdotto){
        for(int i=0;i<magazzino.getPiani().size();i++){
            for(int j=0;j<magazzino.getPiani().get(i).getCorsie().size();j++){
                for(int k=0;k<magazzino.getPiani().get(i).getCorsie().get(j).getScaffali().size();k++){
                    if(magazzino.getPiani().get(i).getCorsie().get(j).getScaffali().get(k).getProdotto().getProdotto().getNome().equals(nomeProdotto)) {
                        return magazzino.getPiani().get(i).getCorsie().get(j).getScaffali().get(k).getProdotto();
                    }
                }
            }
        }
        for(int i=0;i<magazzino.getProdottiSenzaPosizione().size();i++){
            if(magazzino.getProdottiSenzaPosizione().get(i).getProdotto().getNome().equals(nomeProdotto)){
                return magazzino.getProdottiSenzaPosizione().get(i);
            }
        }
        return null;
    }

    public SchedaServizio findServizioByName(String nomeServizio){
        for(int i=0; i<p.getServizi().size();i++){
            if(p.getServizi().get(i).getServizio().getNome().equals(nomeServizio)){
                return p.getServizi().get(i);
            }
        }
        return null;
    }


    public void cercaSchedaProdotto(){
        ArrayList<SchedaProdotto> newList = new ArrayList<>();
        newList.add(findProdottoByName(cercaSchedaProdottoField.getText()));
        schedaProdottoTableModel.setLista(newList);
        schedaProdottoTableModel.fireTableDataChanged();
    }

    public void cercaSchedaServizio(){
        ArrayList<SchedaServizio> newList = new ArrayList<>();
        newList.add(findServizioByName(cercaSchedaServizioField.getText()));
        schedaServizioTableModel.setLista(newList);
        schedaServizioTableModel.fireTableDataChanged();
    }

    public void sfogliaSchedeProdotto(){
        schedaProdottoTableModel.setMagazzino(magazzino);
        schedaProdottoTableModel.fireTableDataChanged();
    }

    public void sfogliaSchedeServizio(){
        schedaServizioTableModel.setLista(p.getServizi());
        schedaServizioTableModel.fireTableDataChanged();
    }


}
