package View.Panels.Center.Manager;

import Business.ModelBusiness.SchedaProdottoBusiness;
import View.AppFrame;
import View.Listener.CenterListeners.Manager.GestioneDisponibilitaListener;
import Business.ModelBusiness.TableModels.DisponibilitaTableModel;

import javax.swing.*;
import java.awt.*;

public class GestioneDisponibilitaPanel extends JPanel {

    private AppFrame appFrame;
    private GestioneDisponibilitaListener gestioneDisponibilitaListener;

    private JLabel labelTitle = new JLabel("Disponibilità Articoli");
    private JScrollPane currentScrollPane;
        private JTable currentTable;
        private DisponibilitaTableModel currentTableModel;
    private JPanel sidePanel = new JPanel();
        private JButton btnRifornisci = new JButton("Rifornisci");


    public GestioneDisponibilitaPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        gestioneDisponibilitaListener = new GestioneDisponibilitaListener(this, appFrame);


        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void rifornisci(){
        int selectedRow = currentTable.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(appFrame,
                    "Per favore seleziona la riga dell'articolo da rifornire",
                    "Supply Product Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            selectedRow = currentTable.convertRowIndexToModel(selectedRow);
            int result = SchedaProdottoBusiness.getInstance().rifornisci(selectedRow, appFrame);
            switch(result){
                case 0:
                    JOptionPane.showMessageDialog(appFrame,
                            "Disponibilità modificata con successo!",
                            "Modify Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(appFrame,
                            "Disponibilità non modificata!",
                            "Modify Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }

    public void tableSetting(){
        currentTable = SchedaProdottoBusiness.getInstance().getTabellaSchedeProdotto();
        currentScrollPane = new JScrollPane(currentTable);
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
        sidePanel.setLayout(new FlowLayout());
        currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void componentsAdding(){
        add(labelTitle, BorderLayout.NORTH);
        add(currentScrollPane, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);
        sidePanel.add(btnRifornisci);
    }

    public void componentsSizing(){
        currentScrollPane.setPreferredSize(new Dimension(1000,500));
    }

    public void listenerSettings(){
        btnRifornisci.setActionCommand("rifornisci");
        btnRifornisci.addActionListener(gestioneDisponibilitaListener);
    }
}
