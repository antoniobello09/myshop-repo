package DAO.Classi;

import DAO.Interfacce.IPosizioneDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Posizione;
import Model.Produttore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PosizioneDAO implements IPosizioneDAO {

    private static PosizioneDAO instance = new PosizioneDAO();
    private Posizione posizione;
    private static IDbConnection conn;
    private static ResultSet rs;

    private PosizioneDAO(){
        posizione = null;
        conn = null;
        rs = null;
    }

    public static PosizioneDAO getInstance() {
        return instance;
    }

    @Override
    public int add(Posizione posizione) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO posizione(idMagazzino, scaffale, corsa, piano) VALUES ('"+ posizione.getIdMagazzino() + "','" + posizione.getScaffale() + "','" + posizione.getCorsia() + "','"+ posizione.getPiano() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Posizione posizione) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE posizione SET idMagazzino = '"+ posizione.getIdMagazzino() + "', scaffale = '" + posizione.getScaffale() + "', corsia = '" + posizione.getCorsia() + "', piano = '" + posizione.getPiano() + "' WHERE idPosizione = '" + posizione.getIdPosizione() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Posizione posizione) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM posizione WHERE idPosizione = '" + posizione.getIdPosizione() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public Posizione findByID(int idPosizione){
        return findByID(idPosizione, 0);
    }
    public Posizione findByID(int idPosizione, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM posizione WHERE idPosizione = '" + idPosizione + "';");
        Posizione posizione;
        try {
            rs.next();
            posizione = new Posizione();
            posizione.setIdPosizione(rs.getInt("idPosizione"));
            posizione.setIdMagazzino(rs.getInt("idMagazzino"));
            posizione.setPiano(rs.getInt("piano"));
            posizione.setCorsia(rs.getInt("corsia"));
            posizione.setScaffale(rs.getInt("scaffale"));
            return posizione;
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
    public Posizione findByPosition(int idMagazzino, int piano, int corsia, int scaffale){
        return findByPosition(idMagazzino, piano, corsia, scaffale, 0);
    }
    public Posizione findByPosition(int idMagazzino, int piano, int corsia, int scaffale, int closeConn) {
        conn = DbConnection.getInstance();
        if(piano == 0 && corsia == 0 && scaffale == 0){
            return null;
        }
        rs = conn.executeQuery("SELECT * FROM posizione WHERE idMagazzino = '" + idMagazzino + "' AND piano = '" + piano + "' AND corsia = '" + corsia + "' AND scaffale = '" + scaffale + "';");
        Posizione posizione;
        try {
            rs.next();
            posizione = new Posizione();
            posizione.setIdPosizione(rs.getInt("idPosizione"));
            posizione.setIdMagazzino(rs.getInt("idMagazzino"));
            posizione.setPiano(rs.getInt("piano"));
            posizione.setCorsia(rs.getInt("corsia"));
            posizione.setScaffale(rs.getInt("scaffale"));
            return posizione;
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
    public ArrayList<Posizione> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM posizione;");
        ArrayList<Posizione> posizioni = new ArrayList<>();
        try {
            while(rs.next()) {
                posizione = new Posizione();
                posizione.setIdPosizione(rs.getInt("idPosizione"));
                posizione.setIdMagazzino(rs.getInt("idMagazzino"));
                posizione.setPiano(rs.getInt("piano"));
                posizione.setCorsia(rs.getInt("corsia"));
                posizione.setScaffale(rs.getInt("scaffale"));
                posizioni.add(posizione);
            }
            return posizioni;
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
