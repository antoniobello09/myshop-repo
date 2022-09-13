package Business.ModelBusiness;

import DAO.Classi.ArticoloDAO;
import DAO.Classi.ProdottoCompositoDAO;
import DAO.Classi.ProdottoDAO;
import Model.Prodotto;
import Model.ProdottoComposito;
import Model.Prodotto_Quantita;

import java.util.ArrayList;

public class ProdottoCompositoBusiness {

    private static ProdottoCompositoBusiness instance;

    public static synchronized ProdottoCompositoBusiness getInstance() {
        if(instance == null) instance = new ProdottoCompositoBusiness();
        return instance;
    }

    private ProdottoCompositoBusiness() {
    }

    public int aggiungi(ProdottoComposito prodotto, Prodotto_Quantita prodotto_quantita){
        if(ArticoloDAO.getInstance().add(prodotto) == 0) return 0;
        prodotto.setIdArticolo(ArticoloDAO.getInstance().findByName(prodotto.getNome()).getIdArticolo());
        if(ProdottoCompositoDAO.getInstance().add(prodotto.getIdArticolo(), prodotto_quantita) == 0) return 0;
        return 1;
    }

    public int aggiorna(Prodotto prodotto, Prodotto_Quantita prodotto_quantita){
        if(ArticoloDAO.getInstance().update(prodotto) == 0) return 0;
        if(ProdottoCompositoDAO.getInstance().update(prodotto.getIdArticolo(), prodotto_quantita) == 0) return 0;
        return 1;
    }

    public int cancella(ProdottoComposito prodotto){
        if(ProdottoCompositoDAO.getInstance().delete(prodotto.getIdArticolo()) == 0) return 0;
        if(ArticoloDAO.getInstance().delete(prodotto) == 0) return 0;
        return 1;
    }

    public ProdottoComposito cercaIDProdottoComposito(int idProdottoComposito){
        return ProdottoCompositoDAO.getInstance().findByID(idProdottoComposito);
    }

    public ProdottoComposito cercaNomeProdottoComposito(String nomeProdotto){
        return ProdottoCompositoDAO.getInstance().findByName(nomeProdotto);
    }

    public ArrayList<Prodotto_Quantita> cercaSottoProdotti(int idProdottoComposito){
        return ProdottoCompositoDAO.getInstance().findSonsByID(idProdottoComposito);
    }

    public ArrayList<ProdottoComposito> cercaTuttiProdottiCompositi(){
        return ProdottoCompositoDAO.getInstance().findAll();
    }

}
