package Business.Bridge;


import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PdfBoxAPI implements PdfAPI {

    @Override
    public void creaPdf(String text, String outFile) {

        try (PDDocument doc = new PDDocument())
        {

            PDPage page = new PDPage();
            doc.addPage(page);

            PDFont font = PDType1Font.HELVETICA_BOLD;

            try (PDPageContentStream contents = new PDPageContentStream(doc, page))
            {
                contents.beginText();
                contents.setFont(font, 12);
                contents.newLineAtOffset(100, 700);
                contents.showText(text);
                contents.endText();
            }

            doc.save(outFile);
            System.out.println("File pdf generato con PdfBox salvato in: " + outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
