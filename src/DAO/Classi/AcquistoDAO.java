package DAO.Classi;

import DAO.Interfacce.IAcquistoDAO;
import DAO.Interfacce.IProduttoreDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Acquisto;
import Model.Lista;
import Model.Produttore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AcquistoDAO implements IAcquistoDAO{

    private static AcquistoDAO instance = new AcquistoDAO();
    private Acquisto acquisto;
    private static IDbConnection conn;
    private static ResultSet rs;

    private AcquistoDAO(){
        acquisto = null;
        conn = null;
        rs = null;
    }

    public static AcquistoDAO getInstance() {
        return instance;
    }

    @Override
    public int add(Acquisto acquisto, Lista lista) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO acquisto(totale, data, idLista) VALUES ('"+ lista.getTotale() + "','" + acquisto.getData() + "','" + lista.getIdLista() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Acquisto acquisto) {
        return 0;
    }

    @Override
    public int delete(int idLista) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM acquisto WHERE idLista = '" + idLista + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public Acquisto findByID(int idLista){
        return findByID(idLista, 0);
    }
    public Acquisto findByID(int idLista, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM acquisto WHERE idLista = '" + idLista + "';");
        Acquisto acquisto;
        try {
            if(rs.next()) {
                acquisto = new Acquisto();
                acquisto.setIdAcquisto(rs.getInt("idAcquisto"));
                acquisto.setData(rs.getString("data"));
                return acquisto;
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
            if(closeConn == 0)
            conn.close();
        }
        return null;
    }



    @Override
    public ArrayList<Acquisto> findAll() {

        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM acquisto a INNER JOIN lista l ON l.idLista = a.idLista;");
        ArrayList<Acquisto> acquisti = new ArrayList<>();
        try {
            while (rs.next()) {
                acquisto = new Acquisto();
                acquisto.setIdAcquisto(rs.getInt("idAcquisto"));
                acquisto.setData(rs.getString("data"));
                acquisti.add(acquisto);
            }
            return acquisti;
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
