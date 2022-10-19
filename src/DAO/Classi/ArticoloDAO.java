package DAO.Classi;

import DAO.Interfacce.IArticoloDAO;
import DbInterface.*;
import DbInterface.Command.*;
import Model.Articolo;
import Model.Fornitore;

import java.io.*;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticoloDAO implements IArticoloDAO {

    private static ArticoloDAO instance = new ArticoloDAO();
    private Articolo articolo;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ArticoloDAO() {
        articolo = null;
        conn = null;
        rs = null;
    }

    public static ArticoloDAO getInstance() {
        return instance;
    }

    @Override
    public int add(Articolo articolo){

        //File che contiene l'immagine
        File immagineFile = articolo.getImmagine();
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        //INSERT con inserimento di una stringa nella colonna 'immagine' per creare il puntatore
        String sql = "INSERT INTO articolo(idCategoria, prezzo, nome, descrizione, immagine) VALUES ('"+ articolo.getIdCategoria() + "','" + articolo.getPrezzo() + "','" + articolo.getNome() + "','" + articolo.getDescrizione() + "', 'a');";
        IDbOperation dbOp = new WriteOperation(sql);
        int rowCount = dbOperationExecutor.executeOperation(dbOp).getRowsAffected();
        //Estrazione del Blob con il puntatore
        sql = "SELECT * FROM articolo WHERE articolo.nome = '" + articolo.getNome() + "';";
        dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp).getResultSet();
        try {
            rs.next();
            Blob immagine = rs.getBlob("immagine");// Blob vuoto + puntatore
            OutputStream outputStream = immagine.setBinaryStream(1);
            FileInputStream inputStream = new FileInputStream(immagineFile);
            while(inputStream.available()>0){
                outputStream.write(inputStream.read());
            }
            outputStream.close();
            inputStream.close();
            //Blob pieno + puntatore
            String psql = "UPDATE articolo SET immagine = ? WHERE nome = '" + articolo.getNome() + "';";    //Update tramite Prepared Statement
            dbOp = new SavePhotoOperation(immagine, psql);
            dbOperationExecutor.executeOperation(dbOp);
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException | IOException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }

        return rowCount;
    }

    @Override
    public int update(Articolo articolo) {
        File immagineFile = articolo.getImmagine();
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "UPDATE articolo SET nome = '"+ articolo.getNome() + "', idCategoria = '" + articolo.getIdCategoria() + "', prezzo = '" + articolo.getPrezzo() + "', descrizione = '" + articolo.getDescrizione() + "' WHERE idArticolo = '" + articolo.getIdArticolo() + "';";
        IDbOperation dbOp = new WriteOperation(sql);
        int rowCount = dbOperationExecutor.executeOperation(dbOp).getRowsAffected();
        //Estrazione del Blob con il puntatore
        sql = "SELECT * FROM articolo WHERE articolo.nome = '" + articolo.getNome() + "';";
        dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp).getResultSet();
        try {
            rs.next();
            Blob immagine = rs.getBlob("immagine");// Blob vuoto + puntatore
            OutputStream outputStream = immagine.setBinaryStream(1);
            FileInputStream inputStream = new FileInputStream(immagineFile);
            while (inputStream.available() > 0) {
                outputStream.write(inputStream.read());
            }
            outputStream.close();
            inputStream.close();
            //Blob pieno + puntatore
            String psql = "UPDATE articolo SET immagine = ? WHERE nome = '" + articolo.getNome() + "';";    //Update tramite Prepared Statement
            dbOp = new SavePhotoOperation(immagine, psql);
            dbOperationExecutor.executeOperation(dbOp);
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }finally {
            conn.close();
        }
        return rowCount;
    }

    @Override
    public int delete(Articolo articolo) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "DELETE FROM articolo WHERE idArticolo = '" + articolo.getIdArticolo() + "';";
        IDbOperation dbOp = new WriteOperation(sql);
        int rowCount = dbOperationExecutor.executeOperation(dbOp).getRowsAffected();
        conn.close();
        return rowCount;
    }

    @Override
    public Articolo findById(int idArticolo) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM articolo WHERE articolo.idArticolo = '" + idArticolo + "';";
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp).getResultSet();
        try {
            rs.next();
            if (rs.getRow()==1) {
                articolo = new Articolo();
                articolo.setIdArticolo(rs.getInt("idArticolo"));
                articolo.setNome(rs.getString("nome"));
                articolo.setDescrizione(rs.getString("descrizione"));
                articolo.setPrezzo(rs.getFloat("prezzo"));
                articolo.setIdCategoria(rs.getInt("idCategoria"));
                Blob immagine = rs.getBlob("immagine");
                if (immagine!=null) {
                    byte[] byteFormat = immagine.getBytes(1, 1);
                    File immagineFile = switch (byteFormat[0]) {
                        case -119 -> File.createTempFile("immagine" + articolo.getNome(), ".png");
                        case 73, 77 -> File.createTempFile("immagine" + articolo.getNome(), ".tif");
                        case 71 -> File.createTempFile("immagine" + articolo.getNome(), ".gif");
                        case -1 -> File.createTempFile("immagine" + articolo.getNome(), ".jpg");
                        default -> File.createTempFile("immagine" + articolo.getNome(),"");
                    };
                    InputStream inputStream = immagine.getBinaryStream();
                    FileOutputStream outputStream = new FileOutputStream(immagineFile);
                    while (inputStream.available() > 0) {
                        outputStream.write(inputStream.read());
                    }
                    outputStream.close();
                    inputStream.close();
                    articolo.setImmagine(immagineFile);
                }
                return articolo;
            }
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<Articolo> findAll() {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM articolo;";
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp).getResultSet();
        ArrayList<Articolo> articoli = new ArrayList<>();
        try {
            while (rs.next()) {
                articolo = new Articolo();
                articolo.setIdArticolo(rs.getInt("idArticolo"));
                articolo.setNome(rs.getString("nome"));
                articolo.setDescrizione(rs.getString("descrizione"));
                articolo.setPrezzo(rs.getFloat("prezzo"));
                articolo.setIdCategoria(rs.getInt("idCategoria"));
                Blob immagine = rs.getBlob("immagine");
                if (immagine!=null) {
                    byte[] byteFormat = immagine.getBytes(1, 1);
                    File immagineFile = switch (byteFormat[0]) {
                        case -119 -> File.createTempFile("immagine" + articolo.getNome(), ".png");
                        case 73, 77 -> File.createTempFile("immagine" + articolo.getNome(), ".tif");
                        case 71 -> File.createTempFile("immagine" + articolo.getNome(), ".gif");
                        case -1 -> File.createTempFile("immagine" + articolo.getNome(), ".jpg");
                        default -> File.createTempFile("immagine" + articolo.getNome(),"");
                    };
                    InputStream inputStream = immagine.getBinaryStream();
                    FileOutputStream outputStream = new FileOutputStream(immagineFile);
                    while (inputStream.available() > 0) {
                        outputStream.write(inputStream.read());
                    }
                    outputStream.close();
                    inputStream.close();
                    articolo.setImmagine(immagineFile);
                }
                articoli.add(articolo);
            }
            return articoli;
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
    public Articolo findByName(String nomeArticolo) {
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM articolo WHERE articolo.nome = '" + nomeArticolo + "';";
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp).getResultSet();
        Articolo articolo = new Articolo();
        try {
            rs.next();
            articolo.setIdArticolo(rs.getInt("idArticolo"));
            articolo.setNome(rs.getString("nome"));
            articolo.setPrezzo(rs.getFloat("prezzo"));
            articolo.setDescrizione(rs.getString("descrizione"));
            articolo.setIdCategoria(rs.getInt("idCategoria"));
            Blob immagine = rs.getBlob("immagine");
            if (immagine!=null) {
                byte[] byteFormat = immagine.getBytes(1, 1);
                File immagineFile = switch (byteFormat[0]) {
                    case -119 -> File.createTempFile("immagine" + articolo.getNome(), ".png");
                    case 73, 77 -> File.createTempFile("immagine" + articolo.getNome(), ".tif");
                    case 71 -> File.createTempFile("immagine" + articolo.getNome(), ".gif");
                    case -1 -> File.createTempFile("immagine" + articolo.getNome(), ".jpg");
                    default -> File.createTempFile("immagine" + articolo.getNome(),"");
                };
                InputStream inputStream = immagine.getBinaryStream();
                FileOutputStream outputStream = new FileOutputStream(immagineFile);
                while (inputStream.available() > 0) {
                    outputStream.write(inputStream.read());
                }
                outputStream.close();
                inputStream.close();
                articolo.setImmagine(immagineFile);
            }
            return articolo;
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
    public boolean isServizio(Articolo articolo){
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM articolo a INNER JOIN servizio s ON a.idArticolo = s.idServizio  WHERE a.idArticolo = '" + articolo.getIdArticolo() + "';";
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp).getResultSet();
        try {
            rs.next();
            if(rs.getRow()==1)  return true;
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
        return false;
    }

    @Override
    public boolean isProdotto(Articolo articolo){
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM articolo a INNER JOIN prodotto p ON a.idArticolo = p.idProdotto  WHERE a.idArticolo = '" + articolo.getIdArticolo() + "';";
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp).getResultSet();
        try {
            rs.next();
            if(rs.getRow()==1)  return true;
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
        return false;
    }

    @Override
    public boolean isProdottoComposito(Articolo articolo){
        conn = DbConnection.getInstance();
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM articolo a INNER JOIN prodottocomposito p ON a.idArticolo = p.idProdottoComposito  WHERE a.idArticolo = '" + articolo.getIdArticolo() + "';";
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp).getResultSet();
        try {
            rs.next();
            if(rs.getRow()==1)  return true;
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
        return false;
    }

}