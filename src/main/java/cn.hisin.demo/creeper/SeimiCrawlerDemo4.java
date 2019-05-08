package cn.hisin.demo.creeper;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import com.alibaba.fastjson.JSON;
import org.seimicrawler.xpath.JXDocument;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hisin
 *
 *
 */
@Crawler(name = "demo4")
public class SeimiCrawlerDemo4 extends BaseSeimiCrawler {


    @Override
    public String[] startUrls() {
        return new String[]{"https://movie.douban.com"};
    }

    @Override
    public void start(Response response) {
        JXDocument document = response.document();
        Map<Object,Object> map = new HashMap<>(1 << 6);
        List<Object> title = document.sel("//div[@class='screening-bd']/ul/li/@data-title");
        List<Object> year = document.sel("//div[@class='screening-bd']/ul/li/@data-release");
        List<Object> rete = document.sel("//div[@class='screening-bd']/ul/li/@data-rate");
        List<Object> trailer= document.sel("//div[@class='screening-bd']/ul/li/@data-trailer");
        List<Object> list = new ArrayList<>();
        for (int i=0; i < title.size();i++){
            Map<String,Object> map1 = new HashMap<>(1 << 6);
            map1.put("title",title.get(i));
            map1.put("year",year.get(i));
            map1.put("rete",rete.get(i));
            map1.put("trailer",trailer.get(i));
            list.add(map1);
        }
        trailer.forEach(url->{
            System.out.println(url);
            push(Request.build("https://movie.douban.com", SeimiCrawlerDemo4::fun));
        });
        map.put("movie",list);
        System.out.println(JSON.toJSON(map));
    }

    private void fun(Response response){
        if (response==null){
            System.out.println("********请求失败********");
        }
        JXDocument document = response.document();
        System.out.println("开始解析二级页面");
        List<Object> sel = document.sel("//div[@class='mod']/ul/li/a/@href");
        sel.forEach(System.out::println);
    }
}
