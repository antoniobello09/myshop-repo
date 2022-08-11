package Business;

import Model.Prodotto;

import java.util.ArrayList;
import java.util.List;

public class ProdottoBusiness {

    private static ProdottoBusiness instance;

    public static synchronized ProdottoBusiness getInstance() {
        if(instance==null) instance = new ProdottoBusiness();
        return instance;
    }

    private ProdottoBusiness() {}

    public List<Prodotto> getAllProducts() {
        // usare la classe ProdottoDAO per inviare la query al db che mi prende tutti i prodotti
        List<Prodotto> list = new ArrayList<>();



        return list;
    }
}
