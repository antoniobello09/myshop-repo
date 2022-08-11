package View.Center.Amministratore.GestioneProdottiPanels.Product;

import Model.Prodotto;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ProdottoTableModel extends AbstractTableModel {


    private List<Prodotto> lista;
    private int rowEditable = -1;

    public ProdottoTableModel(List<Prodotto> lista) {
        this.lista = lista;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return rowIndex == rowEditable;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: lista.get(rowIndex).setNome(value.toString());break;
            case 1: lista.get(rowIndex).setPrezzo(Float.parseFloat(value.toString()));break;
            case 2: lista.get(rowIndex).setDescrizione(value.toString());break;
            case 3: lista.get(rowIndex).getCategoria().setNome(value.toString());
                    lista.get(rowIndex).getCategoria().getSottocategorie().get(0).setNome("");break;
            case 4: lista.get(rowIndex).getCategoria().getSottocategorie().get(0).setNome(value.toString());break;
            case 5: lista.get(rowIndex).getProduttore().setNome(value.toString());break;
        }

    }

    @Override
    public String getColumnName(int columnIndex) {
        switch(columnIndex) {
            case 0: return "Nome";
            case 1: return "Prezzo";
            case 2: return "Descrizione";
            case 3: return "Categoria";
            case 4: return "Sottocategoria";
            case 5: return "Produttore";
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prodotto p = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return p.getNome();
            case 1: return p.getPrezzo();
            case 2: return p.getDescrizione();
            case 3: return p.getCategoria().getNome();
            case 4: return p.getCategoria().getSottocategorie().get(0).getNome();
            case 5: return p.getProduttore().getNome();
        }
        return null;
    }

    public List<Prodotto> getLista() {
        return lista;
    }

    public int getRowEditable() {
        return rowEditable;
    }

    public void setRowEditable(int rowEditable) {
        this.rowEditable = rowEditable;
    }


}
