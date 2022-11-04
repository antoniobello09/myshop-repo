package Business;

import java.util.HashMap;


//Salva dati di una sessione
//In particolare
//key: loggedUser ---> oggetto Utente che memorizza l'utente loggato. Si fa il put nella classe LoginListener
//key: idPuntoVendita ---> id del punto vendita in cui mi trovo. Si fa il put nella classe PuntoVenditaBusiness

public class SessionManager {

    private static SessionManager instance;

    private HashMap<String, Object> session = new HashMap<>();

    public static synchronized SessionManager getInstance() {
        if(instance == null) instance = new SessionManager();
        return instance;
    }

    public HashMap<String, Object> getSession() {
        return session;
    }

    private SessionManager(){}

}
