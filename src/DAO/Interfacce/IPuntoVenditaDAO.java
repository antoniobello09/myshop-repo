package DAO.Interfacce;

import Model.Manager;
import Model.PuntoVendita;

import java.util.ArrayList;

public interface IPuntoVenditaDAO {
    int add(int idManager, String citta, String indirizzo);
    int update(int idPuntoVendita, String citta, String indirizzo);
    int delete(int idPuntoVendita);
    PuntoVendita findByID(int idPuntoVendita);
    PuntoVendita findByName(String indirizzo, String citta);
    PuntoVendita findByManager(int idManager);
    ArrayList<PuntoVendita> findAll();
}
