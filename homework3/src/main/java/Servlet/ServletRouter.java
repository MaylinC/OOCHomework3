package Servlet;

import authentication.Authentication;
import authentication.UserService;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServletRouter {
    private DatabaseConnector databaseConnector;
    private static Map<String, HttpServlet> urls = new HashMap<>();
    private List<Class<? extends AbstractRoutableServlet>> servletClasses;

    public ServletRouter() {
        AllServlet allServlet = new AllServlet();
        servletClasses = allServlet.getServletClasses();
    }
    public DatabaseConnector getDatabaseConnector(){
        return databaseConnector;
    }

    public void initialize(Context ctx) {
        databaseConnector = new DatabaseConnector();
        UserService userService = new UserService(databaseConnector);
        Authentication authentication = new Authentication(userService);
        authentication.setUserService(userService);


        for (Class<? extends AbstractRoutableServlet> servletClass : servletClasses) {
            try {
                AbstractRoutableServlet httpServlet = servletClass.newInstance();// create object
                httpServlet.setAuthentication(authentication);
                Tomcat.addServlet(ctx, httpServlet.getClass().getName(), httpServlet);
                ctx.addServletMapping(httpServlet.getPattern(), httpServlet.getClass().getName());
                urls.put(httpServlet.getPattern(),httpServlet);

            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public  Map<String, HttpServlet> getUrls() {
        return urls;
    }
}
