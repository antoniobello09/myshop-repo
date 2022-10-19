package Model;

import java.io.File;
import java.sql.Blob;

public class Articolo {

    private int idArticolo;
    private String nome;
    private String descrizione;
    private Float prezzo;
    private File immagine;
    private int idCategoria;

    public Articolo(int idArticolo, String nome, String descrizione, Float prezzo, File immagine, int idCategoria) {
        this.idArticolo = idArticolo;
        this.prezzo = prezzo;
        this.nome = nome;
        this.descrizione = descrizione;
        this.immagine = immagine;
        this.idCategoria = idCategoria;
    }

    public Articolo(int idArticolo, String nome, String descrizione, Float prezzo, int idCategoria) {
        this.idArticolo = idArticolo;
        this.prezzo = prezzo;
        this.nome = nome;
        this.descrizione = descrizione;
        this.idCategoria = idCategoria;
    }

    public Articolo(String nome, String descrizione, Float prezzo, File immagine, int idCategoria) {
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

    public Articolo(String nome, String descrizione, Float prezzo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
    }

    public Articolo(int idArticolo, String nome, String descrizione, Float prezzo) {
        this.idArticolo = idArticolo;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
    }

    public Articolo(int idArticolo) {
        this.idArticolo = idArticolo;
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

    public File getImmagine() {
        return immagine;
    }

    public void setImmagine(File immagine) {
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
