package View.Center.Manager.GestioneSchedeServizio;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SchedaServizioModel extends AbstractTableModel {

    private ArrayList<SchedaServizio> lista;
    private int editableRow;

    public SchedaServizioModel(ArrayList<SchedaServizio> lista){
        this.lista = lista;
    }



    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "Nome";
            case 1: return "Fornitore";
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: lista.get(rowIndex).getServizio().getNome();break;
            case 1: lista.get(rowIndex).getFornitoreServizi().getNome();break;
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SchedaServizio s = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return s.getServizio().getNome();
            case 1: if(s.getFornitoreServizi() != null) return s.getFornitoreServizi().getNome();
        }
        return null;
    }

    public ArrayList<SchedaServizio> getLista() {
        return lista;
    }

    public void setLista(ArrayList<SchedaServizio> lista) {
        this.lista = lista;
    }

    public int getEditableRow() {
        return editableRow;
    }

    public void setEditableRow(int editableRow) {
        this.editableRow = editableRow;
    }
}
