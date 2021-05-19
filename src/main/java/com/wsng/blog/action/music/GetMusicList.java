package com.wsng.blog.action.music;

import com.wsng.blog.core.plugin.CoreService;
import com.wsng.blog.core.plugin.IDatasProcessor;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author Cooper
 * @Date: 20210316
 * @Version 0.01
 */

@Component
public class GetMusicList implements CoreService {

    @Override
    public int executor(IDatasProcessor dr) {

        //这里先写几个假数据，后期数据库设计好后从数据库取
        List list = new ArrayList();
        Map map = new HashMap<String,Object>();
        map.put("id","1825842041");
        map.put("author","宇多田光");
        map.put("time","05:16");
        map.put("songName","Beautiful World -2021 Remastered");
        map.put("songUrl","./static/music/bw.mp3");
        map.put("coverUrl","../../../static/img/music/bw.jpeg");
        Map map1 = new HashMap<String,Object>();
        map1.put("id","1432901409");
        map1.put("author","奇迹魔术师");
        map1.put("time","04:48");
        map1.put("songName","So ist es immer - cover");
        map1.put("songUrl","./static/music/always.mp3");
        map1.put("coverUrl","../../../static/img/music/always.jpg");
        Map map2 = new HashMap<String,Object>();
        map2.put("id","1825843935");
        map2.put("author","宇多田光");
        map2.put("time","04:40");
        map2.put("songName","桜流し (2021 Remastered)");
        map2.put("songUrl","./static/music/yl.mp3");
        map2.put("coverUrl","../../../static/img/music/music.jpeg");

        list =  Arrays.asList(map,map1,map2);

        dr.setParam("songList",list);

        return 1;
    }
}
