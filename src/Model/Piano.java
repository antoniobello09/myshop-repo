package Model;

import java.util.ArrayList;

public class Piano {

    private int numero;
    private ArrayList<Corsia> corsie;

    public Piano(){
        corsie = new ArrayList<>();
    }

    public ArrayList<Corsia> getCorsie() {
        return corsie;
    }

    public void setCorsie(ArrayList<Corsia> corsie) {
        this.corsie = corsie;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
