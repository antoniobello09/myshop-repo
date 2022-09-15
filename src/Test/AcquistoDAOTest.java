package Test;

import DAO.Classi.AcquistoDAO;
import DAO.IUtenteDAO;
import DAO.Interfacce.IAcquistoDAO;
import DAO.UtenteDAO;
import Model.Acquisto;
import Model.Utente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;

public class AcquistoDAOTest {

    private int idLista = 30;
    private int idPuntoVendita = 6;
    private int idAcquisto;


    @Before
    public void setUp() throws Exception {
        IAcquistoDAO acquistoDAO = AcquistoDAO.getInstance();
        Acquisto acquisto = new Acquisto();
        acquisto.setData(Date.valueOf("2022-09-15"));
        acquisto.setIdPuntoVendita(idPuntoVendita);
        acquisto.setIdLista(idLista);
        acquistoDAO.add(acquisto);
        idAcquisto = acquistoDAO.findByIDLista(idLista).getIdAcquisto();
    }

    @After
    public void tearDown() throws Exception {
        IAcquistoDAO acquistoDAO = AcquistoDAO.getInstance();
        Acquisto acquisto = new Acquisto();
        acquisto.setIdAcquisto(idAcquisto);
        acquistoDAO.delete(acquisto);
    }

    @Test
    public void deleteTestOK(){
        IAcquistoDAO acquistoDAO = AcquistoDAO.getInstance();
        Acquisto acquisto = new Acquisto();
        acquisto.setIdAcquisto(idAcquisto);
        int rowCount = acquistoDAO.delete(acquisto);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void deleteTestNOK(){
        IAcquistoDAO acquistoDAO = AcquistoDAO.getInstance();
        Acquisto acquisto = new Acquisto();
        acquisto.setIdAcquisto(idAcquisto);
        int rowCount = acquistoDAO.delete(acquisto);
        Assert.assertEquals(0, rowCount);
    }

    @Test
    public void findAllTestOK() {
        IAcquistoDAO acquistoDAO = AcquistoDAO.getInstance();
        ArrayList<Acquisto> acquisti = acquistoDAO.findAll();
        Assert.assertEquals(3, acquisti.size());
    }

    @Test
    public void findAllTestNOK() {
        IAcquistoDAO acquistoDAO = AcquistoDAO.getInstance();
        ArrayList<Acquisto> acquisti = acquistoDAO.findAll();
        Assert.assertEquals(1, acquisti.size());
    }

    @Test
    public void findByIdTestOK() {
        IAcquistoDAO acquistoDAO = AcquistoDAO.getInstance();
        Acquisto acquisto = acquistoDAO.findByID(idAcquisto);
        Assert.assertEquals(idAcquisto, acquisto.getIdAcquisto());
    }

    @Test
    public void findByIdTestNOK() {
        IAcquistoDAO acquistoDAO = AcquistoDAO.getInstance();
        Acquisto acquisto = acquistoDAO.findByID(idAcquisto);
        Assert.assertEquals(0, acquisto.getIdAcquisto());
    }

    @Test
    public void findByIdListaTestOK() {
        IAcquistoDAO acquistoDAO = AcquistoDAO.getInstance();
        Acquisto acquisto = acquistoDAO.findByIDLista(idLista);
        Assert.assertEquals(idLista, acquisto.getIdLista());
    }

    @Test
    public void findByIdListaTestNOK() {
        IAcquistoDAO acquistoDAO = AcquistoDAO.getInstance();
        Acquisto acquisto = acquistoDAO.findByIDLista(idLista);
        Assert.assertEquals(0, acquisto.getIdLista());
    }

}
