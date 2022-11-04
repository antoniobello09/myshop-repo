package DAO.Interfacce;

import Model.Prodotto;
import Model.ProdottoComposito;

import java.util.ArrayList;

public interface IProdottoCompositoDAO {

    int add(Prodotto prodotto);
    int update(Prodotto prodotto);
    int delete(ProdottoComposito prodottoComposito);
    ArrayList<Prodotto> findSonsByID(int idProdottoComposito);
    ProdottoComposito findByID(int idProdottoC);
    ProdottoComposito findByName(String nomeProdottoC);
    ArrayList<ProdottoComposito> findAll();
}
