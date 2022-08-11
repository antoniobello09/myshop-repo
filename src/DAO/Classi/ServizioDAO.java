package DAO.Classi;

import DAO.Interfacce.IServizioDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
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
        int rowCount = conn.executeUpdate("INSERT INTO articolo(idCategoria,prezzo,nome,descrizione) VALUES ('"+ CategoriaServizioDAO.getInstance().findByName(servizio.getCategoria().getNome(),1).getIdCategoria() + "','" + servizio.getPrezzo() + "','" + servizio.getNome() + "','" + servizio.getDescrizione() + "');");
        conn.executeUpdate("INSERT INTO servizio VALUES ('"+  ArticoloDAO.getInstance().findByName(servizio.getNome(),1).getId() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Servizio servizio) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE articolo SET idCategoria = '"+ CategoriaServizioDAO.getInstance().findByName(servizio.getCategoria().getNome(),1).getIdCategoria() + "', prezzo = '" + servizio.getPrezzo() + "', nome = '" + servizio.getNome() + "', immagine = '" + servizio.getImmagine() + "', descrizione = '" + servizio.getDescrizione() + "' WHERE idArticolo = '" + servizio.getId() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Servizio servizio) {
        conn = DbConnection.getInstance();
        conn.executeUpdate("DELETE FROM servizio WHERE idServizio = '" + servizio.getId() + "';");
        conn.executeUpdate("DELETE FROM commento WHERE idArticolo = '" + servizio.getId() + "';");
        conn.executeUpdate("DELETE FROM lista_has_articolo WHERE idArticolo = '" + servizio.getId() + "';");
        int rowCount = conn.executeUpdate("DELETE FROM articolo WHERE idArticolo = '"+ servizio.getId() + "';");
        conn.close();
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
            servizio.setId(rs.getInt("idServizio"));
            servizio.setNome(rs.getString("nome"));
            servizio.setCategoria(CategoriaServizioDAO.getInstance().findByID(rs.getInt("idCategoria"),1));
            servizio.setPrezzo(rs.getFloat("prezzo"));
            servizio.setImmagine(rs.getBlob("immagine"));
            servizio.setDescrizione(rs.getString("descrizione"));
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
            servizio.setId(rs.getInt("idServizio"));
            servizio.setNome(rs.getString("nome"));
            servizio.setCategoria(CategoriaServizioDAO.getInstance().findByID(rs.getInt("idCategoria"),1));
            servizio.setPrezzo(rs.getFloat("prezzo"));
            servizio.setImmagine(rs.getBlob("immagine"));
            servizio.setDescrizione(rs.getString("descrizione"));
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
                servizio.setId(rs.getInt("idServizio"));
                servizio.setNome(rs.getString("nome"));
                servizio.setCategoria(CategoriaServizioDAO.getInstance().findByID(rs.getInt("idCategoria"), 1));
                servizio.setPrezzo(rs.getFloat("prezzo"));
                servizio.setImmagine(rs.getBlob("immagine"));
                servizio.setDescrizione(rs.getString("descrizione"));
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

    @Override
    public ArrayList<Servizio> findByShop(int idPuntoVendita) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM scheda_servizio SS INNER JOIN servizio S ON S.idServizio = SS.idServizio INNER JOIN puntovendita P ON P.idPuntovendita = SS.idPuntoVendita INNER JOIN articolo ON S.idServizio = articolo.idArticolo;");
        ArrayList<Servizio> servizi = new ArrayList<>();
        try {
            while(rs.next()) {
                servizio = new Servizio();
                servizio.setId(rs.getInt("idServizio"));
                servizio.setNome(rs.getString("nome"));
                servizio.setCategoria(CategoriaServizioDAO.getInstance().findByID(rs.getInt("idCategoria"), 1));
                servizio.setPrezzo(rs.getFloat("prezzo"));
                servizio.setImmagine(rs.getBlob("immagine"));
                servizio.setDescrizione(rs.getString("descrizione"));
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
