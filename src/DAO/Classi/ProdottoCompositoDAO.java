package DAO.Classi;

import DAO.Interfacce.IProdottoCompositoDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Prodotto;
import Model.ProdottoComposito;
import Model.Prodotto_Quantita;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoCompositoDAO implements IProdottoCompositoDAO {

    private static ProdottoCompositoDAO instance = new ProdottoCompositoDAO();
    private ProdottoComposito prodottoComposito;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ProdottoCompositoDAO(){
        prodottoComposito = null;
        conn = null;
        rs = null;
    }

    public static ProdottoCompositoDAO getInstance() {
        return instance;
    }

    @Override
    public int add(int idProdottoComposito, Prodotto_Quantita prodotto_quantita) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO prodottocomposito(idProdottoComposito, idProdotto, quantita) VALUES ('" + idProdottoComposito + "','" + prodotto_quantita.getProdotto().getIdArticolo() + "','" + prodotto_quantita.getQuantita() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(int idProdottoComposito, Prodotto_Quantita prodotto_quantita) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE prodottocomposito SET quantita = '" + prodotto_quantita.getQuantita() + "' WHERE idProdottoCmposito = '" + idProdottoComposito +"' AND idProdotto = '" + prodotto_quantita.getProdotto().getIdArticolo() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(int idProdottoComposito) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM prodottocomposito WHERE idProdottoComposito = '" + idProdottoComposito + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public ArrayList<Prodotto_Quantita> findSonsByID(int idProdottoComposito){
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM prodottocomposito WHERE idProdottoComposito = " + idProdottoComposito + "';");
        ArrayList<Prodotto_Quantita> sottoprodotti = new ArrayList<>();
        try {
            while(rs.next()) {
                Prodotto prodotto = ProdottoDAO.getInstance().findByID(rs.getInt("idProdotto"));
                Prodotto_Quantita prodotto_quantita = new Prodotto_Quantita();
                prodotto_quantita.setQuantita(rs.getInt("quantita"));
                prodotto_quantita.setProdotto(prodotto);
                sottoprodotti.add(prodotto_quantita);
            }
            return sottoprodotti;
        } catch (SQLException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }

    @Override
    public ProdottoComposito findByID(int idProdottoComposito) {
        prodottoComposito = (ProdottoComposito) ProdottoDAO.getInstance().findByID(idProdottoComposito);
        prodottoComposito.setSottoprodotti(ProdottoCompositoDAO.getInstance().findSonsByID(idProdottoComposito));
        return prodottoComposito;
    }


    @Override
    public ArrayList<ProdottoComposito> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT DISTINCT idProdottoComposito FROM prodottocomposito;");
        ArrayList<ProdottoComposito> prodottoCompositi = new ArrayList<>();
        try {
            while(rs.next()) {
                prodottoComposito = findByID(rs.getInt("idProdottoComposito"));
                prodottoCompositi.add(prodottoComposito);
            }
            return prodottoCompositi;
        } catch (SQLException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }

    @Override
    public ProdottoComposito findByName(String nomeProdottoC) {
        prodottoComposito = (ProdottoComposito) ProdottoDAO.getInstance().findByName(nomeProdottoC);
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM prodottocomposito pc INNER JOIN prodotto p ON pc.idProdottoComposito = p.idProdotto WHERE p.nome = '" + nomeProdottoC + "';");
        try {
            while(rs.next()) {
                Prodotto p = ProdottoDAO.getInstance().findByID(rs.getInt("idProdotto"));
                prodottoComposito.getSottoprodotti().add(new Prodotto_Quantita(p, rs.getInt("quantita")));
            }
            return prodottoComposito;
        } catch (SQLException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }

}
