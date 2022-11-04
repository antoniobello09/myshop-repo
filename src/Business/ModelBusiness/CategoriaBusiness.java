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
        //L'aggiunta di una categoria avviene in due fasi:
        //1. CategoriaDAO.add per aggiungerla in Categoria
        //2. CategoriaProdottoDAO.add per aggiungerla in CategoriaProdotto
        if(tipoCategoria.equals("Prodotto")){
                //se la categoria padre è vuota allora sto aggiungendo una categoria padre
                if(nomeCategoriaPadre.isEmpty()){
                    CategoriaProdotto categoriaProdotto = new CategoriaProdotto(nomeCategoria);
                    //Controllo se la categoria esiste già
                    if(CategoriaDAO.getInstance().add(categoriaProdotto) == 0){
                        return 2;       //La categoria esiste già
                    }else{
                        //A questo punto ho solo aggiunto la categoria in Categoria
                        //Recupero l'ID della categoria appena aggiunta per poi inserire la categoria anche in CategoriaProdotto
                        int idCategoria = CategoriaDAO.getInstance().findByName(nomeCategoria).getIdCategoria();
                        categoriaProdotto.setIdCategoria(idCategoria);
                        //Aggiungo la categoria anche in CategoriaProdotto
                        CategoriaProdottoDAO.getInstance().add(categoriaProdotto);
                    }
                }else{
                    //La categoria padre non è vuota allora sto aggiungendo una sottocategoria
                    CategoriaProdotto categoriaPadreProdotto = new CategoriaProdotto(nomeCategoriaPadre);
                    //Controllo se la categoria padre esiste nella tabella CategoriaProdotto
                    if(!CategoriaProdottoDAO.getInstance().isCategory(categoriaPadreProdotto)){
                        return 3;   //La categoria padre non esiste
                    }
                    //La categoria padre esiste allora sono pronto ad aggiungere la sottocategoria
                    CategoriaProdotto categoriaProdotto = new CategoriaProdotto(nomeCategoria);
                    if(CategoriaDAO.getInstance().add(categoriaProdotto)==0){
                        return 2;   //la sottocategoria esiste già
                    }
                    //In questo momento ho aggiunto la categoria solo in Categoria
                    //Devo aggiungerla anche in CategoriaProdotto
                    //Recupero l'ID dalla tabella Categoria
                    int idCategoria = CategoriaDAO.getInstance().findByName(nomeCategoria).getIdCategoria();
                    categoriaProdotto.setIdCategoria(idCategoria);
                    //Recupero anche l'ID della categoria padre
                    int idCategoriaPadre = CategoriaDAO.getInstance().findByName(nomeCategoriaPadre).getIdCategoria();
                    categoriaProdotto.setIdCategoriaPadre(idCategoriaPadre);
                    //Aggiungo la sottocategoria alla tabella CategoriaProdotto
                    CategoriaProdottoDAO.getInstance().addSub(categoriaProdotto);
                }

        }else if(tipoCategoria.equals("Servizio")){
                if(!nomeCategoriaPadre.isEmpty()) {
                    return 1;   //la categoria servizio non ammette categorie padri
                }else{
                    CategoriaServizio categoriaServizio = new CategoriaServizio(nomeCategoria);
                    if(CategoriaDAO.getInstance().add(categoriaServizio) == 0){
                        return 2;       //La categoria esiste già
                    }
                    //In questo momento ho aggiunto la categoria solo nella tabella Categoria
                    //Recupero l'ID della categoria dalla tabella Categoria
                    int idCategoria = CategoriaDAO.getInstance().findByName(nomeCategoria).getIdCategoria();
                    categoriaServizio.setIdCategoria(idCategoria);
                    //Aggiungo la categoria anche nella tabella CategoriaServizio
                    CategoriaServizioDAO.getInstance().add(categoriaServizio);
                }
        }
        return 0;
    }

    //Recupera le categorie prodotto padri e le fa diventare un array di stringhe
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

    //Recupera le categorie servizio e le fa diventare un array di stringhe
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

    //Recupera le sottocategorie prodotto e le fa diventare un array di stringhe
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
