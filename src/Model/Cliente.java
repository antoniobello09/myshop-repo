package Model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Utente {

    private int idPuntoVendita; //Punto Vendita dove il cliente si è registrato
    private String canalePreferito;
    private boolean abilitato;

    public Cliente(){

    }

    public Cliente(String username, String password, String name, String surname, String email, String birthdate, String telephone, String address, String job, String canalePreferito, boolean abilitato, int idPuntoVendita) {
        super(username, password, name, surname, email, birthdate, telephone, address, job);
        this.canalePreferito = canalePreferito;
        this.abilitato = abilitato;
        this.idPuntoVendita = idPuntoVendita;
    }

    public Cliente(int idCliente, String username, String password, String name, String surname, String email, String birthdate, String telephone, String address, String job, String canalePreferito, boolean abilitato, int idPuntoVendita) {
        super(idCliente, username, password, name, surname, email, birthdate, telephone, address, job);
        this.canalePreferito = canalePreferito;
        this.abilitato = abilitato;
        this.idPuntoVendita = idPuntoVendita;
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

    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
    }
}
