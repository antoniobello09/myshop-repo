package Business.ModelBusiness.TableModels;

import Business.SessionManager;
import DAO.Classi.CategoriaProdottoDAO;
import DAO.Classi.FornitoreDAO;
import DAO.Classi.SchedaProdottoDAO;
import Model.Prodotto;
import Model.SchedaProdotto;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;

public class ProdottiTableModel extends AbstractTableModel {


    private List<Prodotto> lista;
    private int rowEditable = -1;

    public ProdottiTableModel(List<Prodotto> lista) {
        this.lista = lista;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return rowIndex == rowEditable;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
/*      switch (columnIndex) {
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
            case 4: return "Sottocategoria";
            case 5: return "Immagine";
            case 6: return "Produttore";
            case 7: return "Disponibilit√†";
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prodotto p = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return p.getNome();
            case 1: return p.getDescrizione();
            case 2: return p.getPrezzo();
            //Ora per far vedere i nomi delle categorie, li devo recuperare grazie all' idCategoria presente nel Prodotto p
            //Nome della categoria padre
            case 3: return CategoriaProdottoDAO.getInstance().findByID(CategoriaProdottoDAO.getInstance().findByID(p.getIdCategoria()).getIdCategoriaPadre()).getNome();
            //Nome della sottocategoria
            case 4: return CategoriaProdottoDAO.getInstance().findByID(p.getIdCategoria()).getNome();
            case 5://Serve a far vedere l'immagine
                if(p.getImmagine()!=null){
                    ImageIcon imageIcon = new ImageIcon(p.getImmagine().getPath());
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
            case 6: return FornitoreDAO.getInstance().findByID(p.getIdProduttore()).getNome();
            //Per far vedere la disponibilit√† di un prodotto nel punto vendita dove mi trovo recupero la scheda prodotto.
            //Se il findByProduct_Shop non trova la scheda allora significa che il prodotto non √® associato dall'admin al punto vendita dove mi trovo
            //e quindi ritorno una disponibilit√† pari a 0
            //altrimenti ritorno la disponibilit√† della scheda prodotto trovata
            case 7: SchedaProdotto schedaProdotto = SchedaProdottoDAO.getInstance().findByShop_Product(p.getIdArticolo(), (int) SessionManager.getInstance().getSession().get("idPuntoVendita"));
                    if(schedaProdotto != null) return schedaProdotto.getDisponibilita();
                    else return 0;
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


    //Serve per far vedere le immagini
    @Override
    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 5: return ImageIcon.class;
            default: return String.class;
        }
    }

}
