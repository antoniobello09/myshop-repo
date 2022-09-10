package Test;

import DAO.Classi.ArticoloDAO;
import DAO.Classi.ServizioDAO;
import DAO.IUtenteDAO;
import DAO.Interfacce.IArticoloDAO;
import DAO.Interfacce.IServizioDAO;
import DAO.UtenteDAO;
import Model.Articolo;
import Model.Prodotto;
import Model.Servizio;
import Model.Utente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ServizioDAOTest {

    @Before
    public void setUp() throws Exception {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        articoloDAO.add(new Articolo("ServizioNome", "Una descrizione di prova", (float) 12.50, 26));
        servizioDAO.add(new Servizio(articoloDAO.findByName("ServizioNome").getIdArticolo(), 12));
    }

    @After
    public void tearDown() throws Exception {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        int idServizio = articoloDAO.findByName("ServizioNome").getIdArticolo();
        Servizio servizio = new Servizio();
        servizio.setIdArticolo(idServizio);
        servizioDAO.delete(servizio);
        articoloDAO.delete(servizio);
    }

    @Test
    public void updateTestOK() {
        int idServizio;
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        Servizio servizio = new Servizio("ServizioNome", "Una descrizione di prova", (float) 12.50, 26, 13);
        idServizio = servizioDAO.findByName("ServizioNome").getIdArticolo();
        servizio.setIdArticolo(idServizio);
        servizioDAO.update(servizio);
        Assert.assertEquals(13, servizioDAO.findByID(idServizio).getIdFornitoreServizio());
    }

    @Test
    public void updateTestNOK() {
        int idServizio;
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        Servizio servizio = new Servizio("ServizioNome", "Una descrizione di prova", (float) 12.50, 26, 13);
        idServizio = servizioDAO.findByName("ServizioNome").getIdArticolo();
        servizio.setIdArticolo(idServizio);
        servizioDAO.update(servizio);
        Assert.assertEquals(14, servizioDAO.findByID(idServizio).getIdFornitoreServizio());
    }

    @Test
    public void findByIdTestOK() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        Servizio servizio = servizioDAO.findByID(38);
        Assert.assertEquals(13, servizio.getIdFornitoreServizio());
    }

    @Test
    public void findByIdTestNOK() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        Servizio servizio = servizioDAO.findByID(38);
        Assert.assertEquals(14, servizio.getIdFornitoreServizio());
    }

    @Test
    public void findAllTestOK() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        ArrayList<Servizio> servizi = servizioDAO.findAll();
        Assert.assertEquals(3, servizi.size());
    }

    @Test
    public void findAllTestNOK() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        ArrayList<Servizio> servizi = servizioDAO.findAll();
        Assert.assertEquals(4, servizi.size());
    }

    @Test
    public void findByNameTestOK() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        Servizio servizio = servizioDAO.findByName("Trasporto Max");
        Assert.assertEquals(38, servizio.getIdArticolo());
    }

    @Test
    public void findByNameTestNOK() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        Servizio servizio = servizioDAO.findByName("Trasporto Max");
        Assert.assertEquals(37, servizio.getIdArticolo());
    }


    @Test
    public void deleteTestOK(){
        int rowCount;
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        Servizio servizio = servizioDAO.findByName("ServizioNome");
        rowCount = servizioDAO.delete(servizio);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void deleteTestNOK(){
        int rowCount;
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        Servizio servizio = servizioDAO.findByName("ServizioNome");
        rowCount = servizioDAO.delete(servizio);
        Assert.assertEquals(0, rowCount);
    }

}
