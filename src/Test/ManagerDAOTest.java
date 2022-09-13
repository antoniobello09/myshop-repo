package Test;

import DAO.Classi.ManagerDAO;
import DAO.IUtenteDAO;
import DAO.Interfacce.IManagerDAO;
import DAO.UtenteDAO;
import Model.Manager;
import Model.Utente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ManagerDAOTest {

    private int idManager;

    @Before
    public void setUp() throws Exception {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        Manager manager = new Manager("ManagerTest", "PswTest", "test@gmail.com");
        utenteDAO.add(manager);
        idManager = utenteDAO.findByUsername("ManagerTest").getIdUtente();
        manager.setIdUtente(idManager);
        managerDAO.add(manager);
    }

    @After
    public void tearDown() throws Exception {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        Manager manager = new Manager();
        manager.setIdUtente(idManager);
        managerDAO.delete(manager);
        utenteDAO.delete(manager);
    }

    @Test
    public void findAllTestOK() {
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        ArrayList<Manager> manager = managerDAO.findAll();
        Assert.assertEquals(4, manager.size());
    }

    @Test
    public void findAllTestNOK() {
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        ArrayList<Manager> manager = managerDAO.findAll();
        Assert.assertEquals(3, manager.size());
    }

    @Test
    public void findByIdTestOK() {
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        Manager manager = managerDAO.findByID(idManager);
        Assert.assertEquals("ManagerTest", manager.getUsername());
    }

    @Test
    public void findByIdTestNOK() {
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        Manager manager = managerDAO.findByID(idManager);
        Assert.assertEquals("ManagerTest2", manager.getUsername());
    }

    @Test
    public void findByUsernameTestOK() {
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        Manager manager = managerDAO.findByUsername("ManagerTest");
        Assert.assertEquals("PswTest", manager.getPassword());
    }

    @Test
    public void findByUsernameTestNOK() {
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        Manager manager = managerDAO.findByUsername("ManagerTest");
        Assert.assertEquals("PswTest2", manager.getPassword());
    }

    @Test
    public void updateTestOK() {
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        Manager manager = new Manager("ManagerTest", "PswTestModified", "test@gmail.com");
        manager.setIdUtente(idManager);
        managerDAO.update(manager);
        manager = managerDAO.findByUsername("ManagerTest");
        Assert.assertEquals("PswTestModified", manager.getPassword());
    }

    @Test
    public void updateTestNOK() {
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        Manager manager = new Manager("ManagerTest", "PswTestModified", "test@gmail.com");
        manager.setIdUtente(idManager);
        managerDAO.update(manager);
        manager = managerDAO.findByUsername("ManagerTest");
        Assert.assertEquals("PswTestModified2", manager.getPassword());
    }

    @Test
    public void deleteTestOK(){
        int rowCount;
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        Manager manager = new Manager();
        manager.setIdUtente(idManager);
        rowCount = managerDAO.delete(manager);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void deleteTestNOK(){
        int rowCount;
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        Manager manager = new Manager();
        manager.setIdUtente(idManager);
        rowCount = managerDAO.delete(manager);
        Assert.assertEquals(0, rowCount);
    }




}
