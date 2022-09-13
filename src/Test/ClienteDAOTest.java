package Test;

import DAO.Classi.AmministratoreDAO;
import DAO.Classi.ClienteDAO;
import DAO.IUtenteDAO;
import DAO.Interfacce.IClienteDAO;
import DAO.UtenteDAO;
import Model.Amministratore;
import Model.Cliente;
import Model.Utente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ClienteDAOTest {

    private int idCliente;

    @Before
    public void setUp() throws Exception {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        Cliente cliente = new Cliente("ClienteTest", "PswTest", "NomeTest", "CognomeTest", "valentino@vr46.com", "2000-09-17", "3245551234", "Via Test 122", "Lecce", "Tester", "email", true, 6);
        utenteDAO.add(cliente);
        idCliente = utenteDAO.findByUsername("ClienteTest").getIdUtente();
        cliente.setIdUtente(idCliente);
        clienteDAO.add(cliente);
    }

    @After
    public void tearDown() throws Exception {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        Cliente cliente = new Cliente();
        cliente.setIdUtente(idCliente);
        utenteDAO.delete(cliente);
    }

    @Test
    public void findAllTestOK() {
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        ArrayList<Cliente> clienti = clienteDAO.findAll();
        Assert.assertEquals(2, clienti.size());
    }

    @Test
    public void findAllTestNOK() {
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        ArrayList<Cliente> clienti = clienteDAO.findAll();
        Assert.assertEquals(3, clienti.size());
    }

    @Test
    public void findByIdTestOK() {
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        Cliente cliente = clienteDAO.findByID(idCliente);
        Assert.assertEquals("ClienteTest", cliente.getUsername());
    }

    @Test
    public void findByIdTestNOK() {
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        Cliente cliente = clienteDAO.findByID(idCliente);
        Assert.assertEquals("ClienteTest2", cliente.getUsername());
    }

    @Test
    public void findByIdUsernameTestOK() {
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        Cliente cliente = clienteDAO.findByUsername("ClienteTest");
        Assert.assertEquals("ClienteTest", cliente.getUsername());
    }

    @Test
    public void findByIdUsernameTestNOK() {
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        Cliente cliente = clienteDAO.findByUsername("ClienteTest");
        Assert.assertEquals("ClienteTest2", cliente.getUsername());
    }

    @Test
    public void updateTestOK() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        Cliente cliente = new Cliente("ClienteTest", "PswTest", "NomeTest", "CognomeTest", "valentino@vr46.com", "2000-09-17", "3245551234", "Via Test 122", "Lecce", "Tester", "telefono", false, 6);
        cliente.setIdUtente(utenteDAO.findByUsername("ClienteTest").getIdUtente());
        clienteDAO.update(cliente);
        cliente = clienteDAO.findByUsername("ClienteTest");
        Assert.assertEquals("telefono", cliente.getCanalePreferito());
    }

    @Test
    public void updateTestNOK() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        Cliente cliente = new Cliente("ClienteTest", "PswTest", "NomeTest", "CognomeTest", "valentino@vr46.com", "2000-09-17", "3245551234", "Via Test 122", "Lecce", "Tester", "telefono", true, 6);
        cliente.setIdUtente(utenteDAO.findByUsername("ClienteTest").getIdUtente());
        clienteDAO.update(cliente);
        cliente = clienteDAO.findByUsername("ClienteTest");
        Assert.assertEquals(false, cliente.isAbilitato());
    }

    @Test
    public void deleteTestOK(){
        int rowCount;
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        Cliente cliente = ClienteDAO.getInstance().findByUsername("ClienteTest");
        rowCount = clienteDAO.delete(cliente);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void deleteTestNOK(){
        int rowCount;
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        Cliente cliente = ClienteDAO.getInstance().findByUsername("ClienteTest");
        rowCount = clienteDAO.delete(cliente);
        Assert.assertEquals(0, rowCount);
    }
}
