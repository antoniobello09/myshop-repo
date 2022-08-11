package Business;

import Model.Prodotto;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DocumentoListaAcquisto extends Documento {

    List<Prodotto> lista;

    public DocumentoListaAcquisto(List<Prodotto> lista, PdfAPI pdfAPI) {
        super(pdfAPI);
        this.lista = lista;
    }

    @Override
    public void invia(String indirizzo) {

        // 1. genera il pdf
        String text = "";

        Iterator<Prodotto> i = lista.iterator();
        while(i.hasNext()) {

            Prodotto p = i.next();
            text += p.getNome()+", ";
        }

        File tempFile = null;

        try {
            tempFile = File.createTempFile("listaAcquisto", ".pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(tempFile!=null) pdfAPI.creaPdf(text, tempFile.getAbsolutePath());


        // 2. lo invia...
        // codice per inviare mail all'indirizzo specificato
    }
}
