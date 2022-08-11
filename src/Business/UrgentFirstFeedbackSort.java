package Business;

import Model.FeedBack;

import java.util.Comparator;
import java.util.List;

public class UrgentFirstFeedbackSort implements IFeedbackSortStrategy {

    @Override
    public void sort(List<FeedBack> feedbackList) {

        feedbackList.sort(new Comparator<FeedBack>() {
            @Override
            public int compare(FeedBack o1, FeedBack o2) {
                return o1.getPunteggio() - o2.getPunteggio();
            }
        });

    }
}
