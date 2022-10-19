package DAO.Interfacce;

import Model.*;

import java.util.ArrayList;

public interface ILista_has_ArticoloDAO {

    int add(Lista_has_Articolo lista_has_articolo);
    int update(Lista_has_Articolo lista_has_articoloa);
    int delete(Lista_has_Articolo lista_has_articolo);
    Lista_has_Articolo findByID(int idLista, int idArticolo);
    ArrayList<Lista_has_Articolo> findAll();
    ArrayList<Lista_has_Articolo> findAllListArticles(int idLista);


}
