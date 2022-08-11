package DAO.Interfacce;

import Model.CategoriaProdotto;
import Model.Prodotto;
import Model.Produttore;
import Model.PuntoVendita;

import java.util.ArrayList;

public interface IProdottoDAO {

    int add(Prodotto prodotto);
    int update(Prodotto prodotto);
    int delete(Prodotto prodotto);
    Prodotto findByID(int idProdotto);
    Prodotto findByName(String nomeProdotto);
    ArrayList<Prodotto> findAll();
    ArrayList<Prodotto> findAllProducts();  //Per non prendere i prodotti compositi
    Produttore findProduttore(int idProduttore);
    ArrayList<Prodotto> findByShop(int idPuntoVendita);

}
