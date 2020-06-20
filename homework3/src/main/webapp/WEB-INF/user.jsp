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
        <td>Remove</td>
        <td>Edit</td>
    </tr>
    <%
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connUrl = "jdbc:mysql://localhost:3306/Hw3OOC";
            String username = "Maylin";
            String password = "Maylin0972387819";
            String query = "select * from data";
            Connection connection = DriverManager.getConnection(connUrl,username,password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

            %>

            <%







    %>
</table>
</form>


</body>
</html>
