package Business.ModelBusiness;

import DAO.Classi.AmministratoreDAO;
import DAO.Classi.ManagerDAO;
import DAO.IUtenteDAO;
import DAO.UtenteDAO;
import Model.Amministratore;
import Model.Manager;
import Model.Other.LoginResponse;
import Model.Utente;

import java.util.ArrayList;

public class AmministratoreBusiness {

    private static AmministratoreBusiness instance;


    public static synchronized AmministratoreBusiness getInstance() {
        if(instance == null) instance = new AmministratoreBusiness();
        return instance;
    }

    private AmministratoreBusiness() {}

    public int aggiungi(Amministratore amministratore){
        if(UtenteDAO.getInstance().add(amministratore) == 0) return 0;
        amministratore.setIdUtente(UtenteDAO.getInstance().findByUsername(amministratore.getUsername()).getIdUtente());
        if(AmministratoreDAO.getInstance().add(amministratore) == 0) return 0;
        return 1;
    }

    public int aggiorna(Amministratore amministratore){
        if(AmministratoreDAO.getInstance().update(amministratore) == 0) return 0;
        return 1;
    }

    public int cancella(Amministratore amministratore){
        if(AmministratoreDAO.getInstance().delete(amministratore) == 0) return 0;
        if(UtenteDAO.getInstance().delete(amministratore) == 0) return 0;
        return 1;
    }

    public Amministratore cercaAmministratoreByID(int idAmministratore){
        return AmministratoreDAO.getInstance().findByID(idAmministratore);
    }

    public Amministratore cercaAmministratoreByUsername(String usernameAmministratore){
        return AmministratoreDAO.getInstance().findByUsername(usernameAmministratore);
    }

    public ArrayList<Amministratore> cercaTuttiAmministratori(){
        return AmministratoreDAO.getInstance().findAll();
    }


}

