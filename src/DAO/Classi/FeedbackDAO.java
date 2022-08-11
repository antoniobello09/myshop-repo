package DAO.Classi;

import DAO.Interfacce.IFeedbackDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.FeedBack;
import Model.Produttore;

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
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO commento(commento, indiceGradimento, idArticolo, idAcquisto) VALUES ('"+ feedBack.getCommento() + "','" + feedBack.getPunteggio() + "','" + feedBack.getIdArticolo() + "','"+ feedBack.getIdAcquisto() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(FeedBack feedBack) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE commento SET commento = '"+ feedBack.getCommento() + "', indiceGradimento = '" + feedBack.getPunteggio() + "' WHERE idCommento = '" + feedBack.getIdFeedBack() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(FeedBack feedBack) {
        conn = DbConnection.getInstance();
        conn.executeUpdate("DELETE FROM risposta WHERE idCommento = '" + feedBack.getIdFeedBack() + "';");
        int rowCount = conn.executeUpdate("DELETE FROM commento WHERE idCommento = '" + feedBack.getIdFeedBack() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public FeedBack findByID(int idFeedback){
        return findByID(idFeedback, 0);
    }
    public FeedBack findByID(int idFeedback, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM commento C INNER JOIN acquisto A ON C.idAcquisto = A.idAcquisto INNER JOIN lista L ON L.idLista = A.idLista WHERE idCommento = '" + idFeedback + "';");
        FeedBack feedBack;
        try {
            rs.next();
            feedBack = new FeedBack();
            feedBack.setIdFeedBack(rs.getInt("idCommento"));
            feedBack.setIdCliente(rs.getInt("idCliente"));
            feedBack.setIdAcquisto(rs.getInt("idAcquisto"));
            feedBack.setIdArticolo(rs.getInt("idArticolo"));
            feedBack.setCommento(rs.getString("commento"));
            feedBack.setPunteggio(rs.getInt("indiceGradimento"));
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
    public FeedBack findByInfo(int idAcquisto, int idArticolo) {
        return findByInfo(idAcquisto, idArticolo,0);
    }

    @Override
    public FeedBack findByInfo(int idAcquisto, int idArticolo, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM commento C INNER JOIN acquisto A ON C.idAcquisto = A.idAcquisto INNER JOIN lista L ON L.idLista = A.idLista WHERE idAcquisto = '" + idAcquisto + "' AND idArticolo = '" + idArticolo + "';");
        FeedBack feedBack;
        try {
            rs.next();
            feedBack = new FeedBack();
            feedBack.setIdFeedBack(rs.getInt("idCommento"));
            feedBack.setIdCliente(rs.getInt("idCliente"));
            feedBack.setIdAcquisto(rs.getInt("idAcquisto"));
            feedBack.setIdArticolo(rs.getInt("idArticolo"));
            feedBack.setCommento(rs.getString("commento"));
            feedBack.setPunteggio(rs.getInt("indiceGradimento"));
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
        rs = conn.executeQuery("SELECT * FROM commento;");
        ArrayList<FeedBack> feedBacks = new ArrayList<>();

        try {
            while (rs.next()) {
                rs.next();
                feedBack = new FeedBack();
                feedBack.setIdFeedBack(rs.getInt("idCommento"));
                feedBack.setIdCliente(rs.getInt("idCliente"));
                feedBack.setIdAcquisto(rs.getInt("idAcquisto"));
                feedBack.setIdArticolo(rs.getInt("idArticolo"));
                feedBack.setCommento(rs.getString("commento"));
                feedBack.setPunteggio(rs.getInt("indiceGradimento"));
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

}
