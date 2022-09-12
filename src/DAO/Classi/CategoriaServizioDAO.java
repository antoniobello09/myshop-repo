package DAO.Classi;

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
import java.util.List;

public class CategoriaServizioDAO  implements ICategoriaServizioDAO {

    private static CategoriaServizioDAO instance = new CategoriaServizioDAO();

    private static IDbConnection conn;
    private static ResultSet rs;
    private static ResultSet rs2;


    private CategoriaServizioDAO(){
        conn = null;
        rs = null;
    }

    public static CategoriaServizioDAO getInstance(){
        return instance;
    }

    @Override
    public int add(CategoriaServizio categoriaServizio) {
        conn = DbConnection.getInstance();
        int rowCount;
        conn.executeUpdate("INSERT INTO categoria(nome) VALUES ('"+ categoriaServizio.getNome() + "');");
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "INSERT INTO categoria_servizio VALUES ('" + CategoriaDAO.getInstance().findByName(categoriaServizio.getNome(),1).getIdCategoria() + "');";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }



    @Override
    public int update(CategoriaServizio categoriaServizio) {
        conn = DbConnection.getInstance();
        int rowCount = CategoriaDAO.getInstance().update(categoriaServizio);
        conn.close();
        return rowCount;
    }


    @Override
    public int delete(CategoriaServizio categoriaServizio) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "DELETE CP FROM categoria_servizio CP INNER JOIN categoria C ON CP.idCategoria = C.idCategoria WHERE CP.idCategoria = '" + categoriaServizio.getIdCategoria() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        CategoriaDAO.getInstance().delete(categoriaServizio);
        conn.close();
        return rowCount;
    }

    @Override
    public CategoriaServizio findByID(int idCategoria){
        return findByID(idCategoria, 0);
    }

    public CategoriaServizio findByID(int idCategoria, int i) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM categoria c INNER JOIN categoria_servizio cs ON c.idCategoria = cs.idCategoria WHERE c.idCategoria = '" + idCategoria + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        ResultSet rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        CategoriaServizio categoriaServizio;
        try {
            rs.next();
            if (rs.getRow()==1) {
                categoriaServizio = new CategoriaServizio();
                categoriaServizio.setIdCategoria(rs.getInt("idCategoria"));
                categoriaServizio.setNome(rs.getString("nome"));
                return categoriaServizio;
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
            if(i==0)
                conn.close();
        }
        return null;
    }


    @Override
    public CategoriaServizio findByName(String nomeCategoria){
        return findByName(nomeCategoria, 0);
    }

    public CategoriaServizio findByName(String nomeCategoria, int i) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM categoria c INNER JOIN categoria_servizio cs ON c.idCategoria = cs.idCategoria WHERE nome = '" + nomeCategoria + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        ResultSet rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        CategoriaServizio categoriaServizio;
        try {
            rs.next();
            if (rs.getRow()==1) {
                categoriaServizio = new CategoriaServizio();
                categoriaServizio.setIdCategoria(rs.getInt("idCategoria"));
                categoriaServizio.setNome(rs.getString("nome"));
                return categoriaServizio;
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
            if(i==0)
                conn.close();
        }
        return null;
    }


    @Override
    public ArrayList<CategoriaServizio> findAll() {
        IDbConnection  conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM categoria INNER JOIN categoria_servizio ON categoria.idCategoria = categoria_Servizio.idCategoria;";
        IDbOperation dbOperation = new ReadOperation(sql);
        ResultSet rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        ArrayList<CategoriaServizio> categorieServizi = new ArrayList<>();
        CategoriaServizio categoriaServizio;
        try {
            int i=0;
            while (rs.next()) {
                categoriaServizio = new CategoriaServizio();
                categoriaServizio.setIdCategoria(rs.getInt("idCategoria"));
                categoriaServizio.setNome(rs.getString("nome"));
                categorieServizi.add(categoriaServizio);
            }
            return categorieServizi;
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


