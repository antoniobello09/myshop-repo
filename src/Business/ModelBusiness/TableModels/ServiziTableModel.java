package Business.ModelBusiness.TableModels;

import DAO.Classi.CategoriaServizioDAO;
import DAO.Classi.FornitoreDAO;
import Model.Servizio;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;

public class ServiziTableModel extends AbstractTableModel {


    private List<Servizio> lista;
    private int rowEditable = -1;

    public ServiziTableModel(List<Servizio> lista) {
        this.lista = lista;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return rowIndex == rowEditable;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
/*        switch (columnIndex) {
            case 0: lista.get(rowIndex).setNome(value.toString());break;
            case 1: lista.get(rowIndex).setPrezzo(Float.parseFloat(value.toString()));break;
            case 2: lista.get(rowIndex).setDescrizione(value.toString());break;
            case 3: lista.get(rowIndex).getCategoria().setNome(value.toString());
                    lista.get(rowIndex).getCategoria().getSottocategorie().get(0).setNome("");break;
            case 4: lista.get(rowIndex).getCategoria().getSottocategorie().get(0).setNome(value.toString());break;
            case 5: lista.get(rowIndex).getProduttore().setNome(value.toString());break;
        }
 */
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch(columnIndex) {
            case 0: return "Nome";
            case 1: return "Descrizione";
            case 2: return "Prezzo";
            case 3: return "Categoria";
            case 4: return "Immagine";
            case 5: return "Fornitore";
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
        Servizio s = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return s.getNome();
            case 1: return s.getDescrizione();
            case 2: return s.getPrezzo();
            //Ora per far vedere il nome della categoria, lo devo recuperare grazie all' idCategoria presente nel Prodotto p
            case 3: return CategoriaServizioDAO.getInstance().findByID(s.getIdCategoria()).getNome();
            case 4:
                //Serve per far vedere le immagini
                if(s.getImmagine()!=null){
                    ImageIcon imageIcon = new ImageIcon(s.getImmagine().getPath());
                    if (imageIcon .getIconWidth() > 100) {
                        imageIcon  = new ImageIcon(imageIcon .getImage().
                                getScaledInstance(100, -1,
                                        Image.SCALE_DEFAULT));
                    }
                    if (imageIcon .getIconHeight() > 60) {
                        imageIcon  = new ImageIcon(imageIcon .getImage().
                                getScaledInstance(-1, 60,
                                        Image.SCALE_DEFAULT));
                    }
                    return imageIcon;
                } else {
                    return null;
                }
            case 5: return FornitoreDAO.getInstance().findByID(s.getIdFornitoreServizio()).getNome();
        }
        return null;
    }

    public List<Servizio> getLista() {
        return lista;
    }

    public int getRowEditable() {
        return rowEditable;
    }

    public void setRowEditable(int rowEditable) {
        this.rowEditable = rowEditable;
    }


    //Serve per far vedere le immagini
    @Override
    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 4: return ImageIcon.class;
            default: return String.class;
        }
    }

}
