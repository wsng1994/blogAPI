package com.wsng.blog.core.plugin;

import com.wsng.blog.config.annotation.LogPoint;
import com.wsng.blog.core.plugin.IDatasProcessor;
import com.wsng.blog.core.plugin.IRouterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author Cooper
 * @Date: 20210220 11:23
 * @Version 0.01
 */
@Component
@Service

public interface CoreService {
    public int executor(IDatasProcessor dr);
}
