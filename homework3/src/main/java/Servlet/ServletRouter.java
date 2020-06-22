package Servlet;

import authentication.Authentication;
import authentication.UserService;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.util.ArrayList;
import java.util.List;

public class ServletRouter {

    private final List<Class<? extends AbstractRoutableServlet>> servletClasses = new ArrayList<>();

    {
        servletClasses.add(HomeServlet.class); //get only class
        servletClasses.add(LoginServlet.class);
        servletClasses.add(RegisterServlet.class);
        servletClasses.add(AlterPasswordServlet.class);
        servletClasses.add(UserServlet.class);
        servletClasses.add(EditServlet.class);
    }

    public void initialize(Context ctx) {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        UserService userService = new UserService(databaseConnector);
        Authentication authentication = new Authentication(userService);

        for (Class<? extends AbstractRoutableServlet> servletClass : servletClasses) {
            try {
                AbstractRoutableServlet httpServlet = servletClass.newInstance();// create object
                httpServlet.setAuthentication(authentication);
                Tomcat.addServlet(ctx, httpServlet.getClass().getName(), httpServlet);
                ctx.addServletMapping(httpServlet.getPattern(), httpServlet.getClass().getName());

            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
