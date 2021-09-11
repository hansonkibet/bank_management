<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bank management system</title>
    <link rel="stylesheet" href="../stylesheet/bootstrap.min.css">
</head>
<body>
<h1>WELCOME TO BANK MANAGEMENT SYSTEM</h1>
<p>CUSTOMER REGISTRATION FORM</p>
<form action="CustomerServlet" method="post">
    <p>Enter ID/Passport number <input type="number" name="id" required></p>
    <p>Enter your name <input type="text" name="name" required></p>
    <p>gender (MALE/FEMALE) <input type="text" name="gender" required></p>
    <p>Enter email <input type="text" name="email" required></p>
    <p>Enter phone <input type="text" name="phone" required></p>
    <p>Enter kra pin <input type="text" name="kra" required></p>
    <button class="btn btn-primary">Submit</button>
    <p><input type="submit" value="register"></p>
</form>
</body>
</html>