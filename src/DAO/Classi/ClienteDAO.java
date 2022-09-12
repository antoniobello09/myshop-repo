package DAO.Classi;

import DAO.Interfacce.IClienteDAO;
import DAO.UtenteDAO;
import DbInterface.Command.DbOperationExecutor;
import DbInterface.Command.IDbOperation;
import DbInterface.Command.ReadOperation;
import DbInterface.Command.WriteOperation;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Cliente;
import Model.PuntoVendita;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO implements IClienteDAO {

    private static ClienteDAO instance = new ClienteDAO();
    private Cliente cliente;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ClienteDAO() {
        cliente = null;
        conn = null;
        rs = null;
    }

    public static ClienteDAO getInstance() {
        return instance;
    }

    @Override
    public int add(Cliente cliente) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "INSERT INTO cliente VALUES ('" + UtenteDAO.getInstance().findByUsername(cliente.getUsername(), 1).getIdUtente() +"','" + cliente.getIdPuntoVendita() + "','" + cliente.getCanalePreferito() + "','" + (cliente.isAbilitato() ? 1 : 0) + "');";
        IDbOperation dbOperation = new WriteOperation(sql);
        conn.executeUpdate("INSERT INTO utente(username, password, name, surname, email, birthdate, telephone, address, city, job) VALUES('"+ cliente.getUsername() + "','" + cliente.getPassword() + "','" + cliente.getName() + "','" + cliente.getSurname() + "','" + cliente.getEmail() + "','" + cliente.getBirthdate() + "','" + cliente.getTelephone() + "','" + cliente.getAddress() + "','" + cliente.getCity() + "','" + cliente.getJob() + "');");
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Cliente cliente) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        conn.executeUpdate("UPDATE utente SET username = '" + cliente.getUsername() + "', password = '" + cliente.getPassword() + "', name = '" + cliente.getName() + "', surname = '" + cliente.getSurname() + "', birthdate = '" + cliente.getBirthdate() + "', telephone = '" + cliente.getTelephone() + "', address = '" + cliente.getAddress() + "', job = '" + cliente.getJob() +"' WHERE idUtente = '" + cliente.getIdUtente() + "';");
        String sql = "UPDATE cliente SET canale_preferito = '" + cliente.getCanalePreferito() + "', abilitato = '" + (cliente.isAbilitato() ? 1 : 0) + "' WHERE idCliente = '" + cliente.getIdUtente() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        int rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Cliente cliente) {
        int rowCount = 0;
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM lista l LEFT JOIN acquisto a ON l.idLista = a.idLista WHERE l.idCLiente = '" + cliente.getIdUtente() + "';");
        try {
            while (rs.next()) {
                conn.executeUpdate("DELETE la FROM lista_has_articolo la INNER JOIN lista l ON la.idLista = l.idLista WHERE l.idLista = '" + rs.getInt("idLista") + "';");
                conn.executeUpdate("DELETE FROM feedback WHERE idAcquisto = '" + rs.getInt("idAcquisto") + "';");
                conn.executeUpdate("DELETE FROM acquisto WHERE idAcquisto = '" + rs.getInt("idAcquisto")+ "';");
                conn.executeUpdate("DELETE FROM lista WHERE idLista = '" + rs.getInt("idLista") + "';");
            }
            DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
            String sql = "DELETE FROM cliente WHERE idCliente = '" + cliente.getIdUtente() + "';";
            IDbOperation dbOperation = new WriteOperation(sql);
            rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
            conn.executeUpdate("DELETE FROM utente WHERE idUtente = '" + cliente.getIdUtente() + "';");
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
        return rowCount;
    }

    @Override
    public Cliente findByID(int idCliente) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM utente u INNER JOIN cliente c ON u.idUtente = c.idCliente WHERE c.idCliente = '" + idCliente + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        Cliente cliente;
        try {
            rs.next();
            cliente = new Cliente();
            cliente.setIdUtente(rs.getInt("idUtente"));
            cliente.setEmail(rs.getString("email"));
            cliente.setUsername(rs.getString("username"));
            cliente.setPassword(rs.getString("password"));
            cliente.setName(rs.getString("name"));
            cliente.setSurname(rs.getString("surname"));
            cliente.setBirthdate(rs.getString("birthdate"));
            cliente.setTelephone(rs.getString("telephone"));
            cliente.setAddress(rs.getString("address"));
            cliente.setCity(rs.getString("city"));
            cliente.setJob(rs.getString("job"));
            cliente.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
            cliente.setCanalePreferito(rs.getString("canale_preferito"));
            cliente.setAbilitato(rs.getBoolean("abilitato"));
            return cliente;
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
    public Cliente findByUsername(String username) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM utente u INNER JOIN cliente c ON u.idUtente = c.idCliente WHERE u.username = '" + username + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        Cliente cliente;
        try {
            rs.next();
            cliente = new Cliente();
            cliente.setIdUtente(rs.getInt("idUtente"));
            cliente.setEmail(rs.getString("email"));
            cliente.setUsername(rs.getString("username"));
            cliente.setPassword(rs.getString("password"));
            cliente.setName(rs.getString("name"));
            cliente.setSurname(rs.getString("surname"));
            cliente.setBirthdate(rs.getString("birthdate"));
            cliente.setTelephone(rs.getString("telephone"));
            cliente.setAddress(rs.getString("address"));
            cliente.setCity(rs.getString("city"));
            cliente.setJob(rs.getString("job"));
            cliente.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
            cliente.setCanalePreferito(rs.getString("canale_preferito"));
            cliente.setAbilitato(rs.getBoolean("abilitato"));
            return cliente;
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
    public ArrayList<Cliente> findAll() {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM utente u INNER JOIN cliente c ON u.idUtente = c.idCliente;";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        ArrayList<Cliente> clienti = new ArrayList<>();
        try {
            while(rs.next()) {
                cliente = new Cliente();
                cliente.setIdUtente(rs.getInt("idUtente"));
                cliente.setEmail(rs.getString("email"));
                cliente.setUsername(rs.getString("username"));
                cliente.setPassword(rs.getString("password"));
                cliente.setName(rs.getString("name"));
                cliente.setSurname(rs.getString("surname"));
                cliente.setBirthdate(rs.getString("birthdate"));
                cliente.setTelephone(rs.getString("telephone"));
                cliente.setAddress(rs.getString("address"));
                cliente.setCity(rs.getString("city"));
                cliente.setJob(rs.getString("job"));
                cliente.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
                cliente.setCanalePreferito(rs.getString("canale_preferito"));
                cliente.setAbilitato(rs.getBoolean("abilitato"));
                clienti.add(cliente);
            }
            return clienti;
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
    public ArrayList<Cliente> findByPuntoVendita(int idPuntoVendita) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM utente u INNER JOIN cliente c ON u.idUtente = c.idCliente WHERE idPuntoVendita = '" + idPuntoVendita + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        ArrayList<Cliente> clienti = new ArrayList<>();
        try {
            while(rs.next()) {
                cliente = new Cliente();
                cliente.setIdUtente(rs.getInt("idUtente"));
                cliente.setEmail(rs.getString("email"));
                cliente.setUsername(rs.getString("username"));
                cliente.setPassword(rs.getString("password"));
                cliente.setName(rs.getString("name"));
                cliente.setSurname(rs.getString("surname"));
                cliente.setBirthdate(rs.getString("birthdate"));
                cliente.setTelephone(rs.getString("telephone"));
                cliente.setAddress(rs.getString("address"));
                cliente.setCity(rs.getString("city"));
                cliente.setJob(rs.getString("job"));
                cliente.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
                cliente.setCanalePreferito(rs.getString("canale_preferito"));
                cliente.setAbilitato(rs.getBoolean("abilitato"));
                clienti.add(cliente);
            }
            return clienti;
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

    public boolean isAbilitato(int idCliente){
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT abilitato FROM cliente c WHERE c.idCliente = '" + idCliente + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        Cliente cliente;
        try {
            if(rs.next()){
                if(rs.getBoolean("abilitato")){
                    return true;
                }else{
                    return false;
                }
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
        return false;
    }
}
