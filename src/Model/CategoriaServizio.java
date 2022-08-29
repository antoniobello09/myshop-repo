package Model;

import Model.Other.ICategoria;

import java.util.ArrayList;
import java.util.Iterator;

public class CategoriaServizio implements ICategoria{

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


}
