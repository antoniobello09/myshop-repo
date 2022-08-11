package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PuntoVendita implements Cloneable{

    private int idPuntoVendita;
    private Manager manager;
    private Magazzino magazzino;
    private String citta;
    private String indirizzo;

    private ArrayList<SchedaServizio> servizi = new ArrayList<>();
    private ArrayList<Cliente> clienti = new ArrayList<>();

    public void sendMsgToClienti(/* Messaggio messaggio */) {

    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
    }

    public Magazzino getMagazzino() {
        return magazzino;
    }

    public void setMagazzino(Magazzino magazzino) {
        this.magazzino = magazzino;
    }

    public static ArrayList<PuntoVendita> cloneList(ArrayList<PuntoVendita> lista){
        Iterator<PuntoVendita> it = lista.iterator();
        ArrayList<PuntoVendita> clonedList = new ArrayList<>();
        while(it.hasNext()){
            try{
                clonedList.add((PuntoVendita)it.next().clone());
            }catch (CloneNotSupportedException e){
                return null;
            }

        }
        return clonedList;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        PuntoVendita p = new PuntoVendita();
        p.setIdPuntoVendita(idPuntoVendita);
        p.setManager((Manager)manager.clone());
        p.setCitta(citta);
        p.setIndirizzo(indirizzo);
        return p;
    }




    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }


    public ArrayList<SchedaServizio> getServizi() {
        return servizi;
    }

    public void setServizi(ArrayList<SchedaServizio> servizi) {
        this.servizi = servizi;
    }

    public ArrayList<Cliente> getClienti() {
        return clienti;
    }

    public void setClienti(ArrayList<Cliente> clienti) {
        this.clienti = clienti;
    }
}
