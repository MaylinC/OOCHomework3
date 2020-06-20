package Servlet;

import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.sql.rowset.serial.SerialException;
import java.io.File;

public class main {
    public static void main(String[] args) {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        File docBase = new File("/Users/maylin/Desktop/OOCHomework3/homework3/src/main/webapp");
        docBase.mkdir(); // ชี้ไปที่ webapp

        try {
            Context context = tomcat.addWebapp("", docBase.getAbsolutePath());

            ServletRouter servletRouter = new ServletRouter();
            servletRouter.initialize(context);

            tomcat.start();
            tomcat.getServer().await();

        } catch (LifecycleException | ServletException e) {
            e.printStackTrace();
        }
    }
}
