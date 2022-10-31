package Business.Strategy;

import Model.Prodotto;

import java.util.ArrayList;
import java.util.Comparator;

public class AlphabeticSort implements IProductSortStrategy{

    @Override
    public void sort(ArrayList<Prodotto> listaProdotti) {

        listaProdotti.sort((Comparator<Prodotto>) (o1, o2) -> {

            return o1.getNome().toLowerCase().compareTo(o2.getNome().toLowerCase());
        });

    }
}
