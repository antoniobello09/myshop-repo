package Business.ModelBusiness.TableModels;

import Business.SessionManager;
import DAO.Classi.CategoriaProdottoDAO;
import DAO.Classi.FornitoreDAO;
import DAO.Classi.ProdottoDAO;
import DAO.Classi.SchedaProdottoDAO;
import Model.Prodotto;
import Model.SchedaProdotto;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DisponibilitaTableModel extends AbstractTableModel {


    private List<SchedaProdotto> lista;
    private int rowEditable = -1;

    public DisponibilitaTableModel(List<SchedaProdotto> lista) {
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
            case 1: return "Disponibilità";
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
        SchedaProdotto sp = lista.get(rowIndex);
        Prodotto p = ProdottoDAO.getInstance().findByID(sp.getIdProdotto());
        switch(columnIndex) {
            case 0: return p.getNome();
            case 1: return sp.getDisponibilita();
        }
        return null;
    }

    public List<SchedaProdotto> getLista() {
        return lista;
    }

    public int getRowEditable() {
        return rowEditable;
    }

    public void setRowEditable(int rowEditable) {
        this.rowEditable = rowEditable;
    }

    public void setLista(List<SchedaProdotto> lista) {
        this.lista = lista;
    }
}
