package com.zzx.esdemo02.utils;

import com.zzx.esdemo02.entity.JdContent;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 网页解析工具
 */
@Slf4j
public class HtmlParseUtil {
    public static void main(String[] args) throws Exception {
        parseJd("vue").forEach(jdContent -> {
            log.info("{}", jdContent);
        });
    }

    public static List<JdContent> parseJd(String keyword) throws Exception {
        //获取请求
        String url = "https://search.jd.com/Search?keyword=" + keyword;
        //解析网页
        //返回浏览器的document对象
        Document document = Jsoup.parse(new URL(url), 30000);
        //所有在js中能够使用的在这里都能使用
        Element goodsList = document.getElementById("J_goodsList");
        //log.info("返回的结果：{}", goodsList.html());
        //封装对象
        List<JdContent> jdContentList = new ArrayList<>();

        //获取所有的李元素
        Elements elements = goodsList.getElementsByTag("li");
        //这里的每一个el就是一个li标签了
        for (Element el : elements) {
            //关于图片特别多年的网站，左右的图片都是延迟加载
            //data-lazy-img
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            // log.info("img:{},price:{},title:{}", img, price, title);
            jdContentList.add(new JdContent(img, price, title));
        }
        return jdContentList;
    }
}
