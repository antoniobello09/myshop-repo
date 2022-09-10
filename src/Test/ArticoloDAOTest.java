package Test;

import DAO.Classi.ArticoloDAO;
import DAO.IUtenteDAO;
import DAO.Interfacce.IArticoloDAO;
import DAO.UtenteDAO;
import Model.Articolo;
import Model.Prodotto;
import Model.Utente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ArticoloDAOTest {

    private int idArticolo;

    @Before
    public void setUp() throws Exception {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        articoloDAO.add(new Articolo("ArticoloNome", "Una descrizione dello articolo", (float) 12.50, 33));
        idArticolo = articoloDAO.findByName("ArticoloNome").getIdArticolo();
    }

    @After
    public void tearDown() throws Exception {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = new Articolo();
        articolo.setIdArticolo(idArticolo);
        articoloDAO.delete(articolo);
    }

    @Test
    public void updateTestOK() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = new Articolo("ArticoloNome", "Una descrizione modificata", (float) 12.50, 33);
        articolo.setIdArticolo(idArticolo);
        articoloDAO.update(articolo);
        articolo = articoloDAO.findById(idArticolo);
        Assert.assertEquals("Una descrizione modificata", articolo.getDescrizione());
    }

    @Test
    public void updateTestNOK() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = new Articolo("ArticoloNome", "Una descrizione modificata", (float) 12.50, 33);
        articolo.setIdArticolo(idArticolo);
        articoloDAO.update(articolo);
        articolo = articoloDAO.findById(idArticolo);
        Assert.assertEquals("Una descrizione modificataaa", articolo.getDescrizione());
    }

    @Test
    public void deleteTestOK(){
        int rowCount;
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = new Articolo();
        articolo.setIdArticolo(idArticolo);
        rowCount = articoloDAO.delete(articolo);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void deleteTestNOK(){
        int rowCount;
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = new Articolo();
        articolo.setIdArticolo(99999);
        rowCount = articoloDAO.delete(articolo);
        Assert.assertEquals(1, rowCount);

    }

    @Test
    public void findByIdTestOK() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = articoloDAO.findById(22);
        Assert.assertEquals("Tavolo", articolo.getNome());
    }

    @Test
    public void findByIdTestNOK() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = articoloDAO.findById(24);
        Assert.assertEquals("Tavolo", articolo.getNome());
    }

    @Test
    public void findAllTestOK() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        ArrayList<Articolo> articoli = articoloDAO.findAll();
        Assert.assertEquals(12, articoli.size());
    }

    @Test
    public void findAllTestNOK() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        ArrayList<Articolo> articoli = articoloDAO.findAll();
        Assert.assertEquals(1, articoli.size());
    }

    @Test
    public void findByNameTestOK() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = articoloDAO.findByName("Tavolo");
        Assert.assertEquals(22, articolo.getIdArticolo());
    }

    @Test
    public void findByNameTestNOK() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = articoloDAO.findByName("Comodino");
        Assert.assertEquals(22, articolo.getIdArticolo());
    }

    @Test
    public void  isProdottoTestOK(){
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        boolean response = articoloDAO.isProdotto(new Articolo(22));
        Assert.assertEquals(true, response);
    }

    @Test
    public void  isProdottoTestNOK(){
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        boolean response = articoloDAO.isProdotto(new Articolo(225));
        Assert.assertEquals(true, response);
    }

    @Test
    public void  isServizioTestOK(){
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        boolean response = articoloDAO.isServizio(new Articolo(38));
        Assert.assertEquals(true, response);
    }

    @Test
    public void  isServizioTestNOK(){
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        boolean response = articoloDAO.isServizio(new Articolo(3811));
        Assert.assertEquals(true, response);
    }

    @Test
    public void  isProdottoCompositoTestOK(){
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        boolean response = articoloDAO.isProdottoComposito(new Articolo(35));
        Assert.assertEquals(true, response);
    }

    @Test
    public void  isProdottoCompositoTestNOK(){
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        boolean response = articoloDAO.isProdottoComposito(new Articolo(1135));
        Assert.assertEquals(true, response);
    }





}
