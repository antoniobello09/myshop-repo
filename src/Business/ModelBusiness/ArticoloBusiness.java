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

    public int cancella(String nomeArticolo){
        Articolo articolo = ArticoloDAO.getInstance().findByName(nomeArticolo);
        if (articolo != null) {
            Prodotto prodotto = ProdottoDAO.getInstance().findByID(articolo.getIdArticolo());
            ProdottoComposito prodottoComposito = ProdottoCompositoDAO.getInstance().findByID(articolo.getIdArticolo());
            Servizio servizio = ServizioBusiness.getInstance().cercaIDServizio(articolo.getIdArticolo());
            if (ArticoloDAO.getInstance().isServizio(articolo)) {
                ServizioDAO.getInstance().delete(servizio);
                ArticoloDAO.getInstance().delete(servizio);
            } else if (ArticoloDAO.getInstance().isProdottoComposito(articolo)) {
                ProdottoCompositoDAO.getInstance().delete(prodottoComposito);
                ProdottoDAO.getInstance().delete(prodottoComposito);
                ArticoloDAO.getInstance().delete(articolo);
            } else if (ArticoloDAO.getInstance().isProdotto(articolo)) {
                ProdottoDAO.getInstance().delete(prodotto);
                ArticoloDAO.getInstance().delete(articolo);
            }
        } else {
           return 1;
        }
        return 0;
    }

    public int modifica(AppFrame appFrame, String nomeArticolo){
        this.appFrame = appFrame;
        Articolo articolo = ArticoloDAO.getInstance().findByName(nomeArticolo);
        if(articolo != null){
            if(ArticoloDAO.getInstance().isServizio(articolo)){
                modificaServizio(articolo.getIdArticolo());
            }else if(ArticoloDAO.getInstance().isProdottoComposito(articolo)){
                modificaProdottoComposito(articolo.getIdArticolo());
            }else if(ArticoloDAO.getInstance().isProdotto(articolo)){
                modificaProdotto(articolo.getIdArticolo());
            }
        }else{
            return 1;
        }
        return 0;
    }

    public void modificaServizio(int idServizio){
        Servizio servizio = ServizioDAO.getInstance().findByID(idServizio);
        String nomeCategoria = CategoriaServizioDAO.getInstance().findByID(servizio.getIdCategoria()).getNome();
        String nomeFornitoreServizio = FornitoreDAO.getInstance().findByID(servizio.getIdFornitoreServizio()).getNome();
        ArrayList<String> risultati = ModifyServiceDialog.showDialog(appFrame,
                "Modifica servizio",
                servizio.getNome(), servizio.getDescrizione(), String.valueOf(servizio.getPrezzo()),
                nomeCategoria, servizio.getImmagine(), nomeFornitoreServizio);
        int idCategoriaAggiornata = CategoriaServizioDAO.getInstance().findByName(risultati.get(3)).getIdCategoria();
        int idFornitoreAggiornato = FornitoreDAO.getInstance().findByName(risultati.get(4)).getIdFornitore();
        servizio.setNome(risultati.get(0));
        servizio.setDescrizione(risultati.get(1));
        servizio.setPrezzo(Float.valueOf(risultati.get(2)));
        servizio.setIdFornitoreServizio(idFornitoreAggiornato);
        servizio.setIdCategoria(idCategoriaAggiornata);
        servizio.setImmagine(ModifyServiceDialog.getImmagine());
        ServizioDAO.getInstance().update(servizio);
        ArticoloDAO.getInstance().update(servizio);
    }

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
