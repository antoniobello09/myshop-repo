package Test;

import DAO.Classi.ArticoloDAO;
import DAO.Classi.ProdottoCompositoDAO;
import DAO.Classi.ProdottoDAO;
import DAO.Interfacce.IArticoloDAO;
import DAO.Interfacce.IProdottoCompositoDAO;
import DAO.Interfacce.IProdottoDAO;
import Model.Prodotto;
import Model.ProdottoComposito;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ProdottoCompositoDAOTest {

    private int idProdottoComposito;
    private int idSottoProdotto;

    @Before
    public void setUp() throws Exception {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();

        Prodotto prodotto = new Prodotto("ProdottoCompositoNome", "Descrizione", (float) 67, 0, 0, 35);
        articoloDAO.add(prodotto);
        idProdottoComposito = articoloDAO.findByName("ProdottoCompositoNome").getIdArticolo();
        prodotto.setIdArticolo(idProdottoComposito);
        prodottoDAO.add(prodotto);
        Prodotto sottoProdotto = new Prodotto();
        sottoProdotto.setIdProdottoCompositoPadre(idProdottoComposito);
        sottoProdotto.setQuantita(3);
        idSottoProdotto = 22;
        sottoProdotto.setIdArticolo(idSottoProdotto);
        prodottoCompositoDAO.add(sottoProdotto);
    }

    @After
    public void tearDown() throws Exception {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ProdottoComposito prodottoComposito = new ProdottoComposito();
        prodottoComposito.setIdArticolo(idProdottoComposito);
        prodottoCompositoDAO.delete(prodottoComposito);
        prodottoDAO.delete(prodottoComposito);
        articoloDAO.delete(prodottoComposito);
    }

    @Test
    public void updateTestOK() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        Prodotto sottoProdotto = new Prodotto();
        sottoProdotto.setIdProdottoCompositoPadre(idProdottoComposito);
        sottoProdotto.setQuantita(5);
        sottoProdotto.setIdArticolo(idSottoProdotto);
        int rowCount = prodottoCompositoDAO.update(sottoProdotto);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void updateTestNOK() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        Prodotto sottoProdotto = new Prodotto();
        sottoProdotto.setIdProdottoCompositoPadre(idProdottoComposito);
        sottoProdotto.setQuantita(5);
        sottoProdotto.setIdArticolo(idSottoProdotto);
        int rowCount = prodottoCompositoDAO.update(sottoProdotto);
        Assert.assertEquals(0, rowCount);
    }

    @Test
    public void findByIdTestOK() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = prodottoCompositoDAO.findByID(idProdottoComposito);
        Assert.assertEquals(idProdottoComposito, prodottoComposito.getIdArticolo());
    }

    @Test
    public void findByIdTestNOK() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = prodottoCompositoDAO.findByID(idProdottoComposito);
        Assert.assertEquals(32, prodottoComposito.getIdCategoria());
    }

    @Test
    public void findAllTestOK() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ArrayList<ProdottoComposito> prodottiComposito = prodottoCompositoDAO.findAll();
        Assert.assertEquals(1, prodottiComposito.size());
    }

    @Test
    public void findAllTestNOK() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ArrayList<ProdottoComposito> prodottiComposito = prodottoCompositoDAO.findAll();
        Assert.assertEquals(4, prodottiComposito.size());
    }

    @Test
    public void findAllSonsTestOK() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ArrayList<Prodotto> prodotti = prodottoCompositoDAO.findSonsByID(35);
        Assert.assertEquals(2, prodotti.size());
    }

    @Test
    public void findAllSonsTestNOK() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ArrayList<Prodotto> prodotti = prodottoCompositoDAO.findSonsByID(35);
        Assert.assertEquals(1, prodotti.size());
    }

    @Test
    public void findByNameTestOK() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = prodottoCompositoDAO.findByName("ProdottoCompositoNome");
        Assert.assertEquals(idProdottoComposito, prodottoComposito.getIdArticolo());
    }

    @Test
    public void findByNameTestNOK() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = prodottoCompositoDAO.findByName("ProdottoCompositoNome");
        Assert.assertEquals(98, prodottoComposito.getIdArticolo());
    }


    @Test
    public void deleteTestOK(){
        int rowCount;
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodotto = new ProdottoComposito();
        prodotto.setIdArticolo(idProdottoComposito);
        rowCount = prodottoCompositoDAO.delete(prodotto);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void deleteTestNOK(){
        int rowCount;
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodotto = new ProdottoComposito();
        prodotto.setIdArticolo(idProdottoComposito);
        rowCount = prodottoCompositoDAO.delete(prodotto);
        Assert.assertEquals(0, rowCount);
    }

}
