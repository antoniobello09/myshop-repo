package DAO.Interfacce;

import Model.Manager;
import Model.Utente;

import java.util.ArrayList;

public interface IManagerDAO {

    int add(Manager manager);
    int update(Manager manager);
    int delete(Manager manager);
    Manager findByID(int idManager);
    Manager findByUsername(String username);
    ArrayList<Manager> findAll();

}
