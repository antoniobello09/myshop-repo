package Business.ModelBusiness;

import DAO.IUtenteDAO;
import DAO.UtenteDAO;
import Model.*;
import Business.LoginResponse;

public class UtenteBusiness {

    private static UtenteBusiness instance;

    public enum Privilegio { MANAGE_SHOP, ADMIN_SYSTEM, CLIENT }

    public static synchronized UtenteBusiness getInstance() {
        if(instance == null) instance = new UtenteBusiness();
        return instance;
    }

    private UtenteBusiness() {}


    public LoginResponse login(String username, String password) {

        LoginResponse res = new LoginResponse();
        res.setMessage("Errore non definito.");

        UtenteDAO uDao = UtenteDAO.getInstance();

        // 1. userame non esistente
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

