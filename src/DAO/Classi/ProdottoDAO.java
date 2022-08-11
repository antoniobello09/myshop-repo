package DAO.Classi;

import DAO.Interfacce.IProdottoDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Prodotto;
import Model.Produttore;

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
        int rowCount = conn.executeUpdate("INSERT INTO articolo(idCategoria,prezzo,nome,descrizione) VALUES ('"+ CategoriaProdottoDAO.getInstance().findByName(prodotto.getCategoria().getSottocategorie().get(0).getNome(),1).getIdCategoria() + "','" + prodotto.getPrezzo() + "','" + prodotto.getNome() + "','" + prodotto.getDescrizione() + "');");System.out.println(ArticoloDAO.getInstance().findByName(prodotto.getNome(),1).getId() + "," + ProduttoreDAO.getInstance().findByName(prodotto.getProduttore().getNome(),1).getIdProduttore());
        conn.executeUpdate("INSERT INTO prodotto(idProdotto,idProduttore) VALUES ('"+  ArticoloDAO.getInstance().findByName(prodotto.getNome(),1).getId() + "','" + ProduttoreDAO.getInstance().findByName(prodotto.getProduttore().getNome(),1).getIdProduttore() +"');");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Prodotto prodotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE articolo SET idCategoria = '"+ CategoriaProdottoDAO.getInstance().findByName(prodotto.getCategoria().getSottocategorie().get(0).getNome(),1).getIdCategoria() + "', prezzo = '" + prodotto.getPrezzo() + "', nome = '" + prodotto.getNome() + "', immagine = '" + prodotto.getImmagine() + "', descrizione = '" + prodotto.getDescrizione() + " WHERE idArticolo = '" + prodotto.getId() + "';");
        conn.executeUpdate("UPDATE prodotto SET idProduttore = '" + ProduttoreDAO.getInstance().findByName(prodotto.getProduttore().getNome(),1).getIdProduttore() + "' WHERE idProdotto = '" + prodotto.getId() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int delete(Prodotto prodotto) {
        conn = DbConnection.getInstance();
        conn.executeUpdate("DELETE FROM prodottocomposito WHERE idProdotto = '" + prodotto.getId() + "' OR idProdottoComposito = '" + prodotto.getId() + "';");
        conn.executeUpdate("DELETE FROM prodotto WHERE idProdotto = '" + prodotto.getId() + "';");
        conn.executeUpdate("DELETE FROM commento WHERE idArticolo = '" + prodotto.getId() + "';");
        conn.executeUpdate("DELETE FROM lista_has_articolo WHERE idArticolo = '" + prodotto.getId() + "';");
        int rowCount = conn.executeUpdate("DELETE FROM articolo WHERE idArticolo = '"+ prodotto.getId() + "';");
        conn.close();
        return rowCount;
    }


    @Override
    public Prodotto findByID(int idProdotto){
        return findByID(idProdotto, 0);
    }

    public Prodotto findByID(int idProdotto, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM prodotto INNER JOIN articolo ON prodotto.idProdotto = articolo.idArticolo WHERE prodotto.idProdotto = '" + idProdotto + "';");
        Prodotto prodotto;
        try {
            rs.next();
            prodotto = new Prodotto();
            prodotto.setId(rs.getInt("idProdotto"));
            prodotto.setNome(rs.getString("nome"));
            prodotto.setCategoria(CategoriaProdottoDAO.getInstance().findTopCategoria(CategoriaProdottoDAO.getInstance().findByID(rs.getInt("idCategoria"),1).getNome()));
            prodotto.setPrezzo(rs.getFloat("prezzo"));
            prodotto.setImmagine(rs.getBlob("immagine"));
            prodotto.setDescrizione(rs.getString("descrizione"));
            prodotto.setProduttore(ProduttoreDAO.getInstance().findByID(rs.getInt("idProduttore"),1));
            return prodotto;
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
    public  Prodotto findByName(String nomeProdotto){
        return findByName(nomeProdotto, 0);
    }

    public Prodotto findByName(String nomeProdotto, int closeConn) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM prodotto INNER JOIN articolo ON prodotto.idProdotto = articolo.idArticolo WHERE articolo.nome = '" + nomeProdotto + "';");
        Prodotto prodotto = new Prodotto();
        try {
            rs.next();
            prodotto.setId(rs.getInt("idArticolo"));
            prodotto.setNome(rs.getString("nome"));
            prodotto.setCategoria(CategoriaProdottoDAO.getInstance().findTopCategoria(CategoriaProdottoDAO.getInstance().findByID(rs.getInt("idCategoria"),1).getNome()));
            prodotto.setPrezzo(rs.getFloat("prezzo"));
            prodotto.setImmagine(rs.getBlob("immagine"));
            prodotto.setDescrizione(rs.getString("descrizione"));
            prodotto.setProduttore(ProduttoreDAO.getInstance().findByID(rs.getInt("idProduttore"),1));
            return prodotto;
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
    public ArrayList<Prodotto> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM articolo INNER JOIN prodotto WHERE idArticolo = idProdotto;");
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        try {
            while (rs.next()) {
                prodotto = new Prodotto();
                prodotto.setId(rs.getInt("idArticolo"));
                prodotto.setCategoria(CategoriaProdottoDAO.getInstance().findTopCategoria(CategoriaProdottoDAO.getInstance().findByID(rs.getInt("idCategoria")).getNome()));
                prodotto.setPrezzo(rs.getFloat("prezzo"));
                prodotto.setNome(rs.getString("nome"));
                prodotto.setImmagine(rs.getBlob("immagine"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setProduttore(findProduttore(rs.getInt("idProduttore")));
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
        } finally {
            conn.close();
        }
        return null;
    }


    @Override
    public ArrayList<Prodotto> findAllProducts() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM articolo A INNER JOIN prodotto P ON A.idArticolo = P.idProdotto LEFT JOIN prodottocomposito PC ON P.idProdotto = PC.idProdottoComposito WHERE PC.idProdottoComposito IS NULL;");
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        try {
            while (rs.next()) {
                prodotto = new Prodotto();
                prodotto.setId(rs.getInt("idArticolo"));
                prodotto.setCategoria(CategoriaProdottoDAO.getInstance().findTopCategoria(CategoriaProdottoDAO.getInstance().findByID(rs.getInt("idCategoria")).getNome()));
                prodotto.setPrezzo(rs.getFloat("prezzo"));
                prodotto.setNome(rs.getString("nome"));
                prodotto.setImmagine(rs.getBlob("immagine"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setProduttore(findProduttore(rs.getInt("idProduttore")));
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
        } finally {
            conn.close();
        }
        return null;
    }

    @Override
    public Produttore findProduttore(int idProduttore){
        ProduttoreDAO pDAO = ProduttoreDAO.getInstance();
        return pDAO.findByID(idProduttore);
    }

    @Override
    public ArrayList<Prodotto> findByShop(int idPuntoVendita) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT * FROM magazzino M INNER JOIN puntovendita PV ON M.idMagazzino = PV.idMagazzino INNER JOIN scheda_prodotto SC ON M.idMagazzino = SC.idMagazzino INNER JOIN articolo A ON SC.idProdotto = A.idArticolo INNER JOIN prodotto P ON A.idArticolo = P.idProdotto LEFT JOIN prodottocomposito PC ON P.idProdotto = PC.idProdottoComposito WHERE PC.idProdottoComposito IS NULL;");
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        try {
            while (rs.next()) {
                prodotto = new Prodotto();
                prodotto.setId(rs.getInt("idArticolo"));
                prodotto.setCategoria(CategoriaProdottoDAO.getInstance().findTopCategoria(CategoriaProdottoDAO.getInstance().findByID(rs.getInt("idCategoria")).getNome()));
                prodotto.setPrezzo(rs.getFloat("prezzo"));
                prodotto.setNome(rs.getString("nome"));
                prodotto.setImmagine(rs.getBlob("immagine"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setProduttore(findProduttore(rs.getInt("idProduttore")));
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
        } finally {
            conn.close();
        }
        return null;
    }

}
