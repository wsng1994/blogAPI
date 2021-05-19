package com.wsng.blog.core.plugin;

import com.wsng.blog.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @auther wsng
 * @date 2019/12/29.
 */
@Mapper
public interface IRouterDaoCK {
    List<Object> getClickHouseDatas(String var1,@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    Integer getClickHouseCounts (String var1,Object var2);

    Integer saveLogData(String var, List<CKbatchData> list);

    Integer saveBigData(List<BigData4CKTest> list);
    Integer synLogData(List<MyLogPoint> list);

}
