package DAO.Classi;

import DAO.Interfacce.IPrenotazioneDAO;
import DbInterface.Command.DbOperationExecutor;
import DbInterface.Command.IDbOperation;
import DbInterface.Command.ReadOperation;
import DbInterface.Command.WriteOperation;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Prenotazione;
import Model.Servizio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrenotazioneDAO implements IPrenotazioneDAO {

    private static PrenotazioneDAO instance = new PrenotazioneDAO();
    private static IDbConnection conn;
    private static ResultSet rs;

    private PrenotazioneDAO(){
        conn = null;
        rs = null;
    }

    public static PrenotazioneDAO getInstance() {
        return instance;
    }


    @Override
    public int add(Prenotazione prenotazione) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "INSERT INTO prenotazione(quantita_prenotata, idProdotto, idAcquisto) VALUES ('"+ prenotazione.getQuantita_prenotata() + "','" + prenotazione.getIdProdotto() + "','" + prenotazione.getIdAcquisto() + "');";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Prenotazione prenotazione) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "UPDATE prenotazione SET quantita_prenotata = '" + prenotazione.getQuantita_prenotata() + "' WHERE idPrenotazione = '" + prenotazione.getIdPrenotazione() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Prenotazione prenotazione) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "DELETE FROM prenotazione WHERE idPrenotazione = '" + prenotazione.getIdPrenotazione() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public ArrayList<Prenotazione> findAllByProduct(int idProdotto){
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM prenotazione WHERE idProdotto = '" + idProdotto + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
        try {
            while(rs.next()) {
                Prenotazione prenotazione = new Prenotazione();
                prenotazione.setIdPrenotazione(rs.getInt("idPrenotazione"));
                prenotazione.setIdAcquisto(rs.getInt("idAcquisto"));
                prenotazione.setIdProdotto(rs.getInt("idProdotto"));
                prenotazione.setQuantita_prenotata(rs.getInt("quantita_prenotata"));
                prenotazioni.add(prenotazione);
            }
            return prenotazioni;
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
    public Prenotazione findByProdottoAcquisto(int idProdotto, int idAcquisto){
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM prenotazione WHERE idProdotto = '" + idProdotto + "' AND idAcquisto = '" + idAcquisto + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        try {
            if(rs.next()) {
                Prenotazione prenotazione = new Prenotazione();
                prenotazione.setIdPrenotazione(rs.getInt("idPrenotazione"));
                prenotazione.setIdAcquisto(rs.getInt("idAcquisto"));
                prenotazione.setIdProdotto(rs.getInt("idProdotto"));
                prenotazione.setQuantita_prenotata(rs.getInt("quantita_prenotata"));
                return prenotazione;
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

}
