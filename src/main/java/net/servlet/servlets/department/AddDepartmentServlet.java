package net.servlet.servlets.department;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import net.servlet.connection.DataBaseConnection;
import net.servlet.services.DepartmentManageService;

import java.io.IOException;
import java.sql.*;
@WebServlet("/addDepartment")
public class AddDepartmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("department/addDepartment.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("departmentName");
        DepartmentManageService dms = new DepartmentManageService();
        Long id = dms.addDepartment(name);
        req.setAttribute("departmentName", name);
        req.setAttribute("id", id);
        doGet(req, resp);
    }
}
