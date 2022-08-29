package Model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Iterator;

public class Servizio extends Articolo implements Cloneable{

    private int idFornitoreServizio;


    public Servizio(int idArticolo, String nome, String descrizione, Float prezzo,Blob immagine, int idCategoria, int idFornitoreServizio) {
        super(idArticolo, nome, descrizione, prezzo, immagine, idCategoria);
        this.idFornitoreServizio = idFornitoreServizio;
    }

    public Servizio( String nome, String descrizione, Float prezzo, Blob immagine, int idCategoria, int idFornitoreServizio) {
        super(nome, descrizione, prezzo, immagine, idCategoria);
        this.idFornitoreServizio = idFornitoreServizio;
    }

    public Servizio( String nome, String descrizione, Float prezzo, int idCategoria, int idFornitoreServizio) {
        super(nome, descrizione, prezzo, idCategoria);
        this.idFornitoreServizio = idFornitoreServizio;
    }

    public Servizio(){

    }

    public int getIdFornitoreServizio() {
        return idFornitoreServizio;
    }

    public void setIdFornitoreServizio(int idFornitoreServizio) {
        this.idFornitoreServizio = idFornitoreServizio;
    }
}
