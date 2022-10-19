package Model;

public class Prenotazione {

    private int idPrenotazione;
    private int idProdotto;
    private int quantita_prenotata;
    private int idAcquisto;

    public Prenotazione() {
    }

    public Prenotazione(int idPrenotazione, int idProdotto, int quantita_prenotata, int idAcquisto) {
        this.idPrenotazione = idPrenotazione;
        this.idProdotto = idProdotto;
        this.quantita_prenotata = quantita_prenotata;
        this.idAcquisto = idAcquisto;
    }

    public Prenotazione(int idProdotto, int quantita_prenotata, int idAcquisto) {
        this.idProdotto = idProdotto;
        this.quantita_prenotata = quantita_prenotata;
        this.idAcquisto = idAcquisto;
    }

    public int getIdPrenotazione() {
        return idPrenotazione;
    }

    public void setIdPrenotazione(int idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    public int getQuantita_prenotata() {
        return quantita_prenotata;
    }

    public void setQuantita_prenotata(int quantita_prenotata) {
        this.quantita_prenotata = quantita_prenotata;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public int getIdAcquisto() {
        return idAcquisto;
    }

    public void setIdAcquisto(int idAcquisto) {
        this.idAcquisto = idAcquisto;
    }
}
