package Business.ModelBusiness;

import Business.Strategy.BestFirstFeedbackSort;
import Business.Strategy.IFeedbackSortStrategy;
import Business.SessionManager;
import Business.Strategy.SortedFeedbackList;
import DAO.Classi.FeedbackDAO;
import DAO.Classi.PuntoVenditaDAO;
import Model.FeedBack;
import Model.Utente;
import View.AppFrame;
import View.Panels.Center.Manager.Altro.FeedBackDialog;
import Business.ModelBusiness.TableModels.FeedbackTableModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackBusiness {

    private static FeedbackBusiness instance;
    private FeedbackTableModel feedbackTableModel;
    private int idPuntoVenditaManager;

    public static synchronized FeedbackBusiness getInstance() {
        if(instance == null) instance = new FeedbackBusiness();
        return instance;
    }

    private FeedbackBusiness() {}

    public void sortFeedbacks(List<FeedBack> feedBackList) {

        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");

        // u=null -> strategy = BestFirst
        // u!=null
        //     Cliente -> strategy = RecentFirst
        //     Manager OR Amministratore -> strategy = UrgentFirst

        IFeedbackSortStrategy strategy = new BestFirstFeedbackSort();
        SortedFeedbackList sortedFeedbackList = new SortedFeedbackList(feedBackList);

        /*if(u!=null) {
            if(UtenteBusiness.getInstance().userCan(u, UtenteBusiness.Privilegio.MANAGE_SHOP) || UtenteBusiness.getInstance().userCan(u, UtenteBusiness.Privilegio.ADMIN_SYSTEM))
                strategy = new UrgentFirstFeedbackSort();
            else
                strategy = new RecentFirstFeedbackSort();
        }
*/
        sortedFeedbackList.setSortStrategy(strategy);
        sortedFeedbackList.sort();

    }


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
