<%--
  Created by IntelliJ IDEA.
  User: OS
  Date: 6/11/2021
  Time: 3:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Create new Customer</h2>
<form action="/create" method="post">
    <label>Id:</label>
    <input type="text" name="id"><br>
    <label>Name:</label>
    <input type="text" name="name"><br>
    <label>Email:</label>
    <input type="text" name="email"><br>
    <label>Country:</label>
    <input type="text" name="country">

    <button type="submit">Submit</button>
</form>
</body>
</html>
