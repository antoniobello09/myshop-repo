package Model;

import Model.Other.ICategoria;

import java.sql.Blob;
import java.util.List;

public class Articolo {

    private int idArticolo;
    protected Float prezzo;
    private String nome;
    private String descrizione;
    private Blob immagine;
    private int idCategoria;

    public Articolo(int idArticolo, Float prezzo, String nome, String descrizione, Blob immagine, int idCategoria) {
        this.idArticolo = idArticolo;
        this.prezzo = prezzo;
        this.nome = nome;
        this.descrizione = descrizione;
        this.immagine = immagine;
        this.idCategoria = idCategoria;
    }

    public Articolo(Float prezzo, String nome, String descrizione, Blob immagine, int idCategoria) {
        this.prezzo = prezzo;
        this.nome = nome;
        this.descrizione = descrizione;
        this.immagine = immagine;
        this.idCategoria = idCategoria;
    }

    public Articolo(String nome, String descrizione, Float prezzo, int idCategoria) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.idCategoria = idCategoria;
    }

    public Articolo(){

    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Blob getImmagine() {
        return immagine;
    }

    public void setImmagine(Blob immagine) {
        this.immagine = immagine;
    }

    public int getIdArticolo() {
        return idArticolo;
    }

    public void setIdArticolo(int idArticolo) {
        this.idArticolo = idArticolo;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
