package DAO.Classi;

import DAO.Interfacce.IFeedbackDAO;
import DbInterface.Command.DbOperationExecutor;
import DbInterface.Command.IDbOperation;
import DbInterface.Command.ReadOperation;
import DbInterface.Command.WriteOperation;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.FeedBack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeedbackDAO implements IFeedbackDAO {

    private static FeedbackDAO instance = new FeedbackDAO();
    private FeedBack feedBack;
    private static IDbConnection conn;
    private static ResultSet rs;

    private FeedbackDAO(){
        feedBack = null;
        conn = null;
        rs = null;
    }

    public static FeedbackDAO getInstance() {
        return instance;
    }

    @Override
    public int add(FeedBack feedBack) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "INSERT INTO feedback(commento, indiceGradimento, idArticolo, idAcquisto, risposta, data) VALUES ('"+ feedBack.getCommento() + "','" + feedBack.getIndiceGradimento() + "','" + feedBack.getIdArticolo() + "','"+ feedBack.getIdAcquisto() + "','" + feedBack.getRisposta() + "','" + feedBack.getData() + "');";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int update(FeedBack feedBack) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "UPDATE feedback SET commento = '"+ feedBack.getCommento() + "', indiceGradimento = '" + feedBack.getIndiceGradimento() + "', risposta = '" + feedBack.getRisposta() + "', data = '" + feedBack.getData() + "' WHERE idFeedback = '" + feedBack.getIdFeedBack() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(FeedBack feedBack) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "DELETE FROM feedback WHERE idFeedback = '" + feedBack.getIdFeedBack() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public FeedBack findByID(int idFeedback){
        return findByID(idFeedback, 0);
    }
    public FeedBack findByID(int idFeedback, int closeConn) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM feedback WHERE idFeedback = '" + idFeedback + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        FeedBack feedBack;
        try {
            rs.next();
            feedBack = new FeedBack();
            feedBack.setIdFeedBack(rs.getInt("idFeedback"));
            feedBack.setIdAcquisto(rs.getInt("idAcquisto"));
            feedBack.setIndiceGradimento(rs.getInt("indiceGradimento"));
            feedBack.setIdArticolo(rs.getInt("idArticolo"));
            feedBack.setCommento(rs.getString("commento"));
            feedBack.setRisposta(rs.getString("risposta"));
            feedBack.setData(rs.getDate("data"));
            return feedBack;
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
    public ArrayList<FeedBack> findAll() {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM feedback;";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        ArrayList<FeedBack> feedBacks = new ArrayList<>();

        try {
            while (rs.next()) {
                feedBack = new FeedBack();
                feedBack.setIdFeedBack(rs.getInt("idFeedback"));
                feedBack.setIdAcquisto(rs.getInt("idAcquisto"));
                feedBack.setIndiceGradimento(rs.getInt("indiceGradimento"));
                feedBack.setIdArticolo(rs.getInt("idArticolo"));
                feedBack.setCommento(rs.getString("commento"));
                feedBack.setRisposta(rs.getString("risposta"));
                feedBack.setData(rs.getDate("data"));
                feedBacks.add(feedBack);
            }
            return feedBacks;
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
    public ArrayList<FeedBack> findByPuntoVendita(int idPuntoVendita) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM feedback f INNER JOIN acquisto a ON f.idAcquisto = a.idAcquisto WHERE a.idPuntoVendita = '" + idPuntoVendita + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        ArrayList<FeedBack> feedBacks = new ArrayList<>();
        try {
            while (rs.next()) {
                feedBack = new FeedBack();
                feedBack.setIdFeedBack(rs.getInt("idFeedback"));
                feedBack.setIdAcquisto(rs.getInt("idAcquisto"));
                feedBack.setIndiceGradimento(rs.getInt("indiceGradimento"));
                feedBack.setIdArticolo(rs.getInt("idArticolo"));
                feedBack.setCommento(rs.getString("commento"));
                feedBack.setRisposta(rs.getString("risposta"));
                feedBack.setData(rs.getDate("data"));
                feedBacks.add(feedBack);
            }
            return feedBacks;
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

    public FeedBack findByIDAcquisto_Articolo(int idAcquisto, int idArticolo){
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM feedback WHERE idAcquisto = '" + idAcquisto + "' AND idArticolo = '" + idArticolo + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        FeedBack feedBack;
        try {
            rs.next();
            feedBack = new FeedBack();
            feedBack.setIdFeedBack(rs.getInt("idFeedback"));
            feedBack.setIdAcquisto(rs.getInt("idAcquisto"));
            feedBack.setIndiceGradimento(rs.getInt("indiceGradimento"));
            feedBack.setIdArticolo(rs.getInt("idArticolo"));
            feedBack.setCommento(rs.getString("commento"));
            feedBack.setRisposta(rs.getString("risposta"));
            feedBack.setData(rs.getDate("data"));
            return feedBack;
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
