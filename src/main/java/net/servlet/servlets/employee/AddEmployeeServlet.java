package net.servlet.servlets.employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import net.servlet.connection.DataBaseConnection;

import java.io.IOException;
import java.sql.*;

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

        Class.forName("org.postgresql.Driver");
        Connection con = DataBaseConnection.getInstance().getConnection();
        String sql = "INSERT INTO employee (department_id, chief_id, name, salary) VALUES (?, ?, ?, ?)";
        PreparedStatement st = con.prepareStatement(sql, new String[]{"id"});
        st.setLong(1, departmentId);
        if (chiefId == null) {
            st.setNull(2, Types.NULL);
        } else {
            st.setLong(2, chiefId);
        }
        st.setString(3, name);
        st.setInt(4, salary);
        st.executeUpdate();
        Long id = null;
        ResultSet gk = st.getGeneratedKeys();
        if (gk.next()) {
            id = gk.getLong("id");
        }
        req.setAttribute("employeeName", name);
        req.setAttribute("id", id);
        doGet(req, resp);
    }
}
