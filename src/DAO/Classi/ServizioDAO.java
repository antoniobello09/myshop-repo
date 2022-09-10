package DAO.Classi;

import DAO.Interfacce.IServizioDAO;
import DbInterface.Command.DbOperationExecutor;
import DbInterface.Command.IDbOperation;
import DbInterface.Command.ReadOperation;
import DbInterface.Command.WriteOperation;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Articolo;
import Model.CategoriaServizio;
import Model.Prodotto;
import Model.Servizio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServizioDAO implements IServizioDAO {

    private static ServizioDAO instance = new ServizioDAO();
    private Servizio servizio;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ServizioDAO(){
        servizio = null;
        conn = null;
        rs = null;
    }

    public static ServizioDAO getInstance() {
        return instance;
    }


    @Override
    public int add(Servizio servizio) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "INSERT INTO servizio VALUES ('"+ servizio.getIdArticolo() + "','" + servizio.getIdFornitoreServizio() + "');";
        IDbOperation dbOperation = new WriteOperation(sql);
        int rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Servizio servizio) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "UPDATE servizio SET idFornitoreServizio = '"+ servizio.getIdFornitoreServizio() + "' WHERE idServizio = '" + servizio.getIdArticolo() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        int rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Servizio servizio) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "DELETE FROM servizio WHERE idServizio = '" + servizio.getIdArticolo() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        int rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public Servizio findByID(int idServizio){
        return findByID(idServizio, 0);
    }

    public Servizio findByID(int idServizio, int closeConn) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM servizio INNER JOIN articolo ON servizio.idServizio = articolo.idArticolo WHERE servizio.idServizio = '" + idServizio + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        Servizio servizio;
        try {
            rs.next();
            servizio = new Servizio();
            servizio.setIdArticolo(rs.getInt("idServizio"));
            servizio.setNome(rs.getString("nome"));
            servizio.setIdCategoria(rs.getInt("idCategoria"));
            servizio.setPrezzo(rs.getFloat("prezzo"));
            servizio.setDescrizione(rs.getString("descrizione"));
            servizio.setIdFornitoreServizio(rs.getInt("idFornitoreServizio"));
            return servizio;
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
    public Servizio findByName(String nomeServizio) {
        return findByName(nomeServizio, 0);
    }

    public Servizio findByName(String nomeServizio, int closeConn){
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM servizio INNER JOIN articolo ON servizio.idServizio = articolo.idArticolo WHERE nome = '" + nomeServizio + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        Servizio servizio;
        try {
            rs.next();
            servizio = new Servizio();
            servizio.setIdArticolo(rs.getInt("idServizio"));
            servizio.setNome(rs.getString("nome"));
            servizio.setIdCategoria(rs.getInt("idCategoria"));
            servizio.setPrezzo(rs.getFloat("prezzo"));
            servizio.setDescrizione(rs.getString("descrizione"));
            servizio.setIdFornitoreServizio(rs.getInt("idFornitoreServizio"));
            return servizio;
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
    public ArrayList<Servizio> findAll() {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM servizio INNER JOIN articolo ON servizio.idServizio = articolo.idArticolo;";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        ArrayList<Servizio> servizi = new ArrayList<>();
        try {
            while(rs.next()) {
                servizio = new Servizio();
                servizio.setIdArticolo(rs.getInt("idServizio"));
                servizio.setNome(rs.getString("nome"));
                servizio.setIdCategoria(rs.getInt("idCategoria"));
                servizio.setPrezzo(rs.getFloat("prezzo"));
                servizio.setDescrizione(rs.getString("descrizione"));
                servizio.setIdFornitoreServizio(rs.getInt("idFornitoreServizio"));
                servizi.add(servizio);
            }
            return servizi;
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
