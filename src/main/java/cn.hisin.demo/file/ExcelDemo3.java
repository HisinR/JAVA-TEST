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

    public static List<ExcelVO> readExcel(String path) throws IOException, IllegalAccessException {
        File file = new File(path);
        SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
        InputStream fileInputStream = new FileInputStream(file);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheetAt = xssfWorkbook.getSheet("2019年水电费");

        int lastRowNum = sheetAt.getLastRowNum();


        //读取第一行 拿到日期
        XSSFRow row = sheetAt.getRow(0);
        int lastCellNum = row.getLastCellNum();
        Date[] dates = new Date[lastCellNum - 2];
        for (int j = 2, i = 0; j < lastCellNum; j++, i++) {
            XSSFCell cell = row.getCell(j);
            Object o = getValue(cell);
            if (o instanceof Date) {
                Date date = (Date) o;
                dates[i] = date;
            }
        }

        String[] keys = {"用水量", "水费", "电费", "开水用水量"};

        // 根据月份获取数据，随着月份递增
        int j = 2;

        // 循环最外层的数据层
        int d = 1;

        // 循环每个月份的数据层
        int x = 1;

        List<ExcelVO> list = new ArrayList<>();
        while (d < lastRowNum) {
            XSSFRow dataRow = sheetAt.getRow(d);
            int temp = x;
            for (int l = 0; l < dates.length; l++, j++, d++) {
                ExcelVO excelVO = new ExcelVO();
                //读取数据行
                XSSFCell collegCell = dataRow.getCell(0);
                //获取学校
                excelVO.setCollege(Objects.requireNonNull(getValue(collegCell)).toString());
                //获取实际日期对应的数据
                for (int k = 0; k < keys.length; k++, x++) {
                    XSSFRow sheetAtRow = sheetAt.getRow(x);
                    //获取项目名称
                    XSSFCell kCell = sheetAtRow.getCell(1);
                    String value = Objects.requireNonNull(getValue(kCell)).toString();
                    XSSFCell dataCell = sheetAtRow.getCell(j);
                    switch (value) {
                        case "用水量":
                            excelVO.setWaterAmount(Objects.requireNonNull(getValue(dataCell)).toString());
                            break;
                        case "水费":
                            excelVO.setWaterFee(Objects.requireNonNull(getValue(dataCell)).toString());
                            break;
                        case "电费":
                            excelVO.setElectricityFee(Objects.requireNonNull(getValue(dataCell)).toString());
                            break;
                        case "开水用水量":
                            excelVO.setHotWaterAmount(Objects.requireNonNull(getValue(dataCell)).toString());
                            break;
                        default:
                            throw new IllegalAccessException("没有找到匹配");
                    }
                }
                excelVO.setDate(dates[l]);
                list.add(excelVO);
                x = temp;
            }
            x = d;
            // 所有月份循环结束，重新初始化j
            j = 2;
        }
        System.out.println(list.size());
        return null;
    }

    public static void main(String[] args) throws IOException, IllegalAccessException {
        readExcel("F:\\IDEA项目\\JAVA-TEST\\2019年水电及收入数据.xlsx");
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
            case Cell.CELL_TYPE_BLANK:
                return "";

            case Cell.CELL_TYPE_FORMULA:
                cell.setCellType(Cell.CELL_TYPE_STRING);
                return cell.getStringCellValue();
            default:
                return null;
        }
    }

}
