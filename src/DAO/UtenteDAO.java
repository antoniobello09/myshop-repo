package DAO;

import DbInterface.*;
import Model.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtenteDAO implements IUtenteDAO {

    private static UtenteDAO instance = new UtenteDAO();
    private Utente utente;
    private static IDbConnection conn;
    private static ResultSet rs;

    private UtenteDAO() {
        utente = null;
        conn = null;
        rs = null;
    }

    public static UtenteDAO getInstance() {
        return instance;
    }


    @Override
    public int add(Utente utente) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO utente(username, password, name, surname, email, birthdate, telephone, address, job) VALUES ('"+ utente.getUsername() + "','" + utente.getPassword() + "','" + utente.getName() + "','" + utente.getSurname() + "','" + utente.getEmail() + "','" + utente.getBirthdate() + "','" + utente.getTelephone() + "','" + utente.getAddress() + "','" + utente.getJob() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Utente utente) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM utente WHERE idUtente = '" + utente.getIdUtente() + "'");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Utente utente) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE utente SET username = '" + utente.getUsername() + "', password = '" + utente.getPassword() + "'name = '" + utente.getName() + "', surname = '" + utente.getSurname() + "', birthdate = " + utente.getBirthdate() + "', telephone = '" + utente.getTelephone() + "', address = '" + utente.getAddress() + "', job = '" + utente.getJob() +"' WHERE idUtente = '" + utente.getIdUtente() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public Utente findByID(int idUtente){
        return findByID(idUtente, 0);
    }

    public Utente findByID(int idUtente, int closeConn) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM utente WHERE idUtente = '" + idUtente + "';";
        IDbOperation dbOp = new ReadOperation(sql); // --> executeQuery();
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                utente = new Utente();
                utente.setIdUtente(rs.getInt("idUtente"));
                utente.setEmail(rs.getString("email"));
                utente.setUsername(rs.getString("username"));
                utente.setPassword(rs.getString("password"));
                utente.setName(rs.getString("name"));
                utente.setSurname(rs.getString("surname"));
                utente.setBirthdate(rs.getString("birthdate"));
                utente.setTelephone(rs.getString("telephone"));
                utente.setAddress(rs.getString("address"));
                utente.setJob(rs.getString("job"));
                return utente;
            }
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            if(closeConn == 0);
                conn.close();
        }
        return null;
    }

    @Override
    public Utente findByUsername(String username){
        return findByUsername(username, 0);
    }

    public Utente findByUsername(String username, int closeConn) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        String sql = "SELECT * FROM utente WHERE username = '" + username + "';";

        IDbOperation dbOp = new ReadOperation(sql); // --> executeQuery();

        rs = dbOperationExecutor.executeOperation(dbOp);
        try {
            rs.next();
            if (rs.getRow()==1) {
                utente = new Utente();
                utente.setIdUtente(rs.getInt("idUtente"));
                utente.setEmail(rs.getString("email"));
                utente.setUsername(rs.getString("username"));
                utente.setPassword(rs.getString("password"));
                utente.setName(rs.getString("name"));
                utente.setSurname(rs.getString("surname"));
                utente.setBirthdate(rs.getString("birthdate"));
                utente.setTelephone(rs.getString("telephone"));
                utente.setAddress(rs.getString("address"));
                utente.setJob(rs.getString("job"));
                return utente;
            }
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            if(closeConn == 0)
                conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<Utente> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM utente;");
        ArrayList<Utente> utenti = new ArrayList<>();
        try {
            while (rs.next()) {
                utente = new Utente();
                utente.setIdUtente(rs.getInt("idUtente"));
                utente.setEmail(rs.getString("email"));
                utente.setUsername(rs.getString("username"));
                utente.setPassword(rs.getString("password"));
                utente.setName(rs.getString("Nome"));
                utente.setSurname(rs.getString("Cognome"));
                utente.setBirthdate(rs.getString("birthdate"));
                utente.setTelephone(rs.getString("telephone"));
                utente.setAddress(rs.getString("address"));
                utente.setJob(rs.getString("job"));
                utenti.add(utente);
            }
            return utenti;
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
    public boolean userExists(String username) {
        conn = DbConnection.getInstance();
        boolean userExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS C FROM utente WHERE username = '" + username + "';";
        ReadOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1) userExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        conn.close();
        return userExists;
    }

    @Override
    public boolean credentialsOk(String username, String password) {
        conn = DbConnection.getInstance();
        boolean credentialsOk = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS C FROM utente WHERE username = '" + username + "' and password = '"+password+"';";
        ReadOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1) credentialsOk = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        conn.close();
        return credentialsOk;
    }


    @Override
    public boolean clientExists(Utente u) {
        conn = DbConnection.getInstance();
        boolean clientExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS C FROM utente as U INNER JOIN cliente as C ON U.idUtente = C.idCliente WHERE U.username = '" + u.getUsername() + "';";
        ReadOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1) clientExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        conn.close();

        return clientExists;
    }

    @Override
    public boolean managerExists(Utente u) {
        conn = DbConnection.getInstance();
        boolean managerExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS C FROM utente as U INNER JOIN manager as M ON U.idUtente = M.idManager WHERE U.username = '" + u.getUsername() + "';";
        ReadOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1) managerExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        conn.close();
        return managerExists;
    }

    @Override
    public boolean administratorExists(Utente u) {
        conn = DbConnection.getInstance();
        boolean administratorExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS C FROM utente as U INNER JOIN amministratore as A ON U.idUtente = A.idAmministratore WHERE U.username = '" + u.getUsername() + "';";
        ReadOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1) administratorExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        conn.close();
        return administratorExists;
    }

}