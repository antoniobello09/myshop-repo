package Test;

import DAO.Classi.AcquistoDAO;
import DAO.Classi.PrenotazioneDAO;
import DAO.Interfacce.IPrenotazioneDAO;
import Model.Prenotazione;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;

public class PrenotazioneDAOTest {

    private int idPrenotazione;
    private int idProdotto = 22;
    private int idAcquisto = 4;

    @Before
    public void setUp() throws Exception {
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        Prenotazione prenotazione = new Prenotazione(idProdotto,3, idAcquisto);
        prenotazioneDAO.add(prenotazione);
        idPrenotazione = prenotazioneDAO.findByProdottoAcquisto(idProdotto, idAcquisto).getIdPrenotazione();
    }

    @After
    public void tearDown() throws Exception {
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setIdPrenotazione(idPrenotazione);
        prenotazioneDAO.delete(prenotazione);
    }

    @Test
    public void updateTestOK(){
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        Prenotazione prenotazione = prenotazioneDAO.findByProdottoAcquisto(idProdotto,idAcquisto);
        prenotazione.setQuantita_prenotata(8);
        prenotazioneDAO.update(prenotazione);
        prenotazione = PrenotazioneDAO.getInstance().findByProdottoAcquisto(idProdotto, idAcquisto);
        Assert.assertEquals(8, prenotazione.getQuantita_prenotata());
    }

    @Test
    public void updateTestNOK(){
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        Prenotazione prenotazione = prenotazioneDAO.findByProdottoAcquisto(idProdotto,idAcquisto);
        prenotazione.setQuantita_prenotata(8);
        prenotazioneDAO.update(prenotazione);
        prenotazione = PrenotazioneDAO.getInstance().findByProdottoAcquisto(idProdotto, idAcquisto);
        Assert.assertEquals(9, prenotazione.getQuantita_prenotata());
    }

    @Test
    public void deleteTestOK(){
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        Prenotazione prenotazione = prenotazioneDAO.findByProdottoAcquisto(idProdotto,idAcquisto);
        int rowCount = prenotazioneDAO.delete(prenotazione);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void deleteTestNOK(){
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        Prenotazione prenotazione = prenotazioneDAO.findByProdottoAcquisto(idProdotto,idAcquisto);
        int rowCount = prenotazioneDAO.delete(prenotazione);
        Assert.assertEquals(0, rowCount);
    }

    @Test
    public void findByProdottoAcquistoTestOK() {
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        Prenotazione prenotazione = PrenotazioneDAO.getInstance().findByProdottoAcquisto(idProdotto,idAcquisto);
        Assert.assertEquals(3, prenotazione.getQuantita_prenotata());
    }

    @Test
    public void findByProdottoAcquistoTestNOK() {
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        Prenotazione prenotazione = PrenotazioneDAO.getInstance().findByProdottoAcquisto(idProdotto,idAcquisto);
        Assert.assertEquals(4, prenotazione.getQuantita_prenotata());
    }

    @Test
    public void findAllByProductTestOK() {
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        ArrayList<Prenotazione> prenotazioni = prenotazioneDAO.findAllByProduct(idProdotto);
        Assert.assertEquals(1, prenotazioni.size());
    }

    @Test
    public void findAllByProductTestNOK() {
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        ArrayList<Prenotazione> prenotazioni = prenotazioneDAO.findAllByProduct(idProdotto);
        Assert.assertEquals(13, prenotazioni.size());
    }

}
