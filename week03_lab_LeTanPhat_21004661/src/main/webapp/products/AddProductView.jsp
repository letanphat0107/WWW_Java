<%--
  Created by IntelliJ IDEA.
  User: DMX
  Date: 10/5/2024
  Time: 5:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
    <form action="AddProductController" method="post">
        <input type="hidden" name="action" value="add">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br>
        <label for="description">Price:</label>
        <input type="text" id="description" name="description" required><br>
        <label for="img">Quantity:</label>
        <input type="text" id="img" name="img" required><br>
        <input type="submit" value="Add">
    </form>
</head>
<body>

</body>
</html>
