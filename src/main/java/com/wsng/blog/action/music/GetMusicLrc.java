package com.wsng.blog.action.music;

import com.wsng.blog.core.plugin.CoreService;
import com.wsng.blog.core.http.HttpProcessor;
import com.wsng.blog.core.plugin.IDatasProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author Cooper
 * @Date:
 * @Version 0.01
 */

@Component
public class GetMusicLrc implements CoreService {
    @Override
    public int executor(IDatasProcessor dr) {
        HttpProcessor httpProcessor = new HttpProcessor();
        dr = httpProcessor.getHttp(dr);
        return 1;
    }
}
