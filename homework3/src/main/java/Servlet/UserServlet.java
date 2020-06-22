package Servlet;

import authentication.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class UserServlet extends AbstractRoutableServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // open front web
        if(authentication.isAuthenticated(request)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/user.jsp");
            requestDispatcher.include(request,response); //Dispatcher , for เชื่อ ระหว่าง require and jsp
        }
        else {
            response.sendRedirect("/login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //อะไรที่ต้องทำหลังเปิดเว็ป
        HttpSession httpSession = request.getSession();
        String remove = request.getParameter("remove");
        String edit = request.getParameter("edit");
        User user = null;
        try {
            user = authentication.getUserService().getUserByUsername(remove);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (remove != null) {
            try {
                if (!user.getUsername().equals(httpSession.getAttribute("username"))) {
                    authentication.getUserService().removeUser(user.getUsername());
                    String warning = "You have now deleted the user";
                    request.setAttribute("warning", warning);
                    response.sendRedirect("/user");
                } else {
                    String warning = "you can't delete your our database";
                    request.setAttribute("warning", warning);
                    response.sendRedirect("/user");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (request.getParameter("back") != null) {
            response.sendRedirect("/index.jsp");
        }

        if (request.getParameter("register") != null) {
            response.sendRedirect("/registerUser");
        }

        if (edit != null) {
            httpSession.setAttribute("edit", edit);
            response.sendRedirect("/edit");
        }
    }

    @Override
    public String getPattern() {
        return "/user";
    }
}


