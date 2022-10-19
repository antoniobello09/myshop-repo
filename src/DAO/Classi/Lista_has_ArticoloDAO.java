package DAO.Classi;

import DAO.Interfacce.ILista_has_ArticoloDAO;
import DbInterface.Command.DbOperationExecutor;
import DbInterface.Command.IDbOperation;
import DbInterface.Command.ReadOperation;
import DbInterface.Command.WriteOperation;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Lista_has_ArticoloDAO implements ILista_has_ArticoloDAO {
    private static Lista_has_ArticoloDAO instance = new Lista_has_ArticoloDAO();
    private Lista_has_Articolo lista_has_articolo;
    private static IDbConnection conn;
    private static ResultSet rs;

    private Lista_has_ArticoloDAO(){
        lista_has_articolo = null;
        conn = null;
        rs = null;
    }

    public static Lista_has_ArticoloDAO getInstance() {
        return instance;
    }

    @Override
    public int add(Lista_has_Articolo lista_has_articolo) {
        conn = DbConnection.getInstance();
        int rowCount;
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "INSERT INTO lista_has_articolo(idArticolo, idLista, quantita) VALUES ('" + lista_has_articolo.getIdArticolo() + "','" + lista_has_articolo.getIdLista() + "','" + lista_has_articolo.getQuantita() + "');";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Lista_has_Articolo lista_has_articolo) {
        conn = DbConnection.getInstance();
        int rowCount;
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "UPDATE lista_has_articolo SET quantita = '" + lista_has_articolo.getQuantita() + "' WHERE idLista = '" + lista_has_articolo.getIdLista() + "' AND idArticolo = '" + lista_has_articolo.getIdArticolo() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Lista_has_Articolo lista_has_articolo) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "DELETE FROM lista_has_articolo WHERE idLista = '" + lista_has_articolo.getIdLista() + "' AND idArticolo = '" + lista_has_articolo.getIdArticolo() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public Lista_has_Articolo findByID(int idLista, int idArticolo) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM lista_has_articolo WHERE idLista = '" + idLista + "' AND idArticolo = '" + idArticolo + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        Lista_has_Articolo lista_has_articolo = new Lista_has_Articolo();
        try {
            rs.next();
            lista_has_articolo.setIdArticolo(rs.getInt("idArticolo"));
            lista_has_articolo.setIdLista(rs.getInt("idLista"));
            lista_has_articolo.setQuantita(rs.getInt("quantita"));
            return lista_has_articolo;
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
    public ArrayList<Lista_has_Articolo> findAll() {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM lista_has_articolo;";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        ArrayList<Lista_has_Articolo> liste_has_articolo = new ArrayList<>();
        try {
            while (rs.next()) {
                lista_has_articolo = new Lista_has_Articolo();
                lista_has_articolo.setIdArticolo(rs.getInt("idArticolo"));
                lista_has_articolo.setIdLista(rs.getInt("idLista"));
                lista_has_articolo.setQuantita(rs.getInt("quantita"));
                liste_has_articolo.add(lista_has_articolo);
            }
            return liste_has_articolo;
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
    public ArrayList<Lista_has_Articolo> findAllListArticles(int idLista) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM lista_has_articolo WHERE idLista = '" + idLista + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        ArrayList<Lista_has_Articolo> liste_has_articolo = new ArrayList<>();
        try {
            while (rs.next()) {
                lista_has_articolo = new Lista_has_Articolo();
                lista_has_articolo.setIdArticolo(rs.getInt("idArticolo"));
                lista_has_articolo.setIdLista(rs.getInt("idLista"));
                lista_has_articolo.setQuantita(rs.getInt("quantita"));
                liste_has_articolo.add(lista_has_articolo);
            }
            return liste_has_articolo;
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