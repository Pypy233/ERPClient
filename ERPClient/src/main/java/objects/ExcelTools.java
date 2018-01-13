package objects;

/**
 * created by py on 2017.12.28
 * 导出Excel的工具类
 */

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ExcelTools {
    public static void export(String fileName, String[][] output) throws Exception{
        String path = "E:\\123\\ERPClient-master\\ERPClient-master\\ERPClient\\src\\main\\Excels\\" + fileName + ".xls";
        File file = new File(path);
        OutputStream os = new FileOutputStream(file);
        WritableWorkbook wb = Workbook.createWorkbook(os);
        WritableSheet sheet = wb.createSheet("detail", 0);

        for(int i = 0; i < output.length; i++){
            for(int j = 0; j < output[0].length; j++){
                sheet.addCell(new Label(i, j, String.valueOf(output[i][j])));
            }
        }
        wb.write();
        wb.close();
    }

    /**
     * demo成功

     public static void main(String[] args) throws Exception{
      String[][] output = {{"t1", "demo"}, {"t2", "demo1"}, {"t3", "demo2"}, {"t4", "demo3"}};
             export("demo", output);
    }*/
}
