package utils;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.io.image.ImageDataFactory;

import java.io.File;
import java.io.IOException;

public class PdfReportUtil {

    public static void generatePdf(String folderPath, String pdfPath) throws IOException {

        PdfWriter writer = new PdfWriter(pdfPath);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                Image img = new Image(ImageDataFactory.create(file.getAbsolutePath()));
                img.scaleToFit(500, 700);
                document.add(img);
            }
        }

        document.close();
    }
}
