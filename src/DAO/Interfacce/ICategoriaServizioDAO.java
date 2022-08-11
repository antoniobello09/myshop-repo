package DAO.Interfacce;

import Model.CategoriaServizio;

import java.util.ArrayList;

public interface ICategoriaServizioDAO {

    int add(CategoriaServizio categoriaServizio);
    int update(CategoriaServizio categoriaServizio);
    int delete(CategoriaServizio categoriaServizio);
    CategoriaServizio findByID(int idCategoria);
    CategoriaServizio findByName(String nomeCategoria);
    ArrayList<CategoriaServizio> findAll();
}
