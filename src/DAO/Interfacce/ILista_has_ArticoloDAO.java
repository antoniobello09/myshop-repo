package DAO.Interfacce;

import Model.Articolo;
import Model.Lista;
import Model.Prodotto_Quantita;
import Model.Servizio;

import java.util.ArrayList;

public interface ILista_has_ArticoloDAO {

    int add(Lista lista, Articolo articolo);
    int update(Lista lista, Articolo articolo);
    int delete(Lista lista, Articolo articolo);
    Articolo findByID(int idLista, int idArticolo);
    ArrayList<Articolo> findAll(int idLista);
    ArrayList<Servizio> findAllServizi(int idLista);
    ArrayList<Prodotto_Quantita> findAllProdotti(int idLista);

}
