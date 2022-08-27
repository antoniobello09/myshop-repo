package Business;

import Model.Articolo;
import Model.Other.ICategoria;

public interface AbstractFactory {

    Articolo crea();
    ICategoria creaCategoria();

}
