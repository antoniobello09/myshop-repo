package Business;

import java.util.HashMap;

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
