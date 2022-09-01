package View.Panels.Center.Cliente.Altro;

import DAO.Classi.AcquistoDAO;
import Model.Cliente;
import Model.Lista;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ListsTableModel extends AbstractTableModel {


    private List<Lista> lista;
    private int rowEditable = -1;

    public ListsTableModel(List<Lista> lista) {
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
            case 0: return "Nome";
            case 1: return "Acquistata";
        }
        return null;
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
        Lista l = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return l.getNome();
            case 1: if(AcquistoDAO.getInstance().findByIDLista(l.getIdLista())==null){
                        return "No";
                    }else{
                        return "Sì";
                    }
        }
        return null;
    }

    public List<Lista> getLista() {
        return lista;
    }

    public int getRowEditable() {
        return rowEditable;
    }

    public void setRowEditable(int rowEditable) {
        this.rowEditable = rowEditable;
    }

    public void setLista(List<Lista> lista) {
        this.lista = lista;
    }
}