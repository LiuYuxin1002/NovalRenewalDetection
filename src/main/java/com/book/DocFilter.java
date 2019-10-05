package com.book;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class DocFilter {
    private static Document doc = null;

    public static Chapter getChapter(String url) throws IOException {
        String htmlStr = doGet(url);
        doc = Jsoup.parse(htmlStr);

        String[] bookAndChap = doc.title().split("[_\\-]");
        String bookName = bookAndChap[0];
        String chapName = bookAndChap[1];
        String chapUrl = url;

        String[] urls = url.split("/");
        String bookId = getBookId(urls);    //https://www.tianxiabachang.cn/5_5154/1674905.html
        String siteUrl = urls[0] + "//" + urls[2];
        String nextChapUrl = siteUrl+getNextCharUrl();
        String content = getContent();

        return new Chapter(bookName, bookId, chapName, chapUrl, nextChapUrl, content, siteUrl);
    }

    private static String doGet(String url) throws IOException {
        String content=null;
        //创建httpClient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httpGet实例
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        if (response != null){
            HttpEntity entity =  response.getEntity();  //获取网页内容
            content = EntityUtils.toString(entity, "UTF-8");
            System.out.println("网页内容已加载！");
        }
        if (response != null){
            response.close();
        }
        if (httpClient != null){
            httpClient.close();
        }
        return content;
    }

    private static String getBookId(String[] urls) {
        String bookId = null;
        for (String s : urls){
            if(s.isEmpty()) continue;
            if(s.charAt(0)>='0'&&s.charAt(0)<='9') {
                bookId = s;
                break;
            }
        }
        return bookId;
    }

    private static String getContent(){
        Element ce = doc.getElementById("content");
        String s = ce.text();
        s.replaceAll("&nbsp;","\\ ");
        s.replaceAll("<br />", "\r\n");
        return s;
    }

    private static String getNextCharUrl(){
        Element url = doc.getElementsByClass("bottem1").get(0);
        Element child = url.child(3);
        String linkHref = child.attr("href");
        return linkHref;
    }

}
