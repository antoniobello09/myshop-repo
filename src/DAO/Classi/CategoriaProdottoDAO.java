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
        if(isUnique(categoriaProdotto.getNome(), null)){
            System.out.println("ciaooo");
            rowCount = conn.executeUpdate("INSERT INTO categoria(nome) VALUES ('"+ categoriaProdotto.getNome() + "');");
            conn.executeUpdate("INSERT INTO categoria_prodotto VALUES ('" + findByName(categoriaProdotto.getNome(),1).getIdCategoria() + "');");
            for(int i=0;i<categoriaProdotto.getSottocategorie().size();i++){
                if(isUnique(categoriaProdotto.getSottocategorie().get(i).getNome(),categoriaProdotto.getNome())){
                    conn.executeUpdate("INSERT INTO categoria(nome, idCategoria_Padre) VALUES ('" + categoriaProdotto.getSottocategorie().get(i).getNome() + "','" + findByName(categoriaProdotto.getNome(),1).getIdCategoria() + "')");
                    conn.executeUpdate("INSERT INTO categoria_prodotto VALUES ('" + findByName2(categoriaProdotto.getSottocategorie().get(i).getNome(),categoriaProdotto.getNome(),1).getIdCategoria() + "');");
                }
            }

        }

        conn.close();
        return rowCount;
    }

    @Override
    public int addSub(CategoriaProdotto categoriaProdotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO categoria(nome, idCategoria_Padre) VALUES ('"+ categoriaProdotto.getSottocategorie().get(categoriaProdotto.getSottocategorie().size()-1).getNome() + "', " + categoriaProdotto.getIdCategoria() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(CategoriaProdotto categoriaProdotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE categoria SET nome = '"+ categoriaProdotto.getNome() + "' WHERE idCategoria = '" + categoriaProdotto.getIdCategoria() +"';");
        rs = conn.executeQuery("SELECT count(*) AS C FROM categoria WHERE idCategoria_Padre = '" + categoriaProdotto.getIdCategoria() + "';");
        rs2 = conn.executeQuery("SELECT * FROM categoria WHERE idCategoria_Padre = '" + categoriaProdotto.getIdCategoria() + "';");
        try {
            int found;
            rs.next();
            for(int j=0;j<rs.getInt("C");j++){
                found = 0;
                rs2.next();
                for(int k=0;k<categoriaProdotto.getSottocategorie().size();k++){
                    if(rs2.getInt("idCategoria") == categoriaProdotto.getSottocategorie().get(k).getIdCategoria()){
                        conn.executeUpdate("UPDATE categoria SET nome = '" + categoriaProdotto.getSottocategorie().get(k).getNome() + "' WHERE idCategoria = '" + categoriaProdotto.getSottocategorie().get(k).getIdCategoria() + "';");
                        found = 1;
                    }
                }
                if(found == 0){
                    conn.executeUpdate("DELETE FROM categoria_prodotto WHERE idCategoria = '" + rs2.getInt("idCategoria") + "';");
                    conn.executeUpdate("DELETE FROM categoria WHERE idCategoria = '" + rs2.getInt("idCategoria") + "';");
                }

            }
            for(int i = 0; i<categoriaProdotto.getSottocategorie().size();i++){
                if(categoriaProdotto.getSottocategorie().get(i).getIdCategoria() == -1){
                    conn.executeUpdate("INSERT INTO categoria(nome, idCategoria_Padre) VALUES ('" + categoriaProdotto.getSottocategorie().get(i).getNome() + "','" + categoriaProdotto.getIdCategoria() + "');");
                    conn.executeUpdate("INSERT INTO categoria_prodotto VALUES ('" + CategoriaProdottoDAO.getInstance().findByName(categoriaProdotto.getSottocategorie().get(i).getNome(),1).getIdCategoria() + "');");

                }
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
        conn.close();
        return rowCount;
    }

    @Override
    public int updateSub(CategoriaProdotto categoriaProdotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE categoria SET nome = '"+ categoriaProdotto.getSottocategorie().get(0).getNome() + "', idCategoria_Padre = '" + categoriaProdotto.getIdCategoria() + "' WHERE idCategoria = '" + categoriaProdotto.getSottocategorie().get(0).getIdCategoria() +"';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(CategoriaProdotto categoriaProdotto) {
        conn = DbConnection.getInstance();
        conn.executeUpdate("DELETE CP FROM categoria_prodotto CP INNER JOIN categoria C ON CP.idCategoria = C.idCategoria WHERE idCategoria_Padre = '" + categoriaProdotto.getIdCategoria() + "';");
        conn.executeUpdate("DELETE FROM categoria_prodotto WHERE idCategoria = '" + categoriaProdotto.getIdCategoria() + "';");
        conn.executeUpdate("DELETE FROM categoria WHERE idCategoria_Padre = '" + categoriaProdotto.getIdCategoria() + "';");
        int rowCount = conn.executeUpdate("DELETE FROM categoria WHERE idCategoria = '" + categoriaProdotto.getIdCategoria() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public CategoriaProdotto findByID(int idCategoria){
        return findByID(idCategoria, 0);
    }

    public CategoriaProdotto findByID(int idCategoria, int i) {
        conn = DbConnection.getInstance();
        String sql = "SELECT * FROM categoria WHERE idCategoria = '" + idCategoria + "';";
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
                int c=0;
                while(rs2.next()){
                    sottocategorie.add(findByID(rs2.getInt("idCategoria"),c));
                    c++;
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


    @Override
    public CategoriaProdotto findByName(String nomeCategoria){
        return findByName(nomeCategoria, 0);
    }

    public CategoriaProdotto findByName(String nomeCategoria, int i) {
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
                ResultSet rs2 = conn.executeQuery("SELECT * FROM categoria C1 WHERE C1.idCategoria = '" + rs.getInt("idCategoria_Padre") + "';");
                if(rs2.next()){
                    categoriaProdotto.getSottocategorie().add(findByID(rs.getInt("idCategoria")));
                    categoriaProdotto.setNome(rs2.getString("nome"));
                    categoriaProdotto.setIdCategoria(rs2.getInt("idCategoria"));
                }
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

    @Override
    public ArrayList<CategoriaProdotto> findAll() {
        IDbConnection  conn = DbConnection.getInstance();
        ResultSet rs = conn.executeQuery("SELECT * FROM categoria INNER JOIN categoria_prodotto ON categoria.idCategoria = categoria_prodotto.idCategoria;");
        ArrayList<CategoriaProdotto> categorieProdotti = new ArrayList<>();
        CategoriaProdotto categoriaProdotto;
        try {
            int i=0;
            while (rs.next()) {
                if(!isSubCategory(rs.getInt("idCategoria"))) {
                    List<CategoriaProdotto> sottocategorie = new ArrayList<>();
                    categoriaProdotto = new CategoriaProdotto();
                    categoriaProdotto.setIdCategoria(rs.getInt("idCategoria"));
                    categoriaProdotto.setNome(rs.getString("nome"));
                    ResultSet rs2 = conn.executeQuery("SELECT * FROM categoria C1 WHERE C1.idCategoria_Padre = '" + categoriaProdotto.getIdCategoria() + "';");
                    while (rs2.next()) {
                        sottocategorie.add(findByID(rs2.getInt("idCategoria"), i));
                        i++;
                    }
                    categoriaProdotto.setSottocategorie(sottocategorie);
                    categorieProdotti.add(categoriaProdotto);
                }
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

}
