package Business.ModelBusiness;

import DAO.Classi.CategoriaDAO;
import DAO.Classi.CategoriaProdottoDAO;
import DAO.Classi.CategoriaServizioDAO;
import Model.CategoriaProdotto;
import Model.CategoriaServizio;

import java.util.ArrayList;
import java.util.Iterator;

public class CategoriaBusiness {
    private static CategoriaBusiness instance;

    public static synchronized CategoriaBusiness getInstance() {
        if(instance == null) instance = new CategoriaBusiness();
        return instance;
    }

    private CategoriaBusiness() {
    }


    /* ERRORS CODES for aggiungi()
        0 - successo
        1 - la categoria servizio non ammette categorie padri
        2 - la categoria esiste già
        3 - la categoria Padre non esiste
    */
    public int aggiungi(String nomeCategoria, String tipoCategoria, String nomeCategoriaPadre){
        if(tipoCategoria.equals("Prodotto")){

                if(nomeCategoriaPadre.isEmpty()){
                    CategoriaProdotto categoriaProdotto = new CategoriaProdotto(nomeCategoria);
                    if(CategoriaDAO.getInstance().add(categoriaProdotto) == 0){
                        return 2;       //La categoria esiste già
                    }else{
                        int idCategoria = CategoriaDAO.getInstance().findByName(nomeCategoria).getIdCategoria();
                        categoriaProdotto.setIdCategoria(idCategoria);
                        CategoriaProdottoDAO.getInstance().add(categoriaProdotto);
                    }
                }else{
                    CategoriaProdotto categoriaPadreProdotto = new CategoriaProdotto(nomeCategoriaPadre);
                    if(!CategoriaProdottoDAO.getInstance().isCategory(categoriaPadreProdotto)){
                        return 3;
                    }
                    CategoriaProdotto categoriaProdotto = new CategoriaProdotto(nomeCategoria);
                    if(CategoriaDAO.getInstance().add(categoriaProdotto)==0){
                        return 2;
                    }
                    int idCategoria = CategoriaDAO.getInstance().findByName(nomeCategoria).getIdCategoria();
                    categoriaProdotto.setIdCategoria(idCategoria);
                    int idCategoriaPadre = CategoriaDAO.getInstance().findByName(nomeCategoriaPadre).getIdCategoria();
                    categoriaProdotto.setIdCategoriaPadre(idCategoriaPadre);
                    CategoriaProdottoDAO.getInstance().addSub(categoriaProdotto);
                }

        }else if(tipoCategoria.equals("Servizio")){
                if(!nomeCategoriaPadre.isEmpty()) {
                    return 1;
                }else{
                    CategoriaServizio categoriaServizio = new CategoriaServizio(nomeCategoria);
                    if(CategoriaDAO.getInstance().add(categoriaServizio) == 0){
                        return 2;       //La categoria esiste già
                    }
                    int idCategoria = CategoriaDAO.getInstance().findByName(nomeCategoria).getIdCategoria();
                    categoriaServizio.setIdCategoria(idCategoria);
                    CategoriaServizioDAO.getInstance().add(categoriaServizio);
                }
        }
        return 0;
    }

    public ArrayList<String> getNomiCategorieProdotto(){
        ArrayList<CategoriaProdotto> categorieList = CategoriaProdottoDAO.getInstance().findAll();
        ArrayList<String> categorieNomiList = new ArrayList<>();
        if(categorieList != null) {
            Iterator<CategoriaProdotto> iterator = categorieList.iterator();
            while(iterator.hasNext()){
                CategoriaProdotto cp = iterator.next();
                if (cp.getIdCategoriaPadre() == 0) {
                    categorieNomiList.add(cp.getNome());
                }
            }
        }
        return categorieNomiList;
    }

    public ArrayList<String> getNomiCategorieServizio(){
        ArrayList<CategoriaServizio> categorieList = CategoriaServizioDAO.getInstance().findAll();
        ArrayList<String> categorieNomiList = new ArrayList<>();
        if(categorieList != null) {
            Iterator<CategoriaServizio> iterator = categorieList.iterator();
            while(iterator.hasNext()){
                CategoriaServizio cs = iterator.next();
                categorieNomiList.add(cs.getNome());
            }
        }
        return categorieNomiList;
    }

    public ArrayList<String> getNomiSottoCategorieProdotto(String nomeCategoriaPadre){
        ArrayList<String> sottoCategorieNomiList = new ArrayList<>();
        ArrayList<CategoriaProdotto> sottocategorieList = CategoriaProdottoDAO.getInstance().findAllSons(CategoriaProdottoDAO.getInstance().findByName(nomeCategoriaPadre).getIdCategoria());
        if(sottocategorieList != null) {
            Iterator<CategoriaProdotto> iterator = sottocategorieList.iterator();
            while(iterator.hasNext()) {
                CategoriaProdotto cp = iterator.next();
                sottoCategorieNomiList.add(cp.getNome());
            }
        }
        return sottoCategorieNomiList;
    }



}
