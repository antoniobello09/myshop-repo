package View.Center.Cliente;

import Model.FeedBack;
import Model.Lista;
import View.AppFrame;
import View.Listener.Cliente.DettagliListaListener;
import View.ShopDialog;

import javax.swing.*;
import java.awt.*;


public class DettagliListaPanel extends JPanel {

    private AppFrame appFrame;
    private DettagliListaListener dettagliListaListener;

    private JTable currentTable;
    private ArticoloTableModel currentTableModel;
    private JScrollPane currentScrollPane;

    private JPanel operazioniPanel = new JPanel();
        private JButton btnPdf = new JButton("Manda PDF");
        private JButton btnFeedback = new JButton("Lascia feedback");

    private Lista lista;


    public DettagliListaPanel(AppFrame appFrame, Lista lista){

        this.appFrame = appFrame;
        dettagliListaListener = new DettagliListaListener(this, appFrame);
        this.lista = lista;
        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void mandaPdf(){

    }

    public void commenta(){
        FeedBack feedback = FeedbackDialog.showDialog(this,
                null,
                "Feedback",
                "Feedback",
                null,
                null,
                null);
    }

    public void tableSetting(){
        currentTableModel = new ArticoloTableModel(lista);
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
        currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        operazioniPanel.setLayout(new GridLayout(8,1,15,15));
    }


    public void componentsAdding(){
        add(currentScrollPane, BorderLayout.CENTER);
        add(operazioniPanel, BorderLayout.EAST);
        operazioniPanel.add(btnPdf);
        operazioniPanel.add(btnFeedback);
    }

    public void componentsSizing(){
        //cercaField.setPreferredSize(new Dimension(200,20));
    }

    public void listenerSettings(){
        btnPdf.addActionListener(dettagliListaListener);
        btnFeedback.addActionListener(dettagliListaListener);

        btnPdf.setActionCommand("pdf");
        btnFeedback.setActionCommand("feedback");
    }

}
