package net.proselyte.servettutorial.appservlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import net.proselyte.servettutorial.entities.Employee;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListServlet extends HttpServlet {
    static final String DRIVER_DB = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/homework";
    static final String DATABASE_USER = "xa3uk";
    static final String DATABASE_PASSWORD = "perilrulit1";


    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Model model = Model.getInstance();
//        List<String> names = model.list();
//        req.setAttribute("userNames", names);
        List<Employee> employees = new ArrayList<>();

        Class.forName(DRIVER_DB);
        Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from employee");
        while (rs.next()) {
            Long id = rs.getLong("id");
            Long departmentId = rs.getLong("department_id");
            Long chiefId = rs.getLong("chief_id");
            String name = rs.getString("name");
            int salary = rs.getInt("salary");
            employees.add(new Employee(id, departmentId, chiefId, name, salary));
        }
        req.setAttribute("employeesData", employees);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("list.jsp");
        requestDispatcher.forward(req, resp);
    }
}
