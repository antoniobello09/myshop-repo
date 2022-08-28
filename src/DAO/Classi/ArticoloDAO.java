package DAO.Classi;

import DAO.Interfacce.IArticoloDAO;
import DbInterface.*;
import Model.Articolo;
import Model.Fornitore;

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

    @Override
    public int add(Articolo articolo) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO articolo(idCategoria, prezzo, nome, descrizione) VALUES ('"+ articolo.getIdCategoria() + "','" + articolo.getPrezzo() + "','" + articolo.getNome() + "','"+ articolo.getDescrizione() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Articolo articolo) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE articolo SET nome = '"+ articolo.getNome() + "', idCategoria = '" + articolo.getIdCategoria() + "', prezzo = '" + articolo.getPrezzo() + "', decrizione = '" + articolo.getDescrizione() + "' WHERE idArticolo = '" + articolo.getIdArticolo() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Articolo articolo) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM articolo WHERE idArticolo = '" + articolo.getIdCategoria() + "';");
        conn.close();
        return rowCount;
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
                articolo.setIdArticolo(rs.getInt("idArticolo"));
                articolo.setNome(rs.getString("nome"));
                articolo.setDescrizione(rs.getString("descrizione"));
                articolo.setPrezzo(rs.getFloat("prezzo"));
                articolo.setIdCategoria(rs.getInt("idCategoria"));
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
                articolo.setIdArticolo(rs.getInt("idArticolo"));
                articolo.setNome(rs.getString("nome"));
                articolo.setDescrizione(rs.getString("descrizione"));
                articolo.setPrezzo(rs.getFloat("prezzo"));
                articolo.setIdCategoria(rs.getInt("idCategoria"));
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
    public Articolo findByName(String nomeArticolo){
        return findByName(nomeArticolo,0);
    }

    @Override
    public Articolo findByName(String nomeArticolo, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM articolo WHERE articolo.nome = '" + nomeArticolo + "';");
        Articolo articolo = new Articolo();
        try {
            rs.next();
            articolo.setIdArticolo(rs.getInt("idArticolo"));
            articolo.setNome(rs.getString("nome"));
            articolo.setPrezzo(rs.getFloat("prezzo"));
            articolo.setDescrizione(rs.getString("descrizione"));
            articolo.setIdCategoria(rs.getInt("idCategoria"));
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