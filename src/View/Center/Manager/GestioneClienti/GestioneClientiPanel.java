/*package View.Center.Manager.GestioneClienti;

import Business.SessionManager;
import DAO.Classi.ClienteDAO;

import Model.Cliente;

import Model.Utente;
import View.AppFrame;
import View.Listener.Manager.GestioneClienti.GestioneClientiListener;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class GestioneClientiPanel extends JPanel {

    private AppFrame appFrame;
    private GestioneClientiListener gestioneClientiListener;

    private JPanel eliminaPanel = new JPanel();
    private JButton btnElimina = new JButton("Elimina");
    private JPanel operazioniPanel = new JPanel();
    private JButton btnDisabilita = new JButton("Disabilita");
    private JTextField schedaField = new JTextField();
    private int selectedRow;

    private ArrayList<Utente> u;

    private GestioneClientiModel currentTableModel;
    private JTable currentTable;
    private JScrollPane currentScrollPane;


    public GestioneClientiPanel(AppFrame appFrame) {

        this.appFrame = appFrame;
        gestioneClientiListener = new GestioneClientiListener(this, this.appFrame);

        setLayout(new BorderLayout());

        Utente utente = (Utente) SessionManager.getInstance().getSession().get("loggedUser");

        java.util.List<Cliente> lista = ClienteDAO.getInstance().findByIdPuntoVendita(utente.getIdUtente());

        JPanel operazionitabella = new JPanel();
        JTable table = new JTable(new GestioneClientiModel(lista));
        JScrollPane scrollPane = new JScrollPane(table);


        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(1000,500));
        add(scrollPane, BorderLayout.CENTER);


        operazionitabella.setLayout(new FlowLayout());

        JButton btn = new JButton("Operazione 1");
        operazionitabella.add(btn);
        add(operazionitabella, BorderLayout.SOUTH);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selRow = table.getSelectedRow();
                int selCol = table.getSelectedColumn();
                TableModel model = table.getModel();
                String value = (String) model.getValueAt(selRow, selCol);

                //value = data[selRow][selCol];

                JOptionPane.showMessageDialog(null,
                        "Valore: "+value,
                        "Click event",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        tableSetting();

        layoutSetting();

        componentsAdding();

        componentsSizing();

        listenerSettings();

    }

    public void tableSetting(){
        currentTableModel = new GestioneClientiModel(u);
        currentTable = new JTable(currentTableModel);
        currentScrollPane = new JScrollPane(currentTable);
    }

    public void layoutSetting(){
        setLayout(new BorderLayout());
        currentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        currentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        operazioniPanel.setLayout(new GridLayout(8,1,15,15));
    }

    public  void componentsAdding(){
        add(eliminaPanel, BorderLayout.NORTH);
        eliminaPanel.add(btnElimina);
        eliminaPanel.add(schedaField);
        add(currentScrollPane, BorderLayout.CENTER);
        add(operazioniPanel, BorderLayout.EAST);
        operazioniPanel.add(btnDisabilita);
    }

    public void componentsSizing(){
        schedaField.setPreferredSize(new Dimension(200, 20));
    }

    public void listenerSettings(){
        btnElimina.addActionListener(gestioneClientiListener);
        btnDisabilita.addActionListener(gestioneClientiListener);

        btnElimina.setActionCommand("Elimina");
        btnDisabilita.setActionCommand("Disabilita");
    }

    public void elimina(){
        ArrayList<Cliente> lista = new ArrayList<>();
        Cliente cliente;
        selectedRow = currentTable.convertRowIndexToModel(currentTable.getSelectedRow());
        cliente = lista.get(selectedRow);
        ClienteDAO.getInstance().delete(cliente);
        ClienteDAO.getInstance().update(cliente);
    }

    public void disabilita(){
        ArrayList<Cliente> lista = new ArrayList<>();
        Cliente cliente;
        selectedRow = currentTable.convertRowIndexToModel(currentTable.getSelectedRow());
        cliente = lista.get(selectedRow);
        cliente.setAbilitato(false);
        ClienteDAO.getInstance().update(cliente);
    }


}



 */