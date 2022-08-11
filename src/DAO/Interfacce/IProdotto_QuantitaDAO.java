package DAO.Interfacce;

import Model.Prodotto;
import Model.Prodotto_Quantita;
import Model.Produttore;

import java.util.ArrayList;

public interface IProdotto_QuantitaDAO {

    int add(Prodotto_Quantita prodotto_q);
    int update(Prodotto_Quantita prodotto_q);
    int delete(Prodotto_Quantita prodotto_q);
    Prodotto_Quantita findByID(int idProdotto);
    Prodotto_Quantita findByName(Prodotto_Quantita prodotto_q);
    ArrayList<Prodotto_Quantita> findAll();
    Produttore findProduttore(int idProduttore);


}
