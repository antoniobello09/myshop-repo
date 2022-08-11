package DAO.Interfacce;

import Model.ProdottoComposito;

import java.util.ArrayList;

public interface IProdottoCompositoDAO {

    int add(ProdottoComposito prodottoC);
    int update(ProdottoComposito prodottoC);
    int delete(ProdottoComposito prodottoC);
    ProdottoComposito findByID(String idProdottoC);
    ProdottoComposito findByName(String nomeProdottoC);
    ArrayList<ProdottoComposito> findAll();
}
