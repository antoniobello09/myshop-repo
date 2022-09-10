package Model;

import Model.Other.ICategoria;

import java.util.ArrayList;
import java.util.Iterator;

public class CategoriaServizio extends Categoria implements ICategoria{

    public CategoriaServizio() {
    }

    public CategoriaServizio(String nome) {
        super(nome);
    }

    public CategoriaServizio(int idCategoria, String nome) {
        super(idCategoria, nome);
    }

    @Override
    public String getNome(){
        return super.getNome();
    }
}
