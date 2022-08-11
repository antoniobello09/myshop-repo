package DAO.Interfacce;

import Model.FeedBack;
import Model.Produttore;

import java.util.ArrayList;

public interface IFeedbackDAO {
    int add(FeedBack feedback);
    int update(FeedBack feedBack);
    int delete(FeedBack feedBack);
    FeedBack findByID(int idFeedBack);
    FeedBack findByInfo(int idAcquisto, int idArticolo);
    FeedBack findByInfo(int idAcquisto, int idArticolo, int closeConn);
    ArrayList<FeedBack> findAll();
}
