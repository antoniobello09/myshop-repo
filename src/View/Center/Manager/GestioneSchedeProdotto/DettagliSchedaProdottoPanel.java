package View.Center.Manager.GestioneSchedeProdotto;

import Business.HelpFunctions;
import DAO.Classi.MagazzinoDAO;
import Model.FeedBack;
import Model.Magazzino;
import Model.SchedaProdotto;
import View.AppFrame;
import View.Listener.Manager.GestioneSchedeProdotto.DettagliSchedaProdottoListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DettagliSchedaProdottoPanel extends JPanel {

    private AppFrame appFrame;
    private DettagliSchedaProdottoListener dettagliSchedaProdottoListener;

    private JPanel modificaPanel = new JPanel();
        private JPanel posizionePanel = new JPanel();
            private JLabel inserisciPiano = new JLabel("Piano:");
            private JComboBox<String> pianoField;
            private JLabel inserisciCorsia = new JLabel("Corsia:");
            private JComboBox<String> corsiaField = new JComboBox<>();
            private JLabel inserisciScaffale = new JLabel("Scaffale:");
            private JComboBox<String> scaffaleField = new JComboBox<>();
        private JPanel disponibilitaPanel = new JPanel();
            private JLabel inserisciDisponibilita = new JLabel("Disponibilità: ");
            private JTextField disponibilitaField = new JTextField();
    private JPanel tablePanel = new JPanel();
        private CommentiSchedeProdottoTableModel currentTableModel;
        private JTable currentTable;
        private JScrollPane currentScrollPane;
        private JPanel operazioniPanel = new JPanel();
                private JButton btnRispondi = new JButton("Rispondi");
    private JPanel annullaPanel = new JPanel();
        private JButton btnAnnulla = new JButton("Annulla");
        private JButton btnSalva = new JButton("Salva");

    private Magazzino magazzino;
    private SchedaProdotto schedaProdotto;
    private ArrayList<FeedBack> lista;

    public DettagliSchedaProdottoPanel(AppFrame appFrame, Magazzino magazzino, SchedaProdotto schedaProdotto){
        this.appFrame = appFrame;
        this.schedaProdotto = schedaProdotto;

        //lista = FeedBackDAO.getInstance().findAll();

        dettagliSchedaProdottoListener = new DettagliSchedaProdottoListener(this, this.appFrame);
        this.magazzino = magazzino;

        //HelpFunctions.setComboBox(pianoField, HelpFunctions.fromArraytoString2());

        String[] listaPiani = new String[magazzino.getPiani().size() + 1];
        listaPiani[0] = "";
        for(int i=1;i<magazzino.getPiani().size() + 1;i++){
            listaPiani[i] = String.valueOf(magazzino.getPiani().get(i-1).getNumero());
        }

        pianoField = new JComboBox<>(listaPiani);
        int pianoNumber = getPiano(schedaProdotto.getIdSchedaProdotto()) - 1;
        pianoField.setSelectedIndex(pianoNumber + 1);

        if(pianoNumber != -1) {
            String[] listaCorsie = new String[magazzino.getPiani().get(pianoNumber).getCorsie().size() + 1];
            listaCorsie[0] = "";
            for (int i = 1; i < magazzino.getPiani().get(pianoNumber).getCorsie().size() + 1; i++) {
                listaCorsie[i] = String.valueOf(magazzino.getPiani().get(pianoNumber).getCorsie().get(i - 1).getNumero());
            }
            corsiaField = new JComboBox<>(listaCorsie);
            int corsiaNumber = getCorsia(schedaProdotto.getIdSchedaProdotto()) - 1;
            corsiaField.setSelectedIndex(corsiaNumber + 1);

            if(corsiaNumber != -1) {
                String[] listaScaffali = new String[magazzino.getPiani().get(pianoNumber).getCorsie().get(corsiaNumber).getScaffali().size() + 1];
                listaScaffali[0] = "";
                for (int i = 1; i < magazzino.getPiani().get(pianoNumber).getCorsie().get(corsiaNumber).getScaffali().size() + 1; i++) {
                    listaScaffali[i] = String.valueOf(magazzino.getPiani().get(pianoNumber).getCorsie().get(corsiaNumber).getScaffali().get(i - 1).getNumero());
                }
                scaffaleField = new JComboBox<>(listaScaffali);
                int scaffaleNumber = getScaffale(schedaProdotto.getIdSchedaProdotto()) - 1;
                scaffaleField.setSelectedIndex(scaffaleNumber + 1);
            }else{
                scaffaleField.removeAllItems();
            }
        }else{
            corsiaField.removeAllItems();
        }

        disponibilitaField.setText(String.valueOf(schedaProdotto.getDisponibilita()));

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void salva(){
        schedaProdotto.setDisponibilita(Integer.parseInt(disponibilitaField.getText()));
        if(pianoField.getSelectedItem().equals("")||corsiaField.getSelectedItem().equals("")||scaffaleField.getSelectedItem().equals("")){
            magazzino.getProdottiSenzaPosizione().add(schedaProdotto);
            magazzino.getPiani().get(getPiano(schedaProdotto.getIdSchedaProdotto())).getCorsie().get(getCorsia(schedaProdotto.getIdSchedaProdotto())).getScaffali().get(getScaffale(schedaProdotto.getIdSchedaProdotto())).setProdotto(null);
            MagazzinoDAO.getInstance().updateSchedaProdotto(schedaProdotto, magazzino.getIdMagazzino(), Integer.valueOf(disponibilitaField.getText()));
        }else {
            SchedaProdotto schedaProdottoSpostare = magazzino.getPiani().get(pianoField.getSelectedIndex() - 1).getCorsie().get(corsiaField.getSelectedIndex() - 1).getScaffali().get(scaffaleField.getSelectedIndex() - 1).getProdotto();
            if (schedaProdottoSpostare != null) {
                magazzino.getProdottiSenzaPosizione().add(schedaProdottoSpostare);
                MagazzinoDAO.getInstance().updateSchedaProdotto(schedaProdottoSpostare, magazzino.getIdMagazzino(), Integer.parseInt(disponibilitaField.getText()));
            }
            magazzino.getPiani().get(pianoField.getSelectedIndex() - 1).getCorsie().get(corsiaField.getSelectedIndex() - 1).getScaffali().get(scaffaleField.getSelectedIndex() - 1).setProdotto(schedaProdotto);
            magazzino.getProdottiSenzaPosizione().remove(schedaProdotto);
            MagazzinoDAO.getInstance().updateSchedaProdotto(schedaProdotto, magazzino.getIdMagazzino(), pianoField.getSelectedIndex(), corsiaField.getSelectedIndex(), scaffaleField.getSelectedIndex(), Integer.parseInt(disponibilitaField.getText()));
        }
    }

    public void setCorsie(){
        corsiaField.removeActionListener(dettagliSchedaProdottoListener);
        int selectedPiano = pianoField.getSelectedIndex() - 1;
        if(selectedPiano == -1){
            corsiaField.removeAllItems();
            scaffaleField.removeAllItems();
            return;
        }
        scaffaleField.removeAllItems();
        ArrayList<String> corsieArray = new ArrayList<>();
        for(int i=0;i<magazzino.getPiani().get(selectedPiano).getCorsie().size();i++){
            corsieArray.add(String.valueOf(magazzino.getPiani().get(selectedPiano).getCorsie().get(i).getNumero()));
        }
        HelpFunctions.setComboBox(corsiaField, HelpFunctions.fromArraytoString2(corsieArray));
        corsiaField.addActionListener(dettagliSchedaProdottoListener);
    }

    public void setScaffali(){
        int selectedPiano = pianoField.getSelectedIndex() - 1;
        int selectedCorsia = corsiaField.getSelectedIndex() - 1;
        if((selectedCorsia == -1)||(selectedPiano == -1)){
            scaffaleField.removeAllItems();
            return;
        }
        scaffaleField.removeAllItems();
        ArrayList<String> scaffaliArray = new ArrayList<>();
        for(int i=0;i<magazzino.getPiani().get(selectedPiano).getCorsie().get(selectedCorsia).getScaffali().size();i++){
            scaffaliArray.add(String.valueOf(magazzino.getPiani().get(selectedPiano).getCorsie().get(selectedCorsia).getScaffali().get(i).getNumero()));
        }
        HelpFunctions.setComboBox(scaffaleField, HelpFunctions.fromArraytoString2(scaffaliArray));
    }


    public void tableSetting(){
        currentTableModel = new CommentiSchedeProdottoTableModel(lista);
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
            modificaPanel.setLayout(new BorderLayout());
                posizionePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
                disponibilitaPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
            tablePanel.setLayout(new BorderLayout());
                currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                operazioniPanel.setLayout(new GridLayout(8,1,15,15));
            annullaPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    public void componentsAdding(){
        add(modificaPanel, BorderLayout.NORTH);
            modificaPanel.add(posizionePanel, BorderLayout.NORTH);
                posizionePanel.add(inserisciPiano);
                posizionePanel.add(pianoField);
                posizionePanel.add(inserisciCorsia);
                posizionePanel.add(corsiaField);
                posizionePanel.add(inserisciScaffale);
                posizionePanel.add(scaffaleField);
            modificaPanel.add(disponibilitaPanel, BorderLayout.CENTER);
                disponibilitaPanel.add(inserisciDisponibilita);
                disponibilitaPanel.add(disponibilitaField);
        add(tablePanel, BorderLayout.CENTER);
            tablePanel.add(currentScrollPane, BorderLayout.WEST);
            tablePanel.add(operazioniPanel, BorderLayout.EAST);
                operazioniPanel.add(btnRispondi);
        add(annullaPanel, BorderLayout.SOUTH);
            annullaPanel.add(btnAnnulla);
            annullaPanel.add(btnSalva);

    }

    public void componentsSizing(){
        disponibilitaField.setPreferredSize(new Dimension(50, 20));
    }

    public void listenerSettings(){
        pianoField.setActionCommand("piano");
        corsiaField.setActionCommand("corsia");
        btnAnnulla.setActionCommand("annulla");
        btnSalva.setActionCommand("salva");
        btnRispondi.setActionCommand("rispondi");

        pianoField.addActionListener(dettagliSchedaProdottoListener);
        corsiaField.addActionListener(dettagliSchedaProdottoListener);
        btnAnnulla.addActionListener(dettagliSchedaProdottoListener);
        btnSalva.addActionListener(dettagliSchedaProdottoListener);
        btnRispondi.addActionListener(dettagliSchedaProdottoListener);
    }



    public int getPiano(int idSchedaProdotto){

        for(int i=0;i<magazzino.getProdottiSenzaPosizione().size();i++){
            if(magazzino.getProdottiSenzaPosizione().get(i).getIdSchedaProdotto() == idSchedaProdotto){
                return 0;
            }
        }

        for(int i=0;i<magazzino.getPiani().size();i++){
            for(int j=0;j<magazzino.getPiani().get(i).getCorsie().size();j++){
                for(int k=0;k<magazzino.getPiani().get(i).getCorsie().get(j).getScaffali().size();k++){
                    if(magazzino.getPiani().get(i).getCorsie().get(j).getScaffali().get(k).getProdotto() != null){
                        if(magazzino.getPiani().get(i).getCorsie().get(j).getScaffali().get(k).getProdotto().getIdSchedaProdotto() == idSchedaProdotto){
                            return i+1;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public int getCorsia(int idSchedaProdotto){

        for(int i=0;i<magazzino.getProdottiSenzaPosizione().size();i++){
            if(magazzino.getProdottiSenzaPosizione().get(i).getIdSchedaProdotto() == idSchedaProdotto){
                return 0;
            }
        }

        for(int i=0;i<magazzino.getPiani().size();i++){
            for(int j=0;j<magazzino.getPiani().get(i).getCorsie().size();j++){
                for(int k=0;k<magazzino.getPiani().get(i).getCorsie().get(j).getScaffali().size();k++){
                    if(magazzino.getPiani().get(i).getCorsie().get(j).getScaffali().get(k).getProdotto() != null) {
                        if (magazzino.getPiani().get(i).getCorsie().get(j).getScaffali().get(k).getProdotto().getIdSchedaProdotto() == idSchedaProdotto) {
                            return j + 1;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public int getScaffale(int idSchedaProdotto){

        for(int i=0;i<magazzino.getProdottiSenzaPosizione().size();i++){
            if(magazzino.getProdottiSenzaPosizione().get(i).getIdSchedaProdotto() == idSchedaProdotto){
                return 0;
            }
        }

        for(int i=0;i<magazzino.getPiani().size();i++){
            for(int j=0;j<magazzino.getPiani().get(i).getCorsie().size();j++){
                for(int k=0;k<magazzino.getPiani().get(i).getCorsie().get(j).getScaffali().size();k++){
                    if(magazzino.getPiani().get(i).getCorsie().get(j).getScaffali().get(k).getProdotto() != null) {
                        if (magazzino.getPiani().get(i).getCorsie().get(j).getScaffali().get(k).getProdotto().getIdSchedaProdotto() == idSchedaProdotto) {
                            return k + 1;
                        }
                    }
                }
            }
        }
        return -1;
    }

}
