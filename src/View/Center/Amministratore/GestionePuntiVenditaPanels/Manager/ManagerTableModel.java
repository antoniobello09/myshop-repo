package View.Center.Amministratore.GestionePuntiVenditaPanels.Manager;

import Model.Manager;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Iterator;

public class ManagerTableModel extends AbstractTableModel {

    private ArrayList<Manager> lista;
    private int selectedRow;
    private int editableRow = -1;

    public ManagerTableModel(ArrayList<Manager> lista){
        this.lista = lista;
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "Username";
            case 1: return "Password";
            case 2: return "Email";
            case 3: return "Cognome";
            case 4: return "Nome";
            case 5: return "Età";
            case 6: return "Telefono";
            case 7: return "Indirizzo";
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return rowIndex == editableRow;
    }



    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Manager m = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return m.getUsername();
            case 1: return m.getPassword();
            case 2: return m.getEmail();
            case 3: return m.getSurname();
            case 4: return m.getName();
            case 5: return m.getBirthdate();
            case 6: return m.getTelephone();
            case 7: return m.getAddress();
        }
        return null;
    }

    public ArrayList<Manager> getLista() {
        return lista;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: lista.get(rowIndex).setUsername(value.toString());break;
            case 1: lista.get(rowIndex).setPassword(value.toString());break;
            case 2: lista.get(rowIndex).setEmail(value.toString());break;
            case 3: lista.get(rowIndex).setSurname(value.toString());break;
            case 4: lista.get(rowIndex).setName(value.toString());break;
            case 5: lista.get(rowIndex).setBirthdate(value.toString());break;
            case 6: lista.get(rowIndex).setTelephone(value.toString());break;
            case 7: lista.get(rowIndex).setAddress(value.toString());break;
        }
    }

    public void setLista(ArrayList<Manager> lista) {
        this.lista = lista;
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }

    public Manager findManager(String username){
        Iterator<Manager> it = lista.iterator();
        while(it.hasNext()){
            Manager m = it.next();
            if(username.equals(m.getUsername())){
                return m;
            }
        }
        return null;
    }

    public void setEditableRow(int editableRow){
        this.editableRow = editableRow;
    }
}
