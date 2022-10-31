package Business.Strategy;

import Model.Prodotto;

import java.util.ArrayList;
import java.util.Comparator;

public class PriceSort implements IProductSortStrategy{

    @Override
    public void sort(ArrayList<Prodotto> listaProdotti) {
        listaProdotti.sort(new Comparator<Prodotto>() {
            @Override
            public int compare(Prodotto o1, Prodotto o2) {
                return Float.compare(o1.getPrezzo(), o2.getPrezzo());
            }
        });
    }
}
