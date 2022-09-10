package DAO.Interfacce;

import Model.Categoria;
import Model.CategoriaServizio;

import java.util.ArrayList;

public interface ICategoriaDAO {

    int add(Categoria categoria);
    int update(Categoria categoria);
    int delete(Categoria categoria);
    Categoria findByID(int idCategoria);
    Categoria findByName(String nomeCategoria);
    ArrayList<Categoria> findAll();
}
