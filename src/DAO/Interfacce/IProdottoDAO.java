package DAO.Interfacce;

import Model.Fornitore;
import Model.Prodotto;

import java.util.ArrayList;

public interface IProdottoDAO {

    int add(Prodotto prodotto);
    int update(Prodotto prodotto);
    int delete(Prodotto prodotto);
    Prodotto findByID(int idProdotto);
    Prodotto findByName(String nomeProdotto);
    ArrayList<Prodotto> findAll();

}
