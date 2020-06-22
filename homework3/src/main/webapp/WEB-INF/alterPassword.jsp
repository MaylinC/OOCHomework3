<%--
  Created by IntelliJ IDEA.
  User: maylin
  Date: 6/20/20
  Time: 8:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<h2>
    Please Enter Your New Password
</h2>
<body>
<p>
    ${error}
</p>
<p>
<form method="post">
    <label for="password"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>
    <br>
    <br>
    <button type="submit" name="alter">AlterPassword</button>
</form>
<form method="post">
<button type="submit" name="back">Back</button>
</form>
</p>
</body>
</html>