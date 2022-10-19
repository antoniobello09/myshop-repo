package DAO.Classi;

import DAO.Interfacce.IProdottoDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Articolo;
import Model.Prodotto;
import Model.Fornitore;

import java.io.*;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoDAO implements IProdottoDAO {

    private static ProdottoDAO instance = new ProdottoDAO();
    private Prodotto prodotto;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ProdottoDAO(){
        prodotto = null;
        conn = null;
        rs = null;
    }

    public static ProdottoDAO getInstance() {
        return instance;
    }

    @Override
    public int add(Prodotto prodotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO prodotto(idProdotto,idProduttore,idPosizione) VALUES ('" +  prodotto.getIdArticolo() + "','" + prodotto.getIdProduttore() +"','" + prodotto.getIdPosizione() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Prodotto prodotto) {
        ArticoloDAO.getInstance().update(prodotto);
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE prodotto SET idProduttore = '" + prodotto.getIdProduttore() + "', idPosizione = '" + prodotto.getIdPosizione() + "' WHERE idProdotto = '" + prodotto.getIdArticolo() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Prodotto prodotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM prodotto WHERE idProdotto = '" + prodotto.getIdArticolo() + "';");
        ArticoloDAO.getInstance().delete(prodotto);
        conn.close();
        return rowCount;
    }


    @Override
    public Prodotto findByID(int idProdotto) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM prodotto INNER JOIN articolo ON prodotto.idProdotto = articolo.idArticolo WHERE prodotto.idProdotto = '" + idProdotto + "';");
        Prodotto prodotto;
        try {
            rs.next();
            prodotto = new Prodotto();
            prodotto.setIdArticolo(rs.getInt("idProdotto"));
            prodotto.setNome(rs.getString("nome"));
            prodotto.setIdCategoria(rs.getInt("idCategoria"));
            prodotto.setPrezzo(rs.getFloat("prezzo"));
            prodotto.setDescrizione(rs.getString("descrizione"));
            prodotto.setIdProduttore(rs.getInt("idProduttore"));
            prodotto.setIdPosizione(rs.getInt("idPosizione"));
            Blob immagine = rs.getBlob("immagine");

            if (immagine!=null) {
                byte[] byteFormat = immagine.getBytes(1, 1);
                File immagineFile = switch (byteFormat[0]) {
                    case -119 -> File.createTempFile("immagine" + prodotto.getNome(), ".png");
                    case 73, 77 -> File.createTempFile("immagine" + prodotto.getNome(), ".tif");
                    case 71 -> File.createTempFile("immagine" + prodotto.getNome(), ".gif");
                    case -1 -> File.createTempFile("immagine" + prodotto.getNome(), ".jpg");
                    default -> File.createTempFile("immagine" + prodotto.getNome(),"");
                };
                InputStream inputStream = immagine.getBinaryStream();
                FileOutputStream outputStream = new FileOutputStream(immagineFile);
                while (inputStream.available() > 0) {
                    outputStream.write(inputStream.read());
                }
                outputStream.close();
                inputStream.close();
                immagineFile.deleteOnExit();
                prodotto.setImmagine(immagineFile);
            }
            return prodotto;
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
    public Prodotto findByName(String nomeProdotto) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM prodotto INNER JOIN articolo ON prodotto.idProdotto = articolo.idArticolo WHERE articolo.nome = '" + nomeProdotto + "';");
        Prodotto prodotto = new Prodotto();
        try {
            rs.next();
            prodotto.setIdArticolo(rs.getInt("idProdotto"));
            prodotto.setNome(rs.getString("nome"));
            prodotto.setIdCategoria(rs.getInt("idCategoria"));
            prodotto.setPrezzo(rs.getFloat("prezzo"));
            prodotto.setDescrizione(rs.getString("descrizione"));
            prodotto.setIdProduttore(rs.getInt("idProduttore"));
            prodotto.setIdPosizione(rs.getInt("idPosizione"));
            Blob immagine = rs.getBlob("immagine");

            if (immagine!=null) {
                byte[] byteFormat = immagine.getBytes(1, 1);
                File immagineFile = switch (byteFormat[0]) {
                    case -119 -> File.createTempFile("immagine" + prodotto.getNome(), ".png");
                    case 73, 77 -> File.createTempFile("immagine" + prodotto.getNome(), ".tif");
                    case 71 -> File.createTempFile("immagine" + prodotto.getNome(), ".gif");
                    case -1 -> File.createTempFile("immagine" + prodotto.getNome(), ".jpg");
                    default -> File.createTempFile("immagine" + prodotto.getNome(),"");
                };
                InputStream inputStream = immagine.getBinaryStream();
                FileOutputStream outputStream = new FileOutputStream(immagineFile);
                while (inputStream.available() > 0) {
                    outputStream.write(inputStream.read());
                }
                outputStream.close();
                inputStream.close();
                prodotto.setImmagine(immagineFile);
            }
            return prodotto;
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
    public ArrayList<Prodotto> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM articolo INNER JOIN prodotto WHERE idArticolo = idProdotto;");
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        try {
            while (rs.next()) {
                prodotto = new Prodotto();
                prodotto.setIdArticolo(rs.getInt("idProdotto"));
                prodotto.setNome(rs.getString("nome"));
                prodotto.setIdCategoria(rs.getInt("idCategoria"));
                prodotto.setPrezzo(rs.getFloat("prezzo"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setIdProduttore(rs.getInt("idProduttore"));
                prodotto.setIdPosizione(rs.getInt("idPosizione"));
                Blob immagine = rs.getBlob("immagine");

                if (immagine!=null) {
                    byte[] byteFormat = immagine.getBytes(1, 1);
                    File immagineFile = switch (byteFormat[0]) {
                        case -119 -> File.createTempFile("immagine" + prodotto.getNome(), ".png");
                        case 73, 77 -> File.createTempFile("immagine" + prodotto.getNome(), ".tif");
                        case 71 -> File.createTempFile("immagine" + prodotto.getNome(), ".gif");
                        case -1 -> File.createTempFile("immagine" + prodotto.getNome(), ".jpg");
                        default -> File.createTempFile("immagine" + prodotto.getNome(),"");
                    };
                    InputStream inputStream = immagine.getBinaryStream();
                    FileOutputStream outputStream = new FileOutputStream(immagineFile);
                    while (inputStream.available() > 0) {
                        outputStream.write(inputStream.read());
                    }
                    outputStream.close();
                    inputStream.close();
                    prodotto.setImmagine(immagineFile);
                }
                prodotti.add(prodotto);
            }
            return prodotti;
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
