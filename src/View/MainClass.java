package View;

import Business.*;
import Model.*;

import javax.swing.*;
import java.awt.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.Matrix;

public class MainClass {

    public static void main(String args[]) {


        //variante 1 del factory method
        Cliente c = new Cliente();
        Notifica n = creaNotifica("ciao", c);
        n.notificaUtente();

        //variante 2 del factory method
        SendNotificationClient pushClient = new SendPushNotificationClient("ciao", c);


        //ABSTRACT FACTORY
        ProdottoFactory pFac = (ProdottoFactory) FactoryProvider.getFactory(FactoryProvider.TipoFactory.PRODOTTO);
        Articolo a = pFac.crea();
        ICategoria cat = pFac.creaCategoria();


        //ESEMPI VIEW
        JFrame win = new JFrame("Prima finestra");
        Container cnt = win.getContentPane();
        JLabel lbl = new JLabel("Benvenuti");
        cnt.add(lbl);
        win.setSize(400, 300);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //win.setVisible(true);

        //OGGETTO JFRAME
        /*
        JFrame win2 = new MyFrame("Seconda finestra");

        JFrame win3 = new MyGridLayoutFrame("Terza finestra");

        JFrame win4 = new MyBorderLayoutFrame("Quarta finestra");

        JFrame win5 = new MyHierarchialLayoutFrame("Quinta finestra");
        */

        //...
        new AppFrame();

        String message = "ciao";


        //String filename
/*
        try (PDDocument doc = new PDDocument())
        {

            File tempFile = File.createTempFile("lista", ".pdf");
            PDPage page = new PDPage();
            doc.addPage(page);

            PDFont font = PDType1Font.HELVETICA_BOLD;

            try (PDPageContentStream contents = new PDPageContentStream(doc, page))
            {
                contents.beginText();
                contents.setFont(font, 12);
                contents.newLineAtOffset(100, 700);
                contents.showText(message);
                contents.endText();
            }

            doc.save(tempFile.getAbsolutePath());
            System.out.println("File pdf salvato in: "+tempFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*
        try {
            doIt("ciao");
        } catch (IOException e) {
            e.printStackTrace();
        }
         */

        //dimostrazione pattern bridge
        java.util.List<Prodotto> lista = new ArrayList<Prodotto>();

        // popola la lista prendendola da un DAO


        Documento doc = new DocumentoListaAcquisto(lista, new PdfBoxAPI());
        doc.invia("roberto@gmail.com");

    }

    public static void doIt( String message) throws IOException
    {
        // the document
        try (PDDocument doc = new PDDocument())
        {
            File tempFile = File.createTempFile("lista", ".pdf");
            // Page 1
            PDFont font = PDType1Font.HELVETICA;
            PDPage page = new PDPage(PDRectangle.A4);
            doc.addPage(page);
            float fontSize = 12.0f;

            PDRectangle pageSize = page.getMediaBox();
            float centeredXPosition = (pageSize.getWidth() - fontSize/1000f)/2f;
            float stringWidth = font.getStringWidth( message );
            float centeredYPosition = (pageSize.getHeight() - (stringWidth*fontSize)/1000f)/3f;

            PDPageContentStream contentStream = new PDPageContentStream(doc, page, AppendMode.OVERWRITE, false);
            contentStream.setFont( font, fontSize );
            contentStream.beginText();
            // counterclockwise rotation
            for (int i=0;i<8;i++)
            {
                contentStream.setTextMatrix(Matrix.getRotateInstance(i * Math.PI * 0.25,
                        centeredXPosition, pageSize.getHeight() - centeredYPosition));
                contentStream.showText(message + " " + i);
            }
            // clockwise rotation
            for (int i=0;i<8;i++)
            {
                contentStream.setTextMatrix(Matrix.getRotateInstance(-i*Math.PI*0.25,
                        centeredXPosition, centeredYPosition));
                contentStream.showText(message + " " + i);
            }

            contentStream.endText();
            contentStream.close();

            // Page 2
            page = new PDPage(PDRectangle.A4);
            doc.addPage(page);
            fontSize = 1.0f;

            contentStream = new PDPageContentStream(doc, page, AppendMode.OVERWRITE, false);
            contentStream.setFont( font, fontSize );
            contentStream.beginText();

            // text scaling and translation
            for (int i=0;i<10;i++)
            {
                contentStream.setTextMatrix(new Matrix(12f + (i * 6), 0, 0, 12f + (i * 6),
                        100, 100f + i * 50));
                contentStream.showText(message + " " + i);
            }
            contentStream.endText();
            contentStream.close();

            // Page 3
            page = new PDPage(PDRectangle.A4);
            doc.addPage(page);
            fontSize = 1.0f;

            contentStream = new PDPageContentStream(doc, page, AppendMode.OVERWRITE, false);
            contentStream.setFont( font, fontSize );
            contentStream.beginText();

            int i = 0;
            // text scaling combined with rotation
            contentStream.setTextMatrix(new Matrix(12, 0, 0, 12, centeredXPosition, centeredYPosition*1.5f));
            contentStream.showText(message + " " + i++);

            contentStream.setTextMatrix(new Matrix(0, 18, -18, 0, centeredXPosition, centeredYPosition*1.5f));
            contentStream.showText(message + " " + i++);

            contentStream.setTextMatrix(new Matrix(-24, 0, 0, -24, centeredXPosition, centeredYPosition*1.5f));
            contentStream.showText(message + " " + i++);

            contentStream.setTextMatrix(new Matrix(0, -30, 30, 0, centeredXPosition, centeredYPosition*1.5f));
            contentStream.showText(message + " " + i++);

            contentStream.endText();
            contentStream.close();

            doc.save( tempFile.getAbsolutePath() );
            System.out.println(tempFile.getAbsolutePath() );
        }
    }

    //factory method. ATTENZIONE: 'static' non fa parte del design pattern
    public static Notifica creaNotifica(String msg, Cliente c) {
        String canale = c.getCanalePreferito();
        canale = "SMS";
        if(canale == null || canale.isEmpty())
            return null;
        if("SMS".equals(canale)) return new NotificaSMS(msg, c);
        if("EMAIL".equals(canale)) return new NotificaEMail(msg, c);
        if("PUSH".equals(canale)) return new NotificaPush(msg, c);
        return null;

    }

}
