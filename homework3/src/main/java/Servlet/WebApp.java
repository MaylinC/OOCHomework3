package Servlet;

import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.sql.rowset.serial.SerialException;
import java.io.File;

public class WebApp {
    public static void main(String[] args) {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        File docBase = new File("src/main/webapp");
        docBase.mkdirs(); // ชี้ไปที่ webapp

        try {
            Context context = tomcat.addWebapp("", docBase.getAbsolutePath());
            ServletRouter servletRouter = new ServletRouter();
            servletRouter.initialize(context);
            FilterAllServlet filterAllServlet = new FilterAllServlet(servletRouter);
            FilterDef filterDef = new FilterDef();
            filterDef.setFilter(filterAllServlet);
            filterDef.setFilterName(ServletRouter.class.getSimpleName());
            context.addFilterDef(filterDef);

            FilterMap filterMap = new FilterMap();
            filterMap.setFilterName(ServletRouter.class.getSimpleName());
            filterMap.addURLPattern("/*"); //apply with every link
            context.addFilterMap(filterMap);

            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException | ServletException e) {
            e.printStackTrace();
        }
    }
}
