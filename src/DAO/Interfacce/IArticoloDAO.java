package DAO.Interfacce;

import Model.Articolo;
import Model.Utente;

import java.util.ArrayList;

public interface IArticoloDAO {
    Articolo findById(int idArticolo);
    ArrayList<Articolo> findAll();
    Articolo findByName(String nomeArticolo, int closeConn);
 /*   int add(Articolo articolo);
    int removeById(int idArticolo);
    int update(Utente utente);

    boolean articleExists(String username);


    Utente getByUsername(String username);

    boolean managerExists(Utente u);

    boolean administratorExists(Utente u);
    */

}