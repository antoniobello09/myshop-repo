package DAO.Classi;

import DAO.Interfacce.ICategoriaDAO;
import DAO.Interfacce.ICategoriaServizioDAO;
import DbInterface.Command.DbOperationExecutor;
import DbInterface.Command.IDbOperation;
import DbInterface.Command.ReadOperation;
import DbInterface.Command.WriteOperation;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Categoria;
import Model.CategoriaServizio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDAO implements ICategoriaDAO {

    private static CategoriaDAO instance = new CategoriaDAO();

    private static IDbConnection conn;
    private static ResultSet rs;
    private static ResultSet rs2;


    private CategoriaDAO(){
        conn = null;
        rs = null;
    }

    public static CategoriaDAO getInstance(){
        return instance;
    }

    @Override
    public int add(Categoria categoria) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "INSERT INTO categoria(nome) VALUES ('"+ categoria.getNome() + "');";
        IDbOperation dbOperation = new WriteOperation(sql);
        int rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }



    @Override
    public int update(Categoria categoria) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "UPDATE categoria SET nome = '"+ categoria.getNome() + "' WHERE idCategoria = '" + categoria.getIdCategoria() +"';";
        IDbOperation dbOperation = new WriteOperation(sql);
        int rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }


    @Override
    public int delete(Categoria categoria) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "DELETE FROM categoria WHERE idCategoria = '" + categoria.getIdCategoria() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        int rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public Categoria findByID(int idCategoria) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM categoria WHERE idCategoria = '" + idCategoria + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        Categoria categoria;
        try {
            rs.next();
            if (rs.getRow()==1) {
                categoria = new CategoriaServizio();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setNome(rs.getString("nome"));
                return categoria;
            }else if (rs.getRow()==0){
                return null;
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


    @Override
    public Categoria findByName(String nomeCategoria) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM categoria WHERE nome = '" + nomeCategoria + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        Categoria categoria;
        try {
            rs.next();
            if (rs.getRow()==1) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setNome(rs.getString("nome"));
                return categoria;
            }else if (rs.getRow()==0){
                return null;
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


    @Override
    public ArrayList<Categoria> findAll() {
        IDbConnection  conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM categoria;";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        ArrayList<Categoria> categorie = new ArrayList<>();
        Categoria categoria;
        try {
            int i=0;
            while (rs.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setNome(rs.getString("nome"));
                categorie.add(categoria);
            }
            return categorie;
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


