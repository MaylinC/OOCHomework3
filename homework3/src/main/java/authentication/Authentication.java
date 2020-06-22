package authentication;

import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class Authentication { // check user in the sys , log in , log out

    private UserService userService;

    public Authentication(UserService userService){
        this.userService = userService;
    }

    public Boolean isAuthenticated(HttpServletRequest request) { //check if input username is null or not
        HttpSession session = request.getSession();
        return session.getAttribute("username") != null;
    }

    public String getCurrentUsername(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (String) session.getAttribute("username"); //.getAttri return object so we cast it
    }

    public UserService getUserService() {
        return userService;
    }

    public Boolean login(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.getUserByUsername(username);
        if (userService.checkExist(username)) {
            if (BCrypt.checkpw(password, user.getPassword())) {
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("username", username);
                return true;
            }
            else{
                return false;
            }
        } else {
            return false;
        }
    }

    public void logout(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("username");
        httpSession.invalidate();
    }
}
