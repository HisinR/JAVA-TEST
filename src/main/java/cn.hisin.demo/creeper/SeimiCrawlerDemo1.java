package cn.hisin.demo.creeper;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import org.seimicrawler.xpath.JXDocument;

import java.util.List;

/**
 * @author hisin
 */
@Crawler(name = "demo", delay=1)
public class SeimiCrawlerDemo1 extends BaseSeimiCrawler {
    @Override
    public String[] startUrls() {
        return new String[]{"http://www.cnblogs.com/"};
    }

    @Override
    public void start(Response response) {
        JXDocument document = response.document();
        List<Object> urls  = document.sel("//a[@class='titlelnk']/@href");

        urls.forEach(url ->{
            push(Request.build(url.toString(),SeimiCrawlerDemo1::getTitle));
        });
    }

    public void getTitle(Response response){
        JXDocument doc = response.document();
        try {
            logger.info("url:{} {}", response.getUrl(), doc.sel("//h1[@class='postTitle']/a/text()|//a[@id='cb_post_title_url']/text()"));
            //do something
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
