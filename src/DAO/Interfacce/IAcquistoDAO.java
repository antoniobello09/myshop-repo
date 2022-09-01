package DAO.Interfacce;

import Model.Acquisto;
import Model.Fornitore;

import java.util.ArrayList;

public interface IAcquistoDAO {

    int add(Acquisto acquisto);
    int update(Acquisto acquisto);
    int delete(Acquisto acquisto);
    Acquisto findByID(int idAcquisto);
    ArrayList<Acquisto> findAll();
    Acquisto findByIDLista(int idLista);
}
