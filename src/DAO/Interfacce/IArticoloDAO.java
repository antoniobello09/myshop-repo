package DAO.Interfacce;

import Model.Articolo;
import Model.Utente;

import java.util.ArrayList;

public interface IArticoloDAO {
    int add(Articolo articolo);
    int update(Articolo articolo);
    int delete(Articolo articolo);
    Articolo findById(int idArticolo);
    ArrayList<Articolo> findAll();
    Articolo findByName(String nomeArticolo);
    boolean isServizio(Articolo articolo);
    boolean isProdotto(Articolo articolo);
    boolean isProdottoComposito(Articolo articolo);
}