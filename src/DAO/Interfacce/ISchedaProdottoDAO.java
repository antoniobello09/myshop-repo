package DAO.Interfacce;

import Model.Posizione;
import Model.SchedaProdotto;

import java.util.ArrayList;

public interface ISchedaProdottoDAO {

    int add(int idProdotto, int disponibilita, int puntoVendita);
    int update(int idSchedaProdotto, int disponibilita);
    int delete(int idSchedaProdotto);
    SchedaProdotto findByShop_Product(int idProdotto, int idPuntoVendita);
    ArrayList<SchedaProdotto> findAllByShop(int idPuntoVendita);
    ArrayList<SchedaProdotto> findAll();

}
