package DAO.Interfacce;

import Model.FornitoreServizi;
import Model.Produttore;

import java.util.ArrayList;

public interface IFornitoreServiziDAO {

    int add(FornitoreServizi fornitoreServizi);
    int update(FornitoreServizi fornitoreServizi);
    int delete(FornitoreServizi fornitoreServizi);
    FornitoreServizi findByID(int idFornitore);
    FornitoreServizi findByName(String nomeFornitore);
    FornitoreServizi findByName(String nomeFornitore, int closeConn);
    ArrayList<FornitoreServizi> findAll();

}
