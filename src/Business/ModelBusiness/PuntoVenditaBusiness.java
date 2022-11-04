package Business.ModelBusiness;

import Business.SessionManager;
import DAO.Classi.ArticoloDAO;
import DAO.Classi.ManagerDAO;
import DAO.Classi.PuntoVenditaDAO;
import DAO.Interfacce.IPuntoVenditaDAO;
import DAO.UtenteDAO;
import Model.Articolo;
import Model.Manager;
import Model.PuntoVendita;
import View.ShopDialog;

import java.awt.*;
import java.util.ArrayList;

public class PuntoVenditaBusiness {

    private static PuntoVenditaBusiness instance;
    private IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();

    public static synchronized PuntoVenditaBusiness getInstance() {
        if(instance == null) instance = new PuntoVenditaBusiness();
        return instance;
    }

    private PuntoVenditaBusiness() {

    }

    public ArrayList<String> getAllShopsAddresses(){
        ArrayList<PuntoVendita> shopsList = puntoVenditaDAO.findAll();
        ArrayList<String> indirizzi = new ArrayList<>();
        for(int i=0;i<shopsList.size();i++){
            String shopAddress = shopsList.get(i).getIndirizzo() + ", " + shopsList.get(i).getCitta();
            indirizzi.add(shopAddress);
        }
        return indirizzi;
    }

    public void scegliPuntovenditaDialog(Component frame){
        ArrayList<String> shopsList = PuntoVenditaBusiness.getInstance().getAllShopsAddresses();
        //Mostra il Dialog per la scelta del punto vendita
        String value = ShopDialog.showDialog(frame,
                null,
                "Punto Vendita",
                shopsList);

        String[] indirizzo = value.split(", ");
        PuntoVendita puntoVendita = PuntoVenditaDAO.getInstance().findByName(indirizzo[0], indirizzo[1]);
        SessionManager.getInstance().getSession().put("idPuntoVendita", puntoVendita.getIdPuntoVendita()); //salvataggio dell'ID del punto vendita dove si sta accedendo
    }

    public String creaMessaggioHTMLBenvenuto(){
        PuntoVendita puntoVendita = PuntoVenditaDAO.getInstance().findByID((Integer) SessionManager.getInstance().getSession().get("idPuntoVendita"));
        return "<html>" +
                "<h1><center><b>MY SHOP</b></center></h1><br>" +
                "<h2><center><b>Benvenuto nel punto vendita di " + puntoVendita.getCitta() + ", " + puntoVendita.getIndirizzo() + "</b></center><br>" +
                "Ciao, effettua il login per iniziare, oppure sfoglia il catalogo usando il pulsante a lato!</h2>" +
                "</html>";
    }

    //L'aggiunta di un manager si divide in due fasi
    //1. UtenteDAO.add
    //2. ManagerDAO.add
    public int crea(String emailManager, String usernameManager, String passwordManager,
                     String cittaPuntoVendita, String indirizzoPuntoVendita){

        Manager manager = new Manager(usernameManager, passwordManager, emailManager);
        if(UtenteDAO.getInstance().add(manager)==0){
            return 1;   //L'utente esiste già
        }

        //Ormai aggiunto il manager alla tabella utente
        //Recupero l'id del nuovo manager e faccio ManagerDAO.add
        manager.setIdUtente(UtenteDAO.getInstance().findByUsername(usernameManager).getIdUtente());
        ManagerDAO.getInstance().add(manager);
        //Aggiungo un nuovo puntovendita
        PuntoVendita puntoVendita = new PuntoVendita(manager.getIdUtente(), cittaPuntoVendita, indirizzoPuntoVendita);
        if(PuntoVenditaDAO.getInstance().add(puntoVendita)==0){
            return 1; //Il punto vendita esiste già
        }
        return 0;
    }

}
