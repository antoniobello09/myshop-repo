package Business.ModelBusiness;

import Business.SessionManager;
import DAO.Classi.FeedbackDAO;
import DAO.Classi.PuntoVenditaDAO;
import Model.FeedBack;
import Model.Utente;
import View.AppFrame;
import View.Panels.Center.Manager.Altro.FeedBackDialog;
import Business.ModelBusiness.TableModels.FeedbackTableModel;

import javax.swing.*;
import java.util.ArrayList;

public class FeedbackBusiness {

    private static FeedbackBusiness instance;
    private FeedbackTableModel feedbackTableModel;
    private int idPuntoVenditaManager;

    public static synchronized FeedbackBusiness getInstance() {
        if(instance == null) instance = new FeedbackBusiness();
        return instance;
    }

    private FeedbackBusiness() {}


    public JTable getTabellaFeedbacks(){
        Utente u = (Utente)SessionManager.getInstance().getSession().get("loggedUser");
        idPuntoVenditaManager = PuntoVenditaDAO.getInstance().findByManager(u.getIdUtente()).getIdPuntoVendita();
        ArrayList<FeedBack> lista = FeedbackDAO.getInstance().findByPuntoVendita(idPuntoVenditaManager);
        feedbackTableModel = new FeedbackTableModel(lista);
        JTable tabellaFeedbacks = new JTable(feedbackTableModel);
        return tabellaFeedbacks;
    }

    public int rispondi(int selectedRow, AppFrame appFrame){
        FeedBack f = feedbackTableModel.getLista().get(selectedRow);
        f.setRisposta(FeedBackDialog.showDialog(appFrame, "Rispondi al commento", f.getRisposta()));
        if(FeedbackDAO.getInstance().update(f)==0){
            return 1;
        }
        feedbackTableModel.setLista(FeedbackDAO.getInstance().findByPuntoVendita(idPuntoVenditaManager));
        feedbackTableModel.fireTableDataChanged();
        return 0;
    }


}
