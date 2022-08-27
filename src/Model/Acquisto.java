package Model;

import java.util.Date;

public class Acquisto {

    private int idAcquisto;
    private int idPuntoVendita;
    private Date data;
    private int idLista;

    public Acquisto(int idAcquisto, int idPuntoVendita, Date data) {
        this.idAcquisto = idAcquisto;
        this.idPuntoVendita = idPuntoVendita;
        this.data = data;
    }

    public Acquisto(int idPuntoVendita, Date data, int idLista) {
        this.idPuntoVendita = idPuntoVendita;
        this.data = data;
        this.idLista = idLista;
    }

    public Acquisto() {

    }

    public int getIdAcquisto() {
        return idAcquisto;
    }

    public void setIdAcquisto(int idAcquisto) {
        this.idAcquisto = idAcquisto;
    }

    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }
}
