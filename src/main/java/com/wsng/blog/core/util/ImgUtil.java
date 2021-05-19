package com.wsng.blog.core.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  @Author Sean
 *  @Date: 2021/3/22 19:47
 *  @Version 0.01
 *
 */

@Component
public class ImgUtil {

    @Value("${img.path}")
    private  String url;
    public  String uploadImg(String baseStr,String type){
        //获取当前日期
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String str = formatter.format(date);
        String imgUrl = url+type;

        if (baseStr == null) {
            return "图像数据不能为空";
        } else {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                File imageFile = new File(imgUrl);
                if (imageFile.exists()) {
                    imageFile.delete();
                }
                String data = baseStr.substring(baseStr.indexOf(",") + 1);//去除前缀
                byte[] b = decoder.decodeBuffer(data);

                for(int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {
                        b[i] = (byte)(b[i] + 256);
                    }
                }
                OutputStream out = new FileOutputStream(imgUrl);
                out.write(b);
                out.flush();
                out.close();
                return imgUrl;
            } catch (Exception var6) {
                return var6.toString();
            }
        }
    }

}
