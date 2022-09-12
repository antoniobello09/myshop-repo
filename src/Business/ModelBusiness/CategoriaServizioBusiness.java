package Business.ModelBusiness;

import DAO.Classi.ArticoloDAO;
import DAO.Classi.CategoriaDAO;
import DAO.Classi.CategoriaServizioDAO;
import DAO.Classi.ServizioDAO;
import Model.Categoria;
import Model.CategoriaProdotto;
import Model.CategoriaServizio;
import Model.Servizio;

import java.util.ArrayList;

public class CategoriaServizioBusiness {
    private static CategoriaServizioBusiness instance;

    public static synchronized CategoriaServizioBusiness getInstance() {
        if(instance == null) instance = new CategoriaServizioBusiness();
        return instance;
    }

    private CategoriaServizioBusiness() {
    }

    public int aggiungi(CategoriaServizio categoriaServizio){
        if(CategoriaDAO.getInstance().add(categoriaServizio) == 0) return 0;
        categoriaServizio.setIdCategoria(CategoriaDAO.getInstance().findByName(categoriaServizio.getNome()).getIdCategoria());
        if(CategoriaServizioDAO.getInstance().add(categoriaServizio) == 0) return 0;
        return 1;
    }

    public int aggiorna(CategoriaServizio categoriaServizio){
        if(CategoriaServizioDAO.getInstance().update(categoriaServizio) == 0) return 0;
        return 1;
    }

    public int cancella(CategoriaServizio categoriaServizio){
        if(CategoriaDAO.getInstance().delete(categoriaServizio) == 0) return 0;
        if(CategoriaServizioDAO.getInstance().delete(categoriaServizio) == 0) return 0;
        return 1;
    }

    public CategoriaServizio cercaIDCategoriaServizio(int idCategoriaServizio){
        return CategoriaServizioDAO.getInstance().findByID(idCategoriaServizio);
    }

    public CategoriaServizio cercaNomeServizio(String nomeServizio){
        return CategoriaServizioDAO.getInstance().findByName(nomeServizio);
    }

    public ArrayList<CategoriaServizio> cercaTuttiServizi(){
        return CategoriaServizioDAO.getInstance().findAll();
    }


}
