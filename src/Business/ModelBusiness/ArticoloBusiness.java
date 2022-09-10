package Business.ModelBusiness;

import DAO.Classi.ArticoloDAO;
import DAO.Classi.ServizioDAO;
import Model.Articolo;

import java.util.ArrayList;

public class ArticoloBusiness {

    private static ArticoloBusiness instance;

    public static synchronized ArticoloBusiness getInstance() {
        if(instance == null) instance = new ArticoloBusiness();
        return instance;
    }

    private ArticoloBusiness() {
    }

    public int aggiungi(Articolo articolo){
        if(ArticoloDAO.getInstance().add(articolo) == 0) return 0;
        return 1;
    }

    public int aggiorna(Articolo articolo){
        if(ArticoloDAO.getInstance().update(articolo) == 0) return 0;
        return 1;
    }

    public int cancella(Articolo articolo){
        if(ArticoloDAO.getInstance().delete(articolo) == 0) return 0;
        return 1;
    }

    public Articolo cercaArticoloByID(int idArticolo){
        return ArticoloDAO.getInstance().findById(idArticolo);
    }

    public Articolo cercaArticoloByNome(String usernameArticolo){
        return ArticoloDAO.getInstance().findByName(usernameArticolo);
    }

    public ArrayList<Articolo> cercaTuttiUtenti(){
        return ArticoloDAO.getInstance().findAll();
    }

    public boolean isServizio(Articolo articolo){
        return ArticoloDAO.getInstance().isServizio(articolo);
    }

    public boolean isProdotto(Articolo articolo){
        return ArticoloDAO.getInstance().isProdotto(articolo);
    }

    public boolean isProdottoComposito(Articolo articolo){
        return ArticoloDAO.getInstance().isServizio(articolo);
    }

}
