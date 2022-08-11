package View.Center.Cliente;

import DAO.Classi.PuntoVenditaDAO;
import Model.Lista;
import Model.PuntoVendita;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ListaTableModel extends AbstractTableModel {

    private ArrayList<Lista> lista;
    private ArrayList<PuntoVendita> puntiVendita;


    public ListaTableModel(ArrayList<Lista> lista){
        puntiVendita = PuntoVenditaDAO.getInstance().findAll();
        this.lista = lista;
    }

    @Override
    public String getColumnName(int column) {

        switch(column) {
            case 0:
                return "Nome";
            case 1:
                return "Punto vendita";
            case 2: return "Acquistato";
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }


    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                lista.get(rowIndex).setNome(value.toString());
                break;
            case 1:
                lista.get(rowIndex).setIdPuntoVendita((Integer) value);
                break;
            case 2:
                lista.get(rowIndex).setAcquistato(Boolean.parseBoolean(value.toString()));
                break;
        }
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
        Lista l = lista.get(rowIndex);
        PuntoVendita puntoVendita = findByID(l.getIdPuntoVendita());
        String indirizzo = puntoVendita.getIndirizzo() + ", " + puntoVendita.getCitta();
        if(l.getAcquisto() == null){
            l.setAcquistato(false);
        }else{
            l.setAcquistato(true);
        }
        switch(columnIndex) {
            case 0: return l.getNome();
            case 1: return indirizzo;
            case 2: return l.isAcquistato();
        }
        return null;
    }

    public ArrayList<Lista> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Lista> lista) {
        this.lista = lista;
    }

    public PuntoVendita findByID(int idPuntoVendita){
        for(int i=0;i<puntiVendita.size();i++){
            if(puntiVendita.get(i).getIdPuntoVendita() == idPuntoVendita){
                return puntiVendita.get(i);
            }
        }
        return null;
    }
}
