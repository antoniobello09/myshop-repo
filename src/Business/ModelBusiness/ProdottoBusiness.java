package Business.ModelBusiness;

import Business.SessionManager;
import Business.Strategy.AlphabeticSort;
import Business.Strategy.AvailabilitySort;
import Business.Strategy.PriceSort;
import Business.Strategy.SortProductList;
import DAO.Classi.*;
import Model.Prodotto;
import Business.ModelBusiness.TableModels.ProdottiTableModel;
import Model.Utente;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

import static Business.ModelBusiness.UtenteBusiness.Privilegio.*;

public class ProdottoBusiness {

    private static ProdottoBusiness instance;

    public static synchronized ProdottoBusiness getInstance() {
        if(instance == null) instance = new ProdottoBusiness();
        return instance;
    }

    private ProdottoBusiness() {
    }

    public JTable getTabellaProdotti(){
        //Recupero tutti i prodotti, anche i compositi
        ArrayList<Prodotto> prodotti = ProdottoDAO.getInstance().findAll();
//-----------USO DELLO STRATEGY PER RIORDINARE L'ARRAY DI PRODOTTI IN BASE AL TIPO DI UTENTE LOGGATO---------------//
        SortProductList sortProductList = new SortProductList(prodotti);
        Utente utente = (Utente) SessionManager.getInstance().getSession().get("loggedUser");
        if(utente != null) {
            if (UtenteBusiness.getInstance().userCan(utente, ADMIN_SYSTEM)) {
                sortProductList.setProductSortStartegy(new AlphabeticSort());
            } else if (UtenteBusiness.getInstance().userCan(utente, MANAGE_SHOP)) {
                sortProductList.setProductSortStartegy(new AvailabilitySort());
            } else if (UtenteBusiness.getInstance().userCan(utente, CLIENT)) {
                sortProductList.setProductSortStartegy(new PriceSort());
            }
            sortProductList.sort();
        }
//-------------------------------------------------------------------------------------------------------------------//
        //Creo la tableModel che poi associo al JTable
        ProdottiTableModel currentTableModel = new ProdottiTableModel(prodotti);
        JTable tabella = new JTable(currentTableModel);
        return tabella;
    }



    public int aggiungi(String nomeProdotto, String descrizioneProdotto, String prezzoProdotto, String nomeCategoria, File immagine, String nomeProduttore, String posizione){

        //L'aggiunta di un prodotto avviene in due fasi:
        // 1. ArticoloDAO.add
        // 2. ProdottoDAO.add

        //La posizione è una stringa del tipo "3 piano 2 corsia 4 scaffale" ma a me servono solo i numeri
        String[] posizioneArray = posizione.split(" ");
        //Dai nomi di categoria e produttore e dai numeri della posizione recupero gli id che mi servono
        int idCategoria = CategoriaProdottoDAO.getInstance().findByName(nomeCategoria).getIdCategoria();
        int idProduttore = FornitoreDAO.getInstance().findByName(nomeProduttore).getIdFornitore();
        int idPosizione = PosizioneDAO.getInstance().findByNumbers(Integer.parseInt(posizioneArray[0]),Integer.parseInt(posizioneArray[2]),Integer.parseInt(posizioneArray[4])).getIdPosizione();
        //Creo il prodotto
        Prodotto prodotto = new Prodotto(nomeProdotto, descrizioneProdotto, Float.parseFloat(prezzoProdotto), idCategoria, idProduttore, idPosizione);
        prodotto.setImmagine(immagine);
        if(ArticoloDAO.getInstance().add(prodotto) == 0){
            return 1;   //Errore: L'articolo esiste già
        }
        //Recupero l'ID dalla tabella articolo per poi aggiungere il prodotto nella tabella prodotti
        int idProdotto = ArticoloDAO.getInstance().findByName(prodotto.getNome()).getIdArticolo();
        prodotto.setIdArticolo(idProdotto);
        if(ProdottoDAO.getInstance().add(prodotto) == 0){
            return 1;   //Errore: L'articolo esiste già
        }
        return 0;
    }

}
