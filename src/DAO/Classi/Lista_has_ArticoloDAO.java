package DAO.Classi;

import DAO.Interfacce.ILista_has_ArticoloDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Lista_has_ArticoloDAO implements ILista_has_ArticoloDAO {
    private static Lista_has_ArticoloDAO instance = new Lista_has_ArticoloDAO();
    private Lista_has_Articolo lista_has_articolo;
    private static IDbConnection conn;
    private static ResultSet rs;

    private Lista_has_ArticoloDAO(){
        lista_has_articolo = null;
        conn = null;
        rs = null;
    }

    public static Lista_has_ArticoloDAO getInstance() {
        return instance;
    }

    @Override
    public int add(int idLista, int idArticolo, int quantita) {
        conn = DbConnection.getInstance();
        int rowCount;
        rowCount = conn.executeUpdate("INSERT INTO lista_has_articolo(idArticolo, idLista, quantita) VALUES ('" + idArticolo + "','" + idLista + "','" + quantita + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(int idLista, int idArticolo, int quantita) {
        conn = DbConnection.getInstance();
        int rowCount = 0;
        rowCount = conn.executeUpdate("UPDATE lista_has_articolo SET quantita = '" + quantita + "' WHERE idLista = '" + idLista + "' AND idArticolo = '" + idArticolo + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(int idLista, int idArticolo) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM lista_has_articolo WHERE idLista = '" + idLista + "' AND idArticolo = '" + idArticolo + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public Lista_has_Articolo findByID(int idLista, int idArticolo){
        return findByID(idLista, idArticolo, 0);
    }
    public Lista_has_Articolo findByID(int idLista, int idArticolo, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM lista_has_articolo WHERE idLista = '" + idLista + "' AND idArticolo = '" + idArticolo + "';");
        Lista_has_Articolo lista_has_articolo = new Lista_has_Articolo();
        try {
            rs.next();
            lista_has_articolo.setIdArticolo(rs.getInt("idArticolo"));
            lista_has_articolo.setIdLista(rs.getInt("idLista"));
            lista_has_articolo.setQuantita(rs.getInt("quantita"));
            return lista_has_articolo;
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
    public ArrayList<Lista_has_Articolo> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM lista_has_articolo;");
        ArrayList<Lista_has_Articolo> liste_has_articolo = new ArrayList<>();
        try {
            while (rs.next()) {
                lista_has_articolo = new Lista_has_Articolo();
                lista_has_articolo.setIdArticolo(rs.getInt("idArticolo"));
                lista_has_articolo.setIdLista(rs.getInt("idLista"));
                lista_has_articolo.setQuantita(rs.getInt("quantita"));
                liste_has_articolo.add(lista_has_articolo);
            }
            return liste_has_articolo;
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
    public ArrayList<Lista_has_Articolo> findAllListArticles(int idLista) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM lista_has_articolo WHERE idLista = '" + idLista + "';");
        ArrayList<Lista_has_Articolo> liste_has_articolo = new ArrayList<>();
        try {
            while (rs.next()) {
                lista_has_articolo = new Lista_has_Articolo();
                lista_has_articolo.setIdArticolo(rs.getInt("idArticolo"));
                lista_has_articolo.setIdLista(rs.getInt("idLista"));
                lista_has_articolo.setQuantita(rs.getInt("quantita"));
                liste_has_articolo.add(lista_has_articolo);
            }
            return liste_has_articolo;
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