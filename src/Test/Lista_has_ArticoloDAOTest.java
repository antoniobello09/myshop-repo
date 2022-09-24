package Test;


import DAO.Classi.CategoriaDAO;
import DAO.Classi.ListaDAO;
import DAO.Classi.Lista_has_ArticoloDAO;
import DAO.Interfacce.ICategoriaDAO;
import DAO.Interfacce.IListaDAO;
import DAO.Interfacce.ILista_has_ArticoloDAO;
import Model.Categoria;
import Model.Lista;
import Model.Lista_has_Articolo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class Lista_has_ArticoloDAOTest {

    private int idLista = 30;
    private int idArticolo = 22;

    @Before
    public void setUp() throws Exception {
        ILista_has_ArticoloDAO lista_has_articoloDAO = Lista_has_ArticoloDAO.getInstance();
        Lista_has_Articolo lista_has_articolo = new Lista_has_Articolo( 30, 22, 3);
        lista_has_articoloDAO.add(30,22,3);
    }

    @After
    public void tearDown() throws Exception {
        ILista_has_ArticoloDAO lista_has_articoloDAO = Lista_has_ArticoloDAO.getInstance();
        lista_has_articoloDAO.delete(30,22);
    }

    @Test
    public void updateTestOK() {
        ILista_has_ArticoloDAO lista_has_ArticoloDAO = Lista_has_ArticoloDAO.getInstance();
        lista_has_ArticoloDAO.update(30,22,4);
        Lista_has_Articolo lista_has_articolo = lista_has_ArticoloDAO.findByID(idLista, idArticolo);
        Assert.assertEquals(4, lista_has_articolo.getQuantita());
    }

    @Test
    public void updateTestNOK() {
        ILista_has_ArticoloDAO lista_has_ArticoloDAO = Lista_has_ArticoloDAO.getInstance();
        lista_has_ArticoloDAO.update(30,22,4);
        Lista_has_Articolo lista_has_articolo = lista_has_ArticoloDAO.findByID(idLista, idArticolo);
        Assert.assertEquals(3, lista_has_articolo.getQuantita());
    }

    @Test
    public void findByIDTestOK() {
        ILista_has_ArticoloDAO lista_has_articoloDAO = Lista_has_ArticoloDAO.getInstance();
        Lista_has_Articolo lista_has_articolo = lista_has_articoloDAO.findByID(30, 22);
        Assert.assertEquals(30, lista_has_articolo.getIdLista());
    }

    @Test
    public void findByIDTestNOK() {
        ILista_has_ArticoloDAO lista_has_articoloDAO = Lista_has_ArticoloDAO.getInstance();
        Lista_has_Articolo lista_has_articolo = lista_has_articoloDAO.findByID(30, 22);
        Assert.assertEquals(9, lista_has_articolo.getIdLista());
    }

    @Test
    public void findAllTestOK() {
        ILista_has_ArticoloDAO lista_has_articoloDAO = Lista_has_ArticoloDAO.getInstance();
        ArrayList<Lista_has_Articolo> lista_has_articolo = lista_has_articoloDAO.findAll();
        Assert.assertEquals(3, lista_has_articolo.size());
    }

    @Test
    public void findAllTestNOK() {
        ILista_has_ArticoloDAO lista_has_articoloDAO = Lista_has_ArticoloDAO.getInstance();
        ArrayList<Lista_has_Articolo> lista_has_articolo = lista_has_articoloDAO.findAll();
        Assert.assertEquals(2, lista_has_articolo.size());
    }

    @Test
    public void findAllListArticlesTestOK() {
        ILista_has_ArticoloDAO lista_has_articoloDAO = Lista_has_ArticoloDAO.getInstance();
        ArrayList<Lista_has_Articolo> lista_has_articolo = lista_has_articoloDAO.findAllListArticles(30);
        Assert.assertEquals(1, lista_has_articolo.size());
    }

    @Test
    public void findAllIdClienteTestNOK() {
        ILista_has_ArticoloDAO lista_has_articoloDAO = Lista_has_ArticoloDAO.getInstance();
        ArrayList<Lista_has_Articolo> lista_has_articolo = lista_has_articoloDAO.findAllListArticles(30);
        Assert.assertEquals(2, lista_has_articolo.size());
    }
}
