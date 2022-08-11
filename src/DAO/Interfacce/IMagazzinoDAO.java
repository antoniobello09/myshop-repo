package DAO.Interfacce;

import Model.Magazzino;
import Model.Produttore;
import Model.PuntoVendita;
import Model.SchedaProdotto;

import java.util.ArrayList;

public interface IMagazzinoDAO {

    int add();
    int addSchedaProdotto(SchedaProdotto schedaProdotto, int idMagazzino);
    int updateSchedaProdotto(SchedaProdotto schedaProdotto, int idMagazzino, int piano, int corsia, int scaffale, int disponibilita);
    int updateSchedaProdotto(SchedaProdotto schedaProdotto, int idMagazzino, int disponibilita);
    int update(Magazzino magazzino);
    int delete(Magazzino magazzino);
    Magazzino findByID(int idMagazzino);
    ArrayList<Magazzino> findAll();

}
