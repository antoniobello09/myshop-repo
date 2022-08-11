package DAO.Classi;

import DAO.Interfacce.IArticoloDAO;
import DbInterface.*;
import Model.Articolo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticoloDAO implements IArticoloDAO {

    private static ArticoloDAO instance = new ArticoloDAO();
    private Articolo articolo;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ArticoloDAO() {
        articolo = null;
        conn = null;
        rs = null;
    }

    public static ArticoloDAO getInstance() {
        return instance;
    }

//----------------FIND BY ID-----------------------------------------------------------------------------------------//
    @Override
    public Articolo findById(int idArticolo) {
    //--------------EXECUTOR------------------------------------------------------------------------//
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
    //-------------STRINGA SQL----------------------------------------------------------------------//
        String sql = "SELECT * FROM articolo WHERE articolo.idArticolo = '" + idArticolo + "';";
    //-------------OPERAZIONE-----------------------------------------------------------------------//
        IDbOperation dbOp = new ReadOperation(sql); // --> executeQuery();
    //-------------EXECUTOR ESEGUE OPERAZIONE-------------------------------------------------------//
        rs = dbOperationExecutor.executeOperation(dbOp);
    //----------------------------------------------------------------------------------------------//

    //------------INSTANZA articolo CERCATO-----------------------------------------------------------//
        try {
            rs.next();
            if (rs.getRow()==1) {
                articolo = new Articolo();
                articolo.setId(rs.getInt("idArticolo"));
                articolo.setNome(rs.getString("Nome"));
                articolo.setDescrizione(rs.getString("Descrizione"));
                articolo.setPrezzo(rs.getFloat("Costo"));
                return articolo;
            }
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }
//--------------FIND ALL-----------------------------------------------------------------------------------------------//
    @Override
    public ArrayList<Articolo> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM articolo;");
        ArrayList<Articolo> articoli = new ArrayList<>();
        try {
            while (rs.next()) {
                articolo = new Articolo();
                articolo.setId(rs.getInt("idArticolo"));
                articolo.setNome(rs.getString("nome"));
                articolo.setDescrizione(rs.getString("descrizione"));
                articolo.setPrezzo(rs.getFloat("prezzo"));
                articoli.add(articolo);
            }
            return articoli;
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
    public Articolo findByName(String nomeArticolo, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM articolo WHERE articolo.nome = '" + nomeArticolo + "';");
        Articolo articolo = new Articolo();
        try {
            rs.next();
            articolo.setId(rs.getInt("idArticolo"));
            articolo.setNome(rs.getString("nome"));
            articolo.setPrezzo(rs.getFloat("prezzo"));
            articolo.setImmagine(rs.getBlob("immagine"));
            articolo.setDescrizione(rs.getString("descrizione"));
            return articolo;
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
}