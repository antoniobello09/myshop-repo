package Business.ModelBusiness;

import Business.SessionManager;
import DAO.Classi.ProdottoDAO;
import DAO.Classi.PuntoVenditaDAO;
import DAO.Classi.SchedaProdottoDAO;
import Model.Prodotto;
import Model.SchedaProdotto;
import Model.Utente;
import View.AppFrame;
import View.Panels.Center.Manager.Altro.DisponibilitaDialog;
import Business.ModelBusiness.TableModels.DisponibilitaTableModel;

import javax.swing.*;
import java.util.ArrayList;

public class SchedaProdottoBusiness {

    private static SchedaProdottoBusiness instance;
    private DisponibilitaTableModel disponibilitaTableModel;
    private int idPuntoVenditaManager;

    public static synchronized SchedaProdottoBusiness getInstance() {
        if(instance == null) instance = new SchedaProdottoBusiness();
        return instance;
    }

    private SchedaProdottoBusiness() {
    }

    public JTable getTabellaSchedeProdotto(){
        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");
        idPuntoVenditaManager = PuntoVenditaDAO.getInstance().findByManager(u.getIdUtente()).getIdPuntoVendita();
        ArrayList<SchedaProdotto> disponibilitaList = SchedaProdottoDAO.getInstance().findAllByShop(idPuntoVenditaManager);
        disponibilitaTableModel = new DisponibilitaTableModel(disponibilitaList);
        JTable tabellaDisponibilita = new JTable(disponibilitaTableModel);
        return tabellaDisponibilita;
    }

    public int rifornisci(int selectedRow, AppFrame appFrame){
        SchedaProdotto sp = disponibilitaTableModel.getLista().get(selectedRow);
        sp.setDisponibilita(DisponibilitaDialog.showDialog(appFrame, "Rifornisci Punto Vendita", sp.getDisponibilita()));
        if(SchedaProdottoDAO.getInstance().update(sp)==0){
            return 1;
        }
        sp = SchedaProdottoDAO.getInstance().findByShop_Product(sp.getIdProdotto(), sp.getIdPuntoVendita());
        PrenotazioneBusiness.getInstance().nuovoRifornimento(sp);
        disponibilitaTableModel.setLista(SchedaProdottoDAO.getInstance().findAllByShop(idPuntoVenditaManager));
        disponibilitaTableModel.fireTableDataChanged();
        return 0;
    }

    public int associa(String indirizzoPuntoVenditaCompleto, String nomeProdotto){
        Prodotto p = ProdottoDAO.getInstance().findByName(nomeProdotto);
        if(p !=null){
            int idProdotto = p.getIdArticolo();
            String[] indirizzo = indirizzoPuntoVenditaCompleto.split(", ");
            int idPuntoVendita = PuntoVenditaDAO.getInstance().findByName(indirizzo[0],indirizzo[1]).getIdPuntoVendita();
            SchedaProdotto schedaProdotto = new SchedaProdotto(idProdotto, 0, idPuntoVendita);
            if(SchedaProdottoDAO.getInstance().findByShop_Product(idProdotto, idPuntoVendita) != null){
                return 3;
            }
            if(SchedaProdottoDAO.getInstance().add(schedaProdotto)==0){
                return 2;
            }
            return 0;
        }
        return 1;
    }

}
