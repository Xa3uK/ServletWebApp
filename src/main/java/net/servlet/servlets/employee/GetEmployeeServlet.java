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

import java.io.IOException;
import java.sql.*;
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

        Class.forName("org.postgresql.Driver");
        Connection con = DataBaseConnection.getInstance().getConnection();
        String sql = "select * from employee where id = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setLong(1, employeeId);
        ResultSet rs = st.executeQuery();
        Employee employee = null;
        if (rs.next()) {
            Long chiefId = rs.getLong("chief_id");
            if(rs.wasNull()){
                chiefId = null;
            }
            employee = new Employee(rs.getLong("id"),
                    rs.getLong("department_id"),
                    chiefId,
                    rs.getString("name"),
                    rs.getInt("salary"));
        }
        req.setAttribute("employee", employee);
        req.setAttribute("id", employeeId);
        doGet(req, resp);
    }
}
