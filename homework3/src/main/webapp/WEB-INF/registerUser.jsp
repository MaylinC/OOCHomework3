<%--
  Created by IntelliJ IDEA.
  User: maylin
  Date: 6/16/20
  Time: 9:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Page</title>
</head>
<h2>
    Please register your username and password
</h2>
<body>
<p>
    ${error}
</p>
<p>
<form method="post">
    <label for="name"><b>Name</b></label>
    <input type="text" placeholder="Enter Name" name="Name" required>
    <br>
    <label for="username"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="username" required>
    <br>
    <label for="password"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>
    <br>
    <br>
    <button type="submit" name="addUser">AddUser</button>
</form>
<form method="post">
<button type="submit" name="back">Back</button>
</form>
</p>
</body>
</html>
