package Servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    private FakeDataBase fakeDataBase;

    public LoginServlet(FakeDataBase fakeDataBase) {
        this.fakeDataBase = fakeDataBase;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //อะไรที่ต้องทำก่อนเปิดเว็ป
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");// ตัวที่จะบอกว่าจะเปิด jsp ตัวไหน
        requestDispatcher.include(request,response); // include  ให้มันไปเปิด Dispatcher ตัวนั้นให้เรา
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //อะไรที่ต้องทำหลังเปิดเว็ป
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (fakeDataBase.checkDatabase(username,password)) {
            System.out.println("hiiiiii");
            HttpSession httpSession = request.getSession(); //ของ หรือ object ที่เราเก็บได้
            httpSession.setAttribute("username",username); //เอาของใส่ session
            response.sendRedirect("/home");
        }
        else {
            String error = "Please try again, Password or Username is invalid";
            request.setAttribute("error",error); //เอาของเก็บไว้ใน req
            System.out.println("hiiiiii");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/login.jsp"); // if we redirect right away the object that we have will be gone
            requestDispatcher.include(request,response); // let requestDispatcher open the login page for you , if you redirect then that's mean you use the nre request.
        }
    }
}

