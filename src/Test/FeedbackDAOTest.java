package Test;

import DAO.Classi.*;
import DAO.IUtenteDAO;
import DAO.Interfacce.IAcquistoDAO;
import DAO.Interfacce.IFeedbackDAO;
import DAO.Interfacce.IManagerDAO;
import DAO.Interfacce.ISchedaProdottoDAO;
import DAO.UtenteDAO;
import Model.Acquisto;
import Model.FeedBack;
import Model.Manager;
import Model.SchedaProdotto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;

public class FeedbackDAOTest {

    private int idFeedback;

    @Before
    public void setUp() throws Exception {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        FeedBack feedback = new FeedBack(1, 22, "CommentoTest", 99, Date.valueOf("2022-08-20"));
        feedbackDAO.add(feedback);
        idFeedback = feedbackDAO.findByIDAcquisto_Articolo(1, 22).getIdFeedBack();
    }

    @After
    public void tearDown() throws Exception {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        FeedBack feedback = new FeedBack();
        feedback.setIdFeedBack(idFeedback);
        feedbackDAO.delete(feedback);
    }

    @Test
    public void findByIDTestOK() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        FeedBack feedback = feedbackDAO.findByID(idFeedback);
        Assert.assertEquals("CommentoTest", feedback.getCommento());
    }

    @Test
    public void findByIDTestNOK() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        FeedBack feedback = feedbackDAO.findByID(idFeedback);
        Assert.assertEquals(12, feedback.getIdFeedBack());
    }

    @Test
    public void findAllTestOK() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        ArrayList<FeedBack> feedback = feedbackDAO.findAll();
        Assert.assertEquals(4, feedback.size());
    }

    @Test
    public void findAllTestNOK() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        ArrayList<FeedBack> feedback = feedbackDAO.findAll();
        Assert.assertEquals(12, feedback.size());
    }

    @Test
    public void findByPuntoVenditaTestOK() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        ArrayList<FeedBack> feedback = feedbackDAO.findByPuntoVendita(AcquistoDAO.getInstance().findByID(4).getIdPuntoVendita());
        Assert.assertEquals(1, feedback.size());
    }

    @Test
    public void findByPuntoVenditaTestNOK() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        ArrayList<FeedBack> feedback = feedbackDAO.findByPuntoVendita(AcquistoDAO.getInstance().findByID(4).getIdPuntoVendita());
        Assert.assertEquals(12, feedback.size());
    }

    @Test
    public void findByIDAcquisto_ArticoloTestOK() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        FeedBack feedback = feedbackDAO.findByIDAcquisto_Articolo(1,22);
        Assert.assertEquals("CommentoTest", feedback.getCommento());
    }

    @Test
    public void findByIDAcquisto_ArticoloTestNOK() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        FeedBack feedback = feedbackDAO.findByIDAcquisto_Articolo(1,22);
        Assert.assertEquals("CommentoTest1", feedback.getCommento());
    }

}
