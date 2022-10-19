package Business.ModelBusiness;

import Business.SessionManager;
import DAO.Classi.ClienteDAO;
import DAO.Classi.PuntoVenditaDAO;
import DAO.UtenteDAO;
import Model.Cliente;
import Model.Utente;
import Business.ModelBusiness.TableModels.ClientiTableModel;

import javax.swing.*;
import java.util.ArrayList;

public class ClienteBusiness {

    private static ClienteBusiness instance;
    private ClientiTableModel clientiTableModel;
    private int idPuntoVenditaManager;


    public static synchronized ClienteBusiness getInstance() {
        if(instance == null) instance = new ClienteBusiness();
        return instance;
    }

    private ClienteBusiness() {}

    public int aggiungi(String username, String password, String nome, String cognome, String email, String dataCompleanno, String telefono, String residenza, String citta, String professione, String canale, boolean abilitato, int idPuntoVendita){
        Cliente cliente = new Cliente(username, password, nome, cognome, email, dataCompleanno, telefono, residenza, citta, professione, canale, abilitato, idPuntoVendita);
        if(UtenteDAO.getInstance().add(cliente) == 0) return 1;
        cliente.setIdUtente(UtenteDAO.getInstance().findByUsername(username).getIdUtente());
        if(ClienteDAO.getInstance().add(cliente) == 0) return 1;
        return 0;
    }

    public int update(Cliente cliente){
        if(UtenteDAO.getInstance().update(cliente) == 0) return 0;
        if(ClienteDAO.getInstance().update(cliente) == 0) return 0;
        return 1;
    }

    public JTable getTabellaClienti(){
        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");
        idPuntoVenditaManager = PuntoVenditaDAO.getInstance().findByManager(u.getIdUtente()).getIdPuntoVendita();
        ArrayList<Cliente> lista = ClienteDAO.getInstance().findByPuntoVendita(idPuntoVenditaManager);
        clientiTableModel = new ClientiTableModel(lista);
        JTable tabellaClienti = new JTable(clientiTableModel);
        return tabellaClienti;
    }

    public void abilita_disabilita(int selectedRow){
        Cliente c = clientiTableModel.getLista().get(selectedRow);
        if(c.isAbilitato()){
            c.setAbilitato(false);
        }else{
            c.setAbilitato(true);
        }
        ClienteBusiness.getInstance().update(c);
        clientiTableModel.setLista(ClienteDAO.getInstance().findByPuntoVendita(idPuntoVenditaManager));
        clientiTableModel.fireTableDataChanged();
    }

    public int cancella(int selectedRow){
        Cliente c = clientiTableModel.getLista().get(selectedRow);
        if(ClienteDAO.getInstance().delete(c)==0){
            return 1;
        }
        clientiTableModel.setLista(ClienteDAO.getInstance().findByPuntoVendita(idPuntoVenditaManager));
        clientiTableModel.fireTableDataChanged();
        return 0;
    }

}

