package Business.ModelBusiness.TableModels;

import DAO.Classi.ArticoloDAO;
import Model.Articolo;
import Model.Cliente;
import Model.Lista_has_Articolo;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ArticlesListTableModel extends AbstractTableModel {


    private List<Lista_has_Articolo> lista;
    private int rowEditable = -1;

    public ArticlesListTableModel(List<Lista_has_Articolo> lista) {
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
            case 0: return "Nome Articolo";
            case 1: return "Quantità";
            case 2: return "Prezzo";
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Lista_has_Articolo lista_has_articolo = lista.get(rowIndex);
        Articolo a = ArticoloDAO.getInstance().findById(lista_has_articolo.getIdArticolo());
        switch(columnIndex) {
            case 0: return a.getNome();
            case 1: return lista_has_articolo.getQuantita();
            case 2: return a.getPrezzo()*lista_has_articolo.getQuantita();
        }
        return null;
    }

    public List<Lista_has_Articolo> getLista() {
        return lista;
    }

    public int getRowEditable() {
        return rowEditable;
    }

    public void setRowEditable(int rowEditable) {
        this.rowEditable = rowEditable;
    }

    public void setLista(List<Lista_has_Articolo> lista) {
        this.lista = lista;
    }
}
