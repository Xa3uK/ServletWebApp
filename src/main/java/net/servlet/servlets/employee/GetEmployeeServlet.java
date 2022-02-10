package net.servlet.servlets.employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import net.servlet.entities.Employee;
import net.servlet.services.EmployeeManageService;

import java.io.IOException;

@WebServlet("/getEmployee")
public class GetEmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("employee/getEmployee.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long employeeId = Long.valueOf(req.getParameter("id"));
        EmployeeManageService ems = new EmployeeManageService();
        Employee employee = ems.getEmployee(employeeId);
        req.setAttribute("employee", employee);
        req.setAttribute("id", employeeId);
        doGet(req, resp);
    }
}
