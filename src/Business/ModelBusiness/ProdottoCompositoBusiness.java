package Business.ModelBusiness;

import DAO.Classi.ArticoloDAO;
import DAO.Classi.PosizioneDAO;
import DAO.Classi.ProdottoCompositoDAO;
import DAO.Classi.ProdottoDAO;
import Model.Articolo;
import Model.Prodotto;
import Model.ProdottoComposito;
import Model.Prodotto_Quantita;
import View.AppFrame;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.CompositeProductDialog;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class ProdottoCompositoBusiness {

    private static ProdottoCompositoBusiness instance;

    public static synchronized ProdottoCompositoBusiness getInstance() {
        if(instance == null) instance = new ProdottoCompositoBusiness();
        return instance;
    }

    private ProdottoCompositoBusiness() {
    }

    public int aggiungi(AppFrame appFrame, String nome, String descrizione, String prezzo, File immagine, String posizione){
        String[] posizioneArray = posizione.split(" ");

        int idPosizione = PosizioneDAO.getInstance().findByNumbers(Integer.parseInt(posizioneArray[0]),Integer.parseInt(posizioneArray[2]),Integer.parseInt(posizioneArray[4])).getIdPosizione();
        Prodotto prodotto = new Prodotto(nome, descrizione, Float.parseFloat(prezzo), 0,0, idPosizione);
        prodotto.setImmagine(immagine);
        if(ArticoloDAO.getInstance().add(prodotto) == 0){
            return 1;
        }
        prodotto.setIdArticolo(ArticoloDAO.getInstance().findByName(nome).getIdArticolo());
        ProdottoDAO.getInstance().add(prodotto);
        if(ProdottoDAO.getInstance().findByName(prodotto.getNome())!=null) {
            CompositeProductDialog.showDialog(appFrame, "Aggiungi prodotto", ProdottoDAO.getInstance().findByName(prodotto.getNome()).getIdArticolo());
        }else{
            return 1;
        }
        return 0;
    }

    public boolean aggiungiSottoProdotto(String nomeProdotto, String quantitaProdotto, int idProdottoComposito){
        Prodotto p;
        p = ProdottoDAO.getInstance().findByName(nomeProdotto);
        if(p != null){
            p.setQuantita(Integer.parseInt(quantitaProdotto));
            p.setIdProdottoCompositoPadre(idProdottoComposito);
            ProdottoCompositoDAO.getInstance().add(p);
            return true;
        }else{
            return false;
        }
    }

}
