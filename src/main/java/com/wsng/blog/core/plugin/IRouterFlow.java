package com.wsng.blog.core.plugin;

import com.wsng.blog.core.plugin.IDatasProcessor;

/**
 * @author wsng
 * @date 2019/12/29.
 */
public interface IRouterFlow {
    /**
     *
     * @return
     */
    boolean execute(IDatasProcessor dr, String transCode);

}
