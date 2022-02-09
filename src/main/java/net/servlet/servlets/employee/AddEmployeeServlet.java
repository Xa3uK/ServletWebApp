package net.servlet.servlets.employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import net.servlet.services.EmployeeManageService;

import java.io.IOException;

@WebServlet("/addEmployee")
public class AddEmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("employee/addEmployee.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long departmentId = Long.valueOf(req.getParameter("departmentId"));
        Long chiefId = null;
        if (req.getParameter("chiefId").length() > 0) {
            chiefId = Long.valueOf(req.getParameter("chiefId"));
        }
        String name = req.getParameter("name");
        int salary = Integer.parseInt(req.getParameter("salary"));
        EmployeeManageService ems = new EmployeeManageService();
        Long id = ems.addEmployee(departmentId, chiefId, name, salary);
        req.setAttribute("employeeName", name);
        req.setAttribute("id", id);
        doGet(req, resp);
    }
}
