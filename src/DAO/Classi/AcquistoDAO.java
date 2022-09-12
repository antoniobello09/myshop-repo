package DAO.Classi;

import DAO.Interfacce.IAcquistoDAO;
import DbInterface.Command.DbOperationExecutor;
import DbInterface.Command.IDbOperation;
import DbInterface.Command.ReadOperation;
import DbInterface.Command.WriteOperation;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Acquisto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AcquistoDAO implements IAcquistoDAO {

    private static AcquistoDAO instance = new AcquistoDAO();
    private Acquisto acquisto;
    private static IDbConnection conn;
    private static ResultSet rs;

    private AcquistoDAO(){
        acquisto = null;
        conn = null;
        rs = null;
    }

    public static AcquistoDAO getInstance() {
        return instance;
    }

    @Override
    public int add(Acquisto acquisto) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "INSERT INTO acquisto(idPuntoVendita, data, idLista) VALUES ('"+ acquisto.getIdPuntoVendita() + "','" + acquisto.getData() + "','" + acquisto.getIdLista() + "');";
        IDbOperation dbOperation = new WriteOperation(sql);
        int rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Acquisto acquisto) {
        return 0;
    }

    @Override
    public int delete(Acquisto acquisto) {
        return 0;
    }

    @Override
    public Acquisto findByID(int idAcquisto){
        return findByID(idAcquisto, 0);
    }
    public Acquisto findByID(int idAcquisto, int closeConn) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM acquisto WHERE idAcquisto = '" + idAcquisto + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        Acquisto acquisto;
        try {
            rs.next();
            acquisto = new Acquisto();
            acquisto.setIdAcquisto(rs.getInt("idAcquisto"));
            acquisto.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
            acquisto.setData(rs.getDate("data"));
            acquisto.setIdLista(rs.getInt("idLista"));
            return acquisto;
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
    public ArrayList<Acquisto> findAll() {

        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM acquisto;";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        ArrayList<Acquisto> acquisti = new ArrayList<>();

        try {
            while (rs.next()) {
                acquisto = new Acquisto();
                acquisto.setIdAcquisto(rs.getInt("idAcquisto"));
                acquisto.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
                acquisto.setData(rs.getDate("data"));
                acquisto.setIdLista(rs.getInt("idLista"));
                acquisti.add(acquisto);
            }
            return acquisti;
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

    public Acquisto findByIDLista(int idLista){
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM acquisto WHERE idLista = '" + idLista + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        Acquisto acquisto;
        try {
            if(rs.next()) {
                acquisto = new Acquisto();
                acquisto.setIdAcquisto(rs.getInt("idAcquisto"));
                acquisto.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
                acquisto.setData(rs.getDate("data"));
                acquisto.setIdLista(rs.getInt("idLista"));
                return acquisto;
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

}
