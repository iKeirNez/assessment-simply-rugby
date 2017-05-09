package com.keirnellyer.simplyrugby.listener;

import com.keirnellyer.simplyrugby.repository.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        context.setAttribute("brand", "SimplyRugby");
        context.setAttribute("author", "Keir Nellyer");
        context.setAttribute("userRepository", new UserRepository());
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }
}
