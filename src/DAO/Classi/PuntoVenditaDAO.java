package DAO.Classi;

import DAO.Interfacce.IPuntoVenditaDAO;
import DbInterface.Command.DbOperationExecutor;
import DbInterface.Command.IDbOperation;
import DbInterface.Command.ReadOperation;
import DbInterface.Command.WriteOperation;
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
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "INSERT INTO puntovendita(idManager, citta, indirizzo) VALUES ('" + idManager + "','" + citta + "','" + indirizzo + "');";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int update(int idPuntoVendita, String citta, String indirizzo) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "UPDATE puntovendita SET citta = '" + citta + "', indirizzo = '" + indirizzo + "' WHERE idPuntoVendita = '" + idPuntoVendita + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(int idPuntoVendita) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "DELETE FROM puntovendita WHERE idPuntoVendita = '" + idPuntoVendita;
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public PuntoVendita findByID(int idPuntoVendita) {
        return findByID(idPuntoVendita, 0);
    }

    public PuntoVendita findByID(int idPuntoVendita, int closeConn) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM puntovendita WHERE idPuntoVendita = '" + idPuntoVendita + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
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
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM puntovendita WHERE indirizzo ='" + indirizzo + "' AND citta = '" + citta + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
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
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM puntovendita WHERE idManager = '" + idManager + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
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
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM puntovendita;";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
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
