package cn.hisin.demo.file;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.assertj.core.internal.bytebuddy.implementation.bytecode.Throw;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 */
public class ExcelDemo2 {

    public static void main(String[] args) throws IOException {
        File file = new File("E:\\Work\\JavaTest\\JAVA-TEST\\财务需求.xlsx");
        SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
        InputStream fileInputStream = new FileInputStream(file);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet3 = xssfWorkbook.getSheet("Sheet3");
        int lastRowNum = sheet3.getLastRowNum();
        List<String> firstRow = new ArrayList<>();
        List<Map<String,Object>> listDate = new ArrayList<>();
        Map<Integer,Object> dataMap= new HashMap<>(1 << 8);
        for (int r = 0; r < lastRowNum; r++) {
            ExcelDO excelDO = new ExcelDO();
            //获取一行
            XSSFRow row = sheet3.getRow(r);
            //获取一行单元格的总数
            short lastCellNum = row.getLastCellNum();
            List<Object> tempList = new ArrayList<>();
            for (int j = 0; j < lastCellNum; j++) {
                Object cellValue=null;
                XSSFCell cell = row.getCell(j);
                //如果等于第一行
                if (row.getRowNum() == 0) {
                    cell = row.getCell(j);
                    //如果第一行不等于字符报错
                    if (cell.getCellType() != Cell.CELL_TYPE_STRING) {
                        System.out.println("读取错误，首行必须为文本类型");
                        throw new IllegalStateException("未知类型: " + cell.getCellType());
                    }
                    //首行
                    firstRow.add(cell.getStringCellValue());
                }else {
                    //首行保存完了之后。开始保存数据信息
                    cellValue = getCellValue(cell);
                    tempList.add(cellValue);
                }
                //单元格循环结束
            }
            //首行不添加
            if (row.getRowNum()!=0){
                dataMap.put(row.getRowNum(), tempList);
            }
        }
        firstRow.forEach(System.out::println);
        dataMap.forEach((r,e)-> {
            System.out.println("key="+r+"\t"+"v="+e);
        });

    }


    private static Object getCellValue(Cell cell){
        Object o =null;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)){
                    o=HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    break;
                }
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                String format = decimalFormat.format(cell.getNumericCellValue());
                o=Double.parseDouble(format);
                break;
            case Cell.CELL_TYPE_STRING:
                o=cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                if (cell.getColumnIndex()==0){
                    o="college";
                }
                break;
            case Cell.CELL_TYPE_ERROR:
                throw new IllegalStateException("错误: " + cell.getCellType());
            default:
                throw new IllegalStateException("未知类型: " + cell.getCellType());
        }
        return o;
    }

    /**
     *导入
     */
    public static void exportExcel(){


    }
}
