package DAO.Classi;

import Business.AbstractFactory.AbstractFactory;
import Business.AbstractFactory.FactoryProvider;
import DAO.Interfacce.IServizioDAO;
import DbInterface.Command.DbOperationExecutor;
import DbInterface.Command.IDbOperation;
import DbInterface.Command.ReadOperation;
import DbInterface.Command.WriteOperation;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Prodotto;
import Model.Servizio;

import java.io.*;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServizioDAO implements IServizioDAO {

    private static ServizioDAO instance = new ServizioDAO();
    private Servizio servizio;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ServizioDAO(){
        servizio = null;
        conn = null;
        rs = null;
    }

    public static ServizioDAO getInstance() {
        return instance;
    }


    @Override
    public int add(Servizio servizio) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "INSERT INTO servizio VALUES ('"+ servizio.getIdArticolo() + "','" + servizio.getIdFornitoreServizio() + "');";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Servizio servizio) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "UPDATE servizio SET idFornitoreServizio = '" + servizio.getIdFornitoreServizio() + "' WHERE idServizio = '" + servizio.getIdArticolo() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Servizio servizio) {
        int rowCount;
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "DELETE FROM servizio WHERE idServizio = '" + servizio.getIdArticolo() + "';";
        IDbOperation dbOperation = new WriteOperation(sql);
        rowCount = dbOperationExecutor.executeOperation(dbOperation).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public Servizio findByID(int idServizio) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM servizio INNER JOIN articolo ON servizio.idServizio = articolo.idArticolo WHERE servizio.idServizio = '" + idServizio + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        Servizio servizio;
        try {
            rs.next();
            AbstractFactory articleFactory = FactoryProvider.getFactory(FactoryProvider.FactoryType.ARTICOLO);
            servizio = (Servizio) articleFactory.crea("SERVIZIO");
            servizio.setIdArticolo(rs.getInt("idServizio"));
            servizio.setNome(rs.getString("nome"));
            servizio.setIdCategoria(rs.getInt("idCategoria"));
            servizio.setPrezzo(rs.getFloat("prezzo"));
            servizio.setDescrizione(rs.getString("descrizione"));
            servizio.setIdFornitoreServizio(rs.getInt("idFornitoreServizio"));
            Blob immagine = rs.getBlob("immagine");
            if (immagine != null) {
                byte[] byteFormat = immagine.getBytes(1, 1);
                File immagineFile = switch (byteFormat[0]) {
                    case -119 -> File.createTempFile("immagine" + servizio.getNome(), ".png");
                    case 73, 77 -> File.createTempFile("immagine" + servizio.getNome(), ".tif");
                    case 71 -> File.createTempFile("immagine" + servizio.getNome(), ".gif");
                    case -1 -> File.createTempFile("immagine" + servizio.getNome(), ".jpg");
                    default -> File.createTempFile("immagine" + servizio.getNome(), "");
                };
                InputStream inputStream = immagine.getBinaryStream();
                FileOutputStream outputStream = new FileOutputStream(immagineFile);
                while (inputStream.available() > 0) {
                    outputStream.write(inputStream.read());
                }
                outputStream.close();
                inputStream.close();
                servizio.setImmagine(immagineFile);
            }
            return servizio;
        } catch(SQLException e){
            // Gestisce le differenti categorie d'errore
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch(NullPointerException e){
            // Gestisce le differenti categorie d'errore
            System.out.println("Resultset: " + e.getMessage());
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            conn.close();
        }
        return null;
    }

    @Override
    public Servizio findByName(String nomeServizio){
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM servizio INNER JOIN articolo ON servizio.idServizio = articolo.idArticolo WHERE nome = '" + nomeServizio + "';";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        Servizio servizio;
        try {
            rs.next();
            AbstractFactory articleFactory = FactoryProvider.getFactory(FactoryProvider.FactoryType.ARTICOLO);
            servizio = (Servizio) articleFactory.crea("SERVIZIO");
            servizio.setIdArticolo(rs.getInt("idServizio"));
            servizio.setNome(rs.getString("nome"));
            servizio.setIdCategoria(rs.getInt("idCategoria"));
            servizio.setPrezzo(rs.getFloat("prezzo"));
            servizio.setDescrizione(rs.getString("descrizione"));
            servizio.setIdFornitoreServizio(rs.getInt("idFornitoreServizio"));
            Blob immagine = rs.getBlob("immagine");
            if (immagine != null) {
                byte[] byteFormat = immagine.getBytes(1, 1);
                File immagineFile = switch (byteFormat[0]) {
                    case -119 -> File.createTempFile("immagine" + servizio.getNome(), ".png");
                    case 73, 77 -> File.createTempFile("immagine" + servizio.getNome(), ".tif");
                    case 71 -> File.createTempFile("immagine" + servizio.getNome(), ".gif");
                    case -1 -> File.createTempFile("immagine" + servizio.getNome(), ".jpg");
                    default -> File.createTempFile("immagine" + servizio.getNome(), "");
                };
                InputStream inputStream = immagine.getBinaryStream();
                FileOutputStream outputStream = new FileOutputStream(immagineFile);
                while (inputStream.available() > 0) {
                    outputStream.write(inputStream.read());
                }
                outputStream.close();
                inputStream.close();
                servizio.setImmagine(immagineFile);
            }
            return servizio;
        } catch (SQLException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("Resultset: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<Servizio> findAll() {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM servizio INNER JOIN articolo ON servizio.idServizio = articolo.idArticolo;";
        IDbOperation dbOperation = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOperation).getResultSet();
        ArrayList<Servizio> servizi = new ArrayList<>();
        try {
            while(rs.next()) {
                AbstractFactory articleFactory = FactoryProvider.getFactory(FactoryProvider.FactoryType.ARTICOLO);
                servizio = (Servizio) articleFactory.crea("SERVIZIO");
                servizio.setIdArticolo(rs.getInt("idServizio"));
                servizio.setNome(rs.getString("nome"));
                servizio.setIdCategoria(rs.getInt("idCategoria"));
                servizio.setPrezzo(rs.getFloat("prezzo"));
                servizio.setDescrizione(rs.getString("descrizione"));
                servizio.setIdFornitoreServizio(rs.getInt("idFornitoreServizio"));
                Blob immagine = rs.getBlob("immagine");
                if (immagine != null) {
                    byte[] byteFormat = immagine.getBytes(1, 1);
                    File immagineFile = switch (byteFormat[0]) {
                        case -119 -> File.createTempFile("immagine" + servizio.getNome(), ".png");
                        case 73, 77 -> File.createTempFile("immagine" + servizio.getNome(), ".tif");
                        case 71 -> File.createTempFile("immagine" + servizio.getNome(), ".gif");
                        case -1 -> File.createTempFile("immagine" + servizio.getNome(), ".jpg");
                        default -> File.createTempFile("immagine" + servizio.getNome(), "");
                    };
                    InputStream inputStream = immagine.getBinaryStream();
                    FileOutputStream outputStream = new FileOutputStream(immagineFile);
                    while (inputStream.available() > 0) {
                        outputStream.write(inputStream.read());
                    }
                    outputStream.close();
                    inputStream.close();
                    servizio.setImmagine(immagineFile);
                }
                servizi.add(servizio);
            }
            return servizi;
        } catch (SQLException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("Resultset: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }


}
