package Business.ModelBusiness;

import DAO.Classi.ManagerDAO;
import DAO.IUtenteDAO;
import DAO.UtenteDAO;
import Model.Manager;
import Model.Other.LoginResponse;
import Model.Utente;

import java.util.ArrayList;

public class ManagerBusiness {

    private static ManagerBusiness instance;


    public static synchronized ManagerBusiness getInstance() {
        if(instance == null) instance = new ManagerBusiness();
        return instance;
    }

    private ManagerBusiness() {}

    public int aggiungi(Manager manager){
        if(UtenteDAO.getInstance().add(manager) == 0) return 0;
        manager.setIdUtente(UtenteDAO.getInstance().findByUsername(manager.getUsername()).getIdUtente());
        if(ManagerDAO.getInstance().add(manager) == 0) return 0;
        return 1;
    }

    public int aggiorna(Manager manager){
        if(ManagerDAO.getInstance().update(manager) == 0) return 0;
        return 1;
    }

    public int cancella(Manager manager){
        if(ManagerDAO.getInstance().delete(manager) == 0) return 0;
        if(UtenteDAO.getInstance().delete(manager) == 0) return 0;
        return 1;
    }

    public Manager cercaManagerByID(int idManager){
        return ManagerDAO.getInstance().findByID(idManager);
    }

    public Manager cercaManagerByUsername(String usernameManager){
        return ManagerDAO.getInstance().findByUsername(usernameManager);
    }

    public ArrayList<Manager> cercaTuttiManager(){
        return ManagerDAO.getInstance().findAll();
    }

}

