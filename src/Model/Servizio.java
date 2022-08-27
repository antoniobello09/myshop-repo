package Model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Iterator;

public class Servizio extends Articolo implements Cloneable{

    private int idFornitoreServizio;


    public Servizio(int idArticolo, Float prezzo, String nome, String descrizione, Blob immagine, int idCategoria, int idFornitoreServizio) {
        super(idArticolo, prezzo, nome, descrizione, immagine, idCategoria);
        this.idFornitoreServizio = idFornitoreServizio;
    }

    public Servizio(Float prezzo, String nome, String descrizione, Blob immagine, int idCategoria, int idFornitoreServizio) {
        super(prezzo, nome, descrizione, immagine, idCategoria);
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
