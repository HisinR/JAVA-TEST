package cn.hisin.demo.file;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExcelDemo3 {

    public static List<ExcelVO> readExcel(String path) throws IOException {
        File file = new File(path);
        SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
        InputStream fileInputStream = new FileInputStream(file);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheetAt = xssfWorkbook.getSheetAt(0);

        int lastRowNum = sheetAt.getLastRowNum();
        NumberFormat numberFormat = new DecimalFormat("0");
        int[] dates = new int[12];
        //读取第一行
        for (int i = 0; i < 1; i++) {
            XSSFRow row = sheetAt.getRow(i);
            int lastCellNum = row.getLastCellNum();
            for (int j = 2; j < lastCellNum - 1; j++) {
                XSSFCell cell = row.getCell(j);
                System.out.println(numberFormat.format(cell.getNumericCellValue()));
                dates[j] = Integer.parseInt(numberFormat.format(cell.getNumericCellValue()));
            }
        }

        Calendar calendar = Calendar.getInstance();
        Date[] dates1 = new Date[12];
        for (int i = 0; i < dates.length; i++) {
            if (dates[i] != 0) {
                if (dates[i] == 2) {
                    calendar.set(2017, dates[i] - 1, 28);
                } else {
                    calendar.set(2017, dates[i] - 1, 30);
                }

                dates1[i] = calendar.getTime();
            }
        }
        for (Date date : dates1) {
            if (date != null) {
                System.out.println(sdf.format(date));
            }
        }


        return null;
    }

    public static void main(String[] args) throws IOException {
        readExcel("F:\\IDEA项目\\JAVA-TEST\\浙江正蓝2017年收入成本分析.xlsx");
    }

    private static Object getValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    return HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                }
                return decimalFormat.format(cell.getNumericCellValue());
            default:
                return null;
        }
    }

}
