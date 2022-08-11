package View.Center.Cliente;

import Model.Magazzino;
import Model.Prodotto;
import Model.Prodotto_Quantita;
import Model.SchedaProdotto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ProdottoQuantitaModel extends AbstractTableModel {

    private Magazzino magazzino;
    private ArrayList<Prodotto_Quantita> lista = new ArrayList<>();
    private int editableColumn = 1;

    public ProdottoQuantitaModel(ArrayList<Prodotto_Quantita> lista){
        this.lista = lista;
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "Nome";
            case 1: return "Quantità";
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == editableColumn;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: lista.get(rowIndex).setNome(value.toString());break;
            case 1: lista.get(rowIndex).setQuantita((int)value);break;
        }
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
        Prodotto_Quantita p = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return p.getNome();
            case 1: return p.getQuantita();
        }
        return null;
    }

    public ArrayList<Prodotto_Quantita> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Prodotto_Quantita> lista) {
        this.lista = lista;
    }

    public int getEditableColumn() {
        return editableColumn;
    }

    public void setEditableColumn(int editableColumn) {
        this.editableColumn = editableColumn;
    }

    public void add(SchedaProdotto schedaProdotto){
        Prodotto prodotto = schedaProdotto.getProdotto();
        if(findProdotto(prodotto) != -1){
            int vecchia_quantita = lista.get(findProdotto(prodotto)).getQuantita();
            lista.get(findProdotto(prodotto)).setQuantita(vecchia_quantita + 1);
        }else {
            Prodotto_Quantita prodotto_quantita = new Prodotto_Quantita(prodotto);
            prodotto_quantita.setQuantita(1);
            lista.add(prodotto_quantita);
        }
    }

    public int findProdotto(Prodotto prodotto){
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).getNome().equals(prodotto.getNome())){
                return i;
            }
        }
        return -1;
    }

    public void remove(Prodotto_Quantita prodotto_quantita){
        lista.remove(prodotto_quantita);
    }
}
