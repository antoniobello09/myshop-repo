package Test;

import DAO.Classi.FornitoreDAO;
import DAO.Classi.PosizioneDAO;
import DAO.Interfacce.IFornitoreDAO;
import DAO.Interfacce.IPosizioneDAO;
import Model.Fornitore;
import Model.Posizione;


import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;

public class PosizioneDAOTest {

    private int idPosizione = 24;
    private int piano = 1;
    private int corsia = 1;
    private int scaffale = 2;

    @Test
    public void findByIdTestOK() {
        IPosizioneDAO posizioneDAO = PosizioneDAO.getInstance();
        Posizione posizione = posizioneDAO.findByID(idPosizione);
        Assert.assertEquals(24, posizione.getIdPosizione());
    }

    @Test
    public void findByIdTestNOK() {
        IPosizioneDAO posizioneDAO = PosizioneDAO.getInstance();
        Posizione posizione = posizioneDAO.findByID(idPosizione);
        Assert.assertEquals(1, posizione.getIdPosizione());
    }

    @Test
    public void findByNumbersOK() {
        IPosizioneDAO posizioneDAO = PosizioneDAO.getInstance();
        Posizione posizione = posizioneDAO.findByNumbers(piano, corsia, scaffale);
        Assert.assertEquals(1, posizione.getPiano());
        Assert.assertEquals(1, posizione.getCorsia());
        Assert.assertEquals(2, posizione.getScaffale());
    }

    @Test
    public void findByNumbersNOK() {
        IPosizioneDAO posizioneDAO = PosizioneDAO.getInstance();
        Posizione posizione = posizioneDAO.findByNumbers(piano, corsia, scaffale);
        Assert.assertEquals(3, posizione.getPiano());
        Assert.assertEquals(4, posizione.getCorsia());
        Assert.assertEquals(5, posizione.getScaffale());
    }

    @Test
    public void findAllTestOK() {
        IPosizioneDAO posizioneDAO = PosizioneDAO.getInstance();
        ArrayList<Posizione> posizione = posizioneDAO.findAll();
        Assert.assertEquals(14, posizione.size());
    }

    @Test
    public void findAllTestNOK() {
        IPosizioneDAO posizioneDAO = PosizioneDAO.getInstance();
        ArrayList<Posizione> posizione = posizioneDAO.findAll();
        Assert.assertEquals(15, posizione.size());
    }

}
