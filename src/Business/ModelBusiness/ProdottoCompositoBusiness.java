package Business.ModelBusiness;

import DAO.Classi.ArticoloDAO;
import DAO.Classi.PosizioneDAO;
import DAO.Classi.ProdottoCompositoDAO;
import DAO.Classi.ProdottoDAO;
import Model.Prodotto;
import View.AppFrame;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.CompositeProductDialog;

import java.io.File;

public class ProdottoCompositoBusiness {

    private static ProdottoCompositoBusiness instance;

    public static synchronized ProdottoCompositoBusiness getInstance() {
        if(instance == null) instance = new ProdottoCompositoBusiness();
        return instance;
    }

    private ProdottoCompositoBusiness() {
    }

    //L'aggiunta di un prodotto composito avviene in tre fasi:
    // 1. ArticoloDAO.add
    // 2. ProdottoDAO.add

    public int aggiungi(AppFrame appFrame, String nome, String descrizione, String prezzo, File immagine, String posizione){
        //-----la seguente porzione è uguale all'aggiungi che c'è in ProdottoBusiness------------------------//
        //Si sta solo aggiungendo un nuovo prodotto per il momento
        String[] posizioneArray = posizione.split(" ");
        int idPosizione = PosizioneDAO.getInstance().findByNumbers(Integer.parseInt(posizioneArray[0]),Integer.parseInt(posizioneArray[2]),Integer.parseInt(posizioneArray[4])).getIdPosizione();
        Prodotto prodotto = new Prodotto(nome, descrizione, Float.parseFloat(prezzo), 0,0, idPosizione);
        prodotto.setImmagine(immagine);
        if(ArticoloDAO.getInstance().add(prodotto) == 0){
            return 1;
        }
        prodotto.setIdArticolo(ArticoloDAO.getInstance().findByName(nome).getIdArticolo());
        ProdottoDAO.getInstance().add(prodotto);
        //------------------------------------------------------------------------------------//
        //Adesso se il prodotto è stato creato con successo, è il momento di farlo diventare un prodotto composito
        //Ossia associare prodotti al prodotto appena creato
        if(ProdottoDAO.getInstance().findByName(prodotto.getNome())!=null) {
            //Questo dialog ha il compito di fare gli add alla tabella ProdottoComposito
            CompositeProductDialog.showDialog(appFrame, "Aggiungi prodotto", ProdottoDAO.getInstance().findByName(prodotto.getNome()).getIdArticolo());
        }else{
            return 1;//Il prodotto composito non esiste
        }
        return 0;
    }

    //Viene usato dal CompositeProductDialog per associare prodotti al prodotto composito
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
