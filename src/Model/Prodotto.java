package Model;


import java.io.File;
import java.sql.Blob;

public class Prodotto extends Articolo {

    private int idProduttore;
    private int idPosizione;
    private int quantita;
    private int idProdottoCompositoPadre;

    private Fornitore produttore;
    private Prodotto prodottoPadre;

    public Prodotto(){

    }

    public Prodotto(int idArticolo, String nome, String descrizione, Float prezzo, File immagine, int idCategoria, int idProduttore, int idPosizione) {
        super(idArticolo, nome, descrizione, prezzo,immagine, idCategoria);
        this.idProduttore = idProduttore;
        this.idPosizione = idPosizione;
    }

    public Prodotto(int idArticolo, String nome, String descrizione, Float prezzo, int idCategoria, int idProduttore, int idPosizione) {
        super(idArticolo, nome, descrizione, prezzo, idCategoria);
        this.idProduttore = idProduttore;
        this.idPosizione = idPosizione;
    }

    public Prodotto(String nome, String descrizione, Float prezzo, File immagine, int idCategoria, int idProduttore, int idPosizione) {
        super(nome, descrizione, prezzo, immagine, idCategoria);
        this.idProduttore = idProduttore;
        this.idPosizione = idPosizione;
    }




    public Prodotto(String nome, String descrizione, Float prezzo, int idCategoria, int idProduttore, int idPosizione){
        super(nome, descrizione, prezzo, idCategoria);
        this.idProduttore = idProduttore;
        this.idPosizione = idPosizione;
    }

    public Prodotto(String nome, String descrizione, float prezzo, int idPosizione) {
        super(nome, descrizione, prezzo);
        this.idPosizione = idPosizione;
    }

    public Prodotto(int idProdottoCompositoPadre, String nome, String descrizione, float prezzo, int idPosizione, int quantita) {
        super(nome, descrizione, prezzo);
        this.idPosizione = idPosizione;
        this.idProdottoCompositoPadre = idProdottoCompositoPadre;
        this.quantita = quantita;
    }

    public Prodotto(int idProdotto, String nome, String descrizione, float prezzo, int idPosizione) {
        super(idProdotto, nome, descrizione, prezzo);
        this.idPosizione = idPosizione;
    }

    public Prodotto(int idProdotto) {
        setIdArticolo(idProdotto);
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

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getIdProdottoCompositoPadre() {
        return idProdottoCompositoPadre;
    }

    public void setIdProdottoCompositoPadre(int idProdottoCompositoPadre) {
        this.idProdottoCompositoPadre = idProdottoCompositoPadre;
    }
}
