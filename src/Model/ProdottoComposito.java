package Model;

import Model.Other.IProdotto;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ProdottoComposito extends Prodotto implements IProdotto {

    private ArrayList<Prodotto_Quantita> sottoprodotti = new ArrayList<>();

    public ProdottoComposito() {

    }

    public ProdottoComposito(int idArticolo, String nome, String descrizione, Float prezzo, Blob immagine, int idCategoria, int idProduttore, int idPosizione, ArrayList<Prodotto_Quantita> sottoprodotti) {
        super(idArticolo, nome, descrizione, prezzo,immagine, idCategoria, idProduttore, idPosizione);
        this.sottoprodotti = sottoprodotti;
    }

    public ProdottoComposito(String nome, String descrizione, Float prezzo, Blob immagine, int idCategoria, int idProduttore, int idPosizione, ArrayList<Prodotto_Quantita> sottoprodotti) {
        super(nome, descrizione, prezzo,immagine, idCategoria, idProduttore, idPosizione);
        this.sottoprodotti = sottoprodotti;
    }

    public ArrayList<Prodotto_Quantita> getSottoprodotti() {
        return sottoprodotti;
    }

    public void setSottoprodotti(ArrayList<Prodotto_Quantita> sottoprodotti) {
        this.sottoprodotti = sottoprodotti;
    }
}
