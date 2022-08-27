package Business;

import Model.FeedBack;
import Model.Utente;

import java.util.ArrayList;
import java.util.List;

public class FeedbackBusiness {

    private static FeedbackBusiness instance;

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


}
