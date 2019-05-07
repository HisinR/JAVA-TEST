package cn.hisin.demo.creeper;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author hisin
 *
 * 爬虫webmagic测试
 *
 */
public class WebmagicDemo1 implements PageProcessor {

    /**
        部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
     */
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    @Override
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        page.putField("author", page.getUrl().regex("http://chn.lottedfs\\.cn/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//div[@id='prdasInnerList']//table/caption/text()").toString());
        System.out.println(page.getResultItems().get("name").toString());
        if (page.getResultItems().get("name") == null) {
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='content']/tidyText()"));

        // 部分三：从页面发现后续的url地址来抓取
//        page.addTargetRequests(page.getHtml().links().regex("(http://chn.lottedfs\\.cn/[\\w\\-]+/[\\w\\-]+)").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new WebmagicDemo1()).addUrl("http://chn.lottedfs.cn/kr/product/productDetail?prdNo=20000434472").thread(5).run();
    }
}
