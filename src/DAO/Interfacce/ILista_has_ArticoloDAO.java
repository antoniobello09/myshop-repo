package DAO.Interfacce;

import Model.*;

import java.util.ArrayList;

public interface ILista_has_ArticoloDAO {

    int add(int idLista, int idArticolo, int quantita);
    int update(int idLista, int idArticolo, int quantita);
    int delete(int idLista, int idArticolo);
    Lista_has_Articolo findByID(int idLista, int idArticolo);
    ArrayList<Lista_has_Articolo> findAll();
    ArrayList<Lista_has_Articolo> findAllListArticles(int idLista);


}
