package View.Panels.Center.Cliente;

import Business.SessionManager;
import DAO.Classi.*;
import Model.Articolo;
import Model.Cliente;
import Model.Lista_has_Articolo;
import Model.Utente;
import View.AppFrame;
import View.Listener.CenterListeners.Cliente.ArticlesListListener;
import View.Panels.Center.Cliente.Altro.ArticlesListTableModel;
import View.Panels.Center.Cliente.Altro.FeedBackDialog;


import javax.swing.*;
import java.awt.*;
import java.security.DigestException;
import java.util.ArrayList;

public class ArticlesListPanel extends JPanel {

    private AppFrame appFrame;
    private ArticlesListListener articlesListListener;


    private JPanel topPanel = new JPanel();
        private JLabel inserisciArticolo = new JLabel("Inserisci un nuovo articolo: ");
        private JTextField articoloField = new JTextField();
        private JLabel inserisciQuantita = new JLabel("Quantità: ");
        private JTextField quantitaField = new JTextField();
        private JButton btnAggiungi = new JButton("Aggiungi");
    private JScrollPane currentScrollPane;
    private JTable currentTable;
    private ArticlesListTableModel currentTableModel;
    private JPanel sidePanel = new JPanel();
        private JButton btnFeedBack = new JButton("Feedback");
        private JButton btnElimina = new JButton("Elimina");
    private JPanel bottomPanel = new JPanel();
        private JButton btnTermina = new JButton("Termina");

    private ArrayList<Lista_has_Articolo> lista;
    private int idLista;


    public ArticlesListPanel(AppFrame appFrame, int idLista) {
        this.appFrame = appFrame;
        this.lista = lista;
        articlesListListener = new ArticlesListListener(this, appFrame);

        lista = Lista_has_ArticoloDAO.getInstance().findAllListArticles(idLista);

        if(AcquistoDAO.getInstance().findByIDLista(idLista) == null){
            btnFeedBack.setEnabled(false);
        }


        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();
    }

    public void aggiungi(){
        if(articoloField.getText().isEmpty()||quantitaField.getText().isEmpty()){
            JOptionPane.showMessageDialog(appFrame,
                    "Un campo è vuoto!",
                    "Empty field Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            Articolo a = ArticoloDAO.getInstance().findByName(articoloField.getText());
            if(a == null){
                JOptionPane.showMessageDialog(appFrame,
                        "L'articolo non esiste!",
                        "Wrong Article Error",
                        JOptionPane.ERROR_MESSAGE);
            }else {
                int idArticolo = a.getIdArticolo();
                Lista_has_ArticoloDAO.getInstance().add(idLista, idArticolo, Integer.parseInt(quantitaField.getText()));
                currentTableModel.setLista(Lista_has_ArticoloDAO.getInstance().findAllListArticles(idLista));
                currentTableModel.fireTableDataChanged();
            }
        }
    }

    public void elimina(){
        int selectedRow = currentTable.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(appFrame,
                    "Seleziona l'articolo da recensire!",
                    "Article Selection Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            selectedRow = currentTable.convertRowIndexToModel(selectedRow);
            int idArticolo = currentTableModel.getLista().get(selectedRow).getIdArticolo();
            Lista_has_ArticoloDAO.getInstance().delete(idLista, idArticolo);
            currentTableModel.setLista(Lista_has_ArticoloDAO.getInstance().findAllListArticles(idLista));
            currentTableModel.fireTableDataChanged();
        }
    }

    public void modificaFeedback(){
        int selectedRow = currentTable.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(appFrame,
                    "Seleziona l'articolo da recensire!",
                    "Article Selection Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            selectedRow = currentTable.convertRowIndexToModel(selectedRow);
            int idAcquisto = AcquistoDAO.getInstance().findByIDLista(idLista).getIdAcquisto();
            int idArticolo = currentTableModel.getLista().get(selectedRow).getIdArticolo();
            FeedBackDialog.showDialog(appFrame, "FeedBack Dialog", idAcquisto, idArticolo);
        }
    }

    public void termina(){
        appFrame.getCenter().setCurrentPanel(new ListsPanel(appFrame));
    }

    public void tableSetting(){
        currentTableModel = new ArticlesListTableModel(lista);
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
        sidePanel.setLayout(new GridLayout(8,1,10,10));
        topPanel.setLayout(new FlowLayout());
        currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void componentsAdding(){
        add(topPanel, BorderLayout.NORTH);
            topPanel.add(inserisciArticolo);
            topPanel.add(articoloField);
            topPanel.add(inserisciQuantita);
            topPanel.add(quantitaField);
            topPanel.add(btnAggiungi);
        add(currentScrollPane, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);
            sidePanel.add(btnElimina);
            sidePanel.add(btnFeedBack);
        add(bottomPanel, BorderLayout.SOUTH);
            bottomPanel.add(btnTermina);
    }

    public void componentsSizing(){
        currentScrollPane.setPreferredSize(new Dimension(1000,500));
        articoloField.setPreferredSize(new Dimension(200,30));
        quantitaField.setPreferredSize(new Dimension(50, 30));
    }

    public void listenerSettings(){
        btnAggiungi.setActionCommand("aggiungi");
        btnElimina.setActionCommand("elimina");
        btnFeedBack.setActionCommand("feedback");
        btnTermina.setActionCommand("termina");

        btnAggiungi.addActionListener(articlesListListener);
        btnFeedBack.addActionListener(articlesListListener);
        btnElimina.addActionListener(articlesListListener);
        btnTermina.addActionListener(articlesListListener);
    }

}
