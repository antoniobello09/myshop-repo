package DAO.Interfacce;



import Model.Fornitore;

import java.util.ArrayList;

public interface IFornitoreDAO {

    int add(Fornitore fornitore);
    int update(Fornitore fornitore);
    int delete(Fornitore fornitore);
    Fornitore findByID(int idFornitore);
    Fornitore findByName(String nomeFornitore);
    ArrayList<Fornitore> findAll();
    ArrayList<Fornitore> findAllProd();
    ArrayList<Fornitore> findAllServ();

}
