package View.Center.Amministratore.GestioneServiziPanels.Category;

import Model.CategoriaServizio;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ServiceCategoryTableModel extends AbstractTableModel {

    private ArrayList<CategoriaServizio> lista;
    private int editableRow = -1;

    public ServiceCategoryTableModel(ArrayList<CategoriaServizio> lista){
        this.lista = lista;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch(columnIndex) {
            case 0: return "Nome";
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return rowIndex == editableRow;
    }

    public void setEditableRow(int editableRow){
        this.editableRow = editableRow;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: lista.get(rowIndex).setNome(value.toString());break;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CategoriaServizio c = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return c.getNome();
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    public boolean find(String categoria){
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).getNome().equals(categoria))    return true;
        }
        return false;
    }

    public CategoriaServizio findCategory(String categoria){
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).getNome().equals(categoria))    return lista.get(i);
        }
        return null;
    }


    public void add(CategoriaServizio categoriaServizio){
        lista.add(categoriaServizio);
    }

    public void remove(int index){
        lista.remove(index);
    }

    public ArrayList<CategoriaServizio> getLista(){
        return lista;
    }

    public void setLista(ArrayList<CategoriaServizio> categorie){
        lista = categorie;
    }
}
