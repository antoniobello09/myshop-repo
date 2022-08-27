package Model;


import java.util.Date;

public class FeedBack {

    private int idFeedBack;
    private int idAcquisto;
    private int idArticolo;
    private String commento;
    private int indiceGradimento;
    private String risposta;
    private Date data;


    public FeedBack() {

    }

    public FeedBack(int idFeedBack, int idAcquisto, int idArticolo, String commento, int indiceGradimento, String risposta, Date data) {
        this.idFeedBack = idFeedBack;
        this.idAcquisto= idAcquisto;
        this.idArticolo = idArticolo;
        this.commento = commento;
        this.indiceGradimento = indiceGradimento;
        this.risposta = risposta;
        this.data = data;
    }

    public FeedBack(int idAcquisto, int idArticolo, String commento, int indiceGradimento, String risposta, Date data) {
        this.idAcquisto = idAcquisto;
        this.idArticolo = idArticolo;
        this.commento = commento;
        this.indiceGradimento = indiceGradimento;
        this.risposta = risposta;
        this.data = data;
    }

    public int getIdFeedBack() {
        return idFeedBack;
    }

    public void setIdFeedBack(int idFeedBack) {
        this.idFeedBack = idFeedBack;
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

    public int getIndiceGradimento() {
        return indiceGradimento;
    }

    public void setIndiceGradimento(int indiceGradimento) {
        this.indiceGradimento = indiceGradimento;
    }


    public String getRisposta() {
        return risposta;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
