package Business.Bridge;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PdfBoxAPI implements PdfAPI {

    @Override
    public void creaPdf(String text, String outFile) {


        PDDocument doc = new PDDocument();

        String[] tokens = text.split(" ");

        try {
            for(int j=0; j < tokens.length/40 + 1; j++) {
                PDPage page = new PDPage();
                doc.addPage(page);
                PDPageContentStream contents = new PDPageContentStream(doc, page);

                contents.beginText();
                contents.setLeading(16f);
                contents.newLineAtOffset(80, 700);
                contents.setFont(PDType1Font.TIMES_ROMAN, 15);
                contents.showText("Nome prodotto");
                contents.endText();
                contents.beginText();
                contents.newLineAtOffset(280, 700);
                contents.showText("Piano");
                contents.endText();
                contents.beginText();
                contents.newLineAtOffset(380, 700);
                contents.showText("Corsia");
                contents.endText();
                contents.beginText();
                contents.newLineAtOffset(480, 700);
                contents.showText("Scaffale");
                contents.endText();
                for (int i = 0; i < tokens.length/4 || i == 10; i++) {
                    contents.beginText();
                    contents.newLineAtOffset(80, 700 - (i+1)*20);
                    contents.showText(tokens[i*4]);
                    contents.endText();
                    contents.beginText();
                    contents.newLineAtOffset(280, 700 - (i+1)*20);
                    contents.showText(tokens[i*4+1]);
                    contents.endText();
                    contents.beginText();
                    contents.newLineAtOffset(380, 700 - (i+1)*20);
                    contents.showText(tokens[i*4+2]);
                    contents.endText();
                    contents.beginText();
                    contents.newLineAtOffset(480, 700 - (i+1)*20);
                    contents.showText(tokens[i*4+3]);
                    contents.endText();
                }
                contents.close();
            }

            doc.save(outFile);
            doc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
