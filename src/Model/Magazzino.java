package Model;

import java.util.ArrayList;
import java.util.List;

public class Magazzino {

    private int idMagazzino;
    private ArrayList<Piano> piani;

    private ArrayList<SchedaProdotto> prodottiSenzaPosizione = new ArrayList<>();

    public Magazzino() {
        piani = new ArrayList<>();
    }

    public ArrayList<Piano> getPiani() {
        return piani;
    }

    public void setPiani(ArrayList<Piano> piani) {
        this.piani = piani;
    }

    public ArrayList<SchedaProdotto> getProdottiSenzaPosizione() {
        return prodottiSenzaPosizione;
    }

    public void setProdottiSenzaPosizione(ArrayList<SchedaProdotto> prodottiSenzaPosizione) {
        this.prodottiSenzaPosizione = prodottiSenzaPosizione;
    }

    public int getIdMagazzino() {
        return idMagazzino;
    }

    public void setIdMagazzino(int idMagazzino) {
        this.idMagazzino = idMagazzino;
    }

    //public ArrayList<Prodotto> getAllProductsCorsia(Piano piano, Corsia corsia);

    //public ArrayList<Prodotto> getAllProductsPiano(Piano piano);
}
