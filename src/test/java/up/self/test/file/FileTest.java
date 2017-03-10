package up.self.test.file;

import org.junit.Test;
import up.self.alfer.file.PDFUtils;
import up.self.alfer.file.TxtUtils;

import java.io.File;
import java.util.Set;

/**
 * Created on 2017/3/10
 *
 * @author feng.wei
 */
public class FileTest {

    @Test
    public void test_read_txt() {
        Set<String> set = TxtUtils.readTxt("D:\\data\\dz\\RT.txt");
        String outPathDir = "D:\\data\\dz\\deweight";
        String outPath = outPathDir + File.separator + "RT-de,txt";
        File file = new File(outPathDir);
        if (!file.exists()) {
            file.mkdir();
        }

        TxtUtils.writeFile(outPath, set);
    }

    @Test
    public void test_read_pdf() {
        PDFUtils.readPDF("D:\\data\\dz\\create_pdf.pdf");
    }

    @Test
    public void test_str_trim() {
        String s = "  qu  kong ge   ";
        s = s.trim().replaceAll(" +", ",");
        String[] ss = s.split(",");
        for (String st : ss) {
            System.out.println(st);
        }
        System.out.println(ss.length);
    }
}
