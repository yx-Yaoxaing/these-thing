package org.multi.source.multi;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DynamicDataSourceContextHolder {
    private static final Log logger = LogFactory.getLog(DynamicDataSourceContextHolder.class);
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static String getName() {
        return threadLocal.get();
    }

    public static void setName(String name) {
        logger.info("目前已切换数据库:"+name);
        threadLocal.set(name);
    }

    public static void destroy() {
        threadLocal.remove();
    }


}
