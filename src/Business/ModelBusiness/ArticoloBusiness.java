package Business.ModelBusiness;

import DAO.Classi.*;
import Model.*;
import View.AppFrame;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.ModifyCompositeProductDialog;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.ModifyProductDialog;
import View.Panels.Center.Amministratore.GestioneArticoliPanels.Altro.ModifyServiceDialog;

import javax.swing.*;
import java.util.ArrayList;

public class ArticoloBusiness {

    private static ArticoloBusiness instance;
    private static AppFrame appFrame;

    public static synchronized ArticoloBusiness getInstance() {
        if(instance == null) instance = new ArticoloBusiness();
        return instance;
    }

    private ArticoloBusiness() {
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

    //Cancella un articolo conoscendone il nome
    public int cancella(String nomeArticolo){

        //La cancellazione di un articolo si divide in due fasi:
        // 1. ServizioDAO.delete / ProdottoDAO.delete per cancellarlo dalla tabella servizio/prodotto
        // 2. ArticoloDAO.delete per cancellarlo dalla tabella articolo
        // per il prodotto composito va anche eliminato dalla tabella ProdottoComposito

        //Recupero l'articolo
        Articolo articolo = ArticoloDAO.getInstance().findByName(nomeArticolo);
        if (articolo != null) { //L'articolo esiste?
            //L'articolo è un servizio, prodotto o prodotto composito?
            if (ArticoloDAO.getInstance().isServizio(articolo)) {
                //E' un servizio quindi creo un oggetto servizio e poi faccio delete
                Servizio servizio = ServizioDAO.getInstance().findByID(articolo.getIdArticolo());
                ServizioDAO.getInstance().delete(servizio);
                ArticoloDAO.getInstance().delete(servizio);
            } else if (ArticoloDAO.getInstance().isProdottoComposito(articolo)) {
                //E' un prodotto composito quidni creo un oggetto prodotto composito e poi faccio delete
                ProdottoComposito prodottoComposito = ProdottoCompositoDAO.getInstance().findByID(articolo.getIdArticolo());
                ProdottoCompositoDAO.getInstance().delete(prodottoComposito);
                ProdottoDAO.getInstance().delete(prodottoComposito);
                ArticoloDAO.getInstance().delete(articolo);
            } else if (ArticoloDAO.getInstance().isProdotto(articolo)) {
                //E' un prodotto quidni creo un oggetto prodotto e poi faccio delete
                Prodotto prodotto = ProdottoDAO.getInstance().findByID(articolo.getIdArticolo());
                ProdottoDAO.getInstance().delete(prodotto);
                ArticoloDAO.getInstance().delete(articolo);
            }
        } else {
           return 1;//Errore : l'articolo non esiste
        }
        return 0;//Cancellazione andata a buon fine
    }

    public int modifica(AppFrame appFrame, String nomeArticolo){
        ArticoloBusiness.appFrame = appFrame;
        //recupero l'articolo dal nomeArticolo
        Articolo articolo = ArticoloDAO.getInstance().findByName(nomeArticolo);
        if(articolo != null){//L'articolo esiste?

            //in base al tipo di articolo faccio partire un dialog per la modifica
            if(ArticoloDAO.getInstance().isServizio(articolo)){
                modificaServizio(articolo.getIdArticolo());
            }else if(ArticoloDAO.getInstance().isProdottoComposito(articolo)){
                modificaProdottoComposito(articolo.getIdArticolo());
            }else if(ArticoloDAO.getInstance().isProdotto(articolo)){
                modificaProdotto(articolo.getIdArticolo());
            }
        }else{
            return 1;//L'articolo non esiste
        }
        return 0;
    }

    public void modificaServizio(int idServizio){
        //Recuper il servizip
        Servizio servizio = ServizioDAO.getInstance().findByID(idServizio);
        //Nel dialog voglio mostrare i campi già compilati pertanto mi servono i nomi delle categorie, del produttore ecc.
        String nomeCategoria = CategoriaServizioDAO.getInstance().findByID(servizio.getIdCategoria()).getNome();
        String nomeFornitoreServizio = FornitoreDAO.getInstance().findByID(servizio.getIdFornitoreServizio()).getNome();
        //Questo dialog ha come parametri le informazioni relative al serviio da mofìdificare
        //Restistuisce un array di nuove informazioni
        ArrayList<String> risultati = ModifyServiceDialog.showDialog(appFrame,
                "Modifica servizio",
                servizio.getNome(), servizio.getDescrizione(), String.valueOf(servizio.getPrezzo()),
                nomeCategoria, servizio.getImmagine(), nomeFornitoreServizio);
        //recupero gli id dall'array di nuove informazioni
        int idCategoriaAggiornata = CategoriaServizioDAO.getInstance().findByName(risultati.get(3)).getIdCategoria();
        int idFornitoreAggiornato = FornitoreDAO.getInstance().findByName(risultati.get(4)).getIdFornitore();
        //Preparo iil servizio per poi fare l'update
        servizio.setNome(risultati.get(0));
        servizio.setDescrizione(risultati.get(1));
        servizio.setPrezzo(Float.valueOf(risultati.get(2)));
        servizio.setIdFornitoreServizio(idFornitoreAggiornato);
        servizio.setIdCategoria(idCategoriaAggiornata);
        servizio.setImmagine(ModifyServiceDialog.getImmagine());
        //Update in due fasi
        ServizioDAO.getInstance().update(servizio);
        ArticoloDAO.getInstance().update(servizio);
    }

    //Stessi commenti fatti per il modifica Servizio
    public void modificaProdotto(int idProdotto){
        Prodotto prodotto = ProdottoDAO.getInstance().findByID(idProdotto);
        String nomeCategoria = CategoriaProdottoDAO.getInstance().findByID(CategoriaProdottoDAO.getInstance().findByID(prodotto.getIdCategoria()).getIdCategoriaPadre()).getNome();
        String nomeSottoCategoria = CategoriaProdottoDAO.getInstance().findByID(prodotto.getIdCategoria()).getNome();
        String nomeProduttore = FornitoreDAO.getInstance().findByID(prodotto.getIdProduttore()).getNome();
        String posizione = PosizioneBusiness.getInstance().getPosizioneStringa(PosizioneDAO.getInstance().findByID(prodotto.getIdPosizione()));
        ArrayList<String> risultati = ModifyProductDialog.showDialog(appFrame,
                "Modifica Prodotto",
                prodotto.getNome(), prodotto.getDescrizione(), String.valueOf(prodotto.getPrezzo()),
                nomeCategoria, nomeSottoCategoria, prodotto.getImmagine(), nomeProduttore, posizione);
        int idCategoriaAggiornata = CategoriaProdottoDAO.getInstance().findByName(risultati.get(3)).getIdCategoria();
        int idProduttoreAggiornato = FornitoreDAO.getInstance().findByName(risultati.get(4)).getIdFornitore();
        String[] posizioni = risultati.get(5).split(" ");
        int idPosizioneAggiornato = PosizioneDAO.getInstance().findByNumbers(Integer.parseInt(posizioni[0]), Integer.parseInt(posizioni[2]), Integer.parseInt(posizioni[4])).getIdPosizione();
        prodotto.setNome(risultati.get(0));
        prodotto.setDescrizione(risultati.get(1));
        prodotto.setPrezzo(Float.valueOf(risultati.get(2)));
        prodotto.setIdProduttore(idProduttoreAggiornato);
        prodotto.setIdCategoria(idCategoriaAggiornata);
        prodotto.setIdPosizione(idPosizioneAggiornato);
        prodotto.setImmagine(ModifyProductDialog.getImmagine());
        ProdottoDAO.getInstance().update(prodotto);
        ArticoloDAO.getInstance().update(prodotto);
    }

    //Stessi commenti fatti per il modifica prodotto composito
    public void modificaProdottoComposito(int idProdottoComposito){
        Prodotto prodotto = ProdottoDAO.getInstance().findByID(idProdottoComposito);
        String posizione = PosizioneBusiness.getInstance().getPosizioneStringa(PosizioneDAO.getInstance().findByID(prodotto.getIdPosizione()));
        ArrayList<String> risultati = ModifyCompositeProductDialog.showDialog(appFrame,
                "Modifica prodotto composito",
                prodotto.getNome(), prodotto.getDescrizione(), String.valueOf(prodotto.getPrezzo()), prodotto.getImmagine(), posizione);
        String[] posizioni = risultati.get(3).split(" ");
        int idPosizioneAggiornato = PosizioneDAO.getInstance().findByNumbers(Integer.parseInt(posizioni[0]), Integer.parseInt(posizioni[2]), Integer.parseInt(posizioni[4])).getIdPosizione();
        prodotto.setNome(risultati.get(0));
        prodotto.setDescrizione(risultati.get(1));
        prodotto.setPrezzo(Float.valueOf(risultati.get(2)));
        prodotto.setIdProduttore(0);
        prodotto.setIdCategoria(0);
        prodotto.setIdPosizione(idPosizioneAggiornato);
        prodotto.setImmagine(ModifyCompositeProductDialog.getImmagine());
        ProdottoDAO.getInstance().update(prodotto);
        ArticoloDAO.getInstance().update(prodotto);
    }




}
