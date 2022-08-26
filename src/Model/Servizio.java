package Model;

import java.util.ArrayList;
import java.util.Iterator;

public class Servizio extends Articolo implements Cloneable{

    private int idCategoria;
    private int idFornitoreServizio;


    public Servizio(Servizio servizio) {
        super(servizio);
        this.idCategoria = servizio.getIdCategoria();
    }

    public Servizio(){

    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdFornitoreServizio() {
        return idFornitoreServizio;
    }

    public void setIdFornitoreServizio(int idFornitoreServizio) {
        this.idFornitoreServizio = idFornitoreServizio;
    }
}
