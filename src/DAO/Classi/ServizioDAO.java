package DAO.Classi;

import DAO.Interfacce.IServizioDAO;
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
        ArticoloDAO.getInstance().add(servizio);
        int idServizio =  ArticoloDAO.getInstance().findByName(servizio.getNome()).getIdArticolo();
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO servizio VALUES ('"+ idServizio + "','" + servizio.getIdFornitoreServizio() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Servizio servizio) {
        ArticoloDAO.getInstance().update(servizio);
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE servizio SET idFornitoreServizio = '"+ servizio.getIdFornitoreServizio() + "' WHERE idServizio = '" + servizio.getIdArticolo() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Servizio servizio) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM servizio WHERE idServizio = '" + servizio.getIdArticolo() + "';");
        conn.close();
        ArticoloDAO.getInstance().delete(servizio);
        return rowCount;
    }

    @Override
    public Servizio findByID(int idServizio){
        return findByID(idServizio, 0);
    }

    public Servizio findByID(int idServizio, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM servizio INNER JOIN articolo ON servizio.idServizio = articolo.idArticolo WHERE servizio.idServizio = '" + idServizio + "';");
        Servizio servizio;
        try {
            rs.next();
            servizio = new Servizio();
            servizio.setIdArticolo(rs.getInt("idServizio"));
            servizio.setNome(rs.getString("nome"));
            servizio.setIdCategoria(rs.getInt("idCategoria"));
            servizio.setPrezzo(rs.getFloat("prezzo"));
            servizio.setDescrizione(rs.getString("descrizione"));
            servizio.setIdFornitoreServizio(rs.getInt("idFornitore"));
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
        rs = conn.executeQuery("SELECT * FROM servizio INNER JOIN articolo ON servizio.idServizio = articolo.idArticolo WHERE nome = '" + nomeServizio + "';");
        Servizio servizio;
        try {
            rs.next();
            servizio = new Servizio();
            servizio.setIdArticolo(rs.getInt("idServizio"));
            servizio.setNome(rs.getString("nome"));
            servizio.setIdCategoria(rs.getInt("idCategoria"));
            servizio.setPrezzo(rs.getFloat("prezzo"));
            servizio.setDescrizione(rs.getString("descrizione"));
            servizio.setIdFornitoreServizio(rs.getInt("idFornitore"));
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
        rs = conn.executeQuery("SELECT * FROM servizio INNER JOIN articolo ON servizio.idServizio = articolo.idArticolo;");
        ArrayList<Servizio> servizi = new ArrayList<>();
        try {
            while(rs.next()) {
                servizio = new Servizio();
                servizio.setIdArticolo(rs.getInt("idServizio"));
                servizio.setNome(rs.getString("nome"));
                servizio.setIdCategoria(rs.getInt("idCategoria"));
                servizio.setPrezzo(rs.getFloat("prezzo"));
                servizio.setDescrizione(rs.getString("descrizione"));
                servizio.setIdFornitoreServizio(rs.getInt("idFornitore"));
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
