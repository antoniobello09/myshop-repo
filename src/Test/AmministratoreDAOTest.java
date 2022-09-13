package Test;

import DAO.Classi.AmministratoreDAO;
import DAO.Classi.ManagerDAO;
import DAO.IUtenteDAO;
import DAO.Interfacce.IAmministratoreDAO;
import DAO.Interfacce.IManagerDAO;
import DAO.UtenteDAO;
import Model.Amministratore;
import Model.Manager;
import Model.Utente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class AmministratoreDAOTest {

    private int idAmministratore;

    @Before
    public void setUp() throws Exception {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        Amministratore amministratore = new Amministratore("AmministratoreTest", "PswTest", "test@gmail.com");
        utenteDAO.add(amministratore);
        idAmministratore = utenteDAO.findByUsername("AmministratoreTest").getIdUtente();
        amministratore.setIdUtente(idAmministratore);
        amministratoreDAO.add(amministratore);
    }

    @After
    public void tearDown() throws Exception {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        Amministratore amministratore = new Amministratore();
        amministratore.setIdUtente(idAmministratore);
        amministratoreDAO.delete(amministratore);
        utenteDAO.delete(amministratore);
    }

    @Test
    public void findAllTestOK() {
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        ArrayList<Amministratore> amministratori = amministratoreDAO.findAll();
        Assert.assertEquals(2, amministratori.size());
    }

    @Test
    public void findAllTestNOK() {
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        ArrayList<Amministratore> amministratori = amministratoreDAO.findAll();
        Assert.assertEquals(4, amministratori.size());
    }

    @Test
    public void findByIdTestOK() {
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        Amministratore amministratore = amministratoreDAO.findByID(idAmministratore);
        Assert.assertEquals("AmministratoreTest", amministratore.getUsername());
    }

    @Test
    public void findByIdTestNOK() {
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        Amministratore amministratore = amministratoreDAO.findByID(idAmministratore);
        Assert.assertEquals("AmministratoreTest3", amministratore.getUsername());
    }

    @Test
    public void findByIdUsernameTestOK() {
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        Amministratore amministratore = amministratoreDAO.findByUsername("AmministratoreTest");
        Assert.assertEquals("PswTest", amministratore.getPassword());
    }

    @Test
    public void findByIdUsernameTestNOK() {
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        Amministratore amministratore = amministratoreDAO.findByUsername("AmministratoreTest");
        Assert.assertEquals("PswTest3", amministratore.getPassword());
    }

    @Test
    public void updateTestOK() {
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        Amministratore amministratore = new Amministratore("AmministratoreTest", "PswTestModified", "test@gmail.com");
        amministratore.setIdUtente(idAmministratore);
        amministratoreDAO.update(amministratore);
        amministratore = amministratoreDAO.findByUsername("AmministratoreTest");
        Assert.assertEquals("PswTestModified", amministratore.getPassword());
    }

    @Test
    public void updateTestNOK() {
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        Amministratore amministratore = new Amministratore("AmministratoreTest", "PswTestModified", "test@gmail.com");
        amministratore.setIdUtente(idAmministratore);
        amministratoreDAO.update(amministratore);
        amministratore = amministratoreDAO.findByUsername("AmministratoreTest");
        Assert.assertEquals("PswTes4tModified", amministratore.getPassword());
    }

    @Test
    public void deleteTestOK(){
        int rowCount;
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        Amministratore amministratore = new Amministratore();
        amministratore.setIdUtente(idAmministratore);
        rowCount = amministratoreDAO.delete(amministratore);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void deleteTestNOK(){
        int rowCount;
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        Amministratore amministratore = new Amministratore();
        amministratore.setIdUtente(9943985);
        rowCount = amministratoreDAO.delete(amministratore);
        Assert.assertEquals(1, rowCount);
    }

}
