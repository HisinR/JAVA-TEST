package cn.hisin.demo.file;


import cn.hisin.demo.StringUtils;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * ExcelDemo
 *
 * @author hisin
 */
public class ExcelDemo {

    public static void main(String[] args) throws IOException {

        File file = new File("E:\\Work\\JavaTest\\JAVA-TEST\\财务需求.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = xssfWorkbook.getSheet("Sheet3");
        int firstRowNum = sheet.getFirstRowNum();
        XSSFRow row = sheet.getRow(firstRowNum);
        Iterator<Cell> cellIterator = row.cellIterator();
        cellIterator.forEachRemaining(cell -> {
                    String firstSpell = StringUtils.getFirstSpell(cell.getStringCellValue(), HanyuPinyinCaseType.UPPERCASE);
                    System.out.print(firstSpell + "\t");
                }
        );
        System.out.println("总行数=" + sheet.getPhysicalNumberOfRows());
        //TODO 这个list用来保存学校信息，暂时不知道该何时创建，当一个单元格为空时，需要用到
        List<String> list = new ArrayList<>();
        for (Row row1 : sheet) {
            for (Cell cell : row1) {
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
                            Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                            String value = sdf.format(date);
                            System.out.print(value + "\t");
                            break;
                        }
                        DecimalFormat decimalFormat = new DecimalFormat("0.00");
                        System.out.print(decimalFormat.format(cell.getNumericCellValue()) + "\t");
                        break;
                    case Cell.CELL_TYPE_STRING:
                        //如果第一行第一个单元格
                        if (cell.getColumnIndex() == 0 && row1.getRowNum() != 0) {
                            System.out.print("添加学校");
                            list.add(cell.getStringCellValue());
                        }
                        System.out.print(cell.getStringCellValue() + "\t");
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        if (cell.getColumnIndex() == 0) {

                            System.out.print("学校=" + list + "\t");
                        }
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + cell.getCellType());
                }
            }
            System.out.println();
        }
    }
}
