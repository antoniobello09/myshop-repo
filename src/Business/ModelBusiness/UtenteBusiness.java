package Business.ModelBusiness;

import DAO.IUtenteDAO;
import DAO.UtenteDAO;
import Model.*;
import Model.Other.LoginResponse;
import Model.Other.RegistrationResponse;

import java.util.ArrayList;

public class UtenteBusiness {

    private static UtenteBusiness instance;

    public enum Privilegio { MANAGE_SHOP, ADMIN_SYSTEM, CLIENT }

    public static synchronized UtenteBusiness getInstance() {
        if(instance == null) instance = new UtenteBusiness();
        return instance;
    }

    private UtenteBusiness() {}

    public int aggiungi(Utente utente){
        if(UtenteDAO.getInstance().add(utente) == 0) return 0;
        return 1;
    }

    public int aggiorna(Utente utente){
        if(UtenteDAO.getInstance().update(utente) == 0) return 0;
        return 1;
    }

    public int cancella(Utente utente){
        if(UtenteDAO.getInstance().delete(utente) == 0) return 0;
        return 1;
    }

    public Utente cercaUtenteByID(int idUtente){
        return UtenteDAO.getInstance().findByID(idUtente);
    }

    public Utente cercaUtenteByUsername(String usernameUtente){
        return UtenteDAO.getInstance().findByUsername(usernameUtente);
    }

    public ArrayList<Utente> cercaTuttiUtenti(){
        return UtenteDAO.getInstance().findAll();
    }

    public LoginResponse login(String username, String password) {

        LoginResponse res = new LoginResponse();
        res.setMessage("Errore non definito.");

        UtenteDAO uDao = UtenteDAO.getInstance();

        // 1. username non esistente
        if(!uDao.userExists(username)) {
            res.setMessage("L'utente indicato non esiste.");
            return res;
        }

        // 2. username corretto, ma la pw è sbagliata
        if(!uDao.credentialsOk(username, password)) {
            res.setMessage("La password è errata.");
            return res;
        }
        Utente u = uDao.findByUsername(username);
        if(u != null) {
            res.setMessage("Benvenuto "+u.getName()+"!");
            res.setUtente(u);
        }

        return res;
    }

    public boolean userCan(Utente u, Privilegio p) {

        IUtenteDAO uDao = UtenteDAO.getInstance();

        if(Privilegio.MANAGE_SHOP.equals(p)) {
            // vediamo se u è un manager
            return uDao.managerExists(u);
        }
        if(Privilegio.ADMIN_SYSTEM.equals(p)) {
            // vediamo se u è un amminstratore
            return uDao.administratorExists(u);
        }

        if(Privilegio.CLIENT.equals(p)) {
            // vediamo se u è un cliente
            return uDao.clientExists(u);
        }

        return false;

    }


}

