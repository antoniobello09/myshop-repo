package View.Panels.Center.Cliente;

import Business.ModelBusiness.Lista_has_ArticoloBusiness;
import View.AppFrame;
import View.Listener.CenterListeners.Cliente.ArticlesListListener;
import Business.ModelBusiness.TableModels.ArticlesListTableModel;


import javax.swing.*;
import java.awt.*;

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
    private JPanel sidePanel = new JPanel();
        private JButton btnFeedBack = new JButton("Feedback");
        private JButton btnElimina = new JButton("Elimina");
    private JPanel bottomPanel = new JPanel();
        private JButton btnTermina = new JButton("Termina");

    private int idLista;


    public ArticlesListPanel(AppFrame appFrame, int idLista) {
        this.appFrame = appFrame;
        this.idLista = idLista;
        articlesListListener = new ArticlesListListener(this, appFrame);


        if(!Lista_has_ArticoloBusiness.getInstance().isAcquistato(idLista)){
            btnFeedBack.setEnabled(false);
        }else{
            articoloField.setEditable(false);
            quantitaField.setEditable(false);
            btnAggiungi.setEnabled(false);
            btnElimina.setEnabled(false);
        }


        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();
    }

    public void aggiungi(){
        if(articoloField.getText().isEmpty()){
            JOptionPane.showMessageDialog(appFrame,
                    "Il campo articolo è vuoto!",
                    "Empty field Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            int result = Lista_has_ArticoloBusiness.getInstance().aggiungi(articoloField.getText(), quantitaField.getText());
            switch(result){
                case 0:
                    JOptionPane.showMessageDialog(appFrame,
                            "L'articolo è stato aggiunto alla lista!",
                            "Add Article List Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(appFrame,
                            "L'articolo non esiste!",
                            "Add Article List Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(appFrame,
                            "L'articolo non è stato aggiunto!",
                            "Add Article List Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(appFrame,
                            "Il campo quantità è vuoto!",
                            "Quantity Field Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                }
            articoloField.setText("");
            quantitaField.setText("");
        }
    }

    public void elimina(){
        int selectedRow = currentTable.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(appFrame,
                    "Seleziona l'articolo da eliminare!",
                    "Article Selection Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            selectedRow = currentTable.convertRowIndexToModel(selectedRow);
            int result = Lista_has_ArticoloBusiness.getInstance().elimina(selectedRow);
            switch (result){
                case 0:
                    JOptionPane.showMessageDialog(appFrame,
                            "Articolo eliminato con successo!",
                            "Article Addition Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(appFrame,
                            "Articolo non eliminato!",
                            "Article Addition Error",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
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
            int result = Lista_has_ArticoloBusiness.getInstance().modificaFeedback(appFrame, selectedRow);
            switch (result){
                case 0:
                    JOptionPane.showMessageDialog(appFrame,
                            "Feedback modificato con successo!",
                            "Modify Feedback Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(appFrame,
                            "Feedback non modificato!",
                            "Modify Feedback Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }

    public void termina(){
        appFrame.getCenter().setCurrentPanel(new ListsPanel(appFrame));
    }

    public void tableSetting(){
        currentTable = Lista_has_ArticoloBusiness.getInstance().getTabellaArticoli(idLista);
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
