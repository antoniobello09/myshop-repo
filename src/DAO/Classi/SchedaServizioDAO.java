package DAO.Classi;

import DAO.Interfacce.ISchedaServizioDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.FornitoreServizi;
import Model.SchedaServizio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SchedaServizioDAO implements ISchedaServizioDAO {

    private static SchedaServizioDAO instance = new SchedaServizioDAO();
    private SchedaServizio schedaServizio;
    private static IDbConnection conn;
    private static ResultSet rs;

    private SchedaServizioDAO(){
        schedaServizio = null;
        conn = null;
        rs = null;
    }

    public static SchedaServizioDAO getInstance() {
        return instance;
    }

    @Override
    public int add(SchedaServizio schedaServizio, int idPuntoVendita) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO scheda_servizio(idServizio, idPuntoVendita) VALUES ('"+ schedaServizio.getServizio().getId() + "','" + idPuntoVendita + "');");
        conn.close();
        return rowCount;
    }


    @Override
    public int update(SchedaServizio schedaServizio, FornitoreServizi fornitoreServizi) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE scheda_servizio SET idFornitoreServizio = '"+ fornitoreServizi.getIdFornitore() + "' WHERE idSchedaServizio = '" + schedaServizio.getIdSchedaServizio() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(SchedaServizio schedaServizio) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM scheda_servizio WHERE idSchedaServizio = '" + schedaServizio.getIdSchedaServizio() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public SchedaServizio findByID(int idSchedaServizio){
        return findByID(idSchedaServizio, 0);
    }
    public SchedaServizio findByID(int idSchedaServizio, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM scheda_servizio WHERE idSchedaServizio = '" + idSchedaServizio + "';");
        SchedaServizio schedaServizio;
        try {
            rs.next();
            schedaServizio = new SchedaServizio();
            schedaServizio.setIdSchedaServizio(rs.getInt("idSchedaServizio"));
            schedaServizio.setServizio(ServizioDAO.getInstance().findByID(rs.getInt("idServizio")));
            schedaServizio.setFornitoreServizi(FornitoreServiziDAO.getInstance().findByID(rs.getInt("idFornitoreServizio")));
            return schedaServizio;
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
    public SchedaServizio findByName(String nomeServizio) {
        return findByName(nomeServizio,0);
    }

    public SchedaServizio findByName(String nomeServizio, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM scheda_servizio ss INNER JOIN servizio s ON ss.idServizio = s.idServizio WHERE s.nome = '" + nomeServizio + "';");
        SchedaServizio schedaServizio;
        try {
            rs.next();
            schedaServizio = new SchedaServizio();
            schedaServizio.setIdSchedaServizio(rs.getInt("idSchedaProdotto"));
            schedaServizio.setServizio(ServizioDAO.getInstance().findByID(rs.getInt("idServizio")));
            schedaServizio.setFornitoreServizi(FornitoreServiziDAO.getInstance().findByID(rs.getInt("idFornitoreServizio")));
            return schedaServizio;
        } catch (SQLException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            if(closeConn==0)
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<SchedaServizio> findAll() {

        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM scheda_servizio;");
        ArrayList<SchedaServizio> schedeServizio = new ArrayList<>();
        try {
            while (rs.next()) {
                schedaServizio = new SchedaServizio();
                schedaServizio.setIdSchedaServizio(rs.getInt("idSchedaServizio"));
                schedaServizio.setServizio(ServizioDAO.getInstance().findByID(rs.getInt("idServizio")));
                schedaServizio.setFornitoreServizi(FornitoreServiziDAO.getInstance().findByID(rs.getInt("idFornitoreServizio")));
                schedeServizio.add(schedaServizio);
            }
            return schedeServizio;
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
    public ArrayList<SchedaServizio> findServicesShop(int idPuntoVendita) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM scheda_servizio WHERE idPuntoVendita = '" + idPuntoVendita + "';");
        ArrayList<SchedaServizio> schedeServizio = new ArrayList<>();
        try {
            while (rs.next()) {
                schedaServizio = new SchedaServizio();
                schedaServizio.setIdSchedaServizio(rs.getInt("idSchedaServizio"));
                schedaServizio.setServizio(ServizioDAO.getInstance().findByID(rs.getInt("idServizio")));
                schedaServizio.setFornitoreServizi(FornitoreServiziDAO.getInstance().findByID(rs.getInt("idFornitoreServizio")));
                schedeServizio.add(schedaServizio);
            }
            return schedeServizio;
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
