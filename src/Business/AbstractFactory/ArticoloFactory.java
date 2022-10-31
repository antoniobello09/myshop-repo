package Business.AbstractFactory;

import Model.Prodotto;
import Model.ProdottoComposito;
import Model.Servizio;

public class ArticoloFactory extends AbstractFactory {

    @Override
    public Object crea(String articleType) {
        if(articleType.equalsIgnoreCase("PRODOTTO")){
            return new Prodotto();
        }else if(articleType.equalsIgnoreCase("SERVIZIO")){
            return new Servizio();
        }else if(articleType.equalsIgnoreCase("PRODOTTOCOMPOSITO")) {
            return new ProdottoComposito();
        }
        return null;
    }

}
