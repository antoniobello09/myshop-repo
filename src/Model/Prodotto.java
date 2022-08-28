package Model;


import Model.Other.IProdotto;

import java.sql.Blob;

public class Prodotto extends Articolo implements IProdotto, Cloneable {

    private int idProduttore;
    private int idPosizione;


    public Prodotto(){

    }

    public Prodotto(int idArticolo, Float prezzo, String nome, String descrizione, Blob immagine, int idCategoria, int idProduttore, int idPosizione) {
        super(idArticolo, prezzo, nome, descrizione, immagine, idCategoria);
        this.idProduttore = idProduttore;
        this.idPosizione = idPosizione;
    }

    public Prodotto(Float prezzo, String nome, String descrizione, Blob immagine, int idCategoria, int idProduttore, int idPosizione) {
        super(prezzo, nome, descrizione, immagine, idCategoria);
        this.idProduttore = idProduttore;
        this.idPosizione = idPosizione;
    }

    public Prodotto(String nome, String descrizione, Float prezzo, int idCategoria, int idProduttore, int idPosizione){
        super(nome, descrizione, prezzo, idCategoria);
        this.idProduttore = idProduttore;
        this.idPosizione = idPosizione;
    }

    public int getIdProduttore() {
        return idProduttore;
    }

    public void setIdProduttore(int idProduttore) {
        this.idProduttore = idProduttore;
    }

    public int getIdPosizione() {
        return idPosizione;
    }

    public void setIdPosizione(int idPosizione) {
        this.idPosizione = idPosizione;
    }
}
