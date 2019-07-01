package com.atguigu.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WebconfListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();

        servletContext.setAttribute("APP_PATH",servletContext.getContextPath());


        System.out.println(servletContext.getContextPath());

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
