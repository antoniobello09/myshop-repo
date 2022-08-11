package DAO.Interfacce;

import Model.FeedBack;
import Model.Risposta;

import java.util.ArrayList;

public interface IRispostaDAO {
    int add(Risposta risposta);
    int update(Risposta risposta);
    int delete(Risposta risposta);
    Risposta findByID(int idRisposta);
    Risposta findByInfo(int idCommento);
    Risposta findByInfo(int idCommento, int closeConn);
    ArrayList<Risposta> findAll();
}
