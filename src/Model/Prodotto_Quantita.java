package Model;

public class Prodotto_Quantita extends Prodotto implements IProdotto, Cloneable{

    private int quantita;
    private float totale;

    public Prodotto_Quantita(Prodotto prodotto, int quantita) {
        super(prodotto);
        this.quantita = quantita;
    }

    public Prodotto_Quantita(Prodotto prodotto) {
        super(prodotto);
    }

    public Prodotto_Quantita(){

    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    @Override
    public Float getPrezzo() {
        return super.getPrezzo();
    }

    public void increment(){
        quantita++;
    }

    public void addQuantity(int q){
        quantita = quantita + q;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Prodotto_Quantita((Prodotto)super.clone(), quantita);
    }

    public float getTotale(){
        return quantita*getPrezzo();
    }
}
