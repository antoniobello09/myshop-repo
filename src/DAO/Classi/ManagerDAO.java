package DAO.Classi;

import DAO.Interfacce.IManagerDAO;
import DAO.UtenteDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
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
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO utente(username, password, email) VALUES ('"+ manager.getUsername() + "','" + manager.getPassword() + "','" + manager.getEmail() + "');");
        conn.executeUpdate("INSERT INTO manager VALUES ('" + ManagerDAO.getInstance().findByUsername(manager.getUsername(),1).getIdUtente() +"');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Manager manager) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE utente SET username = '" + manager.getUsername() + "', password = '" + manager.getPassword() + "', email = '" + manager.getEmail() + "' WHERE idUtente = '" + manager.getIdUtente() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Manager manager) {
        return 0;
    }

    @Override
    public Manager findByID(int idManager){
        return findByID(idManager, 0);
    }
    public Manager findByID(int idManager, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM utente INNER JOIN manager ON idUtente = idManager WHERE idManager = '" + idManager + "';");
        Manager manager;
        try {
            rs.next();
            manager = new Manager();
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
            if(closeConn == 0)
            conn.close();
        }
        return null;
    }

    @Override
    public Manager findByUsername(String username){
        return findByUsername(username, 0);
    }

    public Manager findByUsername(String username, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM utente INNER JOIN manager ON idUtente = idManager WHERE username = '" + username + "';");
        Manager manager;
        try {
            if(rs.next()) {
                manager = new Manager();
                manager.setIdUtente(rs.getInt("idUtente"));
                manager.setEmail(rs.getString("email"));
                manager.setUsername(rs.getString("username"));
                manager.setPassword(rs.getString("password"));
            }else{
                return null;
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
            if(closeConn == 0)
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<Manager> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM utente INNER JOIN manager ON idUtente = idManager;");
        ArrayList<Manager> managers = new ArrayList<>();
        try {
            while(rs.next()) {
                manager = new Manager();
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
