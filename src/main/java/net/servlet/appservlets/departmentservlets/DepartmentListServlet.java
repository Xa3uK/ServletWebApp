package net.servlet.appservlets.departmentservlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import net.servlet.entities.Department;
import net.servlet.entities.Employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DepartmentListServlet extends HttpServlet {
    static final String DRIVER_DB = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/homework";
    static final String DATABASE_USER = "xa3uk";
    static final String DATABASE_PASSWORD = "perilrulit1";

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departments = new ArrayList<>();

        Class.forName(DRIVER_DB);
        Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from department");
        while (rs.next()) {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            departments.add(new Department(id, name));
        }
        departments = departments.stream()
                        .sorted(Comparator.comparing(Department::getId))
                                .collect(Collectors.toList());
        req.setAttribute("departmentsData", departments);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("listDepartment.jsp");
        requestDispatcher.forward(req, resp);
    }
}
