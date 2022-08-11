package DAO.Interfacce;

import Model.Cliente;
import Model.PuntoVendita;
import Model.Utente;

import java.util.ArrayList;

public interface IClienteDAO {

    int add(Cliente cliente, PuntoVendita puntoVendita);
    int update(Cliente cliente);
    int delete(Cliente cliente);
    Cliente findByID(int idCliente);
    Cliente findByUsername(String username);
    ArrayList<Cliente> findAll();
}
