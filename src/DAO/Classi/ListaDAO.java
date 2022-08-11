package DAO.Classi;

import DAO.Interfacce.IListaDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Acquisto;
import Model.Lista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListaDAO implements IListaDAO {

    private static ListaDAO instance = new ListaDAO();
    private Lista lista;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ListaDAO(){
        lista = null;
        conn = null;
        rs = null;
    }

    public static ListaDAO getInstance() {
        return instance;
    }

    @Override
    public int add(Lista lista) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO lista(idCliente, nome, idPuntoVendita) VALUES ('" + lista.getIdCliente() + "','" + lista.getNome() + "','" + lista.getIdPuntoVendita() + "');");
        for(int i=0;i<lista.getProdotti().size();i++){
            Lista_has_ArticoloDAO.getInstance().add(findByInfo(lista.getIdPuntoVendita(), lista.getNome(), lista.getIdCliente(),1), lista.getProdotti().get(i));
        }
        for(int i=0;i<lista.getServizi().size();i++){
            Lista_has_ArticoloDAO.getInstance().add(findByInfo(lista.getIdPuntoVendita(), lista.getNome(), lista.getIdCliente(),1), lista.getServizi().get(i));
        }
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Lista lista) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE lista SET nome = '"+ lista.getNome() + "' WHERE idLista ='" + lista.getIdLista() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Lista lista) {
        conn = DbConnection.getInstance();
        conn.executeUpdate("DELETE FROM lista_has_articolo WHERE idLista = '" + lista.getIdLista() + "';");
        int rowCount = conn.executeUpdate("DELETE FROM lista WHERE idLista = '" + lista.getIdLista() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public Lista findByID(int idLista){
        return findByID(idLista, 0);
    }
    public Lista findByID(int idLista, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM lista WHERE idLista = '" + idLista + "';");
        Lista lista;
        try {
            rs.next();
            lista = new Lista();
            lista.setIdLista(rs.getInt("idLista"));
            lista.setIdCliente(rs.getInt("idCliente"));
            lista.setNome(rs.getString("nome"));
            lista.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
            lista.setServizi(Lista_has_ArticoloDAO.getInstance().findAllServizi(lista.getIdLista()));
            lista.setProdotti(Lista_has_ArticoloDAO.getInstance().findAllProdotti(lista.getIdLista()));
            lista.setAcquisto(AcquistoDAO.getInstance().findByID(lista.getIdLista()));
            return lista;
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
    public Lista findByInfo(int idPuntoVendita, String nomeLista, int idCliente){
        return findByInfo(idPuntoVendita, nomeLista, idCliente, 0);
    }
    public Lista findByInfo(int idPuntoVendita, String nomeLista, int idCliente, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM lista WHERE idPuntoVendita = '" + idPuntoVendita + "' AND nome = '" + nomeLista + "' AND idCliente = '" + idCliente + "';");
        Lista lista;
        try {
            rs.next();
            lista = new Lista();
            lista.setIdLista(rs.getInt("idLista"));
            lista.setIdCliente(rs.getInt("idCliente"));
            lista.setNome(rs.getString("nome"));
            lista.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
            return lista;
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
    public ArrayList<Lista> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM lista;");
        ArrayList<Lista> liste = new ArrayList<>();
        try {
            while(rs.next()) {
                lista = new Lista();
                lista.setIdLista(rs.getInt("idLista"));
                lista.setIdCliente(rs.getInt("idCliente"));
                lista.setNome(rs.getString("nome"));
                lista.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
                lista.setServizi(Lista_has_ArticoloDAO.getInstance().findAllServizi(lista.getIdLista()));
                lista.setProdotti(Lista_has_ArticoloDAO.getInstance().findAllProdotti(lista.getIdLista()));
                lista.setAcquisto(AcquistoDAO.getInstance().findByID(lista.getIdLista()));
                liste.add(lista);
            }
            return liste;
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
    public ArrayList<Lista> findAll(int idCliente) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM lista WHERE idCliente = '" + idCliente + "';");
        ArrayList<Lista> liste = new ArrayList<>();
        try {
            while(rs.next()) {
                lista = new Lista();
                lista.setIdLista(rs.getInt("idLista"));
                lista.setIdCliente(rs.getInt("idCliente"));
                lista.setNome(rs.getString("nome"));
                lista.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
                lista.setServizi(Lista_has_ArticoloDAO.getInstance().findAllServizi(lista.getIdLista()));
                lista.setProdotti(Lista_has_ArticoloDAO.getInstance().findAllProdotti(lista.getIdLista()));
                lista.setAcquisto(AcquistoDAO.getInstance().findByID(lista.getIdLista()));
                liste.add(lista);
            }
            return liste;
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
