package Servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HomeServlet extends AbstractRoutableServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // open front web
        HttpSession httpSession = request.getSession(); // keep user name for use (if you log in it will stay with use all the time util we move it out) , sent req to the web then web then respond back, (session make us hold smt which it will follow us throughout the request and respond)
        if(authentication.isAuthenticated(request)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/home.jsp");
            requestDispatcher.include(request,response); //Dispatcher , for เชื่อ ระหว่าง require and jsp
        }
        else {
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // after that we you can do
        if (request.getParameter("register")!=null) {
            response.sendRedirect("/registerUser");
        }

        if (request.getParameter("logout")!= null) {
            authentication.logout(request);
            response.sendRedirect("/login");
        }

        if (request.getParameter("alter")!= null) {
            response.sendRedirect("/alter");
        }

        if (request.getParameter("user")!= null) {
            response.sendRedirect("/user");
        }
    }

    @Override
    public String getPattern() {
        return "/home"; // link to home page
    }
}
