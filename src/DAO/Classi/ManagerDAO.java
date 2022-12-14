package DAO.Classi;

import Business.FactoryMethod.UtenteFactory;
import DAO.Interfacce.IManagerDAO;
import DAO.UtenteDAO;
import DbInterface.Command.DbOperationExecutor;
import DbInterface.Command.IDbOperation;
import DbInterface.Command.ReadOperation;
import DbInterface.Command.WriteOperation;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Amministratore;
import Model.Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerDAO implements IManagerDAO {

    private static ManagerDAO instance = new ManagerDAO();
    private Manager manager;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ManagerDAO() {
        manager = null;
        conn = null;
        rs = null;
    }

    public static ManagerDAO getInstance() {
        return instance;
    }

    @Override
    public int add(Manager manager) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "INSERT INTO manager VALUES ('" + manager.getIdUtente() +"');";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Manager manager) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "UPDATE utente SET username = '" + manager.getUsername() + "', password = '" + manager.getPassword() + "', email = '" + manager.getEmail() + "' WHERE idUtente = '" + manager.getIdUtente() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Manager manager) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "DELETE FROM manager WHERE idManager = '" + manager.getIdUtente() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public Manager findByID(int idManager) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM utente INNER JOIN manager ON idUtente = idManager WHERE idManager = '" + idManager + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        Manager manager;
        try {
            rs.next();
            UtenteFactory utenteFactory = new UtenteFactory();
            manager = (Manager) utenteFactory.crea("MANAGER");
            manager.setIdUtente(rs.getInt("idUtente"));
            manager.setEmail(rs.getString("email"));
            manager.setUsername(rs.getString("username"));
            manager.setPassword(rs.getString("password"));
            return manager;
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
    public Manager findByUsername(String username) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM utente INNER JOIN manager ON idUtente = idManager WHERE username = '" + username + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        Manager manager;
        try {
            if(rs.next()) {
                UtenteFactory utenteFactory = new UtenteFactory();
                manager = (Manager) utenteFactory.crea("MANAGER");
                manager.setIdUtente(rs.getInt("idUtente"));
                manager.setEmail(rs.getString("email"));
                manager.setUsername(rs.getString("username"));
                manager.setPassword(rs.getString("password"));
                return manager;
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
    public ArrayList<Manager> findAll() {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM utente INNER JOIN manager ON idUtente = idManager;";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        ArrayList<Manager> managers = new ArrayList<>();
        try {
            while(rs.next()) {
                UtenteFactory utenteFactory = new UtenteFactory();
                manager = (Manager) utenteFactory.crea("MANAGER");
                manager.setIdUtente(rs.getInt("idUtente"));
                manager.setEmail(rs.getString("email"));
                manager.setUsername(rs.getString("username"));
                manager.setPassword(rs.getString("password"));
                managers.add(manager);
            }
            return managers;
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
