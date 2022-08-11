package View.Center.Cliente;

import Model.*;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ArticoloTableModel extends AbstractTableModel {

    private Lista lista;
    private ArrayList<Articolo> listaA;

    public ArticoloTableModel(Lista lista){
        this.lista = lista;
        listaA = new ArrayList<>();
        listaA.addAll(lista.getProdotti());
        listaA.addAll(lista.getServizi());
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "Nome";
            case 1: return "Quantità";
            case 2: return "Prezzo";
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: listaA.get(rowIndex).setNome(value.toString());break;
            case 1: if(listaA.get(rowIndex) instanceof Prodotto_Quantita){
                ((Prodotto_Quantita) listaA.get(rowIndex)).setQuantita((int)value);
            }
            case 2: listaA.get(rowIndex).setPrezzo((Float)value);
        }
    }

    @Override
    public int getRowCount() {
        int size = 0;
        size += lista.getServizi().size();
        size += lista.getProdotti().size();
        return size;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Articolo a = listaA.get(rowIndex);
        switch(columnIndex) {
            case 0: return a.getNome();
            case 1: if(a instanceof Prodotto_Quantita){
                return ((Prodotto_Quantita) a).getQuantita();
            }else{

            }
            case 2: if(a instanceof Prodotto_Quantita){
                return ((Prodotto_Quantita) a).getQuantita()*a.getPrezzo();
            }else{
                return a.getPrezzo();
            }
        }
        return null;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

}
