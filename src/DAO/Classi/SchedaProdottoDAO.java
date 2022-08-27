package DAO.Classi;

import DAO.Interfacce.ISchedaProdottoDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Posizione;
import Model.SchedaProdotto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SchedaProdottoDAO implements ISchedaProdottoDAO {

    private static SchedaProdottoDAO instance = new SchedaProdottoDAO();
    private SchedaProdotto schedaProdotto;
    private static IDbConnection conn;
    private static ResultSet rs;

    private SchedaProdottoDAO(){
        schedaProdotto = null;
        conn = null;
        rs = null;
    }

    public static SchedaProdottoDAO getInstance() {
        return instance;
    }

    @Override
    public int add(int idProdotto, int disponibilita, int idPuntoVendita) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO scheda_prodotto(idProdotto, disponibilita, idPuntoVendita) VALUES ('"+ idProdotto + "','" + disponibilita + "','" + idPuntoVendita + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(int idSchedaProdotto, int disponibilita) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE scheda_prodotto SET disponibilita = '"+  disponibilita + "' WHERE idSchedaProdotto = '" + idSchedaProdotto + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(int idSchedaProdotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM scheda_prodotto WHERE idSchedaProdotto = '" + idSchedaProdotto + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public SchedaProdotto findByShop_Product(int idSchedaProdotto, int idPuntoVendita){
        return findByShop_Product(idSchedaProdotto, 0);
    }
    public SchedaProdotto findByShop_Product(int idSchedaProdotto , int idPuntoVendita, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM scheda_prodotto WHERE idSchedaProdotto = '" + idSchedaProdotto + "';");
        SchedaProdotto schedaProdotto;
        try {
            rs.next();
            schedaProdotto = new SchedaProdotto();
            schedaProdotto.setIdSchedaProdotto(rs.getInt("idSchedaProdotto"));
            schedaProdotto.setProdotto(ProdottoDAO.getInstance().findByID(rs.getInt("idProdotto")));
            schedaProdotto.setDisponibilita(rs.getInt("disponibilita"));
            return schedaProdotto;
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
    public ArrayList<SchedaProdotto> findAll() {

        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM scheda_prodotto;");
        ArrayList<SchedaProdotto> schedeProdotto = new ArrayList<>();
        try {
            while (rs.next()) {
                schedaProdotto = new SchedaProdotto();
                schedaProdotto.setIdSchedaProdotto(rs.getInt("idSchedaProdotto"));
                schedaProdotto.setProdotto(ProdottoDAO.getInstance().findByID(rs.getInt("idProdotto")));
                schedaProdotto.setDisponibilita(rs.getInt("disponibilita"));
                schedeProdotto.add(schedaProdotto);
            }
            return schedeProdotto;
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
