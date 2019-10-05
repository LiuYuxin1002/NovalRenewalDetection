package com.book;

import java.io.*;
import java.util.List;

public class Book implements Serializable {
    public static final String FILE_LOCATION = "bookInfo";

    private List<Chapter> chapters;
    private String bookUrl;
    private String bookId;
    private String siteUrl;
    private String bookName;
    private int latestChapId;

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
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

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getLatestChapId() {
        return latestChapId;
    }

    public void setLatestChapId(int latestChapId) {
        this.latestChapId = latestChapId;
    }

    public void save() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_LOCATION));
        oos.writeObject(this);
        oos.close();
    }

    public void appendChap(Chapter chapter){
        this.chapters.add(chapter);
        this.latestChapId++;
    }

    public void saveToTxt(String filePath){

    }
}
