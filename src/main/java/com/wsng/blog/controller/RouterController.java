package com.wsng.blog.controller;

import com.wsng.blog.config.annotation.LogPoint;
import com.wsng.blog.config.annotation.LogSE;
import com.wsng.blog.core.base.DatasProcessor;
import com.wsng.blog.core.plugin.IRouterFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
@RestController
@ControllerAdvice
public class RouterController {

    @Autowired
    IRouterFlow routerFlow;
    @Value("${img.path}")
    private String url;
    @SuppressWarnings("unchecked")
    @GetMapping("/img/**")

    public Map<String, Object> getExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setHeader("content-type", "image/jpg");
        String requestPath = request.getServletPath();
        String transCode = requestPath.substring(1).split("/")[1];
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String str = formatter.format(date);
        String path = url+transCode;

        InputStream is = new FileInputStream(new File(path));
        OutputStream os = response.getOutputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        while((len = is.read(buf)) > 0)
        {
            os.write(buf,0,len);
        }
        is.close();
        os.close();


        System.out.println(path);
//        routerFlow.execute(person,transCode);
//        person.getParam("LIST");
        return null;

    }
    @SuppressWarnings("unchecked")
    @LogSE
    @PostMapping("/**/*.do")
    @ResponseBody
    public Map<String, Object> execute(@RequestBody DatasProcessor person,
                                       HttpServletRequest request) {

        try{
            String requestPath = request.getServletPath();
            String transCode = requestPath.substring(1).split("\\.")[0];

            routerFlow.execute(person,transCode);
        }catch (Exception e){
//            System.out.println("---------------"+e.getMessage());
        }

        return person.getParamMap();

    }


    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map map = new HashMap();
        map.put("code", 100);
        map.put("msg", ex.getMessage());
        return map;
    }


}
