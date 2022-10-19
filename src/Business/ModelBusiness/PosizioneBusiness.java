package Business.ModelBusiness;

import DAO.Classi.ArticoloDAO;
import DAO.Classi.PosizioneDAO;
import Model.Articolo;
import Model.Posizione;

import java.util.ArrayList;
import java.util.Iterator;

public class PosizioneBusiness {

    private static PosizioneBusiness instance;

    public static synchronized PosizioneBusiness getInstance() {
        if(instance == null) instance = new PosizioneBusiness();
        return instance;
    }

    private PosizioneBusiness() {
    }


    public ArrayList<String> getPosizioniDisponibili(){
        ArrayList<String> posizioni = new ArrayList<>();
        //Creo la lista di posizioni da cui scegliere
        ArrayList<Posizione> posizioniList = PosizioneDAO.getInstance().findAllEmpty();
        if(posizioniList != null) {
            Iterator<Posizione> iterator = posizioniList.iterator();
            while(iterator.hasNext()) {
                Posizione p = iterator.next();
                posizioni.add("" + p.getPiano() + " piano " + p.getCorsia() + " corsia " + p.getScaffale() + " scaffale");
            }
        }
        return posizioni;
    }

    public String getPosizioneStringa(Posizione posizione){
        String posizioneStringa = "" + posizione.getPiano() + " piano " + posizione.getCorsia() + " corsia " + posizione.getScaffale() + " scaffale";
        return posizioneStringa;
    }


}
