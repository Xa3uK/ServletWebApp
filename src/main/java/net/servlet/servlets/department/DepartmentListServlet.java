package net.servlet.servlets.department;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import net.servlet.connection.DataBaseConnection;
import net.servlet.entities.Department;
import net.servlet.entities.Employee;
import net.servlet.services.DepartmentManageService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@WebServlet("/listDepartment")
public class DepartmentListServlet extends HttpServlet {

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DepartmentManageService dms = new DepartmentManageService();
        List<Department> departments = dms.listDepartment();

        req.setAttribute("departmentsData", departments);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("department/listDepartment.jsp");
        requestDispatcher.forward(req, resp);
    }
}
