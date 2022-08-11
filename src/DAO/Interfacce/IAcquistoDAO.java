package DAO.Interfacce;



import Model.Acquisto;
import Model.Lista;
import Model.Produttore;

import java.util.ArrayList;

public interface IAcquistoDAO {

    int add(Acquisto acquisto, Lista lista);
    int update(Acquisto acquisto);
    int delete(int idLista);
    Acquisto findByID(int idAcquisto);
    ArrayList<Acquisto> findAll();

}
