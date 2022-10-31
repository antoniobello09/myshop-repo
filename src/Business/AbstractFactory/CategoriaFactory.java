package Business.AbstractFactory;

import Model.CategoriaProdotto;
import Model.CategoriaServizio;


public class CategoriaFactory extends AbstractFactory {

    @Override
    public Object crea(String articleType) {
        if(articleType.equalsIgnoreCase("PRODOTTO")){
            return new CategoriaProdotto();
        }else if(articleType.equalsIgnoreCase("SERVIZIO")){
            return new CategoriaServizio();
        }
        return null;
    }

}
