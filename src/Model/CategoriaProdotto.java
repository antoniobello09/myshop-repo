package Model;

import Model.Other.ICategoria;
import View.Nameable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CategoriaProdotto implements ICategoria, Nameable, Cloneable {

    private int idCategoria;
    private String nome;
    private List<CategoriaProdotto> sottocategorie = new ArrayList<>();
    private Integer idCategoriaPadre = null;

    public CategoriaProdotto() {
    }

    public CategoriaProdotto(String nome){
        this.nome = nome;
    }

    public CategoriaProdotto(int idCategoria, String nome, List<CategoriaProdotto> sottocategorie, int idCategoriaPadre) {
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.sottocategorie = sottocategorie;
        this.idCategoriaPadre = idCategoriaPadre;
    }

    public CategoriaProdotto(int idCategoria, String nomeCategoria, List<CategoriaProdotto> sottocategorie){
        this.idCategoria = idCategoria;
        nome = nomeCategoria;
        this.sottocategorie = new ArrayList<>(sottocategorie);
    }



    public CategoriaProdotto(String nomeCategoria, String nomeSottoCategoria){
        nome = nomeCategoria;
        sottocategorie.add(new CategoriaProdotto(nomeSottoCategoria));
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

    public List<CategoriaProdotto> getSottocategorie() {
        return sottocategorie;
    }

    public void setSottocategorie(List<CategoriaProdotto> sottocategorie) {
        this.sottocategorie = sottocategorie;
    }

    public int getIdCategoriaPadre() {
        return idCategoriaPadre;
    }

    public void setIdCategoriaPadre(int idCategoriaPadre) {
        this.idCategoriaPadre = idCategoriaPadre;
    }

    //Verifica se c è una sua sottocategoria
    public boolean hasSottoCategoria(CategoriaProdotto c){
        int i = 0;
        boolean is_sottocategoria = false;
        while((i<sottocategorie.size())&&(is_sottocategoria == false)){
            if(sottocategorie.get(i).getIdCategoria()==c.getIdCategoria())
                is_sottocategoria = true;
            i++;
        }
        return is_sottocategoria;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new CategoriaProdotto(idCategoria, nome, sottocategorie);
    }

    public ArrayList<CategoriaProdotto> cloneList(){
        Iterator<CategoriaProdotto> it = sottocategorie.iterator();
        ArrayList<CategoriaProdotto> newList = new ArrayList<>();
        while(it.hasNext()){
            try {
                newList.add((CategoriaProdotto) it.next().clone());
            }catch(CloneNotSupportedException e){
                return null;
            }
        }
        return newList;
    }
}
