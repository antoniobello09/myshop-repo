package DAO.Interfacce;



import Model.Lista;
import Model.Produttore;

import java.util.ArrayList;

public interface IListaDAO {

    int add(Lista lista);
    int update(Lista lista);
    int delete(Lista lista);
    Lista findByID(int idLista);
    Lista findByInfo(int idPuntoVendita, String nomeLista, int idCliente);
    ArrayList<Lista> findAll();
    ArrayList<Lista> findAll(int idCliente);

}
