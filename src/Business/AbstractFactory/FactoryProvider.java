package Business.AbstractFactory;

public class FactoryProvider {

    public enum FactoryType { ARTICOLO, CATEGORIA }

    public static AbstractFactory getFactory(FactoryType type) {

        if(type == null) return null;

        switch(type) {
            case ARTICOLO: return new ArticoloFactory();
            case CATEGORIA: return new CategoriaFactory();
            default: return null;
        }
    }

}
