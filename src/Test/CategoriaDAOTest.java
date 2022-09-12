package Test;

import DAO.Classi.ArticoloDAO;
import DAO.Classi.CategoriaDAO;
import DAO.Classi.ServizioDAO;
import DAO.Interfacce.IArticoloDAO;
import DAO.Interfacce.ICategoriaDAO;
import DAO.Interfacce.IServizioDAO;
import Model.Articolo;
import Model.Categoria;
import Model.Servizio;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CategoriaDAOTest {

    private int idCategoria;

    @Before
    public void setUp() throws Exception {
        ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
        categoriaDAO.add(new Categoria("CategoriaTest"));
        idCategoria = categoriaDAO.findByName("CategoriaTest").getIdCategoria();
    }

    @After
    public void tearDown() throws Exception {
        ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria);
        categoriaDAO.delete(categoria);
    }

    @Test
    public void updateTestOK() {
        int rowCount;
        ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
        Categoria categoria = new Categoria("CategoriaToast");
        categoria.setIdCategoria(idCategoria);
        rowCount = categoriaDAO.update(categoria);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void updateTestNOK() {
        int rowCount;
        ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
        Categoria categoria = new Categoria("CategoriaToast");
        categoria.setIdCategoria(99999);
        rowCount = categoriaDAO.update(categoria);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void findByIdTestOK() {
        ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
        Categoria categoria = categoriaDAO.findByID(idCategoria);
        Assert.assertEquals("CategoriaTest", categoria.getNome());
    }

    @Test
    public void findByIdTestNOK() {
        ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
        Categoria categoria = categoriaDAO.findByID(36);
        Assert.assertEquals("CategoriaTest", categoria.getNome());
    }

    @Test
    public void findAllTestOK() {
        ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
        ArrayList<Categoria> categorie = categoriaDAO.findAll();
        Assert.assertEquals(20, categorie.size());
    }

    @Test
    public void findAllTestNOK() {
        ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
        ArrayList<Categoria> categorie = categoriaDAO.findAll();
        Assert.assertEquals(21, categorie.size());
    }

    @Test
    public void findByNameTestOK() {
        ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
        Categoria categoria = categoriaDAO.findByName("CategoriaTest");
        Assert.assertEquals("CategoriaTest", categoria.getNome());
    }

    @Test
    public void findByNameTestNOK() {
        ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
        Categoria categoria = categoriaDAO.findByName("Lampadari");
        Assert.assertEquals("CategoriaTest", categoria.getNome());
    }


    @Test
    public void deleteTestOK(){
        int rowCount;
        ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria);
        rowCount = categoriaDAO.delete(categoria);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void deleteTestNOK(){
        int rowCount;
        ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(99999999);
        rowCount = categoriaDAO.delete(categoria);
        Assert.assertEquals(1, rowCount);
    }

}
