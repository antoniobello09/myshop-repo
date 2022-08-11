package DAO.Classi;

import DAO.Interfacce.IProdottoCompositoDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.ProdottoComposito;
import Model.Prodotto_Quantita;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoCompositoDAO implements IProdottoCompositoDAO {

    private static ProdottoCompositoDAO instance = new ProdottoCompositoDAO();
    private ProdottoComposito prodottoComposito;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ProdottoCompositoDAO(){
        prodottoComposito = null;
        conn = null;
        rs = null;
    }

    public static ProdottoCompositoDAO getInstance() {
        return instance;
    }

    @Override
    public int add(ProdottoComposito prodottoC) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO articolo(idCategoria,prezzo,nome,descrizione) VALUES ('"+ CategoriaProdottoDAO.getInstance().findByName(prodottoC.getCategoria().getSottocategorie().get(0).getNome(),1).getIdCategoria() + "','" + prodottoC.getPrezzo() + "','" + prodottoC.getNome() + "','" + prodottoC.getDescrizione() + "');");
        conn.executeUpdate("INSERT INTO prodotto(idProdotto,idProduttore) VALUES ('"+  ArticoloDAO.getInstance().findByName(prodottoC.getNome(),1).getId() + "','" + ProduttoreDAO.getInstance().findByName(prodottoC.getProduttore().getNome(),1).getIdProduttore() +"');");
        for(int i=0;i<prodottoC.getSottoprodotti().size();i++){
            conn.executeUpdate("INSERT INTO prodottocomposito(idProdottoComposito, idProdotto, quantita) VALUES ('" + ProdottoDAO.getInstance().findByName(prodottoC.getNome(),1).getId() + "','" + ProdottoDAO.getInstance().findByName(prodottoC.getSottoprodotti().get(i).getNome(),1).getId() + "','" + prodottoC.getSottoprodotti().get(i).getQuantita() + "');");
        }
        conn.close();
        return rowCount;
    }

    @Override
    public int update(ProdottoComposito prodottoC) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE articolo SET idCategoria = '"+ CategoriaProdottoDAO.getInstance().findByName(prodottoC.getCategoria().getSottocategorie().get(0).getNome(),1).getIdCategoria() + "', prezzo = '" + prodottoC.getPrezzo() + "', nome = '" + prodottoC.getNome() + "', immagine = '" + prodottoC.getImmagine() + "', descrizione = '" + prodottoC.getDescrizione() + "' WHERE idArticolo = '" + prodottoC.getId() +"';");
        conn.executeUpdate("DELETE FROM prodottocomposito WHERE idProdottoComposito = '" + prodottoC.getId() + "'");
        for(int i=0; i<prodottoC.getSottoprodotti().size();i++){
            conn.executeUpdate("INSERT INTO prodottocomposito VALUES('" + prodottoC.getId() + "','" + ProdottoDAO.getInstance().findByName(prodottoC.getSottoprodotti().get(i).getNome(),1).getId() + "','" + prodottoC.getSottoprodotti().get(i).getQuantita() + "');");
        }
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(ProdottoComposito prodottoC) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM prodottocomposito WHERE idProdottoComposito = '" + prodottoC.getId() + "';");
        conn.executeUpdate("DELETE FROM prodotto WHERE idProdotto = '" + prodottoC.getId() + "';");
        conn.executeUpdate("DELETE FROM articolo WHERE idArticolo = '" + prodottoC.getId() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public ProdottoComposito findByID(String idProdottoC) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("" +
        "SELECT * FROM articolo AS A INNER JOIN prodotto AS P ON A.idArticolo = P.idProdotto WHERE A.idArticolo = '" + idProdottoC + "';");
        ProdottoComposito prodottoC;
        try {
            rs.next();
            prodottoComposito = new ProdottoComposito();
            prodottoComposito.setId(rs.getInt("idProdotto"));
            prodottoComposito.setCategoria(CategoriaProdottoDAO.getInstance().findTopCategoria(CategoriaProdottoDAO.getInstance().findByID(rs.getInt("idCategoria")).getNome()));
            prodottoComposito.setPrezzo(rs.getFloat("prezzo"));
            prodottoComposito.setNome(rs.getString("nome"));
            prodottoComposito.setImmagine(rs.getBlob("immagine"));
            prodottoComposito.setProduttore(ProduttoreDAO.getInstance().findByID(rs.getInt("idProduttore")));
            rs = conn.executeQuery("SELECT * FROM prodottocomposito WHERE idProdottoComposito = '" + idProdottoC + "';");
            while (rs.next()){
                prodottoComposito.add(new Prodotto_Quantita(ProdottoDAO.getInstance().findByID(rs.getInt("idProdotto")),rs.getInt("quantita")));
            }
            return prodottoComposito;
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
    public ProdottoComposito findByName(String nomeProdottoC) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("" +
                "SELECT * FROM articolo AS A INNER JOIN prodotto AS P ON A.idArticolo = P.idProdotto WHERE A.nome = '" + nomeProdottoC + "';");
        ProdottoComposito prodottoC;
        try {
            rs.next();
            prodottoComposito = new ProdottoComposito();
            prodottoComposito.setId(rs.getInt("idProdotto"));
            prodottoComposito.setCategoria(CategoriaProdottoDAO.getInstance().findTopCategoria(CategoriaProdottoDAO.getInstance().findByID(rs.getInt("idCategoria")).getNome()));
            prodottoComposito.setPrezzo(rs.getFloat("prezzo"));
            prodottoComposito.setNome(rs.getString("nome"));
            prodottoComposito.setImmagine(rs.getBlob("immagine"));
            prodottoComposito.setProduttore(ProduttoreDAO.getInstance().findByID(rs.getInt("idProduttore")));
            rs = conn.executeQuery("SELECT * FROM prodottocomposito WHERE idProdottoComposito = '" + ProdottoDAO.getInstance().findByName(nomeProdottoC).getId() + "';");
            while (rs.next()){
                prodottoComposito.add(new Prodotto_Quantita(ProdottoDAO.getInstance().findByID(rs.getInt("idProdotto")),rs.getInt("quantita")));
            }
            return prodottoComposito;
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
    public ArrayList<ProdottoComposito> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT DISTINCT idProdottoComposito, idCategoria, prezzo, nome, immagine, descrizione, idProduttore FROM articolo A INNER JOIN prodotto P ON A.idArticolo = P.idProdotto INNER JOIN prodottocomposito PC ON P.idProdotto = PC.idProdottoComposito;");
        ArrayList<ProdottoComposito> prodottiCompositi = new ArrayList<>();
        try {
            while(rs.next()) {
                prodottoComposito = new ProdottoComposito();
                prodottoComposito.setId(rs.getInt("idProdottoComposito"));
                prodottoComposito.setCategoria(CategoriaProdottoDAO.getInstance().findTopCategoria(CategoriaProdottoDAO.getInstance().findByID(rs.getInt("idCategoria"),1).getNome()));
                prodottoComposito.setPrezzo(rs.getFloat("prezzo"));
                prodottoComposito.setNome(rs.getString("nome"));
                prodottoComposito.setDescrizione(rs.getString("descrizione"));
                prodottoComposito.setImmagine(rs.getBlob("immagine"));
                prodottoComposito.setProduttore(ProduttoreDAO.getInstance().findByID(rs.getInt("idProduttore"),1));
                ResultSet rs2 = conn.executeQuery("SELECT * FROM articolo A INNER JOIN prodottocomposito PC ON A.idArticolo = PC.idProdotto WHERE idProdottoComposito = '" + rs.getInt("idProdottoComposito") + "';");
                while (rs2.next()){
                    prodottoComposito.add(new Prodotto_Quantita(ProdottoDAO.getInstance().findByID(rs2.getInt("idProdotto")),rs2.getInt("quantita")));
                }
                prodottiCompositi.add(prodottoComposito);
            }
            return prodottiCompositi;
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
