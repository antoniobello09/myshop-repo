package View.Center.Amministratore.GestionePuntiVenditaPanels.Shop;

import Model.SchedaProdotto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AssociaSchedaProdottoTableModel extends AbstractTableModel {

    private ArrayList<SchedaProdotto> lista;
    private int selectedRow;
    private int editableRow = -1;

    public AssociaSchedaProdottoTableModel(ArrayList<SchedaProdotto> lista){
        this.lista = lista;
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "Prodotto";
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
        SchedaProdotto p = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return p.getProdotto().getNome();
        }
        return null;
    }

    public ArrayList<SchedaProdotto> getLista() {
        return lista;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: lista.get(rowIndex).getProdotto().setNome(value.toString());break;
        }
    }

    public void setLista(ArrayList<SchedaProdotto> lista) {
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