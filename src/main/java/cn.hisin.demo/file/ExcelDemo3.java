package cn.hisin.demo.file;

import cn.wanghaomiao.seimi.boot.Run;
import com.sun.deploy.nativesandbox.IntegrityProcess;
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

/**
 * @author Hisin
 */
public class ExcelDemo3 {

    private static List<ExcelVO> readExcel(String path) throws IOException, IllegalAccessException {
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

        // 循环每个月份的数据层，最里层迭代变量
//        int x = 1;

        List<ExcelVO> list = new ArrayList<>();
        while (d < lastRowNum) {
            XSSFRow dataRow = sheetAt.getRow(d);
//            int temp = x;
            for (int var = 0; var < dates.length; var++, j++, d++) {
                ExcelVO excelVO = new ExcelVO();
                //读取数据行
                XSSFCell collegCell = dataRow.getCell(0);
                //获取学校
                excelVO.setCollege(Objects.requireNonNull(getValue(collegCell)).toString());
                //获取实际日期对应的数据
//                for (int k = 0; k < keys.length; k++, x++) {
//                    XSSFRow sheetAtRow = sheetAt.getRow(x);
//                    //获取项目名称
//                    XSSFCell kCell = sheetAtRow.getCell(1);
//                    String value = Objects.requireNonNull(getValue(kCell)).toString();
//                    XSSFCell dataCell = sheetAtRow.getCell(j);
//                    setExcelVO(excelVO, value, dataCell);
//                }
                excelVO.setDate(dates[var]);
                excelVO.setElectricityFee("");
                excelVO.setHotWaterAmount("");
                excelVO.setWaterAmount("");
                excelVO.setWaterFee("");
                list.add(excelVO);
//                x = temp;
            }
//            x = d;
            // 所有月份循环结束，重新初始化j
            j = 2;
        }

        int var3 = 1;

        for (int var = 1; var < lastRowNum; var++) {

            for (int var1 = 0, k = 2; var1 < dates.length; var1++, k++) {
                ExcelVO excelVO = new ExcelVO();
                excelVO.setElectricityFee("");
                excelVO.setHotWaterAmount("");
                excelVO.setWaterAmount("");
                excelVO.setWaterFee("");
                for (int var2 = 0; var2 < list.size(); var2++, var3++) {
                    if (var2 == dates.length) {
                        break;
                    }
                    XSSFRow dataRow = sheetAt.getRow(var3);
                    XSSFCell cell = dataRow.getCell(0);
                    String college = Objects.requireNonNull(getValue(cell)).toString();
                    excelVO.setCollege(college);
                    excelVO.setDate(dates[var1]);
                    XSSFCell xssfCellK = dataRow.getCell(1);
                    if (list.contains(excelVO)) {
                        ExcelVO excelVO1 = list.get(var1);
                        String value = Objects.requireNonNull(getValue(xssfCellK)).toString();
                        XSSFCell dataCell = dataRow.getCell(k);
                        setExcelVO(excelVO1, value, dataCell);
                        setExcelVO(excelVO, value, dataCell);
                    }


                }

            }

        }

        System.out.println(list.size());
        return null;
    }

    private static void setExcelVO(ExcelVO excelVO, String value, XSSFCell dataCell) throws IllegalAccessException {
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

    public static void main(String[] args) throws IOException, IllegalAccessException {
        long time = System.currentTimeMillis();
        readExcel("E:\\Work\\JavaTest\\JAVA-TEST\\2019年水电及收入数据.xlsx");
        System.out.printf("执行耗时：%s/s", (System.currentTimeMillis() - time) / 1000);
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
