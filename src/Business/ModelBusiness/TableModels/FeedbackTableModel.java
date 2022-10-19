package Business.ModelBusiness.TableModels;

import DAO.Classi.ArticoloDAO;
import Model.FeedBack;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class FeedbackTableModel extends AbstractTableModel {


    private List<FeedBack> lista;
    private int rowEditable = -1;

    public FeedbackTableModel(List<FeedBack> lista) {
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
            case 0: return "Articolo";
            case 1: return "Commento";
            case 2: return "Indice di gradimento";
            case 3: return "Risposta";
        }
        return null;
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
        FeedBack f = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return ArticoloDAO.getInstance().findById(f.getIdArticolo()).getNome();
            case 1: return f.getCommento();
            case 2: return f.getIndiceGradimento();
            case 3: return f.getRisposta();
        }
        return null;
    }

    public List<FeedBack> getLista() {
        return lista;
    }

    public int getRowEditable() {
        return rowEditable;
    }

    public void setRowEditable(int rowEditable) {
        this.rowEditable = rowEditable;
    }

    public void setLista(List<FeedBack> lista) {
        this.lista = lista;
    }
}
