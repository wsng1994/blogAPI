package com.wsng.blog.core.util;

import com.alibaba.druid.sql.visitor.functions.Lcase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @Author Sean
 * @Date: 2021/4/21 10:38
 * @Version 0.01
 */
public class SEQUtil {

    public static String seqGenerator(int i){

        Random rand = new Random();
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String str = df.format(date);

        switch (i) {
            case 17:
                return str;
            case 32:
                return "SEQ_SEAN"+str+rand.nextInt(100000000);
            default:
                return UUID.randomUUID().toString();

        }


    }


    public static void main(String[] args) {
        Date date = new Date();
        Random rand = new Random();

        UUID.randomUUID();
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String str = df.format(date);
        for(int i=0;i<1000;i++){
            System.out.println(UUID.randomUUID());
        }

    }
}
