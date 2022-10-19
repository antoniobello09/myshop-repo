package Test;


import DAO.Classi.ListaDAO;
import DAO.Interfacce.IListaDAO;
import Model.Lista;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ListaDAOTest {

    private int idLista;

    @Before
    public void setUp() throws Exception {
        IListaDAO listaDAO = ListaDAO.getInstance();
        Lista lista = new Lista( "NomeTest", 16);
        listaDAO.add(lista);
        idLista = listaDAO.findByName("NomeTest").getIdLista();
    }

    @After
    public void tearDown() throws Exception {
        IListaDAO listaDAO = ListaDAO.getInstance();
        Lista lista = new Lista();
        lista.setIdLista(idLista);
        listaDAO.delete(lista);
    }

    @Test
    public void updateTestOK(){
        IListaDAO listaDAO = ListaDAO.getInstance();
        Lista lista = new Lista( idLista, "NomeTest2", 16);
        int rowCount = listaDAO.update(lista);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void updateTestNOK(){
        IListaDAO listaDAO = ListaDAO.getInstance();
        Lista lista = new Lista( idLista, "NomeTest2", 16);
        int rowCount = listaDAO.update(lista);
        Assert.assertEquals(0, rowCount);
    }

    @Test
    public void deleteTestOK(){
        IListaDAO listaDAO = ListaDAO.getInstance();
        Lista lista = new Lista();
        lista.setIdLista(idLista);
        int rowCount = listaDAO.delete(lista);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void deleteTestNOK(){
        IListaDAO listaDAO = ListaDAO.getInstance();
        Lista lista = new Lista();
        lista.setIdLista(idLista);
        int rowCount = listaDAO.delete(lista);
        Assert.assertEquals(0, rowCount);
    }

    @Test
    public void findByIDTestOK() {
        IListaDAO listaDAO = ListaDAO.getInstance();
        Lista lista = listaDAO.findByID(idLista);
        Assert.assertEquals("NomeTest", lista.getNome());
    }

    @Test
    public void findByIDTestNOK() {
        IListaDAO listaDAO = ListaDAO.getInstance();
        Lista lista = listaDAO.findByID(idLista);
        Assert.assertEquals("NomeTest2", lista.getNome());
    }

    @Test
    public void findAllTestOK() {
        IListaDAO listaDAO = ListaDAO.getInstance();
        ArrayList<Lista> lista = listaDAO.findAll();
        Assert.assertEquals(11, lista.size());
    }

    @Test
    public void findAllTestNOK() {
        IListaDAO listaDAO = ListaDAO.getInstance();
        ArrayList<Lista> lista = listaDAO.findAll();
        Assert.assertEquals(1, lista.size());
    }

    @Test
    public void findAllIdClienteTestOK() {
        IListaDAO listaDAO = ListaDAO.getInstance();
        ArrayList<Lista> lista = listaDAO.findAll();
        Assert.assertEquals(11, lista.size());
    }

    @Test
    public void findAllIdClienteTestNOK() {
        IListaDAO listaDAO = ListaDAO.getInstance();
        ArrayList<Lista> lista = listaDAO.findAll(16);
        Assert.assertEquals(1, lista.size());
    }

}
