package net.servlet.appservlets.department.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import net.servlet.connection.DataBaseConnection;
import net.servlet.entities.Department;
import net.servlet.entities.Employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GetDepartmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("getDepartment.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long departmentId = Long.valueOf(req.getParameter("id"));

        Class.forName("org.postgresql.Driver");
        Connection con = DataBaseConnection.getInstance().getConnection();
        String sql = "select * from department where id = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setLong(1, departmentId);
        ResultSet rs = st.executeQuery();
        Department department = null;
        if (rs.next()) {
            department = new Department(rs.getLong("id"),
                    rs.getString("name"));
        }
        req.setAttribute("department", department);
        req.setAttribute("id", departmentId);
        doGet(req, resp);
    }
}
