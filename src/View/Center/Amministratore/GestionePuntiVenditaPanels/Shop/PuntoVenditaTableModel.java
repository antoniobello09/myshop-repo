package View.Center.Amministratore.GestionePuntiVenditaPanels.Shop;

import DAO.Classi.ManagerDAO;
import Model.PuntoVendita;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Iterator;

public class PuntoVenditaTableModel extends AbstractTableModel {

    private ArrayList<PuntoVendita> lista;
    private int selectedRow;
    private int editableRow = -1;

    public PuntoVenditaTableModel(ArrayList<PuntoVendita> lista){
        this.lista = lista;
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "Città";
            case 1: return "Indirizzo";
            case 2: return "Manager";
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return rowIndex == editableRow;
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
        PuntoVendita p = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return p.getCitta();
            case 1: return p.getIndirizzo();
            case 2: if(p.getManager() != null){
                        return p.getManager().getUsername();
                    }else{
                        return "";
                    }
        }
        return null;
    }

    public ArrayList<PuntoVendita> getLista() {
        return lista;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: lista.get(rowIndex).setCitta(value.toString());break;
            case 1: lista.get(rowIndex).setIndirizzo(value.toString());break;
            case 2: lista.get(rowIndex).setManager(ManagerDAO.getInstance().findByUsername(value.toString()));
        }
    }

    public void setLista(ArrayList<PuntoVendita> lista) {
        this.lista = lista;
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }

    public PuntoVendita findPuntoVendita(String indirizzo){
        Iterator<PuntoVendita> it = lista.iterator();
        while(it.hasNext()){
            PuntoVendita p = it.next();
            if(indirizzo.equals(p.getIndirizzo())){
                return p;
            }
        }
        return null;
    }


    public void setEditableRow(int editableRow){
        this.editableRow = editableRow;
    }

    public int getEditableRow() {
        return editableRow;
    }
}
