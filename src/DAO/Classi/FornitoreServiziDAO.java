package DAO.Classi;

import DAO.Interfacce.IFornitoreServiziDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.FornitoreServizi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FornitoreServiziDAO implements IFornitoreServiziDAO {


    private static FornitoreServiziDAO instance = new FornitoreServiziDAO();
    private FornitoreServizi fornitoreServizi;
    private static IDbConnection conn;
    private static ResultSet rs;

    private FornitoreServiziDAO(){
        fornitoreServizi = null;
        conn = null;
        rs = null;
    }

    public static FornitoreServiziDAO getInstance() {
        return instance;
    }

    @Override
    public int add(FornitoreServizi fornitoreServizi) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO fornitore_servizio(nome, sito_web, citta, nazione) VALUES ('"+ fornitoreServizi.getNome() + "','" + fornitoreServizi.getSitoweb() + "','" + fornitoreServizi.getCitta() + "','"+ fornitoreServizi.getNazione() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(FornitoreServizi fornitoreServizi) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE fornitore_servizio SET nome = '"+ fornitoreServizi.getNome() + "', sito_web = '" + fornitoreServizi.getSitoweb() + "', citta = '" + fornitoreServizi.getCitta() + "', nazione = '" + fornitoreServizi.getNazione() + "' WHERE idFornitoreServizio = '" + fornitoreServizi.getIdFornitore() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(FornitoreServizi fornitoreServizi) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM fornitore_servizio WHERE idFornitoreServizio = '" + fornitoreServizi.getIdFornitore() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public FornitoreServizi findByID(int idFornitore) {
        return findByID(idFornitore, 0);
    }

    public FornitoreServizi findByID(int idFornitore, int closeConn){
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM fornitore_servizio WHERE idFornitoreServizio = '" + idFornitore + "';");
        FornitoreServizi fornitoreServizi;
        try {
            if(rs.next()) {
                fornitoreServizi = new FornitoreServizi();
                fornitoreServizi.setIdFornitore(rs.getInt("idFornitoreServizio"));
                fornitoreServizi.setNome(rs.getString("nome"));
                fornitoreServizi.setSitoweb(rs.getString("sito_web"));
                fornitoreServizi.setCitta(rs.getString("citta"));
                fornitoreServizi.setNazione(rs.getString("nazione"));
                return fornitoreServizi;
            }else{
                return null;
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
    public FornitoreServizi findByName(String nomeFornitore) {
        return findByName(nomeFornitore, 0);
    }

    public FornitoreServizi findByName(String nomeFornitore, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM fornitore_servizio WHERE nome = '" + nomeFornitore + "';");
        FornitoreServizi fornitoreServizi;
        try {
            rs.next();
            fornitoreServizi = new FornitoreServizi();
            fornitoreServizi.setIdFornitore(rs.getInt("idFornitoreServizio"));
            fornitoreServizi.setNome(rs.getString("nome"));
            fornitoreServizi.setSitoweb(rs.getString("sito_web"));
            fornitoreServizi.setCitta(rs.getString("citta"));
            fornitoreServizi.setNazione(rs.getString("nazione"));
            return fornitoreServizi;
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
    public ArrayList<FornitoreServizi> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM fornitore_servizio;");
        ArrayList<FornitoreServizi> fornitoriServizi = new ArrayList<>();

        try {
            while (rs.next()) {
                fornitoreServizi = new FornitoreServizi();
                fornitoreServizi.setIdFornitore(rs.getInt("idFornitoreServizio"));
                fornitoreServizi.setNome(rs.getString("nome"));
                fornitoreServizi.setSitoweb(rs.getString("sito_web"));
                fornitoreServizi.setCitta(rs.getString("citta"));
                fornitoreServizi.setNazione(rs.getString("nazione"));
                fornitoriServizi.add(fornitoreServizi);
            }
            return fornitoriServizi;
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
