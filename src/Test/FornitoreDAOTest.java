package Test;

import DAO.Classi.FornitoreDAO;
import DAO.Interfacce.IFornitoreDAO;
import Model.Fornitore;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class FornitoreDAOTest {

    private int idFornitore;

    @Before
    public void setUp() throws Exception {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = new Fornitore("FornitoreTest", "SitoWeb", "citta", "nazione", "tipo");
        fornitoreDAO.add(fornitore);
        idFornitore = fornitoreDAO.findByName("FornitoreTest").getIdFornitore();
        fornitore.setIdFornitore(idFornitore);
        fornitoreDAO.add(fornitore);
    }

    @After
    public void tearDown() throws Exception {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = new Fornitore();
        fornitore.setIdFornitore(idFornitore);
        fornitoreDAO.delete(fornitore);
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

}
