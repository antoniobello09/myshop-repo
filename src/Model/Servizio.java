package Model;

import java.io.File;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Iterator;

public class Servizio extends Articolo implements Cloneable{

    private int idFornitoreServizio;

    private Fornitore fornitoreServizio;

    public Servizio(int idArticolo, String nome, String descrizione, Float prezzo, File immagine, int idCategoria, int idFornitoreServizio) {
        super(idArticolo, nome, descrizione, prezzo, immagine, idCategoria);
        this.idFornitoreServizio = idFornitoreServizio;
    }

    public Servizio(String nome, String descrizione, Float prezzo, File immagine, int idCategoria, int idFornitoreServizio) {
        super(nome, descrizione, prezzo, immagine, idCategoria);
        this.idFornitoreServizio = idFornitoreServizio;
    }

    public Servizio( String nome, String descrizione, Float prezzo, int idCategoria, int idFornitoreServizio) {
        super(nome, descrizione, prezzo, idCategoria);
        this.idFornitoreServizio = idFornitoreServizio;
    }

    public Servizio(int idArticolo, String nome, String descrizione, Float prezzo, int idCategoria, int idFornitoreServizio) {
        super(idArticolo, nome, descrizione, prezzo, idCategoria);
        this.idFornitoreServizio = idFornitoreServizio;
    }

    public Servizio(){

    }

    public Servizio(int idServizio, int idFornitoreServizio) {
        this.setIdArticolo(idServizio);
        this.setIdFornitoreServizio(idFornitoreServizio);
    }

    public int getIdFornitoreServizio() {
        return idFornitoreServizio;
    }

    public void setIdFornitoreServizio(int idFornitoreServizio) {
        this.idFornitoreServizio = idFornitoreServizio;
    }
}
