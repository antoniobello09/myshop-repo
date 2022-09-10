package Test;

import DAO.IUtenteDAO;
import DAO.UtenteDAO;
import Model.Utente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class UtenteDAOTest {

    private int idUtente;

    @Before
    public void setUp() throws Exception {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        utenteDAO.add(new Utente("UserTest", "PswTest", "NomeTest", "CognomeTest", "test@gmail.com", "2000-09-17", "3245551234", "Via Test 122", "Lecce", "Tester"));
        idUtente = utenteDAO.findByUsername("UserTest").getIdUtente();
    }

    @After
    public void tearDown() throws Exception {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = new Utente();
        u.setIdUtente(idUtente);
        utenteDAO.delete(u);
    }

    @Test
    public void findAllTestOK() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        ArrayList<Utente> utenti = utenteDAO.findAll();
        Assert.assertEquals(4, utenti.size());
    }

    @Test
    public void findAllTestNOK() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        ArrayList<Utente> utenti = utenteDAO.findAll();
        Assert.assertEquals(5, utenti.size());
    }

    @Test
    public void findByIdTestOK() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = utenteDAO.findByID(1);
        Assert.assertEquals("admin", utente.getUsername());
    }

    @Test
    public void findByIdTestNOK() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = utenteDAO.findByID(1);
        Assert.assertEquals("admin1", utente.getUsername());
    }

    @Test
    public void findByIdUsernameTestOK() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = utenteDAO.findByUsername("admin");
        Assert.assertEquals(1, utente.getIdUtente());
    }

    @Test
    public void findByIdUsernameTestNOK() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = utenteDAO.findByUsername("admin");
        Assert.assertEquals(2, utente.getIdUtente());
    }

    @Test
    public void updateTestOK() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = new Utente("UserTest", "PswTest", "NomeTest", "CognomeTest", "valentino@vr46.com", "2000-09-17", "3245551234", "Via Test 122", "Lecce", "Tester");
        utente.setIdUtente(utenteDAO.findByUsername("UserTest").getIdUtente());
        utenteDAO.update(utente);
        utente = utenteDAO.findByUsername("UserTest");
        Assert.assertEquals("valentino@vr46.com", utente.getEmail());
    }

    @Test
    public void updateTestNOK() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = new Utente("UserTest", "PswTest", "NomeTest", "CognomeTest", "valentino@vr46.com", "2000-09-17", "3245551234", "Via Test 122", "Lecce", "Tester");
        utente.setIdUtente(utenteDAO.findByUsername("UserTest").getIdUtente());
        utenteDAO.update(utente);
        utente = utenteDAO.findByUsername("UserTest");
        Assert.assertEquals("valentino@vr49.com", utente.getEmail());
    }

    @Test
    public void deleteTestOK(){
        int rowCount;
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = UtenteDAO.getInstance().findByUsername("UserTest");
        rowCount = utenteDAO.delete(utente);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void deleteTestNOK(){
        int rowCount;
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = new Utente();
        utente.setIdUtente(111111);
        rowCount = utenteDAO.delete(utente);
        Assert.assertEquals(1, rowCount);
    }


    @Test
    public void userExistsTestOK(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean response = utenteDAO.userExists("admin");
        Assert.assertEquals(true, response);
    }

    @Test
    public void userExistsTestNOK(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean response = utenteDAO.userExists("admin9");
        Assert.assertEquals(true, response);
    }


    @Test
    public void credentialsOkTestOK(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean response = utenteDAO.credentialsOk("admin", "12345");
        Assert.assertEquals(true, response);
    }

    @Test
    public void credentialsOkTestNOK(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean response = utenteDAO.credentialsOk("admin0", "12345");
        Assert.assertEquals(true, response);
    }

    @Test
    public void clientExistsTestOK(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean response = utenteDAO.clientExists(new Utente("cliente1"));
        Assert.assertEquals(true, response);
    }

    @Test
    public void clientExistsTestNOK(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean response = utenteDAO.clientExists(new Utente("cliete1"));
        Assert.assertEquals(true, response);
    }

    @Test
    public void managerExistsTestOK(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean response = utenteDAO.managerExists(new Utente("manager1"));
        Assert.assertEquals(true, response);
    }

    @Test
    public void managerExistsTestNOK(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean response = utenteDAO.managerExists(new Utente("managr1"));
        Assert.assertEquals(true, response);
    }

    @Test
    public void adminExistsTestOK(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean response = utenteDAO.administratorExists(new Utente("admin"));
        Assert.assertEquals(true, response);
    }

    @Test
    public void adminExistsTestNOK(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean response = utenteDAO.administratorExists(new Utente("adhmin"));
        Assert.assertEquals(true, response);
    }






}
