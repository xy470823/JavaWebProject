package com.test.listener;



import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalCacheListener implements ServletContextListener {

    private static Logger logger = LoggerFactory.getLogger(LocalCacheListener.class);

    private Map<String,Object> container = new ConcurrentHashMap<>();

    /**
     * 当Servlet 容器启动Web 应用时调用该方法。在调用完该方法之后，容器再对Filter 初始化，并且对那些在Web 应用启动时就需要被初始化的Servlet 进行初始化
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        container.put("test",1);
        logger.warn("启动时加载数据："+container);
    }

    /**
     * 当Servlet 容器终止Web 应用时调用该方法。在调用该方法之前，容器会先销毁所有的Servlet 和Filter 过滤器。
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        container.remove("test");
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        LocalCacheListener.logger = logger;
    }

    public Map<String, Object> getContainer() {
        return container;
    }

    public void setContainer(Map<String, Object> container) {
        this.container = container;
    }
}
