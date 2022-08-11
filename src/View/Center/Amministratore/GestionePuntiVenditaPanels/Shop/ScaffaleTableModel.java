package View.Center.Amministratore.GestionePuntiVenditaPanels.Shop;

import Model.Scaffale;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ScaffaleTableModel extends AbstractTableModel {

    private ArrayList<Scaffale> lista;
    private int selectedRow;
    private int editableRow = -1;

    public ScaffaleTableModel(ArrayList<Scaffale> lista){
        this.lista = lista;
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "Scaffali";
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
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Scaffale s = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return s.getNumero();
        }
        return null;
    }

    public ArrayList<Scaffale> getLista() {
        return lista;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: lista.get(rowIndex).setNumero((Integer) value);break;
        }
    }

    public void setLista(ArrayList<Scaffale> lista) {
        this.lista = lista;
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }


    public void setEditableRow(int editableRow){
        this.editableRow = editableRow;
    }

    public int getEditableRow() {
        return editableRow;
    }
}
