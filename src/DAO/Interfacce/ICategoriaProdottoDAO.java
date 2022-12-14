package DAO.Interfacce;

import Model.CategoriaProdotto;

import java.util.ArrayList;

public interface ICategoriaProdottoDAO {

    int add(CategoriaProdotto categoriaProdotto);
    int addSub(CategoriaProdotto categoriaProdotto);
    int update(CategoriaProdotto categoriaProdotto);
    int delete(CategoriaProdotto categoriaProdotto);
    int deleteSub(CategoriaProdotto categoriaProdotto);
    boolean isCategory(CategoriaProdotto categoriaProdotto);
    CategoriaProdotto findByID(int idCategoria);
    ArrayList<CategoriaProdotto> findAllSons(int idCategoriaPadre);
    CategoriaProdotto findByName(String nomeCategoria);
    ArrayList<CategoriaProdotto> findAll();


}
