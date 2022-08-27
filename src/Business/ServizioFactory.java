package Business;

import Model.Articolo;
import Model.CategoriaServizio;
import Model.Other.ICategoria;
import Model.Servizio;

public class ServizioFactory implements AbstractFactory{

    @Override
    public Articolo crea() {
        return new Servizio();
    }

    @Override
    public ICategoria creaCategoria() {
        return new CategoriaServizio();
    }
}
