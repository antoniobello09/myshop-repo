package Business.ModelBusiness;

import Business.Bridge.Documento;
import Business.Bridge.DocumentoListaAcquisto;
import Business.Bridge.PdfBoxAPI;
import Business.SessionManager;
import DAO.Classi.AcquistoDAO;
import DAO.Classi.ListaDAO;
import DAO.Classi.Lista_has_ArticoloDAO;
import Model.*;
import View.AppFrame;
import Business.ModelBusiness.TableModels.ListsTableModel;
import View.Panels.Center.Cliente.ArticlesListPanel;

import javax.swing.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class ListaBusiness {

    private static ListaBusiness instance;

    private static ListsTableModel listsTableModel;
    private static int idCliente;

    public static synchronized ListaBusiness getInstance() {
        if(instance == null) instance = new ListaBusiness();
        return instance;
    }

    private ListaBusiness() {
    }

    public JTable getTabellaListe(){
        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");
        idCliente = u.getIdUtente();
        ArrayList<Lista> lista = ListaDAO.getInstance().findAll(idCliente);
        listsTableModel = new ListsTableModel(lista);
        JTable tabellaListe = new JTable(listsTableModel);
        return tabellaListe;
    }

    public int crea(String nomeLista){
        Lista l = new Lista(nomeLista, idCliente);
        if(ListaDAO.getInstance().add(l)==0){
            return 1;
        }
        listsTableModel.setLista(ListaDAO.getInstance().findAll(idCliente));
        listsTableModel.fireTableDataChanged();
        return 0;
    }

    public void modifica(int selectedRow, AppFrame appFrame){
        appFrame.getCenter().setCurrentPanel(new ArticlesListPanel(appFrame, listsTableModel.getLista().get(selectedRow).getIdLista()));
    }

    public int acquista(int selectedRow){
        if(AcquistoDAO.getInstance().findByIDLista(listsTableModel.getLista().get(selectedRow).getIdLista())!=null){
            return 1;
        }else{
            int idPuntoVendita = (int)SessionManager.getInstance().getSession().get("idPuntoVendita");
            Acquisto a = new Acquisto(idPuntoVendita, Date.valueOf(LocalDate.now()), listsTableModel.getLista().get(selectedRow).getIdLista());
            if(AcquistoDAO.getInstance().add(a) == 0){
                return 2;
            }
            listsTableModel.setLista(ListaDAO.getInstance().findAll(idCliente));
            listsTableModel.fireTableDataChanged();
            a = AcquistoDAO.getInstance().findByIDLista(listsTableModel.getLista().get(selectedRow).getIdLista());
            PrenotazioneBusiness.getInstance().nuovoAcquisto(a);
            return 0;
        }
    }

    public void inviaPDF(int selectedRow){
        int idLista = listsTableModel.getLista().get(selectedRow).getIdLista();
        ArrayList<Lista_has_Articolo> l = Lista_has_ArticoloDAO.getInstance().findAllListArticles(idLista);
        Documento listaAcquisto = new DocumentoListaAcquisto(l, new PdfBoxAPI());

        //prendere l'utente loggato dalla sessione e ottenere la mail dall'oggetto cliente
        Utente u = (Utente)SessionManager.getInstance().getSession().get("loggedUser");
        listaAcquisto.invia(u.getEmail());
    }

}
