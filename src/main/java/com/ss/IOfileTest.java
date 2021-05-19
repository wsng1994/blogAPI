//package com.ss;
//
///**
// *  @Author Sean
// *  @Date: 2021/4/14 10:30
// *  @Version 0.01
// *
// */
//
//
//import java.io.*;
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * 测试Java io读取文件
// */
//public class IOfileTest {
//
//    public static void main(String[] args) {
//        try {
//
//            List<String> list = new LinkedList<String>();
//
//            StringBuffer sb= new StringBuffer("");
//            BufferedReader br = new BufferedReader(
//                    new InputStreamReader(
//                            new FileInputStream("/Users/haidongwang/tools/ckdata/manis.log"),"gbk"));
//            String linestr;//按行读取 将每次读取一行的结果赋值给linestr
//            while ((linestr = br.readLine()) != null) {
//
//                //将开始以"2021-"为起点的数据按行读取
//                if(linestr.startsWith("2021-")){
//                    //缓存list数据 每一万条数据清理一次并将数据写到文件中
//                    if(list.size()>=10){
//                        writeData(list);
//                        list.clear();
//                    }
//                    //清空list
//                    list.add(linestr);
//                }else{
//                    String strC = list.get(list.size()-1);
//                    StringBuilder strN = new StringBuilder(strC);
//                    strN.append(linestr);
//                    list.set(list.size()-1,strN.toString());
//                }
//
//                //按列解析数据
////                if(linestr.contains("n1")||linestr.contains("n6")||linestr.contains("结束")||linestr.contains("交易开始")){
////                    sb.append(linestr).append("\n");
////                }
////                System.out.println(linestr);
////                System.out.println("=======================================================");
//            }
//            writeData(list);
//            br.close();//关闭IO
//
//        } catch (Exception e) {
//            System.out.println("文件操作失败");
//            e.printStackTrace();
//        }
//    }
//
//    private static void writeData(List<String> list)  {
//        StringBuffer sb= new StringBuffer("");
//
//        for(int i=0;i<list.size();i++){
//            String t = list.get(i).replaceAll("\n","");
//            sb.append(t).append("\n");
//            System.out.println(list.get(i));
//            System.out.println("----------------");
//        }
//        try {
//            FileWriter writer = new FileWriter("/Users/haidongwang/workspace/workspaceg/wsng_blog/log/ns.log",true);
//            BufferedWriter bw = new BufferedWriter(writer);
//            bw.write(sb.toString());
//            bw.close();
//            writer.close();
//        }catch (Exception e){
////            e.printStackTrace();
//            System.out.println("出错了");
//        }
//
//    }
//
//
//    private void readLineDetail(String str){
//
//        String[] strs = str.split(" - ");
//
//    }
//}
