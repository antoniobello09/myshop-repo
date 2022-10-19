package Test;

import DAO.Classi.FornitoreDAO;
import DAO.Interfacce.IFornitoreDAO;
import Model.Fornitore;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class FornitoreDAOTest {

    private int idFornitore;

    @Before
    public void setUp() throws Exception {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = new Fornitore("FornitoreTest", "SitoWebTest", "citta", "nazione", "tipo");
        fornitoreDAO.add(fornitore);
        idFornitore = fornitoreDAO.findByName("FornitoreTest").getIdFornitore();
        fornitore.setIdFornitore(idFornitore);
    }

    @After
    public void tearDown() throws Exception {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = new Fornitore();
        fornitore.setIdFornitore(idFornitore);
        fornitoreDAO.delete(fornitore);
    }

    @Test
    public void findAllTestOK() {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        ArrayList<Fornitore> fornitore = fornitoreDAO.findAll();
        Assert.assertEquals(6, fornitore.size());
    }

    @Test
    public void findAllTestNOK() {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        ArrayList<Fornitore> fornitore = fornitoreDAO.findAll();
        Assert.assertEquals(5, fornitore.size());
    }

    @Test
    public void findByIdTestOK() {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = fornitoreDAO.findByID(idFornitore);
        Assert.assertEquals("FornitoreTest", fornitore.getNome());
    }

    @Test
    public void findByIdTestNOK() {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = fornitoreDAO.findByID(idFornitore);
        Assert.assertEquals("FornitoreTest2", fornitore.getNome());
    }

    @Test
    public void findByUsernameTestOK() {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = fornitoreDAO.findByName("FornitoreTest");
        Assert.assertEquals("SitoWebTest", fornitore.getSitoweb());
    }

    @Test
    public void findByUsernameTestNOK() {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = fornitoreDAO.findByName("FornitoreTest");
        Assert.assertEquals("SitoWebTest2", fornitore.getSitoweb());
    }


    @Test
    public void updateTestOK() {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = new Fornitore("FornitoreTest", "SitowebTestModified", "citta", "nazione", "tipo");
        fornitore.setIdFornitore(idFornitore);
        fornitoreDAO.update(fornitore);
        fornitore = fornitoreDAO.findByName("FornitoreTest");
        Assert.assertEquals("SitowebTestModified", fornitore.getSitoweb());
    }


    @Test
    public void updateTestNOK() {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = new Fornitore("FornitoreTest", "SitowebTestModified", "citta", "nazione", "tipo");
        fornitore.setIdFornitore(idFornitore);
        fornitoreDAO.update(fornitore);
        fornitore = fornitoreDAO.findByName("FornitoreTest");
        Assert.assertEquals("SitowebTestModified2", fornitore.getSitoweb());
    }

    @Test
    public void deleteTestOK(){
        int rowCount;
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = new Fornitore();
        fornitore.setIdFornitore(idFornitore);
        rowCount = fornitoreDAO.delete(fornitore);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void deleteTestNOK(){
        int rowCount;
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = new Fornitore();
        fornitore.setIdFornitore(idFornitore);
        rowCount = fornitoreDAO.delete(fornitore);
        Assert.assertEquals(0, rowCount);
    }

    @Test
    public void findAllProdTestOK(){
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        ArrayList<Fornitore> fornitore = fornitoreDAO.findAllProd();
        Assert.assertEquals(3, fornitore.size());
    }

    @Test
    public void findAllProdTestNOK(){
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        ArrayList<Fornitore> fornitore = fornitoreDAO.findAllProd();
        Assert.assertEquals(2, fornitore.size());
    }

    @Test
    public void findAllServTestOK(){
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        ArrayList<Fornitore> fornitore = fornitoreDAO.findAllServ();
        Assert.assertEquals(2, fornitore.size());
    }

    @Test
    public void findAllServTestNOK(){
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        ArrayList<Fornitore> fornitore = fornitoreDAO.findAllServ();
        Assert.assertEquals(1, fornitore.size());
    }

}
