package Model;

public class Amministratore extends Utente {

    public Amministratore(int idAmministratore, String username, String password, String email){
        super(idAmministratore, username, password, email);
    }

    public Amministratore(String username, String password, String email){
        super(username, password, email);
    }

    public Amministratore() {

    }
}
