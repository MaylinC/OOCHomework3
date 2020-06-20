package Servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends AbstractRoutableServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //อะไรที่ต้องทำก่อนเปิดเว็ป
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");// ตัวที่จะบอกว่าจะเปิด jsp ตัวไหน
        requestDispatcher.include(request,response); // include  ให้มันไปเปิด Dispatcher ตัวนั้นให้เรา
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //อะไรที่ต้องทำหลังเปิดเว็ป
        try {
            if (authentication.login(request)) {
                response.sendRedirect("/");
            }
            else {
                String error = "Please try again, Password or Username is invalid";
                request.setAttribute("error",error); //เอาของเก็บไว้ใน req , not put in session because we don't want it all the time
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/login.jsp"); // if we redirect right away the object that we have will be gone
                requestDispatcher.include(request,response); // let requestDispatcher open the login page for you , if you redirect then that's mean you use the nre request.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPattern() {
        return "/login";
    }
}

