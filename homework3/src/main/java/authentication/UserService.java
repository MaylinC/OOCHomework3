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

    public void addUser(String name,String password, String username) throws SQLException, ClassNotFoundException {
        databaseConnector.addUser(name,password,username);
    }

    public Boolean checkExist(String username) throws SQLException, ClassNotFoundException {
        if(databaseConnector.checkUser(username)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void changePasswordAndName(String username,String alter, String change) throws SQLException, ClassNotFoundException {
        databaseConnector.editUser(username,alter,change);
    }

    public void  removeUser(String username) throws SQLException, ClassNotFoundException {
        databaseConnector.removeData(username);
    }
}
