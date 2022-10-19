package Business.ModelBusiness;

import DAO.Classi.CategoriaDAO;
import DAO.Classi.CategoriaServizioDAO;
import DAO.Classi.FornitoreDAO;
import Model.CategoriaServizio;
import Model.Fornitore;
import Model.Posizione;

import java.util.ArrayList;
import java.util.Iterator;

public class FornitoreBusiness {
    private static FornitoreBusiness instance;

    public static synchronized FornitoreBusiness getInstance() {
        if(instance == null) instance = new FornitoreBusiness();
        return instance;
    }

    private FornitoreBusiness() {
    }

    public ArrayList<String> getNomiProduttori(){
        ArrayList<String> nomiProduttori = new ArrayList<>();
        ArrayList<Fornitore> produttoriList = FornitoreDAO.getInstance().findAllProd();
        if(produttoriList != null) {
            Iterator<Fornitore> iterator = produttoriList.iterator();
            while(iterator.hasNext()) {
                Fornitore f = iterator.next();
                nomiProduttori.add(f.getNome());
            }
        }
        return nomiProduttori;
    }

    public ArrayList<String> getNomiFornitoriServizi(){
        ArrayList<String> nomiFornitori = new ArrayList<>();
        ArrayList<Fornitore> fornitoriList = FornitoreDAO.getInstance().findAllServ();
        if(fornitoriList != null) {
            Iterator<Fornitore> iterator = fornitoriList.iterator();
            while(iterator.hasNext()) {
                Fornitore f = iterator.next();
                nomiFornitori.add(f.getNome());
            }
        }
        return nomiFornitori;
    }

    public int aggiungi(String nomeFornitore, String sitoweb, String citta, String nazione, String tipo){
        Fornitore fornitore = new Fornitore(nomeFornitore, sitoweb, citta, nazione, tipo);
        if(FornitoreDAO.getInstance().add(fornitore) == 0){
            return 1;
        }
        return 0;
    }



}
