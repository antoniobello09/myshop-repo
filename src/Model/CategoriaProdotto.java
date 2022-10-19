package Model;

import java.util.ArrayList;
import java.util.List;

public class CategoriaProdotto extends Categoria implements ICategoria {


    private List<CategoriaProdotto> sottocategorie = new ArrayList<>();
    private Integer idCategoriaPadre = null;

    public CategoriaProdotto() {
    }

    public CategoriaProdotto(String nome){
        super.setNome(nome);
    }

    public CategoriaProdotto(int idCategoria, String nome, List<CategoriaProdotto> sottocategorie, int idCategoriaPadre) {
        super.setIdCategoria(idCategoria);
        super.setNome(nome);
        this.sottocategorie = sottocategorie;
        this.idCategoriaPadre = idCategoriaPadre;
    }

    public CategoriaProdotto(int idCategoria, String nomeCategoria, List<CategoriaProdotto> sottocategorie){
        super.setIdCategoria(idCategoria);
        super.setNome(nomeCategoria);
        this.sottocategorie = new ArrayList<>(sottocategorie);
    }



    public CategoriaProdotto(String nomeCategoria, String nomeSottoCategoria){
        super.setNome(nomeCategoria);
        sottocategorie.add(new CategoriaProdotto(nomeSottoCategoria));
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


}
