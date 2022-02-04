package net.servlet.servlets.employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import net.servlet.connection.DataBaseConnection;
import net.servlet.entities.Employee;
import net.servlet.entities.EmployeeWithDep;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/listEmployee")
public class EmployeeListServlet extends HttpServlet {

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<EmployeeWithDep> employees = new ArrayList<>();
        String sql = "select e.id, e.chief_id, e.department_id, e.name as emp_name, e.salary, d.name as dep_name from employee e\n" +
                "join department d on e.department_id = d.id";

        Class.forName("org.postgresql.Driver");
        Connection con = DataBaseConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Long id = rs.getLong("id");
            Long departmentId = rs.getLong("department_id");
            Long chiefId = rs.getLong("chief_id");
            if (rs.wasNull()) {
                chiefId = null;
            }
            String name = rs.getString("emp_name");
            int salary = rs.getInt("salary");
            String depName = rs.getString("dep_name");
            employees.add(new EmployeeWithDep(id, departmentId, chiefId, name, salary, depName));
        }
        employees = employees.stream()
                .sorted(Comparator.comparing(Employee::getId))
                .collect(Collectors.toList());
        req.setAttribute("employeesData", employees);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("employee/listEmployee.jsp");
        requestDispatcher.forward(req, resp);
    }
}
