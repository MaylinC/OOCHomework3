package Servlet;

import java.util.ArrayList;
import java.util.List;

public class AllServlet {
    private final List<Class<? extends AbstractRoutableServlet>> servletClasses = new ArrayList<>();

    public void addServlet() {
        servletClasses.add(HomeServlet.class); //get only class
        servletClasses.add(LoginServlet.class);
        servletClasses.add(RegisterServlet.class);
        servletClasses.add(AlterPasswordServlet.class);
        servletClasses.add(UserServlet.class);
    }

    public List<Class<? extends AbstractRoutableServlet>> getServletClasses(){
        addServlet();
        return servletClasses;
    }
}
