package Business.ModelBusiness;

import DAO.Classi.*;
import Model.Servizio;
import Business.ModelBusiness.TableModels.ServiziTableModel;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ServizioBusiness {

    private static ServizioBusiness instance;

    public static synchronized ServizioBusiness getInstance() {
        if (instance == null) instance = new ServizioBusiness();
        return instance;
    }

    private ServizioBusiness() {
    }

    //Restituisce la JTable
    public JTable getTabellaServizi() {
        //Crea la tableModel
        ServiziTableModel currentTableModel = new ServiziTableModel(ServizioDAO.getInstance().findAll());
        JTable tabella = new JTable(currentTableModel);
        return tabella;
    }

    //L'aggiunta di un servizio avviene in due fasi:
    // 1. ArticoloDAO.add
    // 2. ServizioDAO.add
    public int aggiungi(String nomeServizio, String descrizioneServizio, String prezzoServizio, String nomeCategoria, File immagine, String nomeFornitore) {
        //Recupero gli ID dai nomi
        int idCategoria = CategoriaServizioDAO.getInstance().findByName(nomeCategoria).getIdCategoria();
        int idFornitore = FornitoreDAO.getInstance().findByName(nomeFornitore).getIdFornitore();
        Servizio servizio = new Servizio(nomeServizio, descrizioneServizio, Float.parseFloat(prezzoServizio), idCategoria, idFornitore);
        servizio.setImmagine(immagine);
        //Una volta preparato il servizio faccio gli add
        if (ArticoloDAO.getInstance().add(servizio) == 0) return 1;
        servizio.setIdArticolo(ArticoloDAO.getInstance().findByName(servizio.getNome()).getIdArticolo());
        if (ServizioDAO.getInstance().add(servizio) == 0) return 1;
        return 0;
    }
}