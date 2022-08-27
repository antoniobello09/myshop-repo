package DAO.Classi;

import DAO.Interfacce.IPuntoVenditaDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.PuntoVendita;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PuntoVenditaDAO implements IPuntoVenditaDAO {

    private static PuntoVenditaDAO instance = new PuntoVenditaDAO();
    private PuntoVendita puntoVendita;
    private static IDbConnection conn;
    private static ResultSet rs;

    private PuntoVenditaDAO(){
        puntoVendita = null;
        conn = null;
        rs = null;
    }

    public static PuntoVenditaDAO getInstance() {
        return instance;
    }

    @Override
    public int add(int idManager, String citta, String indirizzo) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO puntovendita(idManager, citta, indirizzo) VALUES ('" + idManager + "','" + citta + "','" + indirizzo + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(int idPuntoVendita, String citta, String indirizzo) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE puntovendita SET citta = '" + citta + "', indirizzo = '" + indirizzo + "' WHERE idPuntoVendita = '" + idPuntoVendita + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(int idPuntoVendita) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM puntovendita WHERE idPuntoVendita = '" + idPuntoVendita);
        conn.close();
        return rowCount;
    }

    @Override
    public PuntoVendita findByID(int idPuntoVendita) {
        return findByID(idPuntoVendita, 0);
    }

    public PuntoVendita findByID(int idPuntoVendita, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM puntovendita WHERE idPuntoVendita = '" + idPuntoVendita + "';");
        PuntoVendita puntoVendita;
        try {
            rs.next();
            puntoVendita = new PuntoVendita();
            puntoVendita.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
            puntoVendita.setIdManager(rs.getInt("idManager"));
            puntoVendita.setCitta(rs.getString("citta"));
            puntoVendita.setIndirizzo(rs.getString("indirizzo"));
            return puntoVendita;
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
    public PuntoVendita findByName(String indirizzo, String citta) {
        return findByName(indirizzo, citta, 0);
    }

    public PuntoVendita findByName(String indirizzo, String citta, int closeConn){
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM puntovendita WHERE indirizzo ='" + indirizzo + "' AND citta = '" + citta + "';");
        PuntoVendita puntoVendita;
        try {
            rs.next();
            puntoVendita = new PuntoVendita();
            puntoVendita.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
            puntoVendita.setIdManager(rs.getInt("idManager"));
            puntoVendita.setCitta(rs.getString("citta"));
            puntoVendita.setIndirizzo(rs.getString("indirizzo"));
            return puntoVendita;
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
    public PuntoVendita findByManager(int idManager) {
        return findByManager(idManager, 0);
    }

    public PuntoVendita findByManager(int idManager, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM puntovendita WHERE idManager = '" + idManager + "';");
        PuntoVendita puntoVendita;
        try {
            rs.next();
            puntoVendita = new PuntoVendita();
            puntoVendita.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
            puntoVendita.setIdManager(rs.getInt("idManager"));
            puntoVendita.setCitta(rs.getString("citta"));
            puntoVendita.setIndirizzo(rs.getString("indirizzo"));
            return puntoVendita;
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
    public ArrayList<PuntoVendita> findAll() {
        return findAll(0);
    }

    public ArrayList<PuntoVendita> findAll(int closeConn){
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM puntovendita;");
        ArrayList<PuntoVendita> puntiVendita = new ArrayList<>();
        try {
            while(rs.next()) {
                PuntoVendita puntoVendita = new PuntoVendita();
                puntoVendita.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
                puntoVendita.setIdManager(rs.getInt("idManager"));
                puntoVendita.setCitta(rs.getString("citta"));
                puntoVendita.setIndirizzo(rs.getString("indirizzo"));
                puntiVendita.add(puntoVendita);
            }
            return puntiVendita;
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
}
