package DAO.Classi;

import DAO.Interfacce.IPosizioneDAO;
import DbInterface.Command.DbOperationExecutor;
import DbInterface.Command.IDbOperation;
import DbInterface.Command.ReadOperation;
import DbInterface.Command.WriteOperation;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Posizione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PosizioneDAO implements IPosizioneDAO {

    private static PosizioneDAO instance = new PosizioneDAO();
    private Posizione posizione;
    private static IDbConnection conn;
    private static ResultSet rs;

    private PosizioneDAO(){
        posizione = null;
        conn = null;
        rs = null;
    }

    public static PosizioneDAO getInstance() {
        return instance;
    }

    @Override
    public int add(Posizione posizione) {
        return 0;
    }

    @Override
    public int update(Posizione posizione) {
        return 0;
    }

    @Override
    public int delete(Posizione posizione) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "DELETE FROM posizione WHERE idPosizione = '" + posizione.getIdPosizione() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public Posizione findByID(int idPosizione) {
        conn = DbConnection.getInstance();
        String sql = "SELECT * FROM posizione WHERE idPosizione = '" + idPosizione + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        Posizione posizione;
        try {
            rs.next();
            posizione = new Posizione();
            posizione.setIdPosizione(rs.getInt("idPosizione"));
            posizione.setPiano(rs.getInt("piano"));
            posizione.setCorsia(rs.getInt("corsia"));
            posizione.setScaffale(rs.getInt("scaffale"));
            return posizione;
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
    public Posizione findByNumbers(int piano, int corsia, int scaffale){
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM posizione WHERE piano = '" + piano + "' AND corsia = '" + corsia + "' AND scaffale = '" + scaffale + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        Posizione posizione;
        try {
            rs.next();
            if(rs.getRow() == 1) {
                posizione = new Posizione();
                posizione.setIdPosizione(rs.getInt("idPosizione"));
                posizione.setPiano(rs.getInt("piano"));
                posizione.setCorsia(rs.getInt("corsia"));
                posizione.setScaffale(rs.getInt("scaffale"));
                return posizione;
            }
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
    public ArrayList<Posizione> findAll() {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM posizione;";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        ArrayList<Posizione> posizioni = new ArrayList<>();
        try {
            while(rs.next()) {
                posizione = new Posizione();
                posizione.setIdPosizione(rs.getInt("idPosizione"));
                posizione.setPiano(rs.getInt("piano"));
                posizione.setCorsia(rs.getInt("corsia"));
                posizione.setScaffale(rs.getInt("scaffale"));
                posizioni.add(posizione);
            }
            return posizioni;
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
    public ArrayList<Posizione> findAllEmpty() {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM posizione p LEFT OUTER JOIN prodotto pr ON pr.idPosizione = p.idPosizione WHERE pr.idProdotto IS NULL;";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        ArrayList<Posizione> posizioni = new ArrayList<>();
        try {
            while(rs.next()) {
                posizione = new Posizione();
                posizione.setIdPosizione(rs.getInt("idPosizione"));
                posizione.setPiano(rs.getInt("piano"));
                posizione.setCorsia(rs.getInt("corsia"));
                posizione.setScaffale(rs.getInt("scaffale"));
                posizioni.add(posizione);
            }
            return posizioni;
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
