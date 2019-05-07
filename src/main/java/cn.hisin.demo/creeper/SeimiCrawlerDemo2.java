package cn.hisin.demo.creeper;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Response;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import org.seimicrawler.xpath.JXDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 爬虫测试2
 *
 * @author hisin
 */
@Crawler(name = "demo2")
public class SeimiCrawlerDemo2 extends BaseSeimiCrawler {

    @Override
    public String[] startUrls() {
        return new String[]{"http://chn.lottedfs.cn/kr/product/productDetail?prdNo=20000559564"};
    }

    @Override
    public void start(Response response) {
        JXDocument document = response.document();
        String content = response.getContent();
        List<Object> sel = document.sel("//div[@class='info']/div[@class='buyBtn soldOut mt30']/span/text()");
        List<Object> sel2 = document.sel("//div[@class='info']/div[@class='buyBtn soldOut']/a/text()");
        if (sel.size()==0){
            System.out.println("断货");
        }else if (sel2.size()!=0){
            System.out.println("可申请到货通知");
        }else {
            System.out.println("有货");
        }


//        File file = new File("E:\\Work\\JavaTest\\JAVA-TEST\\test2.html");
//        try {
//            if (file.createNewFile()){
//                FileWriter fileWriter = new FileWriter(file);
//                fileWriter.write(content);
//                fileWriter.close();
//                System.out.println("写入完成");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

}
