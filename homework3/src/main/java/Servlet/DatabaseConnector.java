package Servlet;

import authentication.User;

import java.sql.*;

public class DatabaseConnector {


    public Connection Connection() throws ClassNotFoundException,SQLException {
        Connection connection = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hw3OOC","Maylin", "Maylin0972387819");
        return connection;
    }

    public void addUser(String username, String password, String name) throws SQLException, ClassNotFoundException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        dbConnection = Connection();
        String query = "insert into data (name,password,username) values (?,?,?)";
        preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,password);
        preparedStatement.setString(3,username);
        preparedStatement.executeUpdate();
        dbConnection.close();
    }

    public void editUser(String username, String alter, String) {

    }

    public User getDataFromUser(String username) throws SQLException, ClassNotFoundException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        dbConnection = Connection();
        Statement statement = dbConnection.createStatement();
        String query = "select * from data";
        ResultSet resultSet = statement.executeQuery(query);
        User user = null;
        if (resultSet.next()) {
            if (resultSet.getNString("username").equals(username)) {
                user = new User(resultSet.getNString("username"), resultSet.getNString("password"), resultSet.getNString("name"));
            }
        }
        dbConnection.close();
        return user;
    }

    public Boolean checkUser(String username) throws SQLException, ClassNotFoundException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        dbConnection = Connection();
        String query = "select * from data // where username=?";
        preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery(query);
        dbConnection.close();
        return resultSet.next(); //if have username then return true, if not then false
    }

    public void removeData(String username) throws SQLException, ClassNotFoundException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        dbConnection = Connection();
        String query = "delete from data // where username=?;";
        preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1,username);
        preparedStatement.executeUpdate();
        dbConnection.close();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        databaseConnector.addUser("MaylinC","maylin1234","Maylin");
//        databaseConnector.getDataFromUser("maylin");
    }
}
