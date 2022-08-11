package DAO.Classi;

import DAO.Interfacce.IProduttoreDAO;
import DAO.Interfacce.ISchedaProdottoDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Posizione;
import Model.Produttore;
import Model.SchedaProdotto;
import Model.SchedaServizio;

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
    public int add(SchedaProdotto schedaProdotto, int idMagazzino) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO scheda_prodotto(idProdotto, idMagazzino) VALUES ('"+ schedaProdotto.getProdotto().getId() + "','" + idMagazzino + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(SchedaProdotto schedaProdotto, Posizione posizione) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE scheda_prodotto SET disponibilita = '"+  schedaProdotto.getDisponibilita() + "', idPosizione = '" + posizione.getIdPosizione() + "';");
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
    public SchedaProdotto findByID(int idSchedaProdotto){
        return findByID(idSchedaProdotto, 0);
    }
    public SchedaProdotto findByID(int idSchedaProdotto, int closeConn) {
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
    public SchedaProdotto findByName(String nomeProdotto) {
        return findByName(nomeProdotto,0);
    }

    public SchedaProdotto findByName(String nomeProdotto, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM scheda_prodotto s INNER JOIN prodotto p ON s.idProdotto = p.idProdotto WHERE p.nome = '" + nomeProdotto + "';");
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
            if(closeConn==0)
            conn.close();
        }
        return null;
    }

    @Override
    public SchedaProdotto findByPosition(int idPosizione) {
        return findByPosition(idPosizione,0);
    }

    public SchedaProdotto findByPosition(int idPosizione, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM scheda_prodotto s INNER JOIN posizione p ON s.idPosizione = p.idPosizione WHERE p.idPosizione = '" + idPosizione + "';");
        SchedaProdotto schedaProdotto;
        try {
            if(rs.next()){
                schedaProdotto = new SchedaProdotto();
                schedaProdotto.setIdSchedaProdotto(rs.getInt("idSchedaProdotto"));
                schedaProdotto.setProdotto(ProdottoDAO.getInstance().findByID(rs.getInt("idProdotto"),1));
                schedaProdotto.setDisponibilita(rs.getInt("disponibilita"));
                return schedaProdotto;
            }else{
                return null;
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
            if(closeConn==0)
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

    @Override
    public ArrayList<SchedaProdotto> findProductsShop(int idPuntoVendita) {

        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM scheda_prodotto s INNER JOIN magazzino m ON s.idMagazzino = m.idMagazzino " +
                "INNER JOIN puntovendita p ON m.idMagazzino = p.idMagazzino WHERE idPuntoVendita = '" + idPuntoVendita + "';");
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
