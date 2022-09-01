package DAO.Interfacce;

import Model.Cliente;
import Model.PuntoVendita;
import Model.Utente;

import java.util.ArrayList;

public interface IClienteDAO {

    int add(Cliente cliente);
    int update(Cliente cliente);
    int delete(Cliente cliente);
    Cliente findByID(int idCliente);
    Cliente findByUsername(String username);
    ArrayList<Cliente> findAll();
    ArrayList<Cliente> findByPuntoVendita(int idPuntoVendita);
    boolean isAbilitato(int idCliente);
}
