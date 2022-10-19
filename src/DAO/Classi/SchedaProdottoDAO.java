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
    public int add(SchedaProdotto schedaProdotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO scheda_prodotto(idProdotto, disponibilita, idPuntoVendita) VALUES ('"+ schedaProdotto.getIdProdotto() + "','" + schedaProdotto.getDisponibilita() + "','" + schedaProdotto.getIdPuntoVendita() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(SchedaProdotto schedaProdotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE scheda_prodotto SET disponibilita = '"+  schedaProdotto.getDisponibilita() + "' WHERE idSchedaProdotto = '" + schedaProdotto.getIdSchedaProdotto() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(SchedaProdotto schedaProdotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM scheda_prodotto WHERE idSchedaProdotto = '" + schedaProdotto.getIdSchedaProdotto() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public SchedaProdotto findByShop_Product(int idProdotto , int idPuntoVendita) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM scheda_prodotto WHERE idProdotto = '" + idProdotto + "' AND idPuntovendita = '" + idPuntoVendita + "';");
        SchedaProdotto schedaProdotto;
        try {
            rs.next();
            if (rs.getRow()==1) {
                schedaProdotto = new SchedaProdotto();
                schedaProdotto.setIdSchedaProdotto(rs.getInt("idSchedaProdotto"));
                schedaProdotto.setIdProdotto(rs.getInt("idProdotto"));
                schedaProdotto.setDisponibilita(rs.getInt("disponibilita"));
                schedaProdotto.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
                return schedaProdotto;
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
            conn.close();
        }
        return null;
    }

    public ArrayList<SchedaProdotto> findAllByProduct(int idProdotto) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM scheda_prodotto WHERE idProdotto = '" + idProdotto + "';");
        ArrayList<SchedaProdotto> schedeProdotto = new ArrayList<>();
        try {
            while(rs.next()){
                SchedaProdotto schedaProdotto = new SchedaProdotto();
                schedaProdotto.setIdSchedaProdotto(rs.getInt("idSchedaProdotto"));
                schedaProdotto.setIdProdotto(rs.getInt("idProdotto"));
                schedaProdotto.setDisponibilita(rs.getInt("disponibilita"));
                schedaProdotto.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
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

    @Override
    public ArrayList<SchedaProdotto> findAll() {

        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM scheda_prodotto;");
        ArrayList<SchedaProdotto> schedeProdotto = new ArrayList<>();
        try {
            while (rs.next()) {
                schedaProdotto = new SchedaProdotto();
                schedaProdotto.setIdSchedaProdotto(rs.getInt("idSchedaProdotto"));
                schedaProdotto.setIdProdotto(rs.getInt("idProdotto"));
                schedaProdotto.setDisponibilita(rs.getInt("disponibilita"));
                schedaProdotto.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
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


    @Override
    public ArrayList<SchedaProdotto> findAllByShop(int idPuntoVendita) {

        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM scheda_prodotto WHERE idPuntoVendita = '" + idPuntoVendita + "';");
        ArrayList<SchedaProdotto> schedeProdotto = new ArrayList<>();
        try {
            while (rs.next()) {
                schedaProdotto = new SchedaProdotto();
                schedaProdotto.setIdSchedaProdotto(rs.getInt("idSchedaProdotto"));
                schedaProdotto.setIdProdotto(rs.getInt("idProdotto"));
                schedaProdotto.setDisponibilita(rs.getInt("disponibilita"));
                schedaProdotto.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
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
