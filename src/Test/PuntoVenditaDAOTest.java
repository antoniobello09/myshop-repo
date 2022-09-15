package Test;

import DAO.Classi.*;
import DAO.Interfacce.*;
import Model.Fornitore;
import Model.Posizione;
import Model.Prodotto;
import Model.PuntoVendita;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class PuntoVenditaDAOTest {

    private int idPuntoVendita;
    private int idManager = 11;
    private String citta = "Alessano";
    private String indirizzo = "Via Macurano 100";

    @Before
    public void setUp() throws Exception {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        puntoVenditaDAO.add(idManager, citta, indirizzo);
        idPuntoVendita = puntoVenditaDAO.findByName(indirizzo, citta).getIdPuntoVendita();
    }

    @After
    public void tearDown() throws Exception {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        puntoVenditaDAO.delete(idPuntoVendita);
    }

    @Test
    public void updateTestOK(){
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        puntoVenditaDAO.update(idPuntoVendita, "Lecce", "Montesardo");
        PuntoVendita puntoVendita = puntoVenditaDAO.findByID(idPuntoVendita);
        Assert.assertEquals("Montesardo", puntoVendita.getIndirizzo());
    }

    @Test
    public void updateTestNOK(){
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        puntoVenditaDAO.update(idPuntoVendita, "Lecce", "Montesardo");
        PuntoVendita puntoVendita = puntoVenditaDAO.findByID(idPuntoVendita);
        Assert.assertEquals("Montesardo2", puntoVendita.getIndirizzo());
    }

    @Test
    public void deleteTestOK(){
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        int rowCount = puntoVenditaDAO.delete(idPuntoVendita);
        Assert.assertEquals(0, rowCount);
    }

    @Test
    public void deleteTestNOK(){
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        int rowCount = puntoVenditaDAO.delete(idPuntoVendita);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void findByIdTestOK() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = puntoVenditaDAO.findByID(idPuntoVendita);
        Assert.assertEquals(idPuntoVendita, puntoVendita.getIdPuntoVendita());
    }

    @Test
    public void findByIdTestNOK() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = puntoVenditaDAO.findByID(idPuntoVendita);
        Assert.assertEquals(5, puntoVendita.getIdPuntoVendita());
    }

    @Test
    public void findByNameOK() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = puntoVenditaDAO.findByName(indirizzo, citta);
        Assert.assertEquals("Alessano", puntoVendita.getCitta());
        Assert.assertEquals("Via Macurano 100", puntoVendita.getIndirizzo());
    }

    @Test
    public void findByNameNOK() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = puntoVenditaDAO.findByName(indirizzo, citta);
        Assert.assertEquals("Lecce", puntoVendita.getCitta());
        Assert.assertEquals("Viale Università 3", puntoVendita.getIndirizzo());
    }

    @Test
    public void findByManagerOK() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = puntoVenditaDAO.findByManager(idManager);
        Assert.assertEquals(11, puntoVendita.getIdManager());
    }

    @Test
    public void findByManagerNOK() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = puntoVenditaDAO.findByManager(idManager);
        Assert.assertEquals(10, puntoVendita.getIdManager());
    }


    @Test
    public void findAllTestOK() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        ArrayList<PuntoVendita> puntoVendita = puntoVenditaDAO.findAll();
        Assert.assertEquals(2, puntoVendita.size());
    }

    @Test
    public void findAllTestNOK() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        ArrayList<PuntoVendita> puntoVendita = puntoVenditaDAO.findAll();
        Assert.assertEquals(1, puntoVendita.size());
    }

}
