package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProdottoComposito extends Prodotto implements IProdotto {

    private final ArrayList<Prodotto_Quantita> sottoprodotti = new ArrayList<>();

    public ProdottoComposito() {

    }

    public void add(Prodotto_Quantita prodotto) {
        sottoprodotti.add(prodotto);
    }

    public void setSottoprodotti(List<Prodotto_Quantita> prodotti) {
        sottoprodotti.addAll(prodotti);
    }

    @Override
    public Float getPrezzo() {
        Float p = 0F;


        for(IProdotto prodotto : sottoprodotti) {
            p += prodotto.getPrezzo();
        }

        return p;
    }


    public ArrayList<Prodotto_Quantita> getSottoprodotti() {
        return sottoprodotti;
    }

    public ArrayList<Prodotto_Quantita> cloneList() {
        Iterator<Prodotto_Quantita> it = sottoprodotti.iterator();
        ArrayList<Prodotto_Quantita> newList = new ArrayList<>();
        while(it.hasNext()){
            try {
                newList.add((Prodotto_Quantita) it.next().clone());
            }catch(CloneNotSupportedException e){
                return null;
            }
        }
        return newList;
    }

}
