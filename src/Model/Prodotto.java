package Model;



public class Prodotto extends Articolo implements IProdotto, Cloneable {

    private CategoriaProdotto categoria;
    private Produttore produttore;


    public Prodotto(){

    }

    public Prodotto(Prodotto prodotto){
        super(prodotto);
        this.categoria = prodotto.getCategoria();
        this.produttore = prodotto.getProduttore();
    }

    public CategoriaProdotto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProdotto categoria) {
        this.categoria = categoria;
    }

    @Override
    public Float getPrezzo() {
        return prezzo;
    }

    public Produttore getProduttore() {
        return produttore;
    }

    public void setProduttore(Produttore produttore) {
        this.produttore = produttore;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Prodotto p = new Prodotto(this);
        p.setCategoria((CategoriaProdotto) categoria.clone());
        p.setProduttore((Produttore) produttore.clone());
        return p;
    }
}
