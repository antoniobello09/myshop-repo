package Test;

import DAO.Classi.PuntoVenditaDAO;
import DAO.Classi.SchedaProdottoDAO;
import DAO.Interfacce.IPuntoVenditaDAO;
import DAO.Interfacce.ISchedaProdottoDAO;
import Model.PuntoVendita;
import Model.SchedaProdotto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class SchedaProdottoDAOTest {

    private int idProdotto = 22;
    private int idPuntoVendita = 6;
    private int idSchedaProdotto;

    @Before
    public void setUp() throws Exception{
        ISchedaProdottoDAO schedaProdottoDAO = SchedaProdottoDAO.getInstance();
        SchedaProdotto schedaProdotto = new SchedaProdotto(idProdotto, 13, idPuntoVendita);
        schedaProdottoDAO.add(schedaProdotto);
        idSchedaProdotto = schedaProdottoDAO.findByShop_Product(idProdotto, idPuntoVendita).getIdSchedaProdotto();
    }

    @After
    public void tearDown() throws Exception{
        ISchedaProdottoDAO schedaProdottoDAO = SchedaProdottoDAO.getInstance();
        SchedaProdotto schedaProdotto = new SchedaProdotto();
        schedaProdotto.setIdSchedaProdotto(idSchedaProdotto);
        schedaProdottoDAO.delete(schedaProdotto);
    }

    @Test
    public void updateTestOK(){
        ISchedaProdottoDAO schedaProdottoDAO = SchedaProdottoDAO.getInstance();
        SchedaProdotto schedaProdotto = new SchedaProdotto();
        schedaProdotto.setIdSchedaProdotto(idSchedaProdotto);
        schedaProdotto.setDisponibilita(50);
        schedaProdottoDAO.update(schedaProdotto);
        schedaProdotto = schedaProdottoDAO.findByShop_Product(idProdotto, idPuntoVendita);
        Assert.assertEquals(50, schedaProdotto.getDisponibilita());
    }

    @Test
    public void updateTestNOK(){
        ISchedaProdottoDAO schedaProdottoDAO = SchedaProdottoDAO.getInstance();
        SchedaProdotto schedaProdotto = new SchedaProdotto();
        schedaProdotto.setIdSchedaProdotto(idSchedaProdotto);
        schedaProdotto.setDisponibilita(50);
        schedaProdottoDAO.update(schedaProdotto);
        schedaProdotto = schedaProdottoDAO.findByShop_Product(idProdotto, idPuntoVendita);
        Assert.assertEquals(51, schedaProdotto.getDisponibilita());
    }

    @Test
    public void deleteTestOK(){
        ISchedaProdottoDAO schedaProdottoDAO = SchedaProdottoDAO.getInstance();
        SchedaProdotto schedaProdotto = new SchedaProdotto();
        schedaProdotto.setIdSchedaProdotto(idSchedaProdotto);
        int rowCount = schedaProdottoDAO.delete(schedaProdotto);
        Assert.assertEquals(0, rowCount);
    }

    @Test
    public void deleteTestNOK(){
        ISchedaProdottoDAO schedaProdottoDAO = SchedaProdottoDAO.getInstance();
        SchedaProdotto schedaProdotto = new SchedaProdotto();
        schedaProdotto.setIdSchedaProdotto(idSchedaProdotto);
        int rowCount = schedaProdottoDAO.delete(schedaProdotto);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void findByShop_ProductTestOK() {
        ISchedaProdottoDAO schedaProdottoDAO = SchedaProdottoDAO.getInstance();
        SchedaProdotto schedaProdotto = schedaProdottoDAO.findByShop_Product(idProdotto, idPuntoVendita);
        Assert.assertEquals(13, schedaProdotto.getDisponibilita());
    }

    @Test
    public void findByShop_ProductTestNOK() {
        ISchedaProdottoDAO schedaProdottoDAO = SchedaProdottoDAO.getInstance();
        SchedaProdotto schedaProdotto = schedaProdottoDAO.findByShop_Product(idProdotto, idPuntoVendita);
        Assert.assertEquals(13, schedaProdotto.getDisponibilita());
    }

    @Test
    public void findAllByShopOK() {
        ISchedaProdottoDAO schedaProdottoDAO = SchedaProdottoDAO.getInstance();
        ArrayList<SchedaProdotto> schedaProdotto = schedaProdottoDAO.findAllByShop(idPuntoVendita);
        Assert.assertEquals(1, schedaProdotto.size());
    }

    @Test
    public void findAllByShopNOK() {
        ISchedaProdottoDAO schedaProdottoDAO = SchedaProdottoDAO.getInstance();
        ArrayList<SchedaProdotto> schedaProdotto = schedaProdottoDAO.findAllByShop(idPuntoVendita);
        Assert.assertEquals(3, schedaProdotto.size());
    }

    @Test
    public void findAllTestOK() {
        ISchedaProdottoDAO schedaProdottoDAO = SchedaProdottoDAO.getInstance();
        ArrayList<SchedaProdotto> schedaProdotto = schedaProdottoDAO.findAll();
        Assert.assertEquals(2, schedaProdotto.size());
    }

    @Test
    public void findAllTestNOK() {
        ISchedaProdottoDAO schedaProdottoDAO = SchedaProdottoDAO.getInstance();
        ArrayList<SchedaProdotto> schedaProdotto = schedaProdottoDAO.findAll();
        Assert.assertEquals(2, schedaProdotto.size());
    }

}
