package DAO.Interfacce;

import Model.Posizione;
import Model.SchedaProdotto;

import java.util.ArrayList;

public interface ISchedaProdottoDAO {

    int add(SchedaProdotto schedaProdotto);
    int update(SchedaProdotto schedaProdotto);
    int delete(SchedaProdotto schedaProdotto);
    SchedaProdotto findByShop_Product(int idProdotto, int idPuntoVendita);
    ArrayList<SchedaProdotto> findAllByProduct(int idProdotto);
    ArrayList<SchedaProdotto> findAllByShop(int idPuntoVendita);
    ArrayList<SchedaProdotto> findAll();

}
