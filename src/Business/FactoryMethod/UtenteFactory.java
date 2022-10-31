package Business.FactoryMethod;

import Model.*;

public class UtenteFactory {

    public UtenteFactory(){

    }

    public Utente crea(String utenteType){

        if(utenteType == null){
            return null;
        }
        if(utenteType.equalsIgnoreCase("UTENTE")){
            return new Utente();
        }else if(utenteType.equalsIgnoreCase("AMMINISTRATORE")){
            return new Amministratore();
        }else if(utenteType.equalsIgnoreCase("CLIENTE")){
            return new Cliente();
        }else if(utenteType.equalsIgnoreCase("MANAGER")){
            return new Manager();
        }
        return null;
    }
}
