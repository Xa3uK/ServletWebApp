package net.servlet.appservlets.employee.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import net.servlet.connection.DataBaseConnection;
import net.servlet.entities.Employee;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeListServlet extends HttpServlet {

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employees = new ArrayList<>();

        Class.forName("org.postgresql.Driver");
        Connection con = DataBaseConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from employee");
        while (rs.next()) {
            Long id = rs.getLong("id");
            Long departmentId = rs.getLong("department_id");
            Long chiefId = rs.getLong("chief_id");
            if (rs.wasNull()) {
                chiefId = null;
            }
            String name = rs.getString("name");
            int salary = rs.getInt("salary");
            employees.add(new Employee(id, departmentId, chiefId, name, salary));
        }
        employees = employees.stream()
                .sorted(Comparator.comparing(Employee::getId))
                .collect(Collectors.toList());
        req.setAttribute("employeesData", employees);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("listEmployee.jsp");
        requestDispatcher.forward(req, resp);
    }
}
