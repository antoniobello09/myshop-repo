package Test;

import DAO.IUtenteDAO;
import DAO.UtenteDAO;
import Model.Utente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class AcquistoDAOTest {

    @Before
    public void setUp() throws Exception {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        utenteDAO.add(new Utente("UserTest", "PswTest", "NomeTest", "CognomeTest", "test@gmail.com", "2000-09-17", "3245551234", "Via Test 122", "Lecce", "Tester"));
    }

    @After
    public void tearDown() throws Exception {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = new Utente();
        u.setIdUtente(utenteDAO.findByUsername("UserTest").getIdUtente());
        utenteDAO.delete(u);
    }

    @Test
    public void findAllTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        ArrayList<Utente> utenti = utenteDAO.findAll();
        Assert.assertEquals(4, utenti.size());
    }

    @Test
    public void findByIdTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = utenteDAO.findByID(1);
        Assert.assertEquals("admin", utente.getUsername());
    }

    @Test
    public void findByIdUsernameTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = utenteDAO.findByUsername("admin");
        Assert.assertEquals(1, utente.getIdUtente());
    }

    /*@Test
    public void removeByIdTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = new Utente();
        u.setIdUtente(utenteDAO.findByUsername("UserTest").getIdUtente());
        int rowCount = utenteDAO.delete(u);
        Assert.assertEquals(1, rowCount);
    }
*/
    @Test
    public void updateTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = new Utente("UserTest", "PswTest", "NomeTest", "CognomeTest", "valentino@vr46.com", "2000-09-17", "3245551234", "Via Test 122", "Lecce", "Tester");
        utente.setIdUtente(utenteDAO.findByUsername("UserTest").getIdUtente());
        utenteDAO.update(utente);
        Assert.assertEquals("valentino@vr46.com", utente.getEmail());
    }

    @Test
    public void userExistsTest(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean response = utenteDAO.userExists("admin");
        Assert.assertEquals(true, response);
    }

    @Test
    public void credentialsOkTest(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean response = utenteDAO.credentialsOk("admin", "12345");
        Assert.assertEquals(true, response);
    }

    @Test
    public void clientExistsTest(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean response = utenteDAO.clientExists(new Utente("cliente1"));
        Assert.assertEquals(true, response);
    }

    @Test
    public void managerExistsTest(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean response = utenteDAO.managerExists(new Utente("manager1"));
        Assert.assertEquals(true, response);
    }

    @Test
    public void adminExistsTest(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean response = utenteDAO.administratorExists(new Utente("admin"));
        Assert.assertEquals(true, response);
    }






}
