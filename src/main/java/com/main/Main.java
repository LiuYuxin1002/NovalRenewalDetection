package com.main;

import com.book.Book;
import com.book.Chapter;
import com.book.DocFilter;
import com.wx.WeChatMsgSend;
import com.wx.WeChatUrlData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static String bookId = "5_5154";
    public static String siteUrl = "https://www.tianxiabachang.cn";
    public static String bookName = "仙宫";
    public static String firstUrl = "https://www.tianxiabachang.cn/5_5154/1674905.html";

    public static void main(String[] args) {
        try {
            File bookConfig = new File(Book.FILE_LOCATION);
            if(!bookConfig.exists()){   //配置文件不存在
                Book book = new Book();
                book.setBookId(bookId);
                book.setSiteUrl(siteUrl);
                book.setBookName(bookName);
                book.setLatestChapId(0);
                List<Chapter> chapterList = new LinkedList<>();
                Chapter chapter = DocFilter.getChapter(firstUrl);
                chapterList.add(chapter);
                book.setChapters(chapterList);

                bookConfig.createNewFile();
                book.save();
            }

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(bookConfig));
            Book book = (Book) ois.readObject();
            String bookUrl = book.getBookUrl();
            Chapter lastChap = book.getChapters().get(book.getLatestChapId());
            while(!lastChap.getNextChapUrl().equals(bookUrl)){
                Chapter newChap = DocFilter.getChapter(lastChap.getNextChapUrl());
                book.appendChap(newChap);
                sendToWx(newChap.getChapTitle()+"\r\n"+newChap.getContent());
                lastChap = newChap;
            }
            book.save();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean sendToWx(String content){
        WeChatMsgSend swx = new WeChatMsgSend();
        try {
            String token = swx.getToken("ww2c45fcec2866eed6","e9j2rRESQpYD9m6Jm4qOJk0fH9zjz1LIdQIYTFJWJ5I");
            String postdata = swx.createpostdata("LiuYuXin", "text", 1000002, "content",content);
            String resp = swx.post("utf-8", WeChatMsgSend.CONTENT_TYPE,(new WeChatUrlData()).getSendMessage_Url(), postdata, token);
            System.out.println("获取到的token======>" + token);
            System.out.println("请求数据======>" + postdata);
            System.out.println("发送微信的响应数据======>" + resp);
        }catch (Exception e) {
            e.getStackTrace();
        }
        return true;
    }
}
