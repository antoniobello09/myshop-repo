package Business.AbstractFactory;

import Model.Articolo;
import Model.CategoriaProdotto;
import Model.ICategoria;
import Model.Prodotto;

public class ProdottoFactory implements AbstractFactory {

    @Override
    public Articolo crea() {
        return new Prodotto();
    }

    @Override
    public ICategoria creaCategoria() {
        return new CategoriaProdotto();
    }
}
