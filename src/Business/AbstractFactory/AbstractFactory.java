package Business.AbstractFactory;

import Model.Articolo;
import Model.ICategoria;

public interface AbstractFactory {

    Articolo crea();
    ICategoria creaCategoria();

}
