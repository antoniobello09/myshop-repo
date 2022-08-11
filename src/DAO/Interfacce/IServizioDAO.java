package DAO.Interfacce;

import Model.FornitoreServizi;
import Model.Prodotto;
import Model.Produttore;
import Model.Servizio;

import java.util.ArrayList;

public interface IServizioDAO {

    int add(Servizio servizio);
    int update(Servizio servizio);
    int delete(Servizio servizio);
    Servizio findByID(int idServizio);
    Servizio findByName(String nomeServizio);
    ArrayList<Servizio> findAll();
    ArrayList<Servizio> findByShop(int idPuntoVendita);
}
