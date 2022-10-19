package View.Panels.Center.Manager;

import Business.ModelBusiness.ClienteBusiness;
import View.AppFrame;
import View.Listener.CenterListeners.Manager.GestioneClientiListener;
import Business.ModelBusiness.TableModels.ClientiTableModel;

import javax.swing.*;
import java.awt.*;

public class GestioneClientiPanel extends JPanel {

    private AppFrame appFrame;
    private GestioneClientiListener gestioneClientiListener;

    private JLabel labelTitle = new JLabel("Gestione Clienti");
    private JScrollPane currentScrollPane;
        private JTable currentTable;
        private ClientiTableModel currentTableModel;
    private JPanel sidePanel = new JPanel();
        private JButton btnEmail = new JButton("Invia email");
        private JButton btnAbilitaDisabilita = new JButton("Abilita/Disabilita");
        private JButton btnCancella = new JButton("Cancella");

    public GestioneClientiPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        gestioneClientiListener = new GestioneClientiListener(this, appFrame);

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();
    }

    public void inviaEmail(){
        int selectedRow = currentTable.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(appFrame,
                    "Seleziona il cliente a cui vuoi mandare una email!",
                    "Client Selection Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            selectedRow = currentTable.convertRowIndexToModel(selectedRow);
            //Cliente c = currentTableModel.getLista().get(selectedRow);

            //System.out.println("Si dovrebbe inviare una mail all'indirizzo: " + c.getEmail());
            //...

        }
    }

    public void abilita_disabilita(){
        int selectedRow = currentTable.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(appFrame,
                    "Prima seleziona il cliente da abilitare/disabilitare!",
                    "Client Selection Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            selectedRow = currentTable.convertRowIndexToModel(selectedRow);
            ClienteBusiness.getInstance().abilita_disabilita(selectedRow);
        }
    }

    public void cancella(){
        int selectedRow = currentTable.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(appFrame,
                    "Prima seleziona il cliente da cancellare!",
                    "Client Selection Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            selectedRow = currentTable.convertRowIndexToModel(selectedRow);
            int result = ClienteBusiness.getInstance().cancella(selectedRow);
            switch (result){
                case 0:
                    JOptionPane.showMessageDialog(appFrame,
                            "Cliente cancellato correttamente",
                            "Client cancelled",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(appFrame,
                            "Cliente cancellato in maniera errata",
                            "Client cancelled",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }

    public void tableSetting(){
        currentTable = ClienteBusiness.getInstance().getTabellaClienti();
        currentScrollPane = new JScrollPane(currentTable);
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
        sidePanel.setLayout(new GridLayout(8,1,10,10));
        currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void componentsAdding(){
        add(labelTitle, BorderLayout.NORTH);
        add(currentScrollPane, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);
            sidePanel.add(btnEmail);
            sidePanel.add(btnAbilitaDisabilita);
            sidePanel.add(btnCancella);
    }

    public void componentsSizing(){
        currentScrollPane.setPreferredSize(new Dimension(1000,500));
    }

    public void listenerSettings(){
        btnAbilitaDisabilita.setActionCommand("abilita_disabilita");
        btnCancella.setActionCommand("cancella");
        btnEmail.setActionCommand("invioEmail");

        btnEmail.addActionListener(gestioneClientiListener);
        btnCancella.addActionListener(gestioneClientiListener);
        btnAbilitaDisabilita.addActionListener(gestioneClientiListener);
    }

}

