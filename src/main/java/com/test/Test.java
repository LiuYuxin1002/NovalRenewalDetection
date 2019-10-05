package com.test;

import com.wx.WeChatMsgSend;
import com.wx.WeChatUrlData;

public class Test {

    public static void main(String[] args) {
        WeChatMsgSend swx = new WeChatMsgSend();
        try {
            String token = swx.getToken("ww2c45fcec2866eed6","e9j2rRESQpYD9m6Jm4qOJk0fH9zjz1LIdQIYTFJWJ5I");
            String postdata = swx.createpostdata("LiuYuXin", "file", 1000002, "content","这是一条测试信息");
            String resp = swx.post("utf-8", WeChatMsgSend.CONTENT_TYPE,(new WeChatUrlData()).getSendMessage_Url(), postdata, token);
            System.out.println("获取到的token======>" + token);
            System.out.println("请求数据======>" + postdata);
            System.out.println("发送微信的响应数据======>" + resp);
        }catch (Exception e) {
            e.getStackTrace();
        }
    }
}
