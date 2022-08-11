package View.Center.Manager.GestioneSchedeProdotto;

import Model.CategoriaProdotto;
import Model.Magazzino;
import Model.SchedaProdotto;
import Model.SchedaServizio;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SchedaProdottoModel extends AbstractTableModel {

    private Magazzino magazzino;
    private ArrayList<SchedaProdotto> lista = new ArrayList<>();
    private int editableRow;

    public SchedaProdottoModel(Magazzino magazzino){
        resetLista(magazzino);
    }

    public void resetLista(Magazzino magazzino){
        lista = new ArrayList<>();
        this.magazzino = magazzino;
        lista.addAll(magazzino.getProdottiSenzaPosizione());
        for(int i=0;i<magazzino.getPiani().size();i++){
            for(int j=0;j<magazzino.getPiani().get(i).getCorsie().size();j++){
                for(int k=0;k<magazzino.getPiani().get(i).getCorsie().get(j).getScaffali().size();k++){
                    if(magazzino.getPiani().get(i).getCorsie().get(j).getScaffali().get(k).getProdotto() != null) {
                        lista.add(magazzino.getPiani().get(i).getCorsie().get(j).getScaffali().get(k).getProdotto());
                    }
                }
            }
        }
    }

    public Magazzino getMagazzino() {
        return magazzino;
    }

    public void setMagazzino(Magazzino magazzino) {
        this.magazzino = magazzino;
        resetLista(magazzino);
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "Nome";
            case 1: return "Disponibilita";
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
            case 0: lista.get(rowIndex).getProdotto().setNome(value.toString());break;
            case 1: lista.get(rowIndex).setDisponibilita((int)value);break;
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
        SchedaProdotto s = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return s.getProdotto().getNome();
            case 1: return s.getDisponibilita();
        }
        return null;
    }

    public ArrayList<SchedaProdotto> getLista() {
        return lista;
    }

    public void setLista(ArrayList<SchedaProdotto> lista) {
        this.lista = lista;
    }

    public int getEditableRow() {
        return editableRow;
    }

    public void setEditableRow(int editableRow) {
        this.editableRow = editableRow;
    }
}
