package View.Center.Amministratore.GestioneServiziPanels.Service;

import Model.Servizio;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ServizioTableModel extends AbstractTableModel {

    private ArrayList<Servizio> lista;
    private int rowEditable = -1;

    public ServizioTableModel(ArrayList<Servizio> lista) {
        this.lista = lista;
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "Nome";
            case 1: return "Prezzo";
            case 2: return "Descrizione";
            case 3: return "Categoria";
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return rowIndex == rowEditable;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Servizio s = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return s.getNome();
            case 1: return s.getPrezzo();
            case 2: return s.getDescrizione();
            case 3: return s.getCategoria().getNome();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: lista.get(rowIndex).setNome(value.toString());break;
            case 1: lista.get(rowIndex).setPrezzo(Float.parseFloat(value.toString()));break;
            case 2: lista.get(rowIndex).setDescrizione(value.toString());break;
            case 3: lista.get(rowIndex).getCategoria().setNome(value.toString());break;
        }
    }

    public ArrayList<Servizio> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Servizio> lista){
        this.lista = lista;
    }

    public int getRowEditable() {
        return rowEditable;
    }

    public void setRowEditable(int rowEditable) {
        this.rowEditable = rowEditable;
    }
}
