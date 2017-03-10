package up.self.alfer.file;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 2017/3/10
 *
 * @author feng.wei
 */
public class TxtUtils {

    public static Set<String> readTxt(String filePath) {
        FileInputStream fis = null;
        BufferedReader br = null;
        Set<String> set = new HashSet<String>();
        try {
            fis = new FileInputStream(filePath);
            br = new BufferedReader(new InputStreamReader(fis));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] sarr = line.trim().replaceAll(" +", ",").split(",");
                for (String s : sarr) {
                    set.add(s);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return set;
        }

    }

    public static void writeFile(String outPath, Set<String> set) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPath)));
            for (String s : set) {
                bw.write(s + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
