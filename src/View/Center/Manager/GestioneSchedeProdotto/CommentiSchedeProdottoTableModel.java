package View.Center.Manager.GestioneSchedeProdotto;

import Model.FeedBack;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CommentiSchedeProdottoTableModel extends AbstractTableModel {

    private ArrayList<FeedBack> lista = new ArrayList<>();

    public CommentiSchedeProdottoTableModel(ArrayList<FeedBack> lista){
        //this.lista = lista;
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "Commenti";
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
            case 0: lista.get(rowIndex).setCommento(value.toString());break;
        }
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
        FeedBack f = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return f.getCommento();
        }
        return null;
    }

    public ArrayList<FeedBack> getLista() {
        return lista;
    }

    public void setLista(ArrayList<FeedBack> lista) {
        this.lista = lista;
    }
}
