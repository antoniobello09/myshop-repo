package DAO.Interfacce;

import Model.CategoriaProdotto;

import java.util.ArrayList;

public interface ICategoriaProdottoDAO {

    int add(CategoriaProdotto categoriaProdotto);
    int addSub(CategoriaProdotto categoriaProdotto);

    int update(CategoriaProdotto categoriaProdotto);
    int updateSub(CategoriaProdotto categoriaProdotto);
    int delete(CategoriaProdotto categoriaProdotto);
    CategoriaProdotto findByID(int idCategoria);
    CategoriaProdotto findByName(String nomeCategoria);
    CategoriaProdotto findByName2(String nomeCategoria, String nomeCategoriaPadre);
    CategoriaProdotto findTopCategoria(String nomeCategoria);
    ArrayList<CategoriaProdotto> findAll();
    boolean hasProducts(CategoriaProdotto categoriaProdotto);
    boolean isSubCategory(int idCategoria);
    boolean isUnique(String nomeCategoria, String nomeCategoriaPadre);


}
