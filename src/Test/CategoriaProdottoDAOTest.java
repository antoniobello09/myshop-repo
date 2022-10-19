package Test;

import DAO.Classi.CategoriaDAO;
import DAO.Classi.CategoriaProdottoDAO;
import DAO.Interfacce.ICategoriaDAO;
import DAO.Interfacce.ICategoriaProdottoDAO;
import Model.Categoria;
import Model.CategoriaProdotto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CategoriaProdottoDAOTest {

    private int idCategoriaProdotto;

    @Before
    public void setUp() throws Exception {
        ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();

        categoriaDAO.add(new Categoria("CategoriaProdottoTest"));
        CategoriaProdotto categoriaProdotto = new CategoriaProdotto();
        idCategoriaProdotto = categoriaDAO.findByName("CategoriaProdottoTest").getIdCategoria();
        categoriaProdotto.setIdCategoria(idCategoriaProdotto);
        categoriaProdotto.setNome("CategoriaProdottoTest");
        categoriaProdotto.setIdCategoriaPadre(30);
        categoriaProdottoDAO.addSub(categoriaProdotto);

    }

    @After
    public void tearDown() throws Exception {
        ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();

        CategoriaProdotto categoriaProdotto = new CategoriaProdotto();
        categoriaProdotto.setIdCategoria(idCategoriaProdotto);
        categoriaProdottoDAO.deleteSub(categoriaProdotto);
        categoriaDAO.delete(categoriaProdotto);
    }

    @Test
    public void updateTestOK() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        CategoriaProdotto categoriaProdotto = new CategoriaProdotto("CategoriaToast");
        categoriaProdotto.setIdCategoria(idCategoriaProdotto);
        categoriaProdottoDAO.update(categoriaProdotto);
        categoriaProdotto = categoriaProdottoDAO.findByID(idCategoriaProdotto);
        Assert.assertEquals("CategoriaToast", categoriaProdotto.getNome());
    }

    @Test
    public void updateTestNOK() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        CategoriaProdotto categoriaProdotto = new CategoriaProdotto("CategoriaToast");
        categoriaProdotto.setIdCategoria(idCategoriaProdotto);
        categoriaProdottoDAO.update(categoriaProdotto);
        categoriaProdotto = categoriaProdottoDAO.findByID(idCategoriaProdotto);
        Assert.assertEquals("CategoriaToast2", categoriaProdotto.getNome());
    }

    @Test
    public void findByIdTestOK() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        CategoriaProdotto categoriaProdotto = categoriaProdottoDAO.findByID(idCategoriaProdotto);
        Assert.assertEquals("CategoriaProdottoTest", categoriaProdotto.getNome());
    }

    @Test
    public void findByIdTestNOK() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        CategoriaProdotto categoriaProdotto = categoriaProdottoDAO.findByID(29);
        Assert.assertEquals("CategoriaProdottoTest", categoriaProdotto.getNome());
    }

    @Test
    public void findAllTestOK() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        ArrayList<CategoriaProdotto> categorieProdotto = categoriaProdottoDAO.findAll();
        Assert.assertEquals(15, categorieProdotto.size());
    }

    @Test
    public void findAllTestNOK() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        ArrayList<CategoriaProdotto> categorieProdotto = categoriaProdottoDAO.findAll();
        Assert.assertEquals(21, categorieProdotto.size());
    }

    @Test
    public void findAllSonsTestOK() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        ArrayList<CategoriaProdotto> categorieProdotto = categoriaProdottoDAO.findAllSons(30);
        Assert.assertEquals(3, categorieProdotto.size());
    }

    @Test
    public void findAllSonsTestNOK() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        ArrayList<CategoriaProdotto> categorie = categoriaProdottoDAO.findAllSons(30);
        Assert.assertEquals(4, categorie.size());
    }

    @Test
    public void findByNameTestOK() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        CategoriaProdotto categoriaProdotto = categoriaProdottoDAO.findByName("CategoriaProdottoTest");
        Assert.assertEquals(30, categoriaProdotto.getIdCategoriaPadre());
    }

    @Test
    public void findByNameTestNOK() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        CategoriaProdotto categoriaProdotto = categoriaProdottoDAO.findByName("CategoriaProdottoTest");
        Assert.assertEquals(31, categoriaProdotto.getIdCategoriaPadre());
    }


    /*    @Test
        public void deleteTestOK(){
            int rowCount;
            ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
            CategoriaProdotto categoriaProdotto = new CategoriaProdotto();
            categoriaProdotto.setIdCategoriaPadre(30);
            rowCount = categoriaProdottoDAO.delete(categoriaProdotto);
            Assert.assertEquals(1, rowCount);
        }



    @Test
    public void deleteTestNOK(){
        int rowCount;
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        CategoriaProdotto categoriaProdotto = new CategoriaProdotto();
        categoriaProdotto.setIdCategoriaPadre(30);
        rowCount = categoriaProdottoDAO.delete(categoriaProdotto);
        Assert.assertEquals(0, rowCount);
    }


     */
    @Test
    public void deleteSubTestOK(){
        int rowCount;
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        CategoriaProdotto categoriaProdotto = new CategoriaProdotto();
        categoriaProdotto.setIdCategoria(idCategoriaProdotto);
        rowCount = categoriaProdottoDAO.deleteSub(categoriaProdotto);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void deleteSubTestNOK(){
        int rowCount;
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        CategoriaProdotto categoriaProdotto = new CategoriaProdotto();
        categoriaProdotto.setIdCategoria(idCategoriaProdotto);
        rowCount = categoriaProdottoDAO.deleteSub(categoriaProdotto);
        Assert.assertEquals(0, rowCount);
    }


    @Test
    public void isCategoryTestOK() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        CategoriaProdotto categoriaProdotto = new CategoriaProdotto();
        categoriaProdotto.setNome("CategoriaProdottoTest");
        boolean response = categoriaProdottoDAO.isCategory(categoriaProdotto);
        Assert.assertEquals(false, response);
    }

    @Test
    public void isCategoryTestNOK() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        CategoriaProdotto categoriaProdotto = new CategoriaProdotto();
        categoriaProdotto.setNome("CategoriaProdottoTest");
        boolean response = categoriaProdottoDAO.isCategory(categoriaProdotto);
        Assert.assertEquals(true, response);
    }
}
