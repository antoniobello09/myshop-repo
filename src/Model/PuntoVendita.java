package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PuntoVendita implements Cloneable{

    private int idPuntoVendita;
    private int idManager;
    private String citta;
    private String indirizzo;


    public void sendMsgToClienti(/* Messaggio messaggio */) {

    }

    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
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

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }
}


/*
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


 */