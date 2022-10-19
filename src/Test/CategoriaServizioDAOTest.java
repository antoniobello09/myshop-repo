package Test;

import DAO.Classi.CategoriaDAO;
import DAO.Classi.CategoriaServizioDAO;
import DAO.Interfacce.ICategoriaDAO;
import DAO.Interfacce.ICategoriaServizioDAO;
import Model.Categoria;
import Model.CategoriaServizio;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CategoriaServizioDAOTest {

    private int idCategoriaServizio;

    @Before
    public void setUp() throws Exception {
        ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        CategoriaServizio categoriaServizio = new CategoriaServizio("CategoriaServizioTest");
        categoriaDAO.add(categoriaServizio);
        idCategoriaServizio = categoriaDAO.findByName("CategoriaServizioTest").getIdCategoria();
        categoriaServizio.setIdCategoria(idCategoriaServizio);
        categoriaServizioDAO.add(categoriaServizio);
    }

    @After
    public void tearDown() throws Exception {
        ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        CategoriaServizio categoriaServizio = new CategoriaServizio();
        categoriaServizio.setIdCategoria(idCategoriaServizio);
        categoriaServizioDAO.delete(categoriaServizio);
        categoriaDAO.delete(categoriaServizio);
    }

    @Test
    public void updateTestOK() {
        ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        CategoriaServizio categoriaServizio = new CategoriaServizio("CategoriaToast");
        categoriaServizio.setIdCategoria(idCategoriaServizio);
        categoriaServizioDAO.update(categoriaServizio);
        categoriaServizio = CategoriaServizioDAO.getInstance().findByID(idCategoriaServizio);
        Assert.assertEquals("CategoriaToast", categoriaServizio.getNome());
    }

    @Test
    public void updateTestNOK() {
        ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        CategoriaServizio categoriaServizio = new CategoriaServizio("CategoriaToast");
        categoriaServizio.setIdCategoria(idCategoriaServizio);
        categoriaServizio = CategoriaServizioDAO.getInstance().findByID(idCategoriaServizio);
        Assert.assertEquals("CategoriaToast2", categoriaServizio.getNome());
    }

    @Test
    public void findByIdTestOK() {
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        CategoriaServizio categoriaServizio = categoriaServizioDAO.findByID(idCategoriaServizio);
        Assert.assertEquals("CategoriaServizioTest", categoriaServizio.getNome());
    }

    @Test
    public void findByIdTestNOK() {
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        CategoriaServizio categoriaServizio = categoriaServizioDAO.findByID(idCategoriaServizio);
        Assert.assertEquals("CategoriaToast", categoriaServizio.getNome());
    }

    @Test
    public void findAllTestOK() {
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        ArrayList<CategoriaServizio> categorieServizio = categoriaServizioDAO.findAll();
        Assert.assertEquals(5, categorieServizio.size());
    }

    @Test
    public void findAllTestNOK() {
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        ArrayList<CategoriaServizio> categorieServizio = categoriaServizioDAO.findAll();
        Assert.assertEquals(4, categorieServizio.size());
    }

    @Test
    public void findByNameTestOK() {
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        CategoriaServizio categoriaServizio = categoriaServizioDAO.findByName("CategoriaServizioTest");
        Assert.assertEquals("CategoriaServizioTest", categoriaServizio.getNome());
    }

    @Test
    public void findByNameTestNOK() {
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        CategoriaServizio categoriaServizio = categoriaServizioDAO.findByName("trasporto");
        Assert.assertEquals("CategoriaServizioTest", categoriaServizio.getNome());
    }


    @Test
    public void deleteTestOK(){
        int rowCount;
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        CategoriaServizio categoriaServizio = new CategoriaServizio();
        categoriaServizio.setIdCategoria(idCategoriaServizio);
        rowCount = categoriaServizioDAO.delete(categoriaServizio);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void deleteTestNOK(){
        int rowCount;
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        CategoriaServizio categoriaServizio = new CategoriaServizio();
        categoriaServizio.setIdCategoria(99999999);
        rowCount = categoriaServizioDAO.delete(categoriaServizio);
        Assert.assertEquals(1, rowCount);
    }

}
