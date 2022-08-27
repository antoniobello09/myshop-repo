package View.Center.Amministratore.GestioneProdottiPanels.Producer;

import Model.Fornitore;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ProducerTableModel extends AbstractTableModel {

    private ArrayList<Fornitore> lista;
    private int editableRow = -1;

    public ProducerTableModel(ArrayList<Fornitore> lista){
        this.lista = lista;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch(columnIndex) {
            case 0: return "Nome";
            case 1: return "Sito web";
            case 2: return "Città";
            case 3: return "Nazione";
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
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Fornitore p = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return p.getNome();
            case 1: return p.getSitoweb();
            case 2: return p.getCitta();
            case 3: return p.getNazione();
        }

        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: lista.get(rowIndex).setNome(value.toString());break;
            case 1: lista.get(rowIndex).setSitoweb(value.toString());break;
            case 2: lista.get(rowIndex).setCitta(value.toString());break;
            case 3: lista.get(rowIndex).setNazione(value.toString());break;
        }
    }

    public void setLista(ArrayList<Fornitore> lista){
        this.lista = lista;
    }

    public Fornitore searchProduttore(String nome){
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).getNome().equals(nome)) return lista.get(i);
        }
        return null;
    }

    public void setEditableRow(int editableRow){
        this.editableRow = editableRow;
    }

    public ArrayList<Fornitore> cloneList(){
        ArrayList<Fornitore> clonedList = new ArrayList<>();
        for(int i=0; i<lista.size();i++){
            clonedList.add((Fornitore) lista.get(i).clone());
        }
        return clonedList;
    }

    public ArrayList<Fornitore> getLista(){
        return lista;
    }
}
