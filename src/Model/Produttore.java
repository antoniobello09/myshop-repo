package Model;


import View.Nameable;

import javax.naming.Name;

public class Produttore implements Nameable, Cloneable {

    private int idProduttore;
    private String nome;
    private String sitoweb;
    private String citta;
    private String nazione;

    public Produttore(int idProduttore, String nome, String sitoweb, String citta, String nazione) {
        this.idProduttore = idProduttore;
        this.nome = nome;
        this.sitoweb = sitoweb;
        this.citta = citta;
        this.nazione = nazione;
    }

    public Produttore(String nome, String sitoweb, String citta, String nazione) {
        this.nome = nome;
        this.sitoweb = sitoweb;
        this.citta = citta;
        this.nazione = nazione;
    }

    public Produttore(String nome) {
        this.nome = nome;
    }

    public Produttore() {

    }

    public int getIdProduttore() {
        return idProduttore;
    }

    public void setIdProduttore(int idProduttore) {
        this.idProduttore = idProduttore;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSitoweb() {
        return sitoweb;
    }

    public void setSitoweb(String sitoweb) {
        this.sitoweb = sitoweb;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    @Override
    public Object clone(){
        return new Produttore(nome, sitoweb, citta, nazione);
    }
}
