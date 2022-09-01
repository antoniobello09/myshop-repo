package DAO.Interfacce;

import Model.FeedBack;

import java.util.ArrayList;

public interface IFeedbackDAO {
    int add(FeedBack feedback);
    int update(FeedBack feedBack);
    int delete(FeedBack feedBack);
    FeedBack findByID(int idFeedBack);
    FeedBack findByIDAcquisto_Articolo(int idAcquisto, int idArticolo);
    ArrayList<FeedBack> findByPuntoVendita(int idPuntoVendita);
    ArrayList<FeedBack> findAll();
}
