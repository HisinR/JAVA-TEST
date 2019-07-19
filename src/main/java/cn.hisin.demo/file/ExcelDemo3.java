package cn.hisin.demo.file;


import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.xml.crypto.Data;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Hisin
 */
public class ExcelDemo3 {
    /**
     * 先获取所有时间，在获取所有学校，在把每个学校去重，
     * 根据时间为每个学校添加项目，在去excel表里读取每行的数据，
     *
     * @param path
     * @return
     * @throws IOException
     * @throws IllegalAccessException
     */
    private static List<ExcelVO> readExcel(String path) throws IOException, IllegalAccessException {
        File file = new File(path);
        SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
        InputStream fileInputStream = new FileInputStream(file);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);

        XSSFSheet sheetAt = xssfWorkbook.getSheet("2019年水电成本、管理费");

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

        // 循环最外层的数据层

        //读取学校
        Set<String> colleges = new LinkedHashSet<>();
        for (int c = 1; c < lastRowNum; c++) {
            XSSFRow cRow = sheetAt.getRow(c);
            XSSFCell cell = cRow.getCell(0);
            colleges.add(Objects.requireNonNull(getValue(cell)).toString());
        }

        //每个学校根据日期生成对象
        List<ExcelVO> list = new ArrayList<>();
        for (String college : colleges) {
            for (Date date : dates) {
                ExcelVO excelVO = new ExcelVO();
                //获取学校
                excelVO.setCollege(college);
                excelVO.setDate(date);
                excelVO.setElectricityFee("");
                excelVO.setHotWaterAmount("");
                excelVO.setWaterAmount("");
                excelVO.setWaterFee("");
                list.add(excelVO);
            }
        }

        for (int var1 = 0, k = 2; var1 < dates.length; var1++, k++) {
            ExcelVO excelVO = new ExcelVO();
            excelVO.setElectricityFee("");
            excelVO.setHotWaterAmount("");
            excelVO.setWaterAmount("");
            excelVO.setWaterFee("");
            for (int var2 = 0, var3 = 1; var2 < list.size(); var2++, var3++) {
                XSSFRow dataRow = sheetAt.getRow(var3);
                if (dataRow != null) {
                    XSSFCell cell = dataRow.getCell(0);
                    String college = Objects.requireNonNull(getValue(cell)).toString();
                    excelVO.setCollege(college);
                    excelVO.setDate(dates[var1]);
                    XSSFCell xssfCellK = dataRow.getCell(1);
                    if (list.contains(excelVO)) {
                        ExcelVO excelVO1 = list.get(list.indexOf(excelVO));
                        String value = Objects.requireNonNull(getValue(xssfCellK)).toString();
                        XSSFCell dataCell = dataRow.getCell(k);
                        setExcelVO(excelVO1, value, dataCell);
                        setExcelVO(excelVO, value, dataCell);
                    } else {
                        Optional<ExcelVO> first = list.stream().filter(excelVO1 ->
                                excelVO1.getCollege().equals(excelVO.getCollege()) && excelVO.getDate().equals(excelVO1.getDate())
                        ).findFirst();
                        ExcelVO excelVO2 = first.get();
                        excelVO.setElectricityFee(excelVO2.getElectricityFee());
                        excelVO.setHotWaterAmount(excelVO2.getHotWaterAmount());
                        excelVO.setWaterAmount(excelVO2.getWaterAmount());
                        excelVO.setWaterFee(excelVO2.getWaterFee());
                        excelVO.setManagerFee(excelVO2.getManagerFee());
                        excelVO.setWaterElecCosts(excelVO2.getWaterElecCosts());
                        if (list.contains(excelVO)) {
                            ExcelVO excelVO1 = list.get(list.indexOf(excelVO));
                            String value = Objects.requireNonNull(getValue(xssfCellK)).toString();
                            XSSFCell dataCell = dataRow.getCell(k);
                            setExcelVO(excelVO1, value, dataCell);
                            setExcelVO(excelVO, value, dataCell);
                        }
                    }
                }
            }
        }
        System.out.println(list.size());
        return list;
    }

    public static void readExcel2(String path) throws IOException {
        File file = new File(path);
        SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
        InputStream fileInputStream = new FileInputStream(file);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        List<ExcelVO> list = new ArrayList<>();
        for (int sheetIndex = 0; sheetIndex < xssfWorkbook.getNumberOfSheets(); sheetIndex++) {
            XSSFSheet sheetAt = xssfWorkbook.getSheetAt(sheetIndex);
            int lastRowNum = sheetAt.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                ExcelVO excelVO = new ExcelVO();
                XSSFRow row = sheetAt.getRow(i);
                int lastCellNum = row.getLastCellNum();
                for (int var = 0; var < lastCellNum; var++) {
                    XSSFCell cell = row.getCell(var);
                    if (!"".equals(Objects.requireNonNull(getValue(cell)).toString())) {
                        switch (var) {
                            case 0:
                                excelVO.setCollege(Objects.requireNonNull(getValue(cell)).toString());
                                break;
                            case 1:
                                excelVO.setElectricityAmount(getValue(cell).toString());
                                break;
                            case 2:
                                excelVO.setRecharge(getValue(cell).toString());
                                break;
                            case 3:
                                Date date = (Date) getValue(cell);
                                excelVO.setDate(date);
                                break;
                            default:
                                System.out.println("找不到");
                        }
                    }

                }
                list.add(excelVO);
            }
        }

        for (ExcelVO excelVO : list) {
            System.out.println(excelVO);
        }
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
            case "管理费":
                excelVO.setManagerFee(Objects.requireNonNull(getValue(dataCell)).toString());
                break;
            case "水电成本":
                excelVO.setWaterElecCosts(Objects.requireNonNull(getValue(dataCell)).toString());
                break;
            default:
                throw new IllegalAccessException("没有找到匹配");
        }
    }

    public static void main(String[] args) throws IOException, IllegalAccessException {
        long time = System.currentTimeMillis();
        List<ExcelVO> list = readExcel("E:\\Work\\JavaTest\\JAVA-TEST\\2019年水电及收入数据.xlsx");
        for (ExcelVO excelVO : list) {
            System.out.println(excelVO);
        }
        readExcel2("E:\\Work\\JavaTest\\JAVA-TEST\\2019水电比.xlsx");

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
                System.out.println("选择的是公式");
                cell.setCellType(Cell.CELL_TYPE_STRING);
                return cell.getStringCellValue();
            default:
                return null;
        }
    }

}
