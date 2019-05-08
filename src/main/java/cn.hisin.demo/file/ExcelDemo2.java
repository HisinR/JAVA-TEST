package cn.hisin.demo.file;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 使用阿里云的easyexcel
 *
 */
public class ExcelDemo2 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("F:\\IDEA项目\\JAVA-TEST\\财务需求.xlsx");
        InputStream inputStream = new FileInputStream(file);
        ExcelListener listener = new ExcelListener();
        ExcelReader excelReader = new ExcelReader(inputStream,ExcelTypeEnum.XLSX, null, listener);
        excelReader.read();
    }

    static class ExcelListener extends AnalysisEventListener {

        private List<Object> datas = new ArrayList<Object>();
        @Override
        public void invoke(Object o, AnalysisContext analysisContext) {
            System.out.println("当前行："+analysisContext.getCurrentRowNum());
            System.out.println(o);
            //数据存储到list，供批量处理，或后续自己业务逻辑处理。
            datas.add(o);
            //根据自己业务做处理
            doSomething(o);
        }
        private void doSomething(Object o) {
            //1、入库调用接口
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        }

        public List<Object> getDatas() {
            return datas;
        }

        public void setDatas(List<Object> datas) {
            this.datas = datas;
        }
    }
}
