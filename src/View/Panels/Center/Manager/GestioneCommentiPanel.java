package View.Panels.Center.Manager;

import Business.ModelBusiness.FeedbackBusiness;
import View.AppFrame;
import View.Listener.CenterListeners.Manager.GestioneCommentiListener;
import Business.ModelBusiness.TableModels.FeedbackTableModel;

import javax.swing.*;
import java.awt.*;

public class GestioneCommentiPanel extends JPanel {

    private AppFrame appFrame;
    private GestioneCommentiListener gestioneCommentiListener;

    private JLabel labelTitle = new JLabel("gestione Commenti");
    private JScrollPane currentScrollPane;
        private JTable currentTable;
        private FeedbackTableModel currentTableModel;
    private JPanel sidePanel = new JPanel();
        private JButton btnRispondi = new JButton("Rispondi");

    public GestioneCommentiPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        gestioneCommentiListener = new GestioneCommentiListener(this, appFrame);


        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void rispondi(){
        int selectedRow = currentTable.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(appFrame,
                    "Seleziona il commento a cui rispondere!",
                    "Answer Comment Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            selectedRow = currentTable.convertRowIndexToModel(selectedRow);
            int result = FeedbackBusiness.getInstance().rispondi(selectedRow, appFrame);
            switch (result){
                case 0:
                    JOptionPane.showMessageDialog(appFrame,
                            "Risposta salvata con successo!",
                            "Answer Comment Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(appFrame,
                            "Risposta non salvata!",
                            "Answer Comment Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }

    public void tableSetting(){
        currentTable = FeedbackBusiness.getInstance().getTabellaFeedbacks();
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
        add(sidePanel, BorderLayout.EAST);
            sidePanel.add(btnRispondi);
    }

    public void componentsSizing(){
        currentScrollPane.setPreferredSize(new Dimension(1000,500));
    }

    public void listenerSettings(){
        btnRispondi.setActionCommand("rispondi");
        btnRispondi.addActionListener(gestioneCommentiListener);
    }

}
