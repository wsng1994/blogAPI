package com.wsng.blog.core.http;

import com.alibaba.fastjson.JSONObject;
import com.wsng.blog.core.plugin.IDatasProcessor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @Author Cooper
 * @Date: 20210315 22:02
 * @Version 0.01
 */


public class HttpProcessor {

    /**
     * 调用网易云api获取歌曲歌词
     * @param dr
     */
    public  IDatasProcessor  getHttp(IDatasProcessor dr){

        String id = dr.getParam("id").toString();
        try {
            URL localURL = null;
            if (false) {
                localURL = new URL("https://music.163.com/api/song/lyric?os=pc&id=" + id + "&tv=-1");
            } else {
                localURL = new URL("http://music.163.com/api/song/media?id=" + id);
            }

            InputStream localInputStream = localURL.openStream();
            InputStreamReader localInputStreamReader = new InputStreamReader(localInputStream, "utf-8");
            BufferedReader localBufferedReader = new BufferedReader(localInputStreamReader);

            String str = null;
            String temp = null;
            while ((str = localBufferedReader.readLine()) != null) {
                temp = str;
            }
            String jsonStr = JSONObject.parseObject(temp).get("lyric").toString();

            dr.setParam("lyric",jsonStr);
            localBufferedReader.close();
            localInputStreamReader.close();
            localInputStream.close();
        } catch (Exception localException) {
            if (false) {
                System.out.println("获取翻译歌词异常: " + localException.toString());
            } else {
                System.out.println("获取原歌词异常: " + localException.toString());
            }
        }
        System.out.println(dr);
        return dr;
    }


}
