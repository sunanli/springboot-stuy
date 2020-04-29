package com.tangwh.utils;

import com.tangwh.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HtmlParseUtil {

    public static void main(String[] args) throws IOException {



        new HtmlParseUtil().parseJD("java").forEach(System.out::println);
    }


    public List<Content> parseJD(String keywords) throws IOException {
        //获取请求 https://search.jd.com/Search?keyword=java

        // 前提 需要联网.

        String url = "https://search.jd.com/Search?keyword=" + keywords;
        //解析网页 (jsoup 返回的就是Document对象)

        Document document = Jsoup.parse(new URL(url), 30000);

        // 所有js 中的可以使用的方法 这里都能用
        Element element = document.getElementById("J_goodsList");
//        System.out.println(element.html());

        // 获取所有的li元素
        Elements elements = element.getElementsByTag("li");


        List<Content> goodsList = new ArrayList<>();


        // 获取元素中的内容
        for (Element el : elements) {

            // 获取照片 // 关于照片 都是懒加载。
            String img = el.getElementsByTag("img").eq(0).attr("source-data-lazy-img");

            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();

//            System.out.println("=================================");
//            System.out.println(img);
//            System.out.println(price);
//            System.out.println(title);

            Content content = new Content();
            content.setImg(img);
            content.setPrice(price);
            content.setTitle(title);
            goodsList.add(content);


        }
        return goodsList;

    }

}
