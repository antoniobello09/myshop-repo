package DAO.Interfacce;

import Model.Posizione;

import java.util.ArrayList;

public interface IPosizioneDAO {

    int add(Posizione posizione);
    int update(Posizione posizione);
    int delete(Posizione posizione);
    Posizione findByID(int idPosizione);
    ArrayList<Posizione> findAll();

}
