package net.servlet.servlets.employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import net.servlet.entities.EmployeeWithDep;
import net.servlet.services.EmployeeManageService;

import java.io.IOException;
import java.util.List;

@WebServlet("/listEmployee")
public class EmployeeListServlet extends HttpServlet {

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeManageService ems = new EmployeeManageService();
        List<EmployeeWithDep> employees = ems.getEmployeeWithDep();
        req.setAttribute("employeesData", employees);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("employee/listEmployee.jsp");
        requestDispatcher.forward(req, resp);
    }
}
