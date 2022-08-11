package Test;
/*
import DAO.IUtenteDAO;
import DAO.UtenteDAO;
import DbInterface.DbUser;
import Model.Utente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UtenteDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        utenteDAO.add(new Utente("Valentino", "Rossi", "valentino@gmail.com", 41, "A"));
    }

    @After
    public void tearDown() throws Exception {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        utenteDAO.removeById("valentino@gmail.com");
    }

    @Test
    public void findByIdTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = utenteDAO.findById("valentino@gmail.com");
        Assert.assertEquals("Valentino", utente.getName());
    }

    @Test
    public void updateTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = new Utente("Valentino", "Rossi", "valentino@gmail.com", 42, "A");
        utenteDAO.update(utente);
        utente = utenteDAO.findById("valentino@gmail.com");
        Assert.assertEquals(42, utente.getAge());
    }
}
*/