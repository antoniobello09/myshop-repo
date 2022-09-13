package Business.ModelBusiness;

import DAO.Classi.ArticoloDAO;
import DAO.Classi.ProdottoDAO;
import DAO.Classi.ServizioDAO;
import Model.Prodotto;
import Model.Servizio;

import java.util.ArrayList;

public class ProdottoBusiness {

    private static ProdottoBusiness instance;

    public static synchronized ProdottoBusiness getInstance() {
        if(instance == null) instance = new ProdottoBusiness();
        return instance;
    }

    private ProdottoBusiness() {
    }

    public int aggiungi(Prodotto prodotto){
        if(ArticoloDAO.getInstance().add(prodotto) == 0) return 0;
        prodotto.setIdArticolo(ArticoloDAO.getInstance().findByName(prodotto.getNome()).getIdArticolo());
        if(ProdottoDAO.getInstance().add(prodotto) == 0) return 0;
        return 1;
    }

    public int aggiorna(Prodotto prodotto){
        if(ArticoloDAO.getInstance().update(prodotto) == 0) return 0;
        if(ProdottoDAO.getInstance().update(prodotto) == 0) return 0;
        return 1;
    }

    public int cancella(Prodotto prodotto){
        if(ProdottoDAO.getInstance().delete(prodotto) == 0) return 0;
        if(ArticoloDAO.getInstance().delete(prodotto) == 0) return 0;
        return 1;
    }

    public Prodotto cercaIDProdotto(int idProdotto){
        return ProdottoDAO.getInstance().findByID(idProdotto);
    }

    public Prodotto cercaNomeProdotto(String nomeProdotto){
        return ProdottoDAO.getInstance().findByName(nomeProdotto);
    }

    public ArrayList<Prodotto> cercaTuttiProdotti(){
        return ProdottoDAO.getInstance().findAll();
    }

}
