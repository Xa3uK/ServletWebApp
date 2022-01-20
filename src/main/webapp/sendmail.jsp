<%--
  Created by IntelliJ IDEA.
  User: xa3uk
  Date: 18.01.2022
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SendMail</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue w3-opacity w3-right-align">
    <h1>Servlet Web App</h1>
</div>

<div class="w3-container w3-padding">
    <body>
    <form action="sendmail" method="POST">
        email: <input type="text" name="email">
        <br/>
        subject: <input type="text" name="subject">
        <br/>
        message: <input type="text" name="message"/>
        <input type="submit" value="SEND MAIL"/>
    </form>
    </body>
</div>


<div class="w3-container w3-blue w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/'">Back to main</button>
</div>
</body>
</html>
