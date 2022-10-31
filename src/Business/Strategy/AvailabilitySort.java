package Business.Strategy;

import Business.SessionManager;
import DAO.Classi.SchedaProdottoDAO;
import Model.Prodotto;
import Model.SchedaProdotto;

import java.util.ArrayList;
import java.util.Comparator;

public class AvailabilitySort implements IProductSortStrategy{

    @Override
    public void sort(ArrayList<Prodotto> listaProdotti) {
        listaProdotti.sort(new Comparator<Prodotto>() {
            @Override
            public int compare(Prodotto o1, Prodotto o2) {
                int disponibilita1;
                int disponibilita2;
                SchedaProdotto schedaProdotto = SchedaProdottoDAO.getInstance().findByShop_Product(o1.getIdArticolo(), (int) SessionManager.getInstance().getSession().get("idPuntoVendita"));
                if(schedaProdotto != null) disponibilita1 = schedaProdotto.getDisponibilita();
                else disponibilita1 = 0;
                schedaProdotto = SchedaProdottoDAO.getInstance().findByShop_Product(o2.getIdArticolo(), (int) SessionManager.getInstance().getSession().get("idPuntoVendita"));
                if(schedaProdotto != null) disponibilita2 = schedaProdotto.getDisponibilita();
                else disponibilita2 = 0;
                return Integer.compare(disponibilita2, disponibilita1);
            }
        });
    }
}
