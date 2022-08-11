package Model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Utente {

    private String canalePreferito;
    private boolean abilitato;
    private ArrayList<Lista> liste;

    public Cliente(){

    }

    public Cliente(String username, String password, String name, String surname, String email, String birthdate, String telephone, String address, String job, String canalePreferito, boolean abilitato) {
        super(username, password, name, surname, email, birthdate, telephone, address, job);
        this.canalePreferito = canalePreferito;
        this.abilitato = abilitato;
        liste = new ArrayList<>();
    }

    public String getCanalePreferito() {
        return canalePreferito;
    }
    public void setCanalePreferito(String canalePreferito) {
        this.canalePreferito = canalePreferito;
    }
    public boolean isAbilitato() {
        return abilitato;
    }
    public void setAbilitato(boolean abilitato) {
        this.abilitato = abilitato;
    }

    public ArrayList<Lista> getListe() {
        return liste;
    }

    public void setListe(ArrayList<Lista> liste) {
        this.liste = liste;
    }
}
