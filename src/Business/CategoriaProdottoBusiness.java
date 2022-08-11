package Business;

import Model.CategoriaProdotto;

import java.util.ArrayList;

public class CategoriaProdottoBusiness {
    private static CategoriaProdottoBusiness instance;


    public static synchronized CategoriaProdottoBusiness getInstance() {
        if(instance == null) instance = new CategoriaProdottoBusiness();
        return instance;
    }



    //Restituisce le sottocategorie di una data categoria
    public ArrayList<CategoriaProdotto> findSubCategories(String nomeC, ArrayList<CategoriaProdotto> cList){
        ArrayList<CategoriaProdotto> sottocategorie = new ArrayList<>();

        int i=0;
        boolean found = false;
        while(i<cList.size()&&(!found)){
            if(cList.get(i).getNome().equals(nomeC))
                for(int j=0;j<cList.get(i).getSottocategorie().size();j++){
                    sottocategorie.add(cList.get(i).getSottocategorie().get(j));
                }
            i++;
            found = true;
        }
        return sottocategorie;
    }






}
