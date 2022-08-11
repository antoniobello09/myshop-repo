package DAO.Interfacce;

import Model.FornitoreServizi;
import Model.SchedaProdotto;
import Model.SchedaServizio;

import java.util.ArrayList;

public interface ISchedaServizioDAO {

    int add(SchedaServizio schedaServizio, int idPuntoVendita);
    int update(SchedaServizio schedaServizio, FornitoreServizi fornitoreServizi);
    int delete(SchedaServizio schedaServizio);
    SchedaServizio findByID(int idSchedaServizio);
    SchedaServizio findByName(String nomeServizio);
    ArrayList<SchedaServizio> findAll();
    ArrayList<SchedaServizio> findServicesShop(int idPuntoVendita);

}
