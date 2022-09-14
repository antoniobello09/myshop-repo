package Test;

import DAO.Classi.AcquistoDAO;
import DAO.Classi.FeedbackDAO;
import DAO.Classi.ManagerDAO;
import DAO.Classi.SchedaProdottoDAO;
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
        FeedBack feedback = new FeedBack(10,1, 22, "CommentoTest", 99, Date.valueOf("2022-08-20"));
        feedbackDAO.add(feedback);
        idFeedback = feedbackDAO.findByID(idFeedback).getIdFeedBack();
        feedback.setIdFeedBack(idFeedback);
        feedbackDAO.add(feedback);
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
        Assert.assertEquals(10, feedback.getIdFeedBack());
    }

    @Test
    public void findByIDTestNOK() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        FeedBack feedback = feedbackDAO.findByID(idFeedback);
        Assert.assertEquals(11, feedback.getIdFeedBack());
    }
/*
    @Test
    public void findAllTestOK() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        ArrayList<FeedBack> feedback = feedbackDAO.findAll();
        Assert.assertEquals(1, feedback.size());
    }

    @Test
    public void findAllTestNOK() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        ArrayList<FeedBack> feedback = feedbackDAO.findAll();
        Assert.assertEquals(0, feedback.size());
    }
*/
}
