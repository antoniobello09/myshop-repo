package Business.ModelBusiness;

import DAO.Classi.AcquistoDAO;
import DAO.Classi.ArticoloDAO;
import DAO.Classi.FeedbackDAO;
import DAO.Classi.Lista_has_ArticoloDAO;
import Model.Articolo;
import Model.FeedBack;
import Model.Lista_has_Articolo;
import View.AppFrame;
import Business.ModelBusiness.TableModels.ArticlesListTableModel;
import View.Panels.Center.Cliente.Altro.FeedBackDialog;

import javax.swing.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class Lista_has_ArticoloBusiness {

    private static Lista_has_ArticoloBusiness instance;

    private static ArticlesListTableModel articlesListTableModel;
    private static int idLista;

    public static synchronized Lista_has_ArticoloBusiness getInstance() {
        if(instance == null) instance = new Lista_has_ArticoloBusiness();
        return instance;
    }

    private Lista_has_ArticoloBusiness() {
    }

    public JTable getTabellaArticoli(int idLista){
        this.idLista = idLista;
        ArrayList<Lista_has_Articolo>  lista = Lista_has_ArticoloDAO.getInstance().findAllListArticles(idLista);
        articlesListTableModel = new ArticlesListTableModel(lista);
        JTable tabellaArticoli = new JTable(articlesListTableModel);
        return tabellaArticoli;
    }

    public boolean isAcquistato(int idLista){
        if(AcquistoDAO.getInstance().findByIDLista(idLista) == null){
            return false;
        }else{
            return true;
        }
    }

    public int aggiungi(String nomeArticolo, String quantita){
        Articolo a = ArticoloDAO.getInstance().findByName(nomeArticolo);
        if(a == null){
            return 1;
        }else {
            if(ArticoloDAO.getInstance().isProdotto(a) && quantita.isEmpty()){
                return 3;
            }
            if(ArticoloDAO.getInstance().isServizio(a)){
                quantita = "1";
            }
            int idArticolo = a.getIdArticolo();
            Lista_has_Articolo lista_has_articolo = new Lista_has_Articolo(idLista, idArticolo, Integer.parseInt(quantita));
            if(Lista_has_ArticoloDAO.getInstance().add(lista_has_articolo) == 0){
                return 2;
            }
            articlesListTableModel.setLista(Lista_has_ArticoloDAO.getInstance().findAllListArticles(idLista));
            articlesListTableModel.fireTableDataChanged();
            return 0;
        }
    }

    public int elimina(int selectedRow){
        int idArticolo = articlesListTableModel.getLista().get(selectedRow).getIdArticolo();
        Lista_has_Articolo lista_has_articolo = new Lista_has_Articolo();
        lista_has_articolo.setIdLista(idLista);
        lista_has_articolo.setIdArticolo(idArticolo);
        if(Lista_has_ArticoloDAO.getInstance().delete(lista_has_articolo) == 0){
            return 1;
        }
        articlesListTableModel.setLista(Lista_has_ArticoloDAO.getInstance().findAllListArticles(idLista));
        articlesListTableModel.fireTableDataChanged();
        return 0;
    }

    public int modificaFeedback(AppFrame appFrame, int selectedRow){
        int idAcquisto = AcquistoDAO.getInstance().findByIDLista(idLista).getIdAcquisto();
        int idArticolo = articlesListTableModel.getLista().get(selectedRow).getIdArticolo();
        ArrayList<String> risultati;
        FeedBack f = FeedbackDAO.getInstance().findByIDAcquisto_Articolo(idAcquisto, idArticolo);
        if (f == null){
            f = new FeedBack();
            risultati = FeedBackDialog.showDialog(appFrame, "FeedBack Dialog", "", 0, "");
            f.setIdAcquisto(idAcquisto);
            f.setIdArticolo(idArticolo);
            f.setCommento(risultati.get(0));
            f.setIndiceGradimento(Integer.parseInt(risultati.get(1)));
            f.setData(Date.valueOf(LocalDate.now()));
            if(FeedbackDAO.getInstance().add(f) == 0){
                return 1;
            }
        }else{
            risultati = FeedBackDialog.showDialog(appFrame, "FeedBack Dialog", f.getCommento(), f.getIndiceGradimento(), f.getRisposta());
            f.setCommento(risultati.get(0));
            f.setIndiceGradimento(Integer.parseInt(risultati.get(1)));
            f.setData(Date.valueOf(LocalDate.now()));
            if(FeedbackDAO.getInstance().update(f) == 0){
                return 1;
            }
        }
        return 0;
    }

}
