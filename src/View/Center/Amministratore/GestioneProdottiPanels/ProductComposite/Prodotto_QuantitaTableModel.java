package View.Center.Amministratore.GestioneProdottiPanels.ProductComposite;

import Model.Prodotto_Quantita;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class Prodotto_QuantitaTableModel extends AbstractTableModel {


    private List<Prodotto_Quantita> lista;
    private int rowEditable = -1;

    public Prodotto_QuantitaTableModel(List<Prodotto_Quantita> lista) {
        this.lista = lista;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 6;
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
            case 6: lista.get(rowIndex).setQuantita(Integer.parseInt(value.toString()));break;
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
            case 6: return "Quantità:";
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prodotto_Quantita p = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return p.getNome();
            case 1: return p.getPrezzo();
            case 2: return p.getDescrizione();
            case 3: return p.getCategoria().getNome();
            case 4: return p.getCategoria().getSottocategorie().get(0).getNome();
            case 5: return p.getProduttore().getNome();
            case 6: return p.getQuantita();
        }

        return null;
    }

    public List<Prodotto_Quantita> getLista() {
        return lista;
    }

    public int getRowEditable() {
        return rowEditable;
    }

    public void setRowEditable(int rowEditable) {
        this.rowEditable = rowEditable;
    }

    public void add(Prodotto_Quantita p){
        int index = cercaLista(p);
        if(index != -1){
            lista.get(index).addQuantity(p.getQuantita());
        }else{
            lista.add(p);
        }
    }

    public void copyList(List<Prodotto_Quantita> lista){
        this.lista = lista;
    }

    public int cercaLista(Prodotto_Quantita p){
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).getNome().equals(p.getNome())){
                return i;
            }
        }
        return -1;
    }

    public void remove(int index){
        lista.remove(index);
    }
}
