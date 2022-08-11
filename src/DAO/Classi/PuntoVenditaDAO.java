package DAO.Classi;

import DAO.Interfacce.IPuntoVenditaDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Magazzino;
import Model.Manager;
import Model.PuntoVendita;
import Model.SchedaProdotto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PuntoVenditaDAO implements IPuntoVenditaDAO {

    private static PuntoVenditaDAO instance = new PuntoVenditaDAO();
    private PuntoVendita puntoVendita;
    private static IDbConnection conn;
    private static ResultSet rs;

    private PuntoVenditaDAO(){
        puntoVendita = null;
        conn = null;
        rs = null;
    }

    public static PuntoVenditaDAO getInstance() {
        return instance;
    }

    @Override
    public int add(PuntoVendita puntoVendita) {
        MagazzinoDAO.getInstance().add();
        conn = DbConnection.getInstance();
        int rowCount = 0;
        rs = conn.executeQuery("SELECT idMagazzino FROM magazzino WHERE idMagazzino = (SELECT max(idMagazzino) FROM magazzino)");
        try {
            rs.next();
            rowCount = conn.executeUpdate("INSERT INTO puntovendita(idManager, citta, indirizzo, idMagazzino) VALUES ('"+ ManagerDAO.getInstance().findByUsername(puntoVendita.getManager().getUsername(),1).getIdUtente() + "','" + puntoVendita.getCitta() + "','" + puntoVendita.getIndirizzo() +
                    "','" + rs.getInt("idMagazzino") + "');");
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
    public int update(PuntoVendita puntoVendita) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE puntovendita SET idManager = '" + puntoVendita.getManager().getIdUtente() + "', citta = '" + puntoVendita.getCitta() + "', indirizzo = '" + puntoVendita.getIndirizzo() + "' WHERE idPuntoVendita = '" + puntoVendita.getIdPuntoVendita() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(PuntoVendita puntoVendita) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM puntovendita WHERE idPuntoVendita = '" + puntoVendita.getIdPuntoVendita());
        conn.close();
        return rowCount;
    }

    @Override
    public PuntoVendita findByID(int idPuntoVendita) {
        return findByID(idPuntoVendita, 0);
    }

    public PuntoVendita findByID(int idPuntoVendita, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM puntovendita WHERE idPuntoVendita = '" + idPuntoVendita + "';");
        PuntoVendita puntoVendita;
        try {
            rs.next();
            puntoVendita = new PuntoVendita();
            puntoVendita.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
            puntoVendita.setManager(ManagerDAO.getInstance().findByID(rs.getInt("idManager")));
            puntoVendita.setCitta(rs.getString("citta"));
            puntoVendita.setIndirizzo(rs.getString("indirizzo"));
            puntoVendita.setMagazzino(MagazzinoDAO.getInstance().findByID(rs.getInt("idMagazzino")));
            puntoVendita.setServizi(SchedaServizioDAO.getInstance().findServicesShop(rs.getInt("idPuntoVendita")));
            return puntoVendita;
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
    public PuntoVendita findByName(String indirizzo, String citta) {
        return findByName(indirizzo, citta, 0);
    }

    public PuntoVendita findByName(String indirizzo, String citta, int closeConn){
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM puntovendita WHERE indirizzo ='" + indirizzo + "' AND citta = '" + citta + "';");
        PuntoVendita puntoVendita;
        try {
            rs.next();
            puntoVendita = new PuntoVendita();
            puntoVendita.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
            puntoVendita.setManager(ManagerDAO.getInstance().findByID(rs.getInt("idManager")));
            puntoVendita.setCitta(rs.getString("citta"));
            puntoVendita.setIndirizzo(rs.getString("indirizzo"));
            puntoVendita.setMagazzino(MagazzinoDAO.getInstance().findByID(rs.getInt("idMagazzino")));
            puntoVendita.setServizi(SchedaServizioDAO.getInstance().findServicesShop(rs.getInt("idPuntoVendita")));
            return puntoVendita;
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
    public PuntoVendita findByManager(int idManager) {
        return findByManager(idManager, 0);
    }

    public PuntoVendita findByManager(int idManager, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM puntovendita WHERE idManager = '" + idManager + "';");
        PuntoVendita puntoVendita;
        try {
            rs.next();
            puntoVendita = new PuntoVendita();
            puntoVendita.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
            puntoVendita.setManager(ManagerDAO.getInstance().findByID(rs.getInt("idManager")));
            puntoVendita.setCitta(rs.getString("citta"));
            puntoVendita.setIndirizzo(rs.getString("indirizzo"));
            puntoVendita.setMagazzino(MagazzinoDAO.getInstance().findByID(rs.getInt("idMagazzino")));
            puntoVendita.setServizi(SchedaServizioDAO.getInstance().findServicesShop(rs.getInt("idPuntoVendita")));
            return puntoVendita;
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
    public ArrayList<PuntoVendita> findAll() {
        return findAll(0);
    }

    public ArrayList<PuntoVendita> findAll(int closeConn){
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM puntovendita;");
        ArrayList<PuntoVendita> puntiVendita = new ArrayList<>();
        try {
            while(rs.next()) {
                PuntoVendita puntoVendita = new PuntoVendita();
                puntoVendita.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
                puntoVendita.setManager(ManagerDAO.getInstance().findByID(rs.getInt("idManager"),1));
                puntoVendita.setCitta(rs.getString("citta"));
                puntoVendita.setIndirizzo(rs.getString("indirizzo"));
                puntoVendita.setMagazzino(MagazzinoDAO.getInstance().findByID(rs.getInt("idMagazzino"),1));
                puntoVendita.setServizi(SchedaServizioDAO.getInstance().findServicesShop(rs.getInt("idPuntoVendita")));
                puntiVendita.add(puntoVendita);
            }
            return puntiVendita;
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
}
