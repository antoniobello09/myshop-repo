package Business.Bridge;

import Business.Bridge.Documento;
import Business.Bridge.PdfAPI;
import Business.MailHelper;
import Business.SessionManager;
import DAO.Classi.ArticoloDAO;
import DAO.Classi.ManagerDAO;
import DAO.Classi.PuntoVenditaDAO;
import Model.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DocumentoListaAcquisto extends Documento {

    List<Lista_has_Articolo> lista;

    public DocumentoListaAcquisto(List<Lista_has_Articolo> lista, PdfAPI pdfAPI) {
        super(pdfAPI);
        this.lista = lista;
    }

    @Override
    public void invia(String indirizzo) {

        // 1. genera il pdf
        String text = "";

        Iterator<Lista_has_Articolo> i = lista.iterator();
        while(i.hasNext()) {
            Articolo a = ArticoloDAO.getInstance().findById(i.next().getIdArticolo());
            text += a.getNome()+", ";
        }


        try {
            File tempFile = File.createTempFile("myshop", ".pdf");
            System.out.println(tempFile);
            pdfAPI.creaPdf(text, tempFile.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PuntoVendita p = PuntoVenditaDAO.getInstance().findByID((Integer)SessionManager.getInstance().getSession().get("idPuntoVendita"));
        Manager m = ManagerDAO.getInstance().findByID(p.getIdManager());
        MailHelper.getInstance().send(m.getEmail(), m.getPassword(), indirizzo,"My Shop PDF", "Bella rega");

    }
}
