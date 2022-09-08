package DAO.Classi;

import DAO.Interfacce.ICategoriaProdottoDAO;
import DbInterface.*;
import Model.CategoriaProdotto;

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
        conn = DbConnection.getInstance();
        int rowCount = 0;
        rowCount = conn.executeUpdate("INSERT INTO categoria(nome) VALUES ('"+ categoriaProdotto.getNome() + "');");
        conn.executeUpdate("INSERT INTO categoria_prodotto(idCategoria) VALUES ('" + findByName(categoriaProdotto.getNome(),1).getIdCategoria() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int addSub(CategoriaProdotto categoriaProdotto, int idCategoriaPadre) {
        conn = DbConnection.getInstance();
        int rowCount = 0;
        rowCount = conn.executeUpdate("INSERT INTO categoria(nome) VALUES ('"+ categoriaProdotto.getNome() + "');");
        conn.executeUpdate("INSERT INTO categoria_prodotto VALUES ('" + findByName(categoriaProdotto.getNome(),1).getIdCategoria() + "','" + idCategoriaPadre + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(CategoriaProdotto categoriaProdotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE categoria SET nome = '"+ categoriaProdotto.getNome() + "' WHERE idCategoria = '" + categoriaProdotto.getIdCategoria() +"';");
        conn.close();
        return rowCount;
    }


    @Override
    public int delete(CategoriaProdotto categoriaProdotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM categoria_prodotto WHERE idCategoria_Padre = '" + categoriaProdotto.getIdCategoria() + "';");
        conn.executeUpdate("DELETE FROM categoria_prodotto WHERE idCategoria = '" + categoriaProdotto.getIdCategoria() + "';");
        conn.executeUpdate("DELETE FROM categoria WHERE idCategoria = '" + categoriaProdotto.getIdCategoria() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int deleteSub(CategoriaProdotto categoriaProdotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM categoria_prodotto WHERE idCategoria = '" + categoriaProdotto.getIdCategoria() + "';");
        conn.executeUpdate("DELETE FROM categoria WHERE idCategoria = '" + categoriaProdotto.getIdCategoria() + "';");
        conn.close();
        return rowCount;
    }



    @Override
    public CategoriaProdotto findByID(int idCategoria){
        return findByID(idCategoria, 0);
    }

    public CategoriaProdotto findByID(int idCategoria, int i) {
        conn = DbConnection.getInstance();
        String sql = "SELECT * FROM categoria c INNER JOIN categoria_prodotto cp ON c.idCategoria = cp.idCategoria WHERE c.idCategoria = '" + idCategoria + "';";
        ResultSet rs = conn.executeQuery(sql);
        CategoriaProdotto categoriaProdotto;
        try {
            rs.next();
            if (rs.getRow()==1) {
                categoriaProdotto = new CategoriaProdotto();
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
            if(i==0)
            conn.close();
        }
        return null;
    }

    public ArrayList<CategoriaProdotto> findAllSons(int idCategoriaPadre){
        IDbConnection  conn = DbConnection.getInstance();
        ResultSet rs = conn.executeQuery("SELECT * FROM categoria INNER JOIN categoria_prodotto ON categoria.idCategoria = categoria_prodotto.idCategoria WHERE idCategoriaPadre = '" + idCategoriaPadre + "';");
        ArrayList<CategoriaProdotto> categorieProdotti = new ArrayList<>();
        CategoriaProdotto categoriaProdotto = null;
        try {
            while (rs.next()) {
                categoriaProdotto = new CategoriaProdotto();
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
    public CategoriaProdotto findByName(String nomeCategoria){
        return findByName(nomeCategoria, 0);
    }

    public CategoriaProdotto findByName(String nomeCategoria, int i) {
        conn = DbConnection.getInstance();
        String sql = "SELECT * FROM categoria c WHERE nome = '" + nomeCategoria + "';";
        ResultSet rs = conn.executeQuery(sql);
        CategoriaProdotto categoriaProdotto;
        try {
            rs.next();
            if (rs.getRow()==1) {
                categoriaProdotto = new CategoriaProdotto();
                categoriaProdotto.setIdCategoria(rs.getInt("idCategoria"));
                categoriaProdotto.setNome(rs.getString("nome"));
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
            if(i==0)
                conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<CategoriaProdotto> findAll() {
        IDbConnection  conn = DbConnection.getInstance();
        ResultSet rs = conn.executeQuery("SELECT * FROM categoria INNER JOIN categoria_prodotto ON categoria.idCategoria = categoria_prodotto.idCategoria WHERE categoria.idCategoria <> '0';");
        ArrayList<CategoriaProdotto> categorieProdotti = new ArrayList<>();
        CategoriaProdotto categoriaProdotto;
        try {
            while (rs.next()) {
                categoriaProdotto = new CategoriaProdotto();
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
/*
    @Override
    public CategoriaProdotto findTopCategoria(String nomeCategoria){
        conn = DbConnection.getInstance();
        String sql = "SELECT * FROM categoria WHERE nome = '" + nomeCategoria + "';";
        ResultSet rs = conn.executeQuery(sql);
        CategoriaProdotto categoriaProdotto;
        try {
            rs.next();
            if (rs.getRow()==1) {
                categoriaProdotto = new CategoriaProdotto();
                categoriaProdotto.setIdCategoria(rs.getInt("idCategoria"));
                categoriaProdotto.setNome(rs.getString("nome"));
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
    public CategoriaProdotto findByName2(String nomeCategoria, String nomeCategoriaPadre){
        return findByName2(nomeCategoria, nomeCategoriaPadre, 0);
    }

    public CategoriaProdotto findByName2(String nomeCategoria, String nomeCategoriaPadre, int i) {
        conn = DbConnection.getInstance();
        String sql = "SELECT * FROM categoria WHERE nome = '" + nomeCategoria + "' AND idCategoria_Padre = '" + findByName(nomeCategoriaPadre,1).getIdCategoria() + "';";
        ResultSet rs = conn.executeQuery(sql);
        CategoriaProdotto categoriaProdotto;
        try {
            rs.next();
            if (rs.getRow()==1) {
                categoriaProdotto = new CategoriaProdotto();
                categoriaProdotto.setIdCategoria(rs.getInt("idCategoria"));
                categoriaProdotto.setNome(rs.getString("nome"));
                ResultSet rs2 = conn.executeQuery("SELECT * FROM categoria C1 WHERE C1.idCategoria_Padre = '" + categoriaProdotto.getIdCategoria() + "';");
                List<CategoriaProdotto> sottocategorie = new ArrayList<>();
                while(rs2.next()){
                    sottocategorie.add(findByID(rs2.getInt("idCategoria"),1));
                }
                categoriaProdotto.setSottocategorie(sottocategorie);
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
            if(i==0)
                conn.close();
        }
        return null;
    }
*/

/*
    @Override
    public boolean hasProducts(CategoriaProdotto categoriaProdotto) {
        conn = DbConnection.getInstance();
        String sql = "SELECT count(*) as c FROM categoria INNER JOIN articolo ON categoria.idCategoria = articolo.idCategoria WHERE categoria.idCategoria = '" + categoriaProdotto.getIdCategoria() + "';";
        ResultSet rs = conn.executeQuery(sql);
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
    public boolean isSubCategory(int idCategoria) {
        conn = DbConnection.getInstance();
        String sql = "SELECT count(*) as c FROM categoria WHERE idCategoria = '" + idCategoria + "' AND idCategoria_Padre IS NOT NULL;";
        ResultSet rs = conn.executeQuery(sql);
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

    public boolean isUnique(String nomeCategoria, String nomePadre){
        conn = DbConnection.getInstance();
        if(nomePadre != null)
            rs = conn.executeQuery("SELECT count(*) AS C FROM categoria WHERE nome = '" + nomeCategoria + "' AND idCategoria_Padre = '" + findByName(nomePadre,1).getIdCategoria() + "';");
        else
            rs = conn.executeQuery("SELECT count(*) AS C FROM categoria WHERE nome = '" + nomeCategoria + "' AND idCategoria_Padre IS NULL;");
        try {
            rs.next();
            if(rs.getInt("C") == 0){
                return true;
            }else{
                return false;
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
*/
}
