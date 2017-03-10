package up.self.alfer.file;

import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;

/**
 * Created on 2017/3/10
 *
 * @author feng.wei
 */
public class PDFUtils {


    public static void readPDF(String filePath) {

        RandomAccessBufferedFileInputStream fis = null;
        try {
            fis = new RandomAccessBufferedFileInputStream(filePath);
            PDFParser pdfParser = new PDFParser(fis);
            pdfParser.parse();// 执行PDF解析过程
            PDDocument pdDocument = pdfParser.getPDDocument();
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(1);
            stripper.setEndPage(2);
            String content = stripper.getText(pdDocument);
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
