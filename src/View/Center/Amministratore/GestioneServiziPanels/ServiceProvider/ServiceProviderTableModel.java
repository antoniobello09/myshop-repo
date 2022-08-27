package View.Center.Amministratore.GestioneServiziPanels.ServiceProvider;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ServiceProviderTableModel extends AbstractTableModel {

    private ArrayList<FornitoreServizi> lista;
    private int editableRow = -1;

    public ServiceProviderTableModel(ArrayList<FornitoreServizi> lista){
        this.lista = lista;
    }


    @Override
    public String getColumnName(int column) {
        switch(column) {
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
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: lista.get(rowIndex).setNome(value.toString());break;
            case 1: lista.get(rowIndex).setSitoweb(value.toString());break;
            case 2: lista.get(rowIndex).setCitta(value.toString());break;
            case 3: lista.get(rowIndex).setNazione(value.toString());break;
        }
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
        FornitoreServizi f = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return f.getNome();
            case 1: return f.getSitoweb();
            case 2: return f.getCitta();
            case 3: return f.getNazione();
        }
        return null;
    }

    public void setLista(ArrayList<FornitoreServizi> lista){
        this.lista = lista;
    }

    public FornitoreServizi searchFornitore(String nome){
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).getNome().equals(nome)) return lista.get(i);
        }
        return null;
    }

    public void setEditableRow(int editableRow){
        this.editableRow = editableRow;
    }

    public ArrayList<FornitoreServizi> cloneList(){
        ArrayList<FornitoreServizi> clonedList = new ArrayList<>();
        for(int i=0; i<lista.size();i++){
            clonedList.add((FornitoreServizi) lista.get(i).clone());
        }
        return clonedList;
    }

    public ArrayList<FornitoreServizi> getLista(){
        return lista;
    }
}
