package DAO.Interfacce;



import Model.Lista;

import java.util.ArrayList;

public interface IListaDAO {

    int add(Lista lista);
    int update(Lista lista);
    int delete(Lista lista);
    Lista findByID(int idLista);
    ArrayList<Lista> findAll();
    ArrayList<Lista> findAll(int idCliente);


}
