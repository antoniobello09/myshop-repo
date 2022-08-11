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
        int rowCount = conn.executeUpdate("INSERT INTO utente(username, password, name, surname, email, birthdate, telephone, address, job) VALUES ('"+ manager.getUsername() + "','" + manager.getPassword() + "','" + manager.getName() + "','" + manager.getSurname() + "','" + manager.getEmail() + "','" + manager.getBirthdate() + "','" + manager.getTelephone() + "','" + manager.getAddress() + "','" + manager.getJob() + "');");
        conn.executeUpdate("INSERT INTO manager VALUES ('" + UtenteDAO.getInstance().findByUsername(manager.getUsername()).getIdUtente() +"');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Manager manager) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE utente SET username = '" + manager.getUsername() + "', password = '" + manager.getPassword() + "', email = '" + manager.getEmail() + "', name = '" + manager.getName() + "', surname = '" + manager.getSurname() + "', birthdate = '" + manager.getBirthdate() + "', telephone = '" + manager.getTelephone() + "', address = '" + manager.getAddress() + "', job = '" + manager.getJob() +"' WHERE idUtente = '" + manager.getIdUtente() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Manager manager) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM manager WHERE idManager = '" + manager.getIdUtente() + "';");
        conn.executeUpdate("DELETE FROM utente WHERE idUtente = '" + manager.getIdUtente() + "';");
        conn.close();
        return rowCount;
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
            manager.setName(rs.getString("name"));
            manager.setSurname(rs.getString("surname"));
            manager.setBirthdate(rs.getString("birthdate"));
            manager.setTelephone(rs.getString("telephone"));
            manager.setAddress(rs.getString("address"));
            manager.setJob(rs.getString("job"));
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
                manager.setName(rs.getString("name"));
                manager.setSurname(rs.getString("surname"));
                manager.setBirthdate(rs.getString("birthdate"));
                manager.setTelephone(rs.getString("telephone"));
                manager.setAddress(rs.getString("address"));
                manager.setJob(rs.getString("job"));
                return manager;
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
                manager.setName(rs.getString("name"));
                manager.setSurname(rs.getString("surname"));
                manager.setBirthdate(rs.getString("birthdate"));
                manager.setTelephone(rs.getString("telephone"));
                manager.setAddress(rs.getString("address"));
                manager.setJob(rs.getString("job"));
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

    @Override
    public ArrayList<Manager> findFree(int idPuntoVendita) {
        conn = DbConnection.getInstance();
        if(idPuntoVendita == 0){
            rs = conn.executeQuery("SELECT * FROM utente u INNER JOIN manager m ON u.idUtente = m.idManager " +
                    "LEFT JOIN puntovendita p ON m.idManager = p.idManager WHERE p.idManager IS NULL;");
        }else {
            rs = conn.executeQuery("SELECT * FROM utente u INNER JOIN manager m ON u.idUtente = m.idManager " +
                    "LEFT JOIN puntovendita p ON m.idManager = p.idManager WHERE p.idManager IS NULL OR p.idManager = '" + idPuntoVendita + "';");
        }
        ArrayList<Manager> managers = new ArrayList<>();
        try {
            while(rs.next()) {
                manager = new Manager();
                manager.setIdUtente(rs.getInt("idUtente"));
                manager.setEmail(rs.getString("email"));
                manager.setUsername(rs.getString("username"));
                manager.setPassword(rs.getString("password"));
                manager.setName(rs.getString("name"));
                manager.setSurname(rs.getString("surname"));
                manager.setBirthdate(rs.getString("birthdate"));
                manager.setTelephone(rs.getString("telephone"));
                manager.setAddress(rs.getString("address"));
                manager.setJob(rs.getString("job"));
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
