package Servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private FakeDataBase fakeDataBase;

    public RegisterServlet(FakeDataBase fakeDataBase) {
        this.fakeDataBase = fakeDataBase;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/registerUser.jsp");
        requestDispatcher.include(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = String.valueOf(request.getParameter("username"));
        String password = String.valueOf(request.getParameter("password"));
        System.out.println(username);
        System.out.println(password);
        fakeDataBase.addDataBase(username,password);
        }
    }
