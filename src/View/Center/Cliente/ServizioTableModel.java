package View.Center.Cliente;

import Model.Servizio;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ServizioTableModel extends AbstractTableModel {

    private ArrayList<Servizio> lista;

    public ServizioTableModel(ArrayList<Servizio> lista){
        this.lista = lista;
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "Nome";
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
            case 0: lista.get(rowIndex).setNome(value.toString());break;
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
        Servizio s = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return s.getNome();
        }
        return null;
    }

    public ArrayList<Servizio> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Servizio> lista) {
        this.lista = lista;
    }

    public void add(SchedaServizio schedaServizio){
        Servizio servizio = schedaServizio.getServizio();
        if(findServizio(servizio) != -1){
        }else {
            lista.add(servizio);
        }
    }

    public int findServizio(Servizio servizio){
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).getNome().equals(servizio.getNome())){
                return i;
            }
        }
        return -1;
    }

    public void remove(Servizio servizio){
        lista.remove(servizio);
    }
}
