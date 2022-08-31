package DAO.Classi;

import DAO.Interfacce.IProdottoDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Articolo;
import Model.Prodotto;
import Model.Fornitore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoDAO implements IProdottoDAO {

    private static ProdottoDAO instance = new ProdottoDAO();
    private Prodotto prodotto;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ProdottoDAO(){
        prodotto = null;
        conn = null;
        rs = null;
    }

    public static ProdottoDAO getInstance() {
        return instance;
    }

    @Override
    public int add(Prodotto prodotto) {
        ArticoloDAO.getInstance().add(prodotto);
        int idProdotto = ArticoloDAO.getInstance().findByName(prodotto.getNome()).getIdArticolo();
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO prodotto(idProdotto,idProduttore,idPosizione) VALUES ('" +  idProdotto + "','" + prodotto.getIdProduttore() +"','" + prodotto.getIdPosizione() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Prodotto prodotto) {
        ArticoloDAO.getInstance().update(prodotto);
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE prodotto SET idProduttore = '" + prodotto.getIdProduttore() + "', idPosizione = '" + prodotto.getIdPosizione() + "' WHERE idProdotto = '" + prodotto.getIdArticolo() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Prodotto prodotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM prodotto WHERE idProdotto = '" + prodotto.getIdArticolo() + "';");
        ArticoloDAO.getInstance().delete(prodotto);
        conn.close();
        return rowCount;
    }


    @Override
    public Prodotto findByID(int idProdotto){
        return findByID(idProdotto, 0);
    }

    public Prodotto findByID(int idProdotto, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM prodotto INNER JOIN articolo ON prodotto.idProdotto = articolo.idArticolo WHERE prodotto.idProdotto = '" + idProdotto + "';");
        Prodotto prodotto;
        try {
            rs.next();
            prodotto = new Prodotto();
            prodotto.setIdArticolo(rs.getInt("idProdotto"));
            prodotto.setNome(rs.getString("nome"));
            prodotto.setIdCategoria(rs.getInt("idCategoria"));
            prodotto.setPrezzo(rs.getFloat("prezzo"));
            prodotto.setDescrizione(rs.getString("descrizione"));
            prodotto.setIdProduttore(rs.getInt("idProduttore"));
            prodotto.setIdPosizione(rs.getInt("idPosizione"));
            return prodotto;
        } catch (SQLException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            if(closeConn == 0)
            conn.close();
        }
        return null;
    }

    @Override
    public  Prodotto findByName(String nomeProdotto){
        return findByName(nomeProdotto, 0);
    }

    public Prodotto findByName(String nomeProdotto, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM prodotto INNER JOIN articolo ON prodotto.idProdotto = articolo.idArticolo WHERE articolo.nome = '" + nomeProdotto + "';");
        Prodotto prodotto = new Prodotto();
        try {
            rs.next();
            prodotto.setIdArticolo(rs.getInt("idProdotto"));
            prodotto.setNome(rs.getString("nome"));
            prodotto.setIdCategoria(rs.getInt("idCategoria"));
            prodotto.setPrezzo(rs.getFloat("prezzo"));
            prodotto.setDescrizione(rs.getString("descrizione"));
            prodotto.setIdProduttore(rs.getInt("idProduttore"));
            prodotto.setIdPosizione(rs.getInt("idPosizione"));
            return prodotto;
        } catch (SQLException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            if(closeConn == 0)
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<Prodotto> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM articolo INNER JOIN prodotto WHERE idArticolo = idProdotto;");
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        try {
            while (rs.next()) {
                prodotto = new Prodotto();
                prodotto.setIdArticolo(rs.getInt("idProdotto"));
                prodotto.setNome(rs.getString("nome"));
                prodotto.setIdCategoria(rs.getInt("idCategoria"));
                prodotto.setPrezzo(rs.getFloat("prezzo"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setIdProduttore(rs.getInt("idProduttore"));
                prodotto.setIdPosizione(rs.getInt("idPosizione"));
                prodotti.add(prodotto);
            }
            return prodotti;
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
