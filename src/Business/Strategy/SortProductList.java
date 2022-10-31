package Business.Strategy;

import Model.Prodotto;

import java.util.ArrayList;

public class SortProductList {

    private IProductSortStrategy productSortStrategy;
    private ArrayList<Prodotto> listaProdotti;

    public SortProductList(ArrayList<Prodotto> listaProdotti){
        this.listaProdotti = listaProdotti;
    }

    public void setProductSortStartegy(IProductSortStrategy productSortStrategy){
        this.productSortStrategy = productSortStrategy;
    }

    public void sort(){
        productSortStrategy.sort(listaProdotti);
    }


}
