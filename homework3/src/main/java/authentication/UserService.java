package authentication;

import Servlet.DatabaseConnector;

import java.sql.SQLException;

public class UserService {

    private DatabaseConnector databaseConnector;

    public UserService(DatabaseConnector databaseConnector){
        this.databaseConnector = databaseConnector;
    }

    public User getUserByUsername(String username) throws SQLException, ClassNotFoundException {
        return databaseConnector.getDataFromUser(username);
    }

    public void addUser(String username,String password, String name) throws SQLException, ClassNotFoundException {
        databaseConnector.addUser(username, password, name);
    }

    public Boolean checkUserExist(String username) throws SQLException, ClassNotFoundException {
        if(databaseConnector.checkUser(username)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void  removeUser(String username) throws SQLException, ClassNotFoundException {
        databaseConnector.removeData(username);
    }
}
