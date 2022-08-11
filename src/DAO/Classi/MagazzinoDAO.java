package DAO.Classi;

import DAO.Interfacce.IMagazzinoDAO;
import DAO.Interfacce.IManagerDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MagazzinoDAO implements IMagazzinoDAO {

    private static MagazzinoDAO instance = new MagazzinoDAO();
    private Magazzino magazzino;
    private static IDbConnection conn;
    private static ResultSet rs;

    private MagazzinoDAO(){
        magazzino = null;
        conn = null;
        rs = null;
    }

    public static MagazzinoDAO getInstance() {
        return instance;
    }

    @Override
    public int add() {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO magazzino VALUES ();");
        conn.close();
        return rowCount;
    }

    @Override
    public int addSchedaProdotto(SchedaProdotto schedaProdotto, int idMagazzino) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO scheda_prodotto(idProdotto, idMagazzino) VALUES ('"+ schedaProdotto.getProdotto().getId() + "','" + idMagazzino + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int updateSchedaProdotto(SchedaProdotto schedaProdotto, int idMagazzino, int piano, int corsia, int scaffale, int disponibilita) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE scheda_prodotto SET idPosizione = '"+ PosizioneDAO.getInstance().findByPosition(idMagazzino, piano, corsia, scaffale, 1).getIdPosizione() + "', disponibilita = '" + disponibilita + "' WHERE idSchedaProdotto = '" + schedaProdotto.getIdSchedaProdotto() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int updateSchedaProdotto(SchedaProdotto schedaProdotto, int idMagazzino, int disponibilita) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE scheda_prodotto SET idPosizione = NULL, disponibilita = '" + disponibilita + "' WHERE idSchedaProdotto = '" + schedaProdotto.getIdSchedaProdotto() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Magazzino magazzino) {
        conn = DbConnection.getInstance();
        int rowCount = 0;
        rs = conn.executeQuery("SELECT * FROM magazzino m INNER JOIN posizione p ON m.idMagazzino = p.idMagazzino WHERE m.idMagazzino = '" + magazzino.getIdMagazzino() + "' ;");
        try {
            while(rs.next()) {
                boolean found = false;
                for(int i=0;i<magazzino.getPiani().size() && !found;i++){
                    for(int j=0;j<magazzino.getPiani().get(i).getCorsie().size() && !found;j++){
                        for(int k=0;k<magazzino.getPiani().get(i).getCorsie().get(j).getScaffali().size() && !found;k++){
                            if( (rs.getInt("piano") == magazzino.getPiani().get(i).getNumero())&&
                                (rs.getInt("corsia") == magazzino.getPiani().get(i).getCorsie().get(j).getNumero())&&
                                (rs.getInt("scaffale") == magazzino.getPiani().get(i).getCorsie().get(j).getScaffali().get(k).getNumero()))
                            {
                                found = true;
                            }
                        }
                    }
                }
                if(!found){
                    rowCount = conn.executeUpdate("DELETE FROM posizione WHERE idPosizione = '" + rs.getInt("idPosizione") + "';");
                }
            }
            for(int i=0;i<magazzino.getPiani().size();i++){
                for(int j=0;j<magazzino.getPiani().get(i).getCorsie().size();j++){
                    for(int k=0;k<magazzino.getPiani().get(i).getCorsie().get(j).getScaffali().size();k++){
                            ResultSet rs2 = conn.executeQuery("SELECT * FROM magazzino m INNER JOIN posizione p ON m.idMagazzino = p.idMagazzino WHERE m.idMagazzino = '" + magazzino.getIdMagazzino() + "' " +
                                    "AND piano = '" + magazzino.getPiani().get(i).getNumero() + "' " +
                                    "AND corsia = '" + magazzino.getPiani().get(i).getCorsie().get(j).getNumero() + "' " +
                                    "AND scaffale = '" + magazzino.getPiani().get(i).getCorsie().get(j).getScaffali().get(k).getNumero() + "';");
                            if(!rs2.next()){
                                conn.executeUpdate("INSERT INTO posizione(piano, corsia, scaffale, idMagazzino) VALUES " +
                                        "('" +  magazzino.getPiani().get(i).getNumero()                                         + "','" +
                                                magazzino.getPiani().get(i).getCorsie().get(j).getNumero()                      + "','" +
                                                magazzino.getPiani().get(i).getCorsie().get(j).getScaffali().get(k).getNumero() + "','" +
                                                magazzino.getIdMagazzino()                                                      + "');");

                            }

                    }
                }
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

        }
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Magazzino magazzino) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM magazzino WHERE idMagazzino = '" + magazzino.getIdMagazzino() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public Magazzino findByID(int idMagazzino){
        return findByID(idMagazzino, 0);
    }

    public Magazzino findByID(int idMagazzino, int closeConn) {
        conn = DbConnection.getInstance();
        ResultSet rsPiani = conn.executeQuery("SELECT DISTINCT piano FROM magazzino m INNER JOIN posizione p ON m.idMagazzino = p.idMagazzino  WHERE m.idMagazzino = '" + idMagazzino + "';");
        Magazzino magazzino = new Magazzino();
        magazzino.setIdMagazzino(idMagazzino);
        try {
            while(rsPiani.next()){
                Piano piano = new Piano();
                piano.setNumero(rsPiani.getInt("piano"));
                ResultSet rsCorsie = conn.executeQuery("SELECT DISTINCT corsia FROM magazzino m INNER JOIN posizione p ON m.idMagazzino = p.idMagazzino  WHERE m.idMagazzino = '" + idMagazzino + "' AND piano = '" + piano.getNumero() + "';");
                while(rsCorsie.next()){
                    Corsia corsia = new Corsia();
                    corsia.setNumero(rsCorsie.getInt("corsia"));
                    ResultSet rsScaffali = conn.executeQuery("SELECT DISTINCT scaffale FROM magazzino m INNER JOIN posizione p ON m.idMagazzino = p.idMagazzino  WHERE m.idMagazzino = '" + idMagazzino + "' AND piano = '" + piano.getNumero() + "' AND corsia = '" + corsia.getNumero() + "';");

                    while(rsScaffali.next()){
                        Scaffale scaffale = new Scaffale();
                        scaffale.setNumero(rsScaffali.getInt("scaffale"));
                        Posizione p = PosizioneDAO.getInstance().findByPosition(idMagazzino, piano.getNumero(), corsia.getNumero(), scaffale.getNumero(),1);
                        scaffale.setProdotto(SchedaProdottoDAO.getInstance().findByPosition(p.getIdPosizione(), 1));
                        corsia.getScaffali().add(scaffale);
                    }
                    piano.getCorsie().add(corsia);
                }
                magazzino.getPiani().add(piano);
            }
            ResultSet rsSchede = conn.executeQuery("SELECT * FROM scheda_prodotto WHERE idMagazzino = '" + idMagazzino + "' AND idPosizione IS NULL");
            while(rsSchede.next()){
                magazzino.getProdottiSenzaPosizione().add(SchedaProdottoDAO.getInstance().findByID(rsSchede.getInt("idSchedaProdotto"),1));
            }
            return magazzino;
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
    public ArrayList<Magazzino> findAll(){
        return findAll(0);
    }

    public ArrayList<Magazzino> findAll(int closeConn) {
        conn = DbConnection.getInstance();
        ArrayList<Magazzino> magazzini = new ArrayList<>();
        rs = conn.executeQuery("SELECT * FROM magazzino");
        try {
            while(rs.next()) {
                Magazzino magazzino = new Magazzino();
                ResultSet rsPiani = conn.executeQuery("SELECT DISTINCT piano FROM magazzino m INNER JOIN posizione p ON m.idMagazzino = p.idMagazzino  WHERE m.idMagazzino = '" + rs.getInt("idMagazzino") + "';");
                magazzino.setIdMagazzino(rs.getInt("idMagazzino"));
                while (rsPiani.next()) {
                    Piano piano = new Piano();
                    piano.setNumero(rsPiani.getInt("piano"));
                    ResultSet rsCorsie = conn.executeQuery("SELECT DISTINCT corsie FROM magazzino m INNER JOIN posizione p ON m.idMagazzino = p.idMagazzino  WHERE m.idMagazzino = '" + rs.getInt("idMagazzino") + "' AND piano = '" + piano.getNumero() + "';");
                    while (rsCorsie.next()) {
                        Corsia corsia = new Corsia();
                        corsia.setNumero(rsCorsie.getInt("corsia"));
                        ResultSet rsScaffali = conn.executeQuery("SELECT DISTINCT scaffale FROM magazzino m INNER JOIN posizione p ON m.idMagazzino = p.idMagazzino  WHERE m.idMagazzino = '" + rs.getInt("idMagazzino") + "' AND piano = '" + piano.getNumero() + "' AND corsia = '" + corsia.getNumero() + "';");
                        while (rsScaffali.next()) {
                            Scaffale scaffale = new Scaffale();
                            scaffale.setNumero(rsScaffali.getInt("scaffale"));
                            Posizione p = PosizioneDAO.getInstance().findByPosition(magazzino.getIdMagazzino(), piano.getNumero(), corsia.getNumero(), scaffale.getNumero());
                            scaffale.setProdotto(SchedaProdottoDAO.getInstance().findByPosition(p.getIdPosizione()));
                            corsia.getScaffali().add(scaffale);
                        }
                        piano.getCorsie().add(corsia);
                    }
                    magazzino.getPiani().add(piano);
                    ResultSet rsSchede = conn.executeQuery("SELECT * FROM scheda_prodotto WHERE idMagazzino = '" + magazzino.getIdMagazzino() + "' AND idPosizione IS NULL");
                    while(rsSchede.next()){
                        magazzino.getProdottiSenzaPosizione().add(SchedaProdottoDAO.getInstance().findByID(rsSchede.getInt("idSchedaProdotto"),1));
                    }
                }
                magazzini.add(magazzino);
            }
            return magazzini;
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



}
