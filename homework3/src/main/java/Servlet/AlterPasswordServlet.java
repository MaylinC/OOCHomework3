package Servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class AlterPasswordServlet extends AbstractRoutableServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // open front web
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/alterPassword.jsp");
        requestDispatcher.include(request,response); //Dispatcher , for เชื่อ ระหว่าง require and jsp
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // after that we you can do
        String password = request.getParameter("password");
        if (request.getParameter("alter")!= null) {
            try {
                authentication.getUserService().changePasswordAndName((String) request.getSession().getAttribute("username"),password,"password");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            response.sendRedirect("/index.jsp");
        }
        if (request.getParameter("back")!= null) {
            response.sendRedirect("/index.jsp");
        }
    }

    @Override
    public String getPattern() {
        return "/alter";
    }
}
