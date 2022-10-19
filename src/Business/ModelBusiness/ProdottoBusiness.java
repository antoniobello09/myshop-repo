package Business.ModelBusiness;

import DAO.Classi.*;
import Model.Prodotto;
import Business.ModelBusiness.TableModels.ProdottiTableModel;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

public class ProdottoBusiness {

    private static ProdottoBusiness instance;

    public static synchronized ProdottoBusiness getInstance() {
        if(instance == null) instance = new ProdottoBusiness();
        return instance;
    }

    private ProdottoBusiness() {
    }

    public JTable getTabellaProdotti(){
        ProdottiTableModel currentTableModel = new ProdottiTableModel(ProdottoDAO.getInstance().findAll());
        JTable tabella = new JTable(currentTableModel);
        return tabella;
    }

    public int aggiungi(String nomeProdotto, String descrizioneProdotto, String prezzoProdotto, String nomeCategoria, File immagine, String nomeProduttore, String posizione){
        String[] posizioneArray = posizione.split(" ");
        int idCategoria = CategoriaProdottoDAO.getInstance().findByName(nomeCategoria).getIdCategoria();
        int idProduttore = FornitoreDAO.getInstance().findByName(nomeProduttore).getIdFornitore();
        int idPosizione = PosizioneDAO.getInstance().findByNumbers(Integer.parseInt(posizioneArray[0]),Integer.parseInt(posizioneArray[2]),Integer.parseInt(posizioneArray[4])).getIdPosizione();
        Prodotto prodotto = new Prodotto(nomeProdotto, descrizioneProdotto, Float.parseFloat(prezzoProdotto), idCategoria, idProduttore, idPosizione);
        prodotto.setImmagine(immagine);
        if(ArticoloDAO.getInstance().add(prodotto) == 0){
            return 1;
        }
        int idProdotto = ArticoloDAO.getInstance().findByName(prodotto.getNome()).getIdArticolo();
        prodotto.setIdArticolo(idProdotto);
        if(ProdottoDAO.getInstance().add(prodotto) == 0){
            return 1;
        }
        return 0;
    }

}
