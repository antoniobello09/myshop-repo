package View.Center.Manager.GestioneClienti;

import Model.Cliente;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class GestioneClientiModel extends AbstractTableModel {

    private List<Cliente> lista;
    private int rowEditable = -1;

    public GestioneClientiModel(List<Cliente> lista) {
        this.lista = lista;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return super.isCellEditable(rowIndex, columnIndex);
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: lista.get(rowIndex).setName(value.toString());break;
            case 1: lista.get(rowIndex).setSurname(value.toString());break;
            case 2: lista.get(rowIndex).setEmail(value.toString());break;
           /* case 3: lista.get(rowIndex).getBirthdate()(value.toString());break;
            case 4: lista.get(rowIndex).getTelephone()(value.toString());break;
            case 5: lista.get(rowIndex).getAddress()(value.toString());break; */
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch(columnIndex) {
            case 0: return "Nome";
            case 1: return "Cognome";
            case 2: return "Email";
            case 3: return "Data di nascita";
            case 4: return "Telefono";
            case 5: return "Indirizzo";
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente c = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return c.getNome();
            case 1: return c.getSurname();
            case 2: return c.getEmail();
            case 3: return c.getBirthdate();
            case 4: return c.getTelephone();
            case 5: return c.getAddress();
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

}
