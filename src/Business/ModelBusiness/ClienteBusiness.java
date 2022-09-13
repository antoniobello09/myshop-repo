package Business.ModelBusiness;

import DAO.Classi.ClienteDAO;
import DAO.Classi.ListaDAO;
import DAO.Classi.Lista_has_ArticoloDAO;
import DAO.IUtenteDAO;
import DAO.UtenteDAO;
import Model.Cliente;
import Model.Lista;
import Model.Other.LoginResponse;
import Model.Utente;

import java.util.ArrayList;
import java.util.Iterator;

public class ClienteBusiness {

    private static ClienteBusiness instance;


    public static synchronized ClienteBusiness getInstance() {
        if(instance == null) instance = new ClienteBusiness();
        return instance;
    }

    private ClienteBusiness() {}

    public int aggiungi(Cliente cliente){
        if(UtenteDAO.getInstance().add(cliente) == 0) return 0;
        if(ClienteDAO.getInstance().add(cliente) == 0) return 0;
        return 1;
    }

    public int aggiorna(Cliente cliente){
        if(UtenteDAO.getInstance().update(cliente) == 0) return 0;
        if(ClienteDAO.getInstance().update(cliente) == 0) return 0;
        return 1;
    }

    public int cancella(Cliente cliente){
        if(ClienteDAO.getInstance().delete(cliente) == 0) return 0;
        if(UtenteDAO.getInstance().delete(cliente) == 0) return 0;
        return 1;
    }

    public Cliente cercaClienteByID(int idCliente){
        return ClienteDAO.getInstance().findByID(idCliente);
    }

    public Cliente cercaClienteByUsername(String usernameCliente){
        return ClienteDAO.getInstance().findByUsername(usernameCliente);
    }

    public ArrayList<Cliente> cercaTuttiClienti(){
        return ClienteDAO.getInstance().findAll();
    }



}

