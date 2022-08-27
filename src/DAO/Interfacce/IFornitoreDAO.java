package DAO.Interfacce;



import Model.Fornitore;

import java.util.ArrayList;

public interface IFornitoreDAO {

    int add(Fornitore fornitore);
    int update(Fornitore fornitore);
    int delete(Fornitore fornitore);
    Fornitore findByID(int idFornitore);
    Fornitore findByName(String nomeFornitore);
    Fornitore findByName(String nomeFornitore, int closeConn);
    ArrayList<Fornitore> findAll();

}
