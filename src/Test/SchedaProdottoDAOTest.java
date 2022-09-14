package Test;

import DAO.Classi.PuntoVenditaDAO;
import DAO.Classi.SchedaProdottoDAO;
import DAO.Interfacce.IPuntoVenditaDAO;
import DAO.Interfacce.ISchedaProdottoDAO;
import Model.PuntoVendita;
import Model.SchedaProdotto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class SchedaProdottoDAOTest {

    private int idProdotto = 22;
    private int idPuntoVendita = 6;

    @Test
    public void findByShop_ProductTestOK() {
        ISchedaProdottoDAO schedaProdottoDAO = SchedaProdottoDAO.getInstance();
        SchedaProdotto schedaProdotto = schedaProdottoDAO.findByShop_Product(idProdotto, idPuntoVendita);
        Assert.assertEquals(6, schedaProdotto.getIdPuntoVendita());
        Assert.assertEquals(22, schedaProdotto.getProdotto().getIdArticolo());
    }

    @Test
    public void findByShop_ProductTestNOK() {
        ISchedaProdottoDAO schedaProdottoDAO = SchedaProdottoDAO.getInstance();
        SchedaProdotto schedaProdotto = schedaProdottoDAO.findByShop_Product(idProdotto, idPuntoVendita);
        Assert.assertEquals(7, schedaProdotto.getIdPuntoVendita());
        Assert.assertEquals(23, schedaProdotto.getProdotto().getIdArticolo());
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
        Assert.assertEquals(2, schedaProdotto.size());
    }

    @Test
    public void findAllTestOK() {
        ISchedaProdottoDAO schedaProdottoDAO = SchedaProdottoDAO.getInstance();
        ArrayList<SchedaProdotto> schedaProdotto = schedaProdottoDAO.findAll();
        Assert.assertEquals(1, schedaProdotto.size());
    }

    @Test
    public void findAllTestNOK() {
        ISchedaProdottoDAO schedaProdottoDAO = SchedaProdottoDAO.getInstance();
        ArrayList<SchedaProdotto> schedaProdotto = schedaProdottoDAO.findAll();
        Assert.assertEquals(2, schedaProdotto.size());
    }

}
