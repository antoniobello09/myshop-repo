package Model;

import java.util.ArrayList;

public class SchedaProdotto {

    private int idSchedaProdotto;
    private Prodotto prodotto;
    private int disponibilita = 0;


    public int getIdSchedaProdotto() {
        return idSchedaProdotto;
    }

    public void setIdSchedaProdotto(int idSchedaProdotto) {
        this.idSchedaProdotto = idSchedaProdotto;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public int getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(int disponibilita) {
        this.disponibilita = disponibilita;
    }

}
