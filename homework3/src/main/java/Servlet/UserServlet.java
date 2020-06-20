package Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // open front web
        HttpSession httpSession = request.getSession();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //อะไรที่ต้องทำหลังเปิดเว็ป
        String username = request.getParameter("username");
    }
}


