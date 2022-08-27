package View.Center.Manager.GestioneSchedeServizio;

import Business.HelpFunctions;
import DAO.Classi.MagazzinoDAO;
import DAO.Classi.SchedaServizioDAO;
import Model.*;
import View.AppFrame;
import View.Center.Manager.GestioneSchedeProdotto.CommentiSchedeProdottoTableModel;
import View.Listener.Manager.GestioneSchedeServizio.DettagliSchedaServizioListener;
import View.Nameable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DettagliSchedaServizioPanel extends JPanel {

    private AppFrame appFrame;
    private DettagliSchedaServizioListener dettagliSchedaServizioListener;
    private SchedaServizio schedaServizio;


    private JPanel fornitorePanel = new JPanel();
        private JLabel inserisciFornitore = new JLabel("Fornitore: ");
        private JComboBox<String> fornitoreField = new JComboBox<>();
    private JPanel tablePanel = new JPanel();
        private CommentiSchedeProdottoTableModel currentTableModel;
        private JTable currentTable;
        private JScrollPane currentScrollPane;
        private JPanel operazioniPanel = new JPanel();
            private JButton btnRispondi = new JButton("Rispondi");
        private JPanel annullaPanel = new JPanel();
            private JButton btnAnnulla = new JButton("Annulla");
            private JButton btnSalva = new JButton("Salva");

    private ArrayList<FeedBack> lista;

    public DettagliSchedaServizioPanel(AppFrame appFrame, SchedaServizio schedaServizio){
        this.appFrame = appFrame;
        dettagliSchedaServizioListener = new DettagliSchedaServizioListener(this, this.appFrame);
        this.schedaServizio = schedaServizio;

        //lista = FeedBackDAO.getInstance().findAll();

        //HelpFunctions.setComboBox(pianoField, HelpFunctions.fromArraytoString2());

        ArrayList<FornitoreServizi> fornitoriLista = FornitoreServiziDAO.getInstance().findAll();
        ArrayList<Nameable> comboList = (ArrayList<Nameable>)fornitoriLista.clone();
        HelpFunctions.setComboBox(fornitoreField, HelpFunctions.fromArraytoString(comboList));
        if(schedaServizio.getFornitoreServizi() != null) {
            fornitoreField.setSelectedItem(schedaServizio.getFornitoreServizi().getNome());
        }
        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void salva(){

        SchedaServizioDAO.getInstance().update(schedaServizio, FornitoreServiziDAO.getInstance().findByName(String.valueOf(fornitoreField.getSelectedItem())));

    }




    public void tableSetting(){
        currentTableModel = new CommentiSchedeProdottoTableModel(lista);
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
        fornitorePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        tablePanel.setLayout(new BorderLayout());
            currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            operazioniPanel.setLayout(new GridLayout(8,1,15,15));
        annullaPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    public void componentsAdding(){
        add(fornitorePanel, BorderLayout.NORTH);
            fornitorePanel.add(inserisciFornitore);
            fornitorePanel.add(fornitoreField);
        add(tablePanel, BorderLayout.CENTER);
            tablePanel.add(currentScrollPane, BorderLayout.WEST);
            tablePanel.add(operazioniPanel, BorderLayout.EAST);
                operazioniPanel.add(btnRispondi);
        add(annullaPanel, BorderLayout.SOUTH);
            annullaPanel.add(btnAnnulla);
            annullaPanel.add(btnSalva);

    }

    public void componentsSizing(){
        fornitoreField.setPreferredSize(new Dimension(200, 20));
    }

    public void listenerSettings(){
        btnAnnulla.setActionCommand("annulla");
        btnSalva.setActionCommand("salva");
        btnRispondi.setActionCommand("rispondi");


        btnAnnulla.addActionListener(dettagliSchedaServizioListener);
        btnSalva.addActionListener(dettagliSchedaServizioListener);
        btnRispondi.addActionListener(dettagliSchedaServizioListener);
    }



}
