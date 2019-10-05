package com.book;

import org.jsoup.nodes.Document;

import java.io.Serializable;

public class Chapter implements Serializable {
    private String bookName;
    private String bookId;  //url中的书号
    private String chapTitle;
    private String thisChapUrl;
    private String nextChapUrl;
    private String content;
    private String siteUrl; //书籍站点URL

    public String getNextChapUrl() {
        return nextChapUrl;
    }

    public void setNextChapUrl(String nextChapUrl) {
        this.nextChapUrl = nextChapUrl;
    }

    public String getThisChapUrl() {
        return thisChapUrl;
    }

    public void setThisChapUrl(String thisChapUrl) {
        this.thisChapUrl = thisChapUrl;
    }

    public String getChapTitle() {
        return chapTitle;
    }

    public void setChapTitle(String chapTitle) {
        this.chapTitle = chapTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public Chapter(String bookName, String bookId, String chapTitle, String thisChapUrl, String nextChapUrl, String content, String siteUrl) {
        this.bookName = bookName;
        this.bookId = bookId;
        this.chapTitle = chapTitle;
        this.thisChapUrl = thisChapUrl;
        this.nextChapUrl = nextChapUrl;
        this.content = content;
        this.siteUrl = siteUrl;
    }
}
