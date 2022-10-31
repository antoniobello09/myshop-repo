package Model;

import java.io.File;
import java.sql.Blob;
import java.util.ArrayList;

public class ProdottoComposito extends Prodotto {

    private ArrayList<Prodotto> sottoprodotti = new ArrayList<>();

    public ProdottoComposito() {

    }
    public ProdottoComposito(Prodotto prodotto){
        super(prodotto.getIdArticolo(), prodotto.getNome(), prodotto.getDescrizione(), prodotto.getPrezzo(), prodotto.getIdPosizione());
    }

    public ProdottoComposito(int idArticolo, String nome, String descrizione, Float prezzo, File immagine, int idCategoria, int idProduttore, int idPosizione, ArrayList<Prodotto> sottoprodotti) {
        super(idArticolo, nome, descrizione, prezzo,immagine, idCategoria, idProduttore, idPosizione);
        this.sottoprodotti = sottoprodotti;
    }

    public ProdottoComposito(String nome, String descrizione, Float prezzo, File immagine, int idCategoria, int idProduttore, int idPosizione, ArrayList<Prodotto> sottoprodotti) {
        super(nome, descrizione, prezzo,immagine, idCategoria, idProduttore, idPosizione);
        this.sottoprodotti = sottoprodotti;
    }

    public ArrayList<Prodotto> getSottoprodotti() {
        return sottoprodotti;
    }

    public ProdottoComposito(int idArticolo, String nome, String descrizione, Float prezzo, int idCategoria, int idProduttore, int idPosizione) {
        super(idArticolo, nome, descrizione, prezzo, idCategoria, idProduttore, idPosizione);
    }

    public ProdottoComposito(int idProdotto, String nome, String descrizione, float prezzo, int idPosizione) {
        super(idProdotto, nome, descrizione, prezzo, idPosizione);
    }

    public void setSottoprodotti(ArrayList<Prodotto> sottoprodotti) {
        this.sottoprodotti = sottoprodotti;
    }


}
