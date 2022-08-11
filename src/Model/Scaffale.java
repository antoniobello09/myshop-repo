package Model;

public class Scaffale {

    private int numero;
    private SchedaProdotto prodotto = null;


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public SchedaProdotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(SchedaProdotto prodotto) {
        this.prodotto = prodotto;
    }

}
