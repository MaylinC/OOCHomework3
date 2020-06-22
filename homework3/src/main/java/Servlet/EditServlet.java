package Servlet;

import authentication.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class EditServlet extends AbstractRoutableServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // open front web
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/edit.jsp");// ตัวที่จะบอกว่าจะเปิด jsp ตัวไหน
        requestDispatcher.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        String edit = request.getParameter("edit");
        String editName = request.getParameter("editName");
        String editUser = (String) httpSession.getAttribute("edit");
        if (edit != null) {
            try {
                authentication.getUserService().changePasswordAndName(editUser, editName, "name");
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            response.sendRedirect("/user");
        }
    }

    @Override
    public String getPattern() {
        return "/edit";
    }
}
