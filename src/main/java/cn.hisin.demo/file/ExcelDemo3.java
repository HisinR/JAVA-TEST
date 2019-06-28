package cn.hisin.demo.file;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ExcelDemo3 {

    public static List<ExcelVO> readExcel(String path) throws IOException {
        File file = new File(path);
        SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
        InputStream fileInputStream = new FileInputStream(file);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheetAt = xssfWorkbook.getSheetAt(0);

        int lastRowNum = sheetAt.getLastRowNum();
        for (int i = 0; i < 1; i++) {
            XSSFRow row = sheetAt.getRow(i);
            int lastCellNum = row.getLastCellNum();
            for (int j = 1; j < lastCellNum - 1; j++) {
                XSSFCell cell = row.getCell(j);
                System.out.println(getValue(cell));
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        readExcel("C:\\Users\\qiqiang\\Desktop\\正蓝数据(1)\\正蓝数据\\浙江正蓝2017年收入成本分析.xlsx");
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
