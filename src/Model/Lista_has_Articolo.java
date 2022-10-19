package Model;

public class Lista_has_Articolo {

    private int idLista;
    private int idArticolo;
    private int quantita;

    private Articolo articolo;

    public Lista_has_Articolo(int idLista, int idArticolo, int quantita) {
        this.idLista = idLista;
        this.idArticolo = idArticolo;
        this.quantita = quantita;
    }

    public Lista_has_Articolo() {
    }

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public int getIdArticolo() {
        return idArticolo;
    }

    public void setIdArticolo(int idArticolo) {
        this.idArticolo = idArticolo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
