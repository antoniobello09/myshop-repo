package View.Panels.Center.Cliente;

import Business.Bridge.Documento;
import Business.Bridge.DocumentoListaAcquisto;
import Business.Bridge.PdfBoxAPI;
import Business.SessionManager;
import DAO.Classi.*;
import Model.*;
import View.AppFrame;
import View.Listener.CenterListeners.Cliente.ListsListener;
import View.Panels.Center.Cliente.Altro.ListsTableModel;
import View.Panels.Center.Manager.Altro.ClientiTableModel;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class ListsPanel extends JPanel {

    private AppFrame appFrame;
    private ListsListener listsListener;

    private JPanel topPanel = new JPanel();
        private JLabel inserisciLista = new JLabel("Inserisci una nuova lista: ");
        private JTextField listaField = new JTextField();
        private JButton btnCrea = new JButton("Crea");
    private JScrollPane currentScrollPane;
    private JTable currentTable;
    private ListsTableModel currentTableModel;
    private JPanel sidePanel = new JPanel();
    private JButton btnModifica = new JButton("Dettagli");
    private JButton btnAcquista = new JButton("Acquista");
    private JButton btnPDF = new JButton("Scarica PDF");

    private ArrayList<Lista> lista;
    private Lista l;
    private int idCliente;

    public ListsPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        listsListener = new ListsListener(this, appFrame);

        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");
        idCliente = u.getIdUtente();
        lista = ListaDAO.getInstance().findAll(idCliente);


        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();
    }

    public void crea(){
        if(listaField.getText().isEmpty()){
            JOptionPane.showMessageDialog(appFrame,
                    "Il campo è vuoto!",
                    "Empty field Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            Lista l = new Lista(listaField.getText(), idCliente);
            ListaDAO.getInstance().add(l);
            currentTableModel.setLista(ListaDAO.getInstance().findAll(idCliente));
            currentTableModel.fireTableDataChanged();
            listaField.setText("");
        }
    }

    public void modifica(){
        int selectedRow = currentTable.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(appFrame,
                    "Seleziona la lista da modificare!",
                    "List Selection Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            selectedRow = currentTable.convertRowIndexToModel(selectedRow);
            appFrame.getCenter().setCurrentPanel(new ArticlesListPanel(appFrame, currentTableModel.getLista().get(selectedRow).getIdLista()));
        }
    }

    public void acquista(){
        int selectedRow = currentTable.getSelectedRow();
        selectedRow = currentTable.convertRowIndexToModel(selectedRow);
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(appFrame,
                    "Seleziona la lista da acquistare!",
                    "List Selection Error",
                    JOptionPane.ERROR_MESSAGE);
        }else if (AcquistoDAO.getInstance().findByIDLista(currentTableModel.getLista().get(selectedRow).getIdLista())!=null) {
            JOptionPane.showMessageDialog(appFrame,
                    "Lista già acquistata!",
                    "Purchased List Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            int idPuntoVendita = (int)SessionManager.getInstance().getSession().get("idPuntoVendita");
            Acquisto a = new Acquisto(idPuntoVendita, Date.valueOf(LocalDate.now()), currentTableModel.getLista().get(selectedRow).getIdLista());
            AcquistoDAO.getInstance().add(a);
            currentTableModel.setLista(ListaDAO.getInstance().findAll(idCliente));
            currentTableModel.fireTableDataChanged();
        }
    }

    public void scaricaPDF(){
        int selectedRow = currentTable.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(appFrame,
                    "Seleziona la lista da scaricare!",
                    "List Selection Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            selectedRow = currentTable.convertRowIndexToModel(selectedRow);
            int idLista = currentTableModel.getLista().get(selectedRow).getIdLista();
            ArrayList<Lista_has_Articolo> l = Lista_has_ArticoloDAO.getInstance().findAllListArticles(idLista);
            Documento listaAcquisto = new DocumentoListaAcquisto(l, new PdfBoxAPI());

            //prendere l'utente loggato dalla sessione e ottenere la mail dall'oggetto cliente
            Utente u = (Utente)SessionManager.getInstance().getSession().get("loggedUser");
            listaAcquisto.invia(u.getEmail());
        }
    }

    public void tableSetting(){
        currentTableModel = new ListsTableModel(lista);
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
        sidePanel.setLayout(new GridLayout(8,1,10,10));
        currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void componentsAdding(){
        add(topPanel, BorderLayout.NORTH);
            topPanel.add(inserisciLista);
            topPanel.add(listaField);
            topPanel.add(btnCrea);
        add(currentScrollPane, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);
            sidePanel.add(btnModifica);
            sidePanel.add(btnAcquista);
            sidePanel.add(btnPDF);
    }

    public void componentsSizing(){
        currentScrollPane.setPreferredSize(new Dimension(1000,500));
        listaField.setPreferredSize(new Dimension(200,30));
    }

    public void listenerSettings(){
        btnCrea.setActionCommand("crea");
        btnAcquista.setActionCommand("acquista");
        btnModifica.setActionCommand("modifica");
        btnPDF.setActionCommand("pdf");

        btnCrea.addActionListener(listsListener);
        btnAcquista.addActionListener(listsListener);
        btnModifica.addActionListener(listsListener);
        btnPDF.addActionListener(listsListener);
    }




}
