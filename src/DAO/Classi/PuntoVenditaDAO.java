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
    public int add(PuntoVendita puntoVendita) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "INSERT INTO puntovendita(idManager, citta, indirizzo) VALUES ('" + puntoVendita.getIdManager() + "','" + puntoVendita.getCitta() + "','" + puntoVendita.getIndirizzo() + "');";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int update(PuntoVendita puntoVendita) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "UPDATE puntovendita SET citta = '" + puntoVendita.getCitta() + "', indirizzo = '" + puntoVendita.getIndirizzo() + "' WHERE idPuntoVendita = '" + puntoVendita.getIdPuntoVendita() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(PuntoVendita puntoVendita) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "DELETE FROM puntovendita WHERE idPuntoVendita = '" + puntoVendita.getIdPuntoVendita() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public PuntoVendita findByID(int idPuntoVendita) {
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
            conn.close();
        }
        return null;
    }

    @Override
    public PuntoVendita findByName(String indirizzo, String citta){
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM puntovendita WHERE indirizzo = '" + indirizzo + "' AND citta = '" + citta + "';";
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
            conn.close();
        }
        return null;
    }

    @Override
    public PuntoVendita findByManager(int idManager) {
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
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<PuntoVendita> findAll(){
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
            conn.close();
        }
        return null;
    }
}
