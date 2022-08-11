package Model;

import java.util.ArrayList;
import java.util.List;

public class Lista {

    private int idLista;
    private String nome;
    private int idPuntoVendita;
    private int idCliente;
    private ArrayList<Prodotto_Quantita> prodotti = new ArrayList<>();
    private ArrayList<Servizio> servizi = new ArrayList<>();
    private Acquisto acquisto;
    private boolean acquistato;
    private float totale = 0;

    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
    }

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Prodotto_Quantita> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<Prodotto_Quantita> prodotti) {
        this.prodotti = prodotti;
    }

    public ArrayList<Servizio> getServizi() {
        return servizi;
    }

    public void setServizi(ArrayList<Servizio> servizi) {
        this.servizi = servizi;
    }

    public Acquisto getAcquisto() {
        return acquisto;
    }

    public void setAcquisto(Acquisto acquisto) {
        this.acquisto = acquisto;
    }

    public boolean isAcquistato() {
        return acquistato;
    }

    public void setAcquistato(boolean acquistato) {
        this.acquistato = acquistato;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public float getTotale(){
        for(int i=0;i<prodotti.size();i++){
            totale += prodotti.get(i).getTotale();
        }
        for(int i=0;i<servizi.size();i++){
            totale += servizi.get(i).getPrezzo();
        }
        return totale;
    }
}
