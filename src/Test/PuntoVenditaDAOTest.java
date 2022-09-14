package Test;

import DAO.Classi.FornitoreDAO;
import DAO.Classi.PosizioneDAO;
import DAO.Classi.PuntoVenditaDAO;
import DAO.Interfacce.IFornitoreDAO;
import DAO.Interfacce.IPosizioneDAO;
import DAO.Interfacce.IPuntoVenditaDAO;
import Model.Fornitore;
import Model.Posizione;
import Model.PuntoVendita;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class PuntoVenditaDAOTest {

    private int idPuntoVendita = 6;
    private int idManager = 11;
    private String citta = "Alessano";
    private String indirizzo = "Via Macurano 100";

    @Test
    public void findByIdTestOK() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = puntoVenditaDAO.findByID(idPuntoVendita);
        Assert.assertEquals(6, puntoVendita.getIdPuntoVendita());
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
        Assert.assertEquals(1, puntoVendita.size());
    }

    @Test
    public void findAllTestNOK() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        ArrayList<PuntoVendita> puntoVendita = puntoVenditaDAO.findAll();
        Assert.assertEquals(2, puntoVendita.size());
    }

}
