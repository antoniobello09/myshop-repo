package DAO.Interfacce;

import Model.Amministratore;

import java.util.ArrayList;

public interface IAmministratoreDAO {

    int add(Amministratore amministratore);
    int update(Amministratore amministratore);
    int delete(Amministratore amministrator);
    Amministratore findByID(int idAmministratore);
    Amministratore findByUsername(String username);
    ArrayList<Amministratore> findAll();

}
