package com.wsng.blog.config.datasource;/**
 * @Author Cooper
 * @Date: 2021/4/11 18:30
 * @Version 0.01
 */

/**
 *  @Author Sean
 *  @Date: 2021/4/11 18:30
 *  @Version 0.01
 *
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<DataSourceKey> currentDatesource = new ThreadLocal<>();

    /**
     * 清除当前数据源
     */
    public static void clear() {
        currentDatesource.remove();
    }

    /**
     * 获取当前使用的数据源
     *
     * @return 当前使用数据源的ID
     */
    public static DataSourceKey get() {
        return currentDatesource.get();
    }

    /**
     * 设置当前使用的数据源
     *
     * @param value 需要设置的数据源ID
     */
    public static void set(DataSourceKey value) {
        currentDatesource.set(value);
    }

    /**
     * 设置从从库读取数据
     * 采用简单生成随机数的方式切换不同的从库
     */
    public static void setSlave() {
//        if (RandomUtils.nextInt(0, 2) > 0) {
        //若没有数据源的时候默认数据源设为DB_BLOG
        DataSourceContextHolder.set(DataSourceKey.DB_BLOG);
//        } else {
//            DataSourceContextHolder.set(DataSourceKey.DB_CK);
//        }
    }


}
