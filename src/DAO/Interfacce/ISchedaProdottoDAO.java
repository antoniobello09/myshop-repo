package DAO.Interfacce;

import Model.Posizione;
import Model.SchedaProdotto;

import java.util.ArrayList;

public interface ISchedaProdottoDAO {

    int add(SchedaProdotto schedaProdotto, int idMagazzino);
    int update(SchedaProdotto schedaProdotto, Posizione posizione);
    int delete(SchedaProdotto schedaProdotto);
    SchedaProdotto findByID(int idSchedaProdotto);
    SchedaProdotto findByName(String nomeProdotto);
    SchedaProdotto findByPosition(int idPosizione);
    ArrayList<SchedaProdotto> findAll();
    ArrayList<SchedaProdotto> findProductsShop(int idPuntoVendita);

}
