package Model;


public class Manager extends Utente {

    public Manager(int idAmministratore, String username, String password, String email){
        super(idAmministratore, username, password, email);
    }

    public Manager(String username, String password, String email){
        super(username, password, email);
    }

    public Manager() {

    }

}
