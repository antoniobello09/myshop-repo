package DAO.Classi;

import DAO.Interfacce.IProduttoreDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Produttore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProduttoreDAO implements IProduttoreDAO {

    private static ProduttoreDAO instance = new ProduttoreDAO();
    private Produttore produttore;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ProduttoreDAO(){
        produttore = null;
        conn = null;
        rs = null;
    }

    public static ProduttoreDAO getInstance() {
        return instance;
    }

    @Override
    public int add(Produttore produttore) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO produttore(nome, sito_web, citta, nazione) VALUES ('"+ produttore.getNome() + "','" + produttore.getSitoweb() + "','" + produttore.getCitta() + "','"+ produttore.getNazione() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Produttore produttore) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE produttore SET nome = '"+ produttore.getNome() + "', sito_web = '" + produttore.getSitoweb() + "', citta = '" + produttore.getCitta() + "', nazione = '" + produttore.getNazione() + "' WHERE idProduttore = '" + produttore.getIdProduttore() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Produttore produttore) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM produttore WHERE idProduttore = '" + produttore.getIdProduttore() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public Produttore findByID(int idProduttore){
        return findByID(idProduttore, 0);
    }
    public Produttore findByID(int idProduttore, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM produttore WHERE idProduttore = '" + idProduttore + "';");
        Produttore produttore;
        try {
            rs.next();
            produttore = new Produttore();
            produttore.setIdProduttore(rs.getInt("idProduttore"));
            produttore.setNome(rs.getString("nome"));
            produttore.setSitoweb(rs.getString("sito_web"));
            produttore.setCitta(rs.getString("citta"));
            produttore.setNazione(rs.getString("nazione"));
            return produttore;
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
    public Produttore findByName(String nomeProduttore) {
        return findByName(nomeProduttore,0);
    }

    @Override
    public Produttore findByName(String nomeProduttore, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM produttore WHERE nome = '" + nomeProduttore + "';");
        Produttore produttore;
        try {
            rs.next();
            produttore = new Produttore();
            produttore.setIdProduttore(rs.getInt("idProduttore"));
            produttore.setNome(rs.getString("nome"));
            produttore.setSitoweb(rs.getString("sito_web"));
            produttore.setCitta(rs.getString("citta"));
            produttore.setNazione(rs.getString("nazione"));
            return produttore;
        } catch (SQLException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            if(closeConn==0)
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<Produttore> findAll() {

        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM produttore WHERE nome <> 'Vario';");
        ArrayList<Produttore> produttori = new ArrayList<>();

        try {
            while (rs.next()) {
                produttore = new Produttore();
                produttore.setIdProduttore(rs.getInt("idProduttore"));
                produttore.setNome(rs.getString("nome"));
                produttore.setSitoweb(rs.getString("sito_web"));
                produttore.setCitta(rs.getString("citta"));
                produttore.setNazione(rs.getString("nazione"));
                produttori.add(produttore);
            }
            return produttori;
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
