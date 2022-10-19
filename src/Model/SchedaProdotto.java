package Model;

import java.util.ArrayList;

public class SchedaProdotto {

    private int idSchedaProdotto;
    private int idProdotto;
    private int disponibilita = 0;
    private int idPuntoVendita;

    private Prodotto prodotto;
    private PuntoVendita puntoVendita;

    public SchedaProdotto() {
    }

    public SchedaProdotto(int idProdotto, int disponibilita, int idPuntoVendita) {
        this.idProdotto = idProdotto;
        this.disponibilita = disponibilita;
        this.idPuntoVendita = idPuntoVendita;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public PuntoVendita getPuntoVendita() {
        return puntoVendita;
    }

    public void setPuntoVendita(PuntoVendita puntoVendita) {
        this.puntoVendita = puntoVendita;
    }

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

    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
    }
}
