package Model;

public class CategoriaServizio extends Categoria{

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
