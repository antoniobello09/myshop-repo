package DAO.Classi;

import DAO.Interfacce.IListaDAO;
import DbInterface.Command.DbOperationExecutor;
import DbInterface.Command.IDbOperation;
import DbInterface.Command.ReadOperation;
import DbInterface.Command.WriteOperation;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;

import Model.Lista;
import Model.Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListaDAO implements IListaDAO {

    private static ListaDAO instance = new ListaDAO();
    private Lista lista;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ListaDAO(){
        lista = null;
        conn = null;
        rs = null;
    }

    public static ListaDAO getInstance() {
        return instance;
    }

    @Override
    public int add(Lista lista) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "INSERT INTO lista(idCliente, nome) VALUES ('" + lista.getIdCliente() + "','" + lista.getNome() + "');";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Lista lista) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "UPDATE lista SET nome = '"+ lista.getNome() + "' WHERE idLista = '" + lista.getIdLista() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Lista lista) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        conn.executeUpdate("DELETE FROM lista_has_articolo WHERE idLista = '" + lista.getIdLista() + "';");
        String sql = "DELETE FROM lista WHERE idLista = '" + lista.getIdLista() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public Lista findByID(int idLista){
        return findByID(idLista, 0);
    }
    public Lista findByID(int idLista, int closeConn) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM lista WHERE idLista = '" + idLista + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        Lista lista;
        try {
            rs.next();
            lista = new Lista();
            lista.setIdLista(rs.getInt("idLista"));
            lista.setIdCliente(rs.getInt("idCliente"));
            lista.setNome(rs.getString("nome"));
            return lista;
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
    public Lista findByName(String nome){
        return findByName(nome, 0);
    }

    public Lista findByName(String nome, int closeConn) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM lista WHERE nome = '" + nome + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        Manager manager;
        try {
            if(rs.next()) {
                lista = new Lista();
                lista.setIdLista(rs.getInt("idLista"));
                lista.setIdCliente(rs.getInt("idCliente"));
                lista.setNome(rs.getString("nome"));
                return lista;
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
    public ArrayList<Lista> findAll() {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM lista;";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        ArrayList<Lista> liste = new ArrayList<>();
        try {
            while(rs.next()) {
                lista = new Lista();
                lista.setIdLista(rs.getInt("idLista"));
                lista.setIdCliente(rs.getInt("idCliente"));
                lista.setNome(rs.getString("nome"));
                liste.add(lista);
            }
            return liste;
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
    public ArrayList<Lista> findAll(int idCliente) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM lista WHERE idCliente = '" + idCliente + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        ArrayList<Lista> liste = new ArrayList<>();
        try {
            while(rs.next()) {
                lista = new Lista();
                lista.setIdLista(rs.getInt("idLista"));
                lista.setIdCliente(rs.getInt("idCliente"));
                lista.setNome(rs.getString("nome"));
                liste.add(lista);
            }
            return liste;
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
