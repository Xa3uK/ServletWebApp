<%@ page import="java.util.List" %>
<%@ page import="net.proselyte.servlet.entities.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Users list</title>
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
  <h1>HAZAPOWER SUPER APP</h1>
</div>

<div class="w3-container w3-center w3-margin-bottom w3-padding">
  <div class="w3-card-4">
    <div class="w3-container w3-light-blue">
      <h2>Employees</h2>
    </div>
    <%
      List<Employee> employees = (List<Employee>) request.getAttribute("employeesData");

      if (employees != null && !employees.isEmpty()) {
        out.println("<ul class=\"w3-ul\">");
        for (Employee e : employees) {
          out.println("<li class=\"w3-hover-sand\">" + "id: " + e.getId() + ", " + e.getName() + ", salary: " + e.getSalary() + "</li>");
        }
        out.println("</ul>");

      } else out.println("<div class=\"w3-panel w3-green w3-display-container w3-card-4 w3-round\">\n"
              +
              "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
              "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-green w3-border w3-border-green w3-hover-border-grey\">×</span>\n" +
              "   <h5>There are no users yet!</h5>\n" +
              "</div>");
    %>
  </div>
</div>

<div class="w3-container w3-blue w3-opacity w3-right-align w3-padding">
  <button class="w3-btn w3-round-large" onclick="location.href='/'">Back to main</button>
</div>
</body>
</html>