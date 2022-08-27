package Model;

import Model.Other.ICategoria;
import View.Nameable;

import java.util.ArrayList;
import java.util.Iterator;

public class CategoriaServizio implements ICategoria, Nameable, Cloneable {

    private int idCategoria;
    private String nome;

    public CategoriaServizio() {
    }

    public CategoriaServizio(String nome){
        this.nome = nome;
    }

    public CategoriaServizio(int idCategoria, String nomeCategoria){
        this.idCategoria = idCategoria;
        nome = nomeCategoria;
    }


    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    protected Object clone(){
        return new CategoriaServizio(idCategoria, nome);
    }

    public static ArrayList<CategoriaServizio> cloneList(ArrayList<CategoriaServizio> lista) {
        Iterator<CategoriaServizio> it = lista.iterator();
        ArrayList<CategoriaServizio> newList = new ArrayList<>();
        while(it.hasNext()){
            newList.add((CategoriaServizio) it.next().clone());
        }
        return newList;
    }
}
