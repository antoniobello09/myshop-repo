package DAO.Classi;

import DAO.Interfacce.IFornitoreDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Fornitore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FornitoreDAO implements IFornitoreDAO {

    private static FornitoreDAO instance = new FornitoreDAO();
    private Fornitore fornitore;
    private static IDbConnection conn;
    private static ResultSet rs;

    private FornitoreDAO(){
        fornitore = null;
        conn = null;
        rs = null;
    }

    public static FornitoreDAO getInstance() {
        return instance;
    }

    @Override
    public int add(Fornitore fornitore) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO fornitore(nome, sito_web, citta, nazione, prod_serv) VALUES ('"+ fornitore.getNome() + "','" + fornitore.getSitoweb() + "','" + fornitore.getCitta() + "','"+ fornitore.getNazione() + "','" + fornitore.getProd_serv() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Fornitore fornitore) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE fornitore SET nome = '"+ fornitore.getNome() + "', sito_web = '" + fornitore.getSitoweb() + "', citta = '" + fornitore.getCitta() + "', nazione = '" + fornitore.getNazione() + "' WHERE idFornitore = '" + fornitore.getIdFornitore() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Fornitore fornitore) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM fornitore WHERE idFornitore = '" + fornitore.getIdFornitore() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public Fornitore findByID(int idFornitore){
        return findByID(idFornitore, 0);
    }
    public Fornitore findByID(int idFornitore, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM fornitore WHERE idFornitore = '" + idFornitore + "';");
        Fornitore fornitore;
        try {
            rs.next();
            fornitore = new Fornitore();
            fornitore.setIdFornitore(rs.getInt("idFornitore"));
            fornitore.setNome(rs.getString("nome"));
            fornitore.setSitoweb(rs.getString("sito_web"));
            fornitore.setCitta(rs.getString("citta"));
            fornitore.setNazione(rs.getString("nazione"));
            fornitore.setProd_serv(rs.getString("prod_serv"));
            return fornitore;
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
    public Fornitore findByName(String nomefornitore) {
        return findByName(nomefornitore,0);
    }

    @Override
    public Fornitore findByName(String nomefornitore, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM fornitore WHERE nome = '" + nomefornitore + "';");
        Fornitore fornitore;
        try {
            rs.next();
            fornitore = new Fornitore();
            fornitore.setIdFornitore(rs.getInt("idFornitore"));
            fornitore.setNome(rs.getString("nome"));
            fornitore.setSitoweb(rs.getString("sito_web"));
            fornitore.setCitta(rs.getString("citta"));
            fornitore.setNazione(rs.getString("nazione"));
            fornitore.setProd_serv(rs.getString("prod_serv"));
            return fornitore;
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
    public ArrayList<Fornitore> findAll() {

        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM fornitore;");
        ArrayList<Fornitore> produttori = new ArrayList<>();

        try {
            while (rs.next()) {
                fornitore = new Fornitore();
                fornitore.setIdFornitore(rs.getInt("idFornitore"));
                fornitore.setNome(rs.getString("nome"));
                fornitore.setSitoweb(rs.getString("sito_web"));
                fornitore.setCitta(rs.getString("citta"));
                fornitore.setNazione(rs.getString("nazione"));
                fornitore.setProd_serv(rs.getString("prod_serv"));
                produttori.add(fornitore);
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


    public ArrayList<Fornitore> findAllProd(){
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM fornitore WHERE prod_serv = 'p';");
        ArrayList<Fornitore> produttori = new ArrayList<>();

        try {
            while (rs.next()) {
                fornitore = new Fornitore();
                fornitore.setIdFornitore(rs.getInt("idFornitore"));
                fornitore.setNome(rs.getString("nome"));
                fornitore.setSitoweb(rs.getString("sito_web"));
                fornitore.setCitta(rs.getString("citta"));
                fornitore.setNazione(rs.getString("nazione"));
                fornitore.setProd_serv(rs.getString("prod_serv"));
                produttori.add(fornitore);
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
    public ArrayList<Fornitore> findAllServ(){
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM fornitore WHERE prod_serv = 's';");
        ArrayList<Fornitore> produttori = new ArrayList<>();

        try {
            while (rs.next()) {
                fornitore = new Fornitore();
                fornitore.setIdFornitore(rs.getInt("idFornitore"));
                fornitore.setNome(rs.getString("nome"));
                fornitore.setSitoweb(rs.getString("sito_web"));
                fornitore.setCitta(rs.getString("citta"));
                fornitore.setNazione(rs.getString("nazione"));
                fornitore.setProd_serv(rs.getString("prod_serv"));
                produttori.add(fornitore);
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
