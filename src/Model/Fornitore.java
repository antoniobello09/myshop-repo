package Model;


public class Fornitore{

    private int idFornitore;
    private String nome;
    private String sitoweb;
    private String citta;
    private String nazione;
    private String prod_serv;

    public Fornitore(int idFornitore, String nome, String sitoweb, String citta, String nazione,String prod_serv) {
        this.idFornitore = idFornitore;
        this.nome = nome;
        this.sitoweb = sitoweb;
        this.citta = citta;
        this.nazione = nazione;
        this.prod_serv = prod_serv;
    }

    public Fornitore(String nome, String sitoweb, String citta, String nazione, String prod_serv) {
        this.nome = nome;
        this.sitoweb = sitoweb;
        this.citta = citta;
        this.nazione = nazione;
        this.prod_serv = prod_serv;
    }

    public Fornitore(String nome) {
        this.nome = nome;
    }

    public Fornitore() {

    }

    public int getIdFornitore() {
        return idFornitore;
    }

    public void setIdFornitore(int idFornitore) {
        this.idFornitore = idFornitore;
    }

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

    public String getProd_serv() {
        return prod_serv;
    }

    public void setProd_serv(String prod_serv) {
        this.prod_serv = prod_serv;
    }
}
