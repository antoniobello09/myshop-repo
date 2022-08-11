package DAO.Interfacce;

import Model.Manager;
import Model.PuntoVendita;

import java.util.ArrayList;

public interface IPuntoVenditaDAO {
    int add(PuntoVendita puntoVendita);
    int update(PuntoVendita puntoVendita);
    int delete(PuntoVendita puntoVendita);
    PuntoVendita findByID(int idPuntoVendita);
    PuntoVendita findByName(String indirizzo, String citta);
    PuntoVendita findByManager(int idManager);
    ArrayList<PuntoVendita> findAll();
}
