package View.Center.Amministratore.GestioneArticoliPanels.Category;

import Model.CategoriaProdotto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CategoryTableModel extends AbstractTableModel {

    private ArrayList<CategoriaProdotto> lista;
    private boolean isEditable = false;

    public CategoryTableModel(ArrayList<CategoriaProdotto> lista){
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
        return isEditable;
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
        CategoriaProdotto c = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return c.getNome();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: lista.get(rowIndex).setNome(value.toString());break;
        }
    }

    public ArrayList<CategoriaProdotto> getLista(){
        return lista;
    }

    public void setLista(ArrayList<CategoriaProdotto> categorie){
        lista = categorie;
    }

    public void add(CategoriaProdotto categoriaProdotto){
        lista.add(categoriaProdotto);
    }

    public void remove(int index){
        lista.remove(index);
    }

    public boolean find(String categoria){
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).getNome().equals(categoria))    return true;
        }
        return false;
    }

    public CategoriaProdotto findCategory(String categoria){
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).getNome().equals(categoria))    return lista.get(i);
        }
        return null;
    }


    public void setCellEditable(boolean isEditable){
        this.isEditable = isEditable;
    }

}
