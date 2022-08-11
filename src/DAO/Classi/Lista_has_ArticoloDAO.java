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
    private Lista lista;
    private static IDbConnection conn;
    private static ResultSet rs;

    private Lista_has_ArticoloDAO(){
        lista = null;
        conn = null;
        rs = null;
    }

    public static Lista_has_ArticoloDAO getInstance() {
        return instance;
    }

    @Override
    public int add(Lista lista, Articolo articolo) {
        conn = DbConnection.getInstance();
        int rowCount;
        if(articolo instanceof Servizio) {
            rowCount = conn.executeUpdate("INSERT INTO lista_has_articolo(idArticolo, idLista, quantita) VALUES ('" + articolo.getId() + "','" + lista.getIdLista() + "','0');");
        }else{
            Prodotto_Quantita p = (Prodotto_Quantita) articolo;
            rowCount = conn.executeUpdate("INSERT INTO lista_has_articolo(idArticolo, idLista, quantita) VALUES ('" + articolo.getId() + "','" + lista.getIdLista() + "','" + p.getQuantita() + "');");
        }
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Lista lista, Articolo articolo) {
        conn = DbConnection.getInstance();
        int rowCount = 0;
        if(articolo instanceof Prodotto_Quantita){
            Prodotto_Quantita p = (Prodotto_Quantita) articolo;
            rowCount = conn.executeUpdate("UPDATE lista_has_articolo SET quantita = '" + p.getQuantita() + "' WHERE idLista = '" + lista.getIdLista() + "' AND idArticolo = '" + articolo.getId() + "';");
        }
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Lista lista, Articolo articolo) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM lista_has_articolo WHERE idLista = '" + lista.getIdLista() + "' AND idArticolo = '" + articolo.getId() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public Articolo findByID(int idLista, int idArticolo){
        return findByID(idLista, idArticolo, 0);
    }
    public Articolo findByID(int idLista, int idArticolo, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM lista_has_articolo WHERE idLista = '" + idLista + "' AND idArticolo = '" + idArticolo + "';");
        Lista lista;
        try {
            rs.next();
            if(rs.getInt("quantita") == 0){
                Servizio servizio = ServizioDAO.getInstance().findByID(rs.getInt("idArticolo"));
                return servizio;
            }else{
                Prodotto_Quantita prodotto_quantita = new Prodotto_Quantita(ProdottoDAO.getInstance().findByID(rs.getInt("idArticolo")));
                prodotto_quantita.setQuantita(rs.getInt("quantita"));
                return prodotto_quantita;
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
    public ArrayList<Articolo> findAll(int idLista) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM lista_has_articolo WHERE idLista = '" + idLista + "';");
        ArrayList<Articolo> lista_has_articoli = new ArrayList<>();
        try {
            rs.next();
            if(rs.getInt("quantita") == 0){
                Servizio servizio = ServizioDAO.getInstance().findByID(rs.getInt("idArticolo"));
                lista_has_articoli.add(servizio);
            }else{
                Prodotto_Quantita prodotto_quantita = new Prodotto_Quantita(ProdottoDAO.getInstance().findByID(rs.getInt("idArticolo")));
                prodotto_quantita.setQuantita(rs.getInt("quantita"));
                lista_has_articoli.add(prodotto_quantita);
            }
            return lista_has_articoli;
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
    public ArrayList<Servizio> findAllServizi(int idLista) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM lista_has_articolo WHERE idLista = '" + idLista + "';");
        ArrayList<Servizio> lista_has_articoli = new ArrayList<>();
        try {
            while(rs.next()) {
                if (rs.getInt("quantita") == 0) {
                    Servizio servizio = ServizioDAO.getInstance().findByID(rs.getInt("idArticolo"));
                  lista_has_articoli.add(servizio);
                }
            }
            return lista_has_articoli;
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
    public ArrayList<Prodotto_Quantita> findAllProdotti(int idLista) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM lista_has_articolo WHERE idLista = '" + idLista + "';");
        ArrayList<Prodotto_Quantita> lista_has_articoli = new ArrayList<>();
        try {
            while(rs.next()){
                if (rs.getInt("quantita") != 0) {
                    Prodotto_Quantita prodotto_quantita = new Prodotto_Quantita(ProdottoDAO.getInstance().findByID(rs.getInt("idArticolo")));
                    prodotto_quantita.setQuantita(rs.getInt("quantita"));
                    lista_has_articoli.add(prodotto_quantita);
                }
            }
            return lista_has_articoli;
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