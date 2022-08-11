package Business;

import Model.Articolo;
import Model.ICategoria;

public interface AbstractFactory {

    Articolo crea();
    ICategoria creaCategoria();

}
