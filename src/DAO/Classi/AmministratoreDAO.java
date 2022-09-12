package DAO.Classi;

import DAO.Interfacce.IAmministratoreDAO;
import DbInterface.Command.DbOperationExecutor;
import DbInterface.Command.IDbOperation;
import DbInterface.Command.ReadOperation;
import DbInterface.Command.WriteOperation;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Amministratore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AmministratoreDAO implements IAmministratoreDAO {

    private static AmministratoreDAO instance = new AmministratoreDAO();
    private Amministratore amministratore;
    private static IDbConnection conn;
    private static ResultSet rs;

    private AmministratoreDAO() {
        amministratore = null;
        conn = null;
        rs = null;
    }

    public static AmministratoreDAO getInstance() {
        return instance;
    }

    @Override
    public int add(Amministratore amministratore) {
        return 0;
    }

    @Override
    public int update(Amministratore amministratore) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "UPDATE utente SET username = '" + amministratore.getUsername() + "', password = '" + amministratore.getPassword() + "', email = '" + amministratore.getEmail() + "' WHERE idUtente = '" + amministratore.getIdUtente() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Amministratore amministratore) {
        return 0;
    }

    @Override
    public Amministratore findByID(int idAmministratore) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        rs = conn.executeQuery("SELECT * FROM utente INNER JOIN amministratore ON idUtente = idAmministratore WHERE idAmministratore = '" + idAmministratore + "';");
        Amministratore amministratore;
        try {
            rs.next();
            amministratore = new Amministratore();
            amministratore.setIdUtente(rs.getInt("idUtente"));
            amministratore.setEmail(rs.getString("email"));
            amministratore.setUsername(rs.getString("username"));
            amministratore.setPassword(rs.getString("password"));
            return amministratore;
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
    public Amministratore findByUsername(String username) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM utente INNER JOIN amministratore ON idUtente = idAmministratore WHERE username = '" + username + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        Amministratore amministratore;
        try {
            rs.next();
            amministratore = new Amministratore();
            amministratore.setIdUtente(rs.getInt("idUtente"));
            amministratore.setEmail(rs.getString("email"));
            amministratore.setUsername(rs.getString("username"));
            amministratore.setPassword(rs.getString("password"));
            return amministratore;
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
    public ArrayList<Amministratore> findAll() {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM utente INNER JOIN amministratore ON idUtente = idAmministratore;";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        ArrayList<Amministratore> amministratori = new ArrayList<>();
        try {
            while(rs.next()) {
                amministratore = new Amministratore();
                amministratore.setIdUtente(rs.getInt("idUtente"));
                amministratore.setEmail(rs.getString("email"));
                amministratore.setUsername(rs.getString("username"));
                amministratore.setPassword(rs.getString("password"));
                amministratori.add(amministratore);
            }
            return amministratori;
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
