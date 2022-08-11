package Business;

public abstract class Documento {

    protected PdfAPI pdfAPI;

    protected Documento(PdfAPI pdfAPI) {
        this.pdfAPI = pdfAPI;
    }

    public abstract void invia(String indirizzo);

}
