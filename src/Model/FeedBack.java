package Model;

import java.util.Date;

public class FeedBack {

    private int idFeedBack;
    private int idCliente;
    private int idAcquisto;
    private int idArticolo;
    private String commento;
    private int punteggio;
    private Risposta risposta;

    public int getId() {
        return idFeedBack;
    }

    public void setId(int idFeedBack) {
        this.idFeedBack = idFeedBack;
    }

    public int getIdFeedBack() {
        return idFeedBack;
    }

    public void setIdFeedBack(int idFeedBack) {
        this.idFeedBack = idFeedBack;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdAcquisto() {
        return idAcquisto;
    }

    public void setIdAcquisto(int idAcquisto) {
        this.idAcquisto = idAcquisto;
    }

    public int getIdArticolo() {
        return idArticolo;
    }

    public void setIdArticolo(int idArticolo) {
        this.idArticolo = idArticolo;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }


    public Risposta getRisposta() {
        return risposta;
    }

    public void setRisposta(Risposta risposta) {
        this.risposta = risposta;
    }
}
