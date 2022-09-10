package Business.ModelBusiness;

import DAO.Classi.ArticoloDAO;
import DAO.Classi.ServizioDAO;
import Model.Servizio;

import java.util.ArrayList;

public class ServizioBusiness {

    private static ServizioBusiness instance;

    public static synchronized ServizioBusiness getInstance() {
        if(instance == null) instance = new ServizioBusiness();
        return instance;
    }

    private ServizioBusiness() {
    }

    public int aggiungi(Servizio servizio){
        if(ArticoloDAO.getInstance().add(servizio) == 0) return 0;
        servizio.setIdArticolo(ArticoloDAO.getInstance().findByName(servizio.getNome()).getIdArticolo());
        if(ServizioDAO.getInstance().add(servizio) == 0) return 0;
        return 1;
    }

    public int aggiorna(Servizio servizio){
        if(ArticoloDAO.getInstance().update(servizio) == 0) return 0;
        if(ServizioDAO.getInstance().update(servizio) == 0) return 0;
        return 1;
    }

    public int cancella(Servizio servizio){
        if(ServizioDAO.getInstance().delete(servizio) == 0) return 0;
        if(ArticoloDAO.getInstance().delete(servizio) == 0) return 0;
        return 1;
    }

    public Servizio cercaIDServizio(int idServizio){
        return ServizioDAO.getInstance().findByID(idServizio);
    }

    public Servizio cercaNomeServizio(String nomeServizio){
        return ServizioDAO.getInstance().findByName(nomeServizio);
    }

    public ArrayList<Servizio> cercaTuttiServizi(){
        return ServizioDAO.getInstance().findAll();
    }

}
