package Model;

import java.util.ArrayList;
import java.util.Iterator;

public class Servizio extends Articolo implements Cloneable{

    private CategoriaServizio categoria;


    public Servizio(Servizio servizio) {
        super(servizio);
        this.categoria = servizio.getCategoria();
    }

    public Servizio(){

    }

    public CategoriaServizio getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaServizio categoria) {
        this.categoria = categoria;
    }

    @Override
    protected Object clone(){
        Servizio s = new Servizio(this);
        s.setCategoria((CategoriaServizio) categoria.clone());
        return s;
    }

    public static ArrayList<Servizio> cloneList(ArrayList<Servizio> lista) {
        Iterator<Servizio> it = lista.iterator();
        ArrayList<Servizio> newList = new ArrayList<>();
        while(it.hasNext()){
            newList.add((Servizio) it.next().clone());
        }
        return newList;
    }
}
