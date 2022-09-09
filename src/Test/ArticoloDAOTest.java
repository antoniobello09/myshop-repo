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

    @Before
    public void setUp() throws Exception {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        articoloDAO.add(new Articolo("ArticoloNome", "Una descrizione dello articolo", (float) 12.50, 33));
    }

    @After
    public void tearDown() throws Exception {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = new Articolo();
        articolo.setIdArticolo(articoloDAO.findByName("ArticoloNome").getIdArticolo());
        articoloDAO.delete(articolo);
    }

    @Test
    public void updateTest() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = new Articolo("ArticoloNome", "Una descrizione modificata", (float) 12.50, 33);
        articolo.setIdArticolo(articoloDAO.findByName("ArticoloNome").getIdArticolo());
        articoloDAO.update(articolo);
        articolo = articoloDAO.findByName("ArticoloNome");
        Assert.assertEquals("Una descrizione modificata", articolo.getDescrizione());
    }

    @Test
    public void findByIdTest() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = articoloDAO.findById(22);
        Assert.assertEquals("Tavolo", articolo.getNome());
    }

    @Test
    public void findAllTest() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        ArrayList<Articolo> utenti = articoloDAO.findAll();
        Assert.assertEquals(11, utenti.size());
    }

    @Test
    public void findByNameTest() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = articoloDAO.findByName("Tavolo");
        Assert.assertEquals(22, articolo.getIdArticolo());
    }

    @Test
    public void  isProdottoTest(){
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        boolean response = articoloDAO.isProdotto(new Articolo(22));
        Assert.assertEquals(true, response);
    }

    @Test
    public void  isServizioTest(){
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        boolean response = articoloDAO.isServizio(new Articolo(38));
        Assert.assertEquals(true, response);
    }

    @Test
    public void  isProdottoCompositoTest(){
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        boolean response = articoloDAO.isProdottoComposito(new Articolo(35));
        Assert.assertEquals(true, response);
    }







}
