package Model;

public class PuntoVendita {

    private int idPuntoVendita;
    private int idManager;
    private String citta;
    private String indirizzo;

    public PuntoVendita() {
    }

    public PuntoVendita(int idManager, String citta, String indirizzo) {
        this.idManager = idManager;
        this.citta = citta;
        this.indirizzo = indirizzo;
    }

    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }
}
