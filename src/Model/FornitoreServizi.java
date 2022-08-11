package Model;

import View.Nameable;

public class FornitoreServizi implements Nameable, Cloneable {

        private int idFornitore;
        private String nome;
        private String sitoweb;
        private String citta;
        private String nazione;

    public FornitoreServizi(int idFornitore, String nome, String sitoweb, String citta, String nazione) {
            this.idFornitore = idFornitore;
            this.nome = nome;
            this.sitoweb = sitoweb;
            this.citta = citta;
            this.nazione = nazione;
        }

    public FornitoreServizi(String nome, String sitoweb, String citta, String nazione) {
            this.nome = nome;
            this.sitoweb = sitoweb;
            this.citta = citta;
            this.nazione = nazione;
        }

    public FornitoreServizi(String nome) {
            this.nome = nome;
        }

    public FornitoreServizi() {

    }

    public int getIdFornitore() {
        return idFornitore;
    }

    public void setIdFornitore(int idFornitore) {
        this.idFornitore = idFornitore;
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
        return new FornitoreServizi(nome, sitoweb, citta, nazione);
    }
}
