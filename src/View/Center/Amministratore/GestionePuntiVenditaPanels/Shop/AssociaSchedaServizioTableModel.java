package View.Center.Amministratore.GestionePuntiVenditaPanels.Shop;

import Model.SchedaServizio;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AssociaSchedaServizioTableModel extends AbstractTableModel {

    private ArrayList<SchedaServizio> lista;
    private int selectedRow;
    private int editableRow = -1;

    public AssociaSchedaServizioTableModel(ArrayList<SchedaServizio> lista){
        this.lista = lista;
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "Servizio";
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
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
        SchedaServizio s = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return s.getServizio().getNome();
        }
        return null;
    }

    public ArrayList<SchedaServizio> getLista() {
        return lista;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: lista.get(rowIndex).getServizio().setNome(value.toString());break;
        }
    }

    public void setLista(ArrayList<SchedaServizio> lista) {
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
