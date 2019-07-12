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
import java.util.*;

public class ExcelDemo3 {

    public static List<ExcelVO> readExcel(String path) throws IOException {
        File file = new File(path);
        SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
        InputStream fileInputStream = new FileInputStream(file);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheetAt = xssfWorkbook.getSheet("2019年水电费");

        int lastRowNum = sheetAt.getLastRowNum();

        Date[] dates = new Date[12];
        //读取第一行 拿到日期
        for (int i = 0; i < 1; i++) {
            XSSFRow row = sheetAt.getRow(i);
            int lastCellNum = row.getLastCellNum();
            for (int j = 2; j < lastCellNum - 1; j++) {
                XSSFCell cell = row.getCell(j);
                Object o = getValue(cell);
                if (o instanceof Date) {
                    Date date = (Date) o;
                    dates[j] = date;
                }
            }
        }

        List<Object> list = new ArrayList<>();
        //读取数据行
        for (int i = 1; i <= 3; i++) {
            XSSFRow row = sheetAt.getRow(i);
            int lastCellNum = row.getLastCellNum();
            Map<String, Object> dataMap = new HashMap<>(1 << 5);
            for (int j = 0; j < 1; j++) {
                XSSFCell cell = row.getCell(0);
                System.out.println("学校：" + getValue(cell));
                dataMap.put("college",getValue(cell));
            }

            //项目名称
            for (int k = 1; k < 2; k++) {
                XSSFCell kCell = row.getCell(k);
                System.out.println(getValue(kCell) + ":");
                // 月份
                Map<Object, Object> dateMap = new HashMap<>(1 << 2);
                for (int d = 0, dk = 2; d < dates.length; d++, dk++) {
                    XSSFCell dataCell = row.getCell(dk);
                    if (dataCell != null) {
                        dateMap.put(dates[d], getValue(dataCell));
                        System.out.println(getValue(dataCell));
                    }
                }
            }
        }


        return null;
    }

    public static void main(String[] args) throws IOException {
        readExcel("E:\\Work\\JavaTest\\JAVA-TEST\\2019年水电及收入数据.xlsx");
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
