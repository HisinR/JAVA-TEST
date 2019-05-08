package cn.hisin.demo.creeper;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Response;
import com.alibaba.fastjson.JSON;
import org.seimicrawler.xpath.JXDocument;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author hisin
 * 爬虫测试3 爬取豆瓣
 */
@Crawler(name = "demo3")
public class SeimiCrawlerDemo3 extends BaseSeimiCrawler {
    @Override
    public String[] startUrls() {
        return new String[]{"https://movie.douban.com/subject/26100958/trailer"};
    }

    @Override
    public void start(Response response) {
        JXDocument document = response.document();
        List<Object> sel = document.sel("//div[@class='mod']/ul/li/a/@href");
        System.out.println(sel);
    }
}
