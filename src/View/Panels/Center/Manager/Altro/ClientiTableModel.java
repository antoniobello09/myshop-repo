package View.Panels.Center.Manager.Altro;

import DAO.Classi.ArticoloDAO;
import Model.Cliente;
import Model.FeedBack;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ClientiTableModel extends AbstractTableModel {


    private List<Cliente> lista;
    private int rowEditable = -1;

    public ClientiTableModel(List<Cliente> lista) {
        this.lista = lista;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return rowIndex == rowEditable;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {

    }

    @Override
    public String getColumnName(int columnIndex) {
        switch(columnIndex) {
            case 0: return "Cognome";
            case 1: return "Nome";
            case 2: return "Username";
            case 3: return "Email";
            case 4: return "Data di nascita";
            case 5: return "Telefono";
            case 6: return "Indirizzo";
            case 7: return "Città";
            case 8: return "Lavoro";
            case 9: return "Canale preferito";
            case 10: return "Abilitato";
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 11;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente c = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return c.getName();
            case 1: return c.getSurname();
            case 2: return c.getUsername();
            case 3: return c.getEmail();
            case 4: return c.getBirthdate();
            case 5: return c.getTelephone();
            case 6: return c.getAddress();
            case 7: return c.getCity();
            case 8: return c.getJob();
            case 9: return c.getCanalePreferito();
            case 10: if(c.isAbilitato()) return "Sì";
                    else return "No";
        }
        return null;
    }

    public List<Cliente> getLista() {
        return lista;
    }

    public int getRowEditable() {
        return rowEditable;
    }

    public void setRowEditable(int rowEditable) {
        this.rowEditable = rowEditable;
    }

    public void setLista(List<Cliente> lista) {
        this.lista = lista;
    }
}
