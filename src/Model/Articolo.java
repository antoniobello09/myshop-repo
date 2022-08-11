package Model;

import java.sql.Blob;
import java.util.List;

public class Articolo {

    private int idArticolo;
    protected Float prezzo;
    private String nome;
    private String descrizione;
    private Blob immagine;
    private List<FeedBack> feedbacks;

    public Articolo(Articolo articolo){
        this.idArticolo = articolo.getIdArticolo();
        this.nome = articolo.getNome();
        this.descrizione = articolo.getDescrizione();
        this.prezzo = articolo.getPrezzo();
        this.immagine = articolo.getImmagine();
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

    public int getId() {
        return idArticolo;
    }

    public void setId(int idArticolo) {
        this.idArticolo = idArticolo;
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

    public List<FeedBack> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedBack> feedbacks) {
        this.feedbacks = feedbacks;
    }




}
