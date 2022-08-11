package DAO.Classi;

import DAO.Interfacce.IRispostaDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.FeedBack;
import Model.Risposta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RispostaDAO implements IRispostaDAO {


    private static RispostaDAO instance = new RispostaDAO();
    private Risposta risposta;
    private static IDbConnection conn;
    private static ResultSet rs;

    private RispostaDAO(){
        risposta = null;
        conn = null;
        rs = null;
    }

    public static RispostaDAO getInstance() {
        return instance;
    }

    @Override
    public int add(Risposta risposta) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO risposta(idCommento, idManager, risposta) VALUES ('"+ risposta.getIdCommento() + "','" + risposta.getIdManager() + "','" + risposta.getCommento() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Risposta risposta) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE risposta SET risposta = '"+ risposta.getCommento() + "' WHERE idRisposta = '" + risposta.getIdRisposta() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Risposta risposta) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM risposta WHERE idRisposta = '" + risposta.getIdRisposta() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public Risposta findByID(int idRisposta){
        return findByID(idRisposta, 0);
    }
    public Risposta findByID(int idRisposta, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM risposta R WHERE idRisposta = '" + idRisposta + "';");
        Risposta risposta;
        try {
            rs.next();
            risposta = new Risposta();
            risposta.setIdRisposta(rs.getInt("idRisposta"));
            risposta.setIdCommento(rs.getInt("idCommento"));
            risposta.setIdManager(rs.getInt("idManager"));
            risposta.setCommento(rs.getString("risposta"));
            return risposta;
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
    public Risposta findByInfo(int idCommento) {
        return findByInfo(idCommento,0);
    }

    @Override
    public Risposta findByInfo(int idCommento, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM risposta WHERE idCommento = '" + idCommento + "';");
        Risposta risposta;
        try {
            rs.next();
            risposta = new Risposta();
            risposta.setIdRisposta(rs.getInt("idRisposta"));
            risposta.setIdCommento(rs.getInt("idCommento"));
            risposta.setIdManager(rs.getInt("idManager"));
            risposta.setCommento(rs.getString("risposta"));
            return risposta;
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
    public ArrayList<Risposta> findAll() {

        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM commento;");
        ArrayList<Risposta> risposte = new ArrayList<>();

        try {
            while (rs.next()) {
                rs.next();
                risposta = new Risposta();
                risposta.setIdRisposta(rs.getInt("idRisposta"));
                risposta.setIdCommento(rs.getInt("idCommento"));
                risposta.setIdManager(rs.getInt("idManager"));
                risposta.setCommento(rs.getString("risposta"));
                risposte.add(risposta);
            }
            return risposte;
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
