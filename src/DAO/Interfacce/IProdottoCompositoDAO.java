package DAO.Interfacce;

import Model.ProdottoComposito;
import Model.Prodotto_Quantita;

import java.util.ArrayList;

public interface IProdottoCompositoDAO {

    int add(int idProdottoComposito, Prodotto_Quantita prodotto_quantita);
    int update(int idProdottoComposito, Prodotto_Quantita prodotto_quantita);
    int delete(int idProdottoComposito);
    ArrayList<Prodotto_Quantita> findSonsByID(int idProdottoComposito);
    ProdottoComposito findByID(int idProdottoC);
    ProdottoComposito findByName(String nomeProdottoC);
    ArrayList<ProdottoComposito> findAll();
}
