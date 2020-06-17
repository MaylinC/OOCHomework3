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
        docBase.mkdir();

        try {
            Context context = tomcat.addWebapp("", docBase.getAbsolutePath());

            FakeDataBase fakeDataBase = new FakeDataBase();
            HomeServlet homeServlet = new HomeServlet();
            Tomcat.addServlet(context, homeServlet.getClass().getName(), homeServlet);
            context.addServletMapping("/home", homeServlet.getClass().getName());

            LoginServlet loginServlet = new LoginServlet(fakeDataBase);
            Tomcat.addServlet(context, loginServlet.getClass().getName(), loginServlet);
            context.addServletMapping("/login", loginServlet.getClass().getName());

            RegisterServlet registerServlet = new RegisterServlet(fakeDataBase);
            Tomcat.addServlet(context, registerServlet.getClass().getName(), registerServlet);
            context.addServletMapping("/registerUser", registerServlet.getClass().getName());

            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException | ServletException e) {
            e.printStackTrace();
        }
    }
}
