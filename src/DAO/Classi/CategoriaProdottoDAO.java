package DAO.Classi;

import Business.AbstractFactory.AbstractFactory;
import Business.AbstractFactory.FactoryProvider;
import DAO.Interfacce.ICategoriaProdottoDAO;
import DbInterface.*;
import DbInterface.Command.DbOperationExecutor;
import DbInterface.Command.IDbOperation;
import DbInterface.Command.ReadOperation;
import DbInterface.Command.WriteOperation;
import Model.CategoriaProdotto;
import Model.Servizio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaProdottoDAO implements ICategoriaProdottoDAO {

    private static CategoriaProdottoDAO instance = new CategoriaProdottoDAO();

    private static IDbConnection conn;
    private static ResultSet rs;
    private static ResultSet rs2;


    private CategoriaProdottoDAO(){
        conn = null;
        rs = null;
    }

    public static CategoriaProdottoDAO getInstance(){
        return instance;
    }

    @Override
    public int add(CategoriaProdotto categoriaProdotto) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "INSERT INTO categoria_prodotto(idCategoria) VALUES ('" + categoriaProdotto.getIdCategoria() + "');";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int addSub(CategoriaProdotto categoriaProdotto) {
        conn = DbConnection.getInstance();
        int rowCount = 0;
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "INSERT INTO categoria_prodotto VALUES ('" + categoriaProdotto.getIdCategoria() + "','" + categoriaProdotto.getIdCategoriaPadre() + "');";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int update(CategoriaProdotto categoriaProdotto) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "UPDATE categoria SET nome = '"+ categoriaProdotto.getNome() + "' WHERE idCategoria = '" + categoriaProdotto.getIdCategoria() +"';";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }


    @Override
    public int delete(CategoriaProdotto categoriaProdotto) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "DELETE FROM categoria_prodotto WHERE idCategoriaPadre = '" + categoriaProdotto.getIdCategoriaPadre() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int deleteSub(CategoriaProdotto categoriaProdotto) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "DELETE FROM categoria_prodotto WHERE idCategoria = '" + categoriaProdotto.getIdCategoria() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.executeUpdate("DELETE FROM categoria WHERE idCategoria = '" + categoriaProdotto.getIdCategoria() + "';");
        conn.close();
        return rowCount;
    }



    @Override
    public CategoriaProdotto findByID(int idCategoria) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM categoria c INNER JOIN categoria_prodotto cp ON c.idCategoria = cp.idCategoria WHERE c.idCategoria = '" + idCategoria + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        CategoriaProdotto categoriaProdotto;
        try {
            rs.next();
            if (rs.getRow()==1) {
                AbstractFactory categoryFactory = FactoryProvider.getFactory(FactoryProvider.FactoryType.CATEGORIA);
                categoriaProdotto = (CategoriaProdotto) categoryFactory.crea("PRODOTTO");
                categoriaProdotto.setIdCategoria(rs.getInt("idCategoria"));
                categoriaProdotto.setNome(rs.getString("nome"));
                categoriaProdotto.setIdCategoriaPadre(rs.getInt("idCategoriaPadre"));
                return categoriaProdotto;
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

    public ArrayList<CategoriaProdotto> findAllSons(int idCategoriaPadre){
        IDbConnection  conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM categoria INNER JOIN categoria_prodotto ON categoria.idCategoria = categoria_prodotto.idCategoria WHERE idCategoriaPadre = '" + idCategoriaPadre + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        ArrayList<CategoriaProdotto> categorieProdotti = new ArrayList<>();
        CategoriaProdotto categoriaProdotto;
        try {
            while (rs.next()) {
                AbstractFactory categoryFactory = FactoryProvider.getFactory(FactoryProvider.FactoryType.CATEGORIA);
                categoriaProdotto = (CategoriaProdotto) categoryFactory.crea("PRODOTTO");
                categoriaProdotto.setIdCategoria(rs.getInt("idCategoria"));
                categoriaProdotto.setNome(rs.getString("nome"));
                categoriaProdotto.setIdCategoriaPadre(rs.getInt("idCategoriaPadre"));
                categorieProdotti.add(categoriaProdotto);
            }
            return categorieProdotti;
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
    public CategoriaProdotto findByName(String nomeCategoria) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM categoria c INNER JOIN categoria_prodotto cp ON c.idCategoria = cp.idCategoria WHERE nome = '" + nomeCategoria + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        CategoriaProdotto categoriaProdotto;
        try {
            rs.next();
            if (rs.getRow()==1) {
                AbstractFactory categoryFactory = FactoryProvider.getFactory(FactoryProvider.FactoryType.CATEGORIA);
                categoriaProdotto = (CategoriaProdotto) categoryFactory.crea("PRODOTTO");
                categoriaProdotto.setIdCategoria(rs.getInt("idCategoria"));
                categoriaProdotto.setNome(rs.getString("nome"));
                categoriaProdotto.setIdCategoriaPadre(rs.getInt("idCategoriaPadre"));
                return categoriaProdotto;
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
    public boolean isCategory(CategoriaProdotto categoriaProdotto) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) as c FROM categoria INNER JOIN categoria_prodotto ON categoria.idCategoria = categoria_prodotto.idCategoria WHERE nome = '" + categoriaProdotto.getNome() + "' AND idCategoriaPadre IS NULL;";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        try {
            rs.next();
            if(rs.getInt("c")==0){
                return false;
            }else{
                return true;
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

        }
        return false;
    }

    @Override
    public ArrayList<CategoriaProdotto> findAll() {
        IDbConnection  conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM categoria INNER JOIN categoria_prodotto ON categoria.idCategoria = categoria_prodotto.idCategoria WHERE categoria.idCategoria <> '0';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        ArrayList<CategoriaProdotto> categorieProdotti = new ArrayList<>();
        CategoriaProdotto categoriaProdotto;
        try {
            while (rs.next()) {
                AbstractFactory categoryFactory = FactoryProvider.getFactory(FactoryProvider.FactoryType.CATEGORIA);
                categoriaProdotto = (CategoriaProdotto) categoryFactory.crea("PRODOTTO");
                categoriaProdotto.setIdCategoria(rs.getInt("idCategoria"));
                categoriaProdotto.setNome(rs.getString("nome"));
                categoriaProdotto.setIdCategoriaPadre(rs.getInt("idCategoriaPadre"));
                categorieProdotti.add(categoriaProdotto);
            }
            return categorieProdotti;
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
