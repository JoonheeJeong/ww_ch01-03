package org.zerock.w1.todo.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
@Slf4j
public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("-------init-------");
        log.info("-------init-------");
        log.info("-------init-------");

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("appName", "w1");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("-------destroy-------");
        log.info("-------destroy-------");
        log.info("-------destroy-------");
    }
}
