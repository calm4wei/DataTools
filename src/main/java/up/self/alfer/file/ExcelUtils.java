package up.self.alfer.file;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created on 2017/3/10
 *
 * @author feng.wei
 */
public class ExcelUtils {

    private static Map<String, Set<String>> map = new HashMap<String, Set<String>>();

    /**
     * @param filePath
     * @throws IOException
     */
    public static void readExcelAndDeweight(String filePath) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filePath));
        Sheet sheet = workbook.getSheet("NgdsTXCB");
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        for (int rowIndex = firstRowNum + 1; rowIndex <= lastRowNum; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            addSets(row.getCell(2), "UF");

        }
    }

    /**
     * 读取内容不是进行额外操作，如分隔、去重等
     *
     * @param filePath
     * @throws IOException
     */
    public static void readExcel(String filePath) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filePath));
        Sheet sheet = workbook.getSheet("NgdsTXCB");
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        System.out.println("firstrow=" + firstRowNum + " ,lastrow=" + lastRowNum);
        // 非叙词
        FileOutputStream fosToUF = new FileOutputStream(new File("D:\\data\\dz\\UF.txt"));
        // 下位词
        FileOutputStream fosToNT = new FileOutputStream(new File("D:\\data\\dz\\NT.txt"));
        // 上位词
        FileOutputStream fosToBT = new FileOutputStream(new File("D:\\data\\dz\\BT.txt"));
        // 族首词
        FileOutputStream fosToTT = new FileOutputStream(new File("D:\\data\\dz\\TT.txt"));
        // 相关词
        FileOutputStream fosToRT = new FileOutputStream(new File("D:\\data\\dz\\RT.txt"));
        // 叙词
        FileOutputStream fosToCuser = new FileOutputStream(new File("D:\\data\\dz\\cUSER.txt"));
        // 英文叙词
        FileOutputStream fosToENGNAME = new FileOutputStream(new File("D:\\data\\dz\\ENGNAME.txt"));
        // XT_ORGWORD_S（叙词）
        FileOutputStream fosToXT = new FileOutputStream(new File("D:\\data\\dz\\XT.txt"));
        for (int rowIndex = firstRowNum + 1; rowIndex <= lastRowNum; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            writeFile(row.getCell(2), fosToUF);
            writeFile(row.getCell(3), fosToNT);
            writeFile(row.getCell(4), fosToBT);
            writeFile(row.getCell(5), fosToTT);
            writeFile(row.getCell(6), fosToRT);
            writeFile(row.getCell(11), fosToCuser);
            writeFile(row.getCell(12), fosToENGNAME);
            writeFile(row.getCell(15), fosToXT);
        }
        fosToUF.close();
        fosToNT.close();
        fosToBT.close();
        fosToTT.close();
        fosToRT.close();
        fosToCuser.close();
        fosToENGNAME.close();
        fosToXT.close();

    }

    public static void addSets(Cell cell, String key) {

        if (null == cell) {
            return;
        }

        String[] values = cell.getStringCellValue().replaceAll(",", " ").trim()
                .split(" ");

        Set<String> s = null;
        if (map.containsKey(key)) {
            s = map.get(key);

        } else {
            s = new HashSet<String>();
            map.put("UF", s);
        }

        for (String value : values) {
            s.add(value);
        }

    }


    public static void writeFile(Cell cell, FileOutputStream fos) {
        try {
            if (cell != null) {
                fos.write(cell.getStringCellValue().getBytes());
                // 换行
                fos.write("\n".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        readExcel("D:\\data\\dz\\地质汉语叙词表.xls");
    }

}
