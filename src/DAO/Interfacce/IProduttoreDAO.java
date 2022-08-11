package DAO.Interfacce;



import Model.Produttore;

import java.util.ArrayList;

public interface IProduttoreDAO {

    int add(Produttore produttore);
    int update(Produttore produttore);
    int delete(Produttore produttore);
    Produttore findByID(int idProduttore);
    Produttore findByName(String nomeProduttore);
    Produttore findByName(String nomeProduttore, int closeConn);
    ArrayList<Produttore> findAll();

}
