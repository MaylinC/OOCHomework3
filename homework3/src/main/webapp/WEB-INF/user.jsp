<%--
  Created by IntelliJ IDEA.
  User: maylin
  Date: 6/19/20
  Time: 1:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="java.sql.*"%>
<html>
<head>
    <title>User Table</title>
</head>
<body>
<form method="post">
<table border="2">
    <tr>
        <td>ID</td>
        <td>Name</td>
<%--        <td>Edit</td>--%>
        <td>Remove</td>
    </tr>
    <%
        try {
            String connUrl = "jdbc:mysql://localhost:3306/Hw3OOC";
            String username = "Maylin";
            String password = "Maylin0972387819";
            Integer num = 0;
            String query = "select * from data";
            ResultSet resultSet = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(connUrl,username,password);
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
    %>
    <tr>
            <% num++; %>
            <td> <%=num%> </td>
            <td> <%= resultSet.getString("name")%> </td>
<%--            <td> <button type="submit" name="edit" value<%=resultSet.getNString("username")%>>Edit</button> </td>--%>
            <td> <button type="submit" name="remove" value=<%=resultSet.getNString("username")%>>Remove</button> </td>

    </tr>
    <%  }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    %>
</table>
    <button type="submit" name="register">Register</button>
    <button type="submit" name="back">Back</button>
</form>
</body>
</html>
