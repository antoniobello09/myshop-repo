package Model;



public class Prodotto extends Articolo implements IProdotto, Cloneable {

    private int idCategoria;
    private int idProduttore;
    private int idPosizione;


    public Prodotto(){

    }

    public Prodotto(Prodotto prodotto){
        super(prodotto);
        this.idCategoria = prodotto.getIdCategoria();
        this.idPosizione = prodotto.getIdPosizione();
        this.idProduttore = prodotto.getIdProduttore();
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdProduttore() {
        return idProduttore;
    }

    public void setIdProduttore(int idProduttore) {
        this.idProduttore = idProduttore;
    }

    public int getIdPosizione() {
        return idPosizione;
    }

    public void setIdPosizione(int idPosizione) {
        this.idPosizione = idPosizione;
    }
}
