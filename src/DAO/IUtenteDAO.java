package DAO;

import Model.Utente;

import java.util.ArrayList;

public interface IUtenteDAO {

    int add(Utente utente);
    int update(Utente utente);
    int delete(Utente utente);
    Utente findByID(int idUtente);
    Utente findByUsername(String username);
    ArrayList<Utente> findAll();

    boolean credentialsOk(String username, String password);

    boolean userExists(String username);
    boolean clientExists(Utente u);
    boolean managerExists(Utente u);
    boolean administratorExists(Utente u);
}