package Business.Bridge;

import Business.Bridge.Documento;
import Business.Bridge.PdfAPI;
import Business.MailHelper;
import Business.SessionManager;
import DAO.Classi.*;
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

        // 1. genera la stringa
        String text = "";

        Iterator<Lista_has_Articolo> i = lista.iterator();
        while(i.hasNext()) {
            Articolo a = ArticoloDAO.getInstance().findById(i.next().getIdArticolo());
            if(ArticoloDAO.getInstance().isProdotto(a) || ArticoloDAO.getInstance().isProdottoComposito(a)){
                text += a.getNome() + " ";
                Prodotto prodotto = ProdottoDAO.getInstance().findByID(a.getIdArticolo());
                Posizione posizione = PosizioneDAO.getInstance().findByID(prodotto.getIdPosizione());
                text += posizione.getPiano() + " " + posizione.getCorsia() + " " + posizione.getScaffale() + " ";
            }
        }


        try {
            File tempFile = File.createTempFile("myshopLista", ".pdf");
            pdfAPI.creaPdf(text, tempFile.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*
            PuntoVendita p = PuntoVenditaDAO.getInstance().findByID((Integer)SessionManager.getInstance().getSession().get("idPuntoVendita"));
            Manager m = ManagerDAO.getInstance().findByID(p.getIdManager());
            MailHelper.getInstance().send(m.getEmail(), m.getPassword(), indirizzo,"My Shop PDF", "Bella rega");
         */

    }
}
