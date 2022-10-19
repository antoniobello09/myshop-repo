package View.Panels.Center.Cliente;

import Business.ModelBusiness.ListaBusiness;
import View.AppFrame;
import View.Listener.CenterListeners.Cliente.ListsListener;

import javax.swing.*;
import java.awt.*;

public class ListsPanel extends JPanel {

    private AppFrame appFrame;
    private ListsListener listsListener;

    private JPanel topPanel = new JPanel();
        private JLabel inserisciLista = new JLabel("Inserisci una nuova lista: ");
        private JTextField listaField = new JTextField();
        private JButton btnCrea = new JButton("Crea");
    private JScrollPane currentScrollPane;
    private JTable currentTable;
    private JPanel sidePanel = new JPanel();
    private JButton btnModifica = new JButton("Dettagli");
    private JButton btnAcquista = new JButton("Acquista");
    private JButton btnPDF = new JButton("Scarica PDF");

    private int idCliente;

    public ListsPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        listsListener = new ListsListener(this, appFrame);

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
            int result = ListaBusiness.getInstance().crea(listaField.getText());
            switch (result){
                case 0:
                    JOptionPane.showMessageDialog(appFrame,
                            "Lista creata con successo!",
                            "Create list success",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(appFrame,
                            "Lista non creata!",
                            "Create list error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }
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
            ListaBusiness.getInstance().modifica(selectedRow, appFrame);
        }
    }

    public void acquista(){
        int selectedRow = currentTable.getSelectedRow();
        selectedRow = currentTable.convertRowIndexToModel(selectedRow);
        int result = ListaBusiness.getInstance().acquista(selectedRow);
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(appFrame,
                    "Seleziona la lista da acquistare!",
                    "List Selection Error",
                    JOptionPane.ERROR_MESSAGE);
        }else if (result == 1) {
            JOptionPane.showMessageDialog(appFrame,
                    "Lista già acquistata!",
                    "Purchased List Error",
                    JOptionPane.ERROR_MESSAGE);
        }else if(result == 2){
            JOptionPane.showMessageDialog(appFrame,
                    "Lista non acquistata!",
                    "Purchased List Error",
                    JOptionPane.ERROR_MESSAGE);
        }else if(result == 0){
            JOptionPane.showMessageDialog(appFrame,
                    "Lista acquistata!",
                    "Purchased List Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void inviaPDF(){
        int selectedRow = currentTable.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(appFrame,
                    "Seleziona la lista da inviarti come pdf!",
                    "List Selection Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            selectedRow = currentTable.convertRowIndexToModel(selectedRow);
            ListaBusiness.getInstance().inviaPDF(selectedRow);
        }
    }

    public void tableSetting(){
        currentTable = ListaBusiness.getInstance().getTabellaListe();
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
