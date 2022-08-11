package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Corsia {

    private int numero;
    private ArrayList<Scaffale> scaffali = new ArrayList<>();

    public Corsia() {
        scaffali = new ArrayList<>();
    }

    public ArrayList<SchedaProdotto> getAllProducts() {
        ArrayList<SchedaProdotto> prodottiInCorsia = new ArrayList<>();
        Iterator<Scaffale> i = scaffali.iterator();
        while(i.hasNext()) {
            Scaffale s = i.next();
            prodottiInCorsia.add(s.getProdotto());
        }
        return prodottiInCorsia;
    }

    public ArrayList<Scaffale> getScaffali() {
        return scaffali;
    }

    public void setScaffali(ArrayList<Scaffale> scaffali) {
        this.scaffali = scaffali;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
