package View.Center.Amministratore.GestionePuntiVenditaPanels.Shop;

import Model.Corsia;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CorsiaTableModel extends AbstractTableModel {

    private ArrayList<Corsia> lista;
    private int selectedRow;
    private int editableRow = -1;

    public CorsiaTableModel(ArrayList<Corsia> lista){
        this.lista = lista;
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "Corsie";
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
        Corsia c = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return c.getNumero();
        }
        return null;
    }

    public ArrayList<Corsia> getLista() {
        return lista;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: lista.get(rowIndex).setNumero((Integer) value);break;
        }
    }

    public void setLista(ArrayList<Corsia> lista) {
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
