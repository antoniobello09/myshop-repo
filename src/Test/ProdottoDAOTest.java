package Test;

import DAO.Classi.ArticoloDAO;
import DAO.Classi.ProdottoDAO;
import DAO.Classi.ServizioDAO;
import DAO.Interfacce.IArticoloDAO;
import DAO.Interfacce.IProdottoDAO;
import DAO.Interfacce.IServizioDAO;
import Model.Articolo;
import Model.Prodotto;
import Model.Servizio;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ProdottoDAOTest {

    private int idProdotto;

    @Before
    public void setUp() throws Exception {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        Prodotto prodotto = new Prodotto("ProdottoNome", "Una descrizione di prova", (float) 12.50, 26, 8, 34);
        articoloDAO.add(prodotto);
        idProdotto = articoloDAO.findByName("ProdottoNome").getIdArticolo();
        prodotto.setIdArticolo(idProdotto);
        prodottoDAO.add(prodotto);
    }

    @After
    public void tearDown() throws Exception {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        Prodotto prodotto = new Prodotto();
        prodotto.setIdArticolo(idProdotto);
        prodottoDAO.delete(prodotto);
        articoloDAO.delete(prodotto);
    }

    @Test
    public void updateTestOK() {
        int idProdotto;
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = new Prodotto("ProdottoNome", "Una descrizione di prova4", (float) 12.50, 26, 8, 34);
        idProdotto = prodottoDAO.findByName("ProdottoNome").getIdArticolo();
        prodotto.setIdArticolo(idProdotto);
        prodottoDAO.update(prodotto);
        prodotto = ProdottoDAO.getInstance().findByID(idProdotto);
        Assert.assertEquals("Una descrizione di prova4", prodotto.getDescrizione());
    }

    @Test
    public void updateTestNOK() {
        int idProdotto;
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = new Prodotto("ProdottoNome", "Una descrizione di prova", (float) 12.50, 26, 8, 34);
        idProdotto = prodottoDAO.findByName("ProdottoNome").getIdArticolo();
        prodotto.setIdArticolo(idProdotto);
        prodottoDAO.update(prodotto);
        prodotto = ProdottoDAO.getInstance().findByID(idProdotto);
        Assert.assertEquals("Una descrizione di prova4", prodotto.getDescrizione());
    }

    @Test
    public void findByIdTestOK() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = prodottoDAO.findByID(idProdotto);
        Assert.assertEquals(26, prodotto.getIdCategoria());
    }

    @Test
    public void findByIdTestNOK() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = prodottoDAO.findByID(idProdotto);
        Assert.assertEquals(25, prodotto.getIdCategoria());
    }

    @Test
    public void findAllTestOK() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ArrayList<Prodotto> prodotti = prodottoDAO.findAll();
        Assert.assertEquals(7, prodotti.size());
    }

    @Test
    public void findAllTestNOK() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ArrayList<Prodotto> prodotti = prodottoDAO.findAll();
        Assert.assertEquals(6, prodotti.size());
    }

    @Test
    public void findByNameTestOK() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = prodottoDAO.findByName("ProdottoNome");
        Assert.assertEquals(26, prodotto.getIdCategoria());
    }

    @Test
    public void findByNameTestNOK() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = prodottoDAO.findByName("ProdottoNome");
        Assert.assertEquals(25, prodotto.getIdCategoria());
    }


    @Test
    public void deleteTestOK(){
        int rowCount;
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = new Prodotto();
        prodotto.setIdArticolo(idProdotto);
        rowCount = prodottoDAO.delete(prodotto);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void deleteTestNOK(){
        int rowCount;
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = new Prodotto();
        prodotto.setIdArticolo(idProdotto);
        rowCount = prodottoDAO.delete(prodotto);
        Assert.assertEquals(0, rowCount);
    }

}
