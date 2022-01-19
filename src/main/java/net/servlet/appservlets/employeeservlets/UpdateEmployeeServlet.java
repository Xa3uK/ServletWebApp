package net.servlet.appservlets.employeeservlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.*;

public class UpdateEmployeeServlet extends HttpServlet {
    static final String DRIVER_DB = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/homework";
    static final String DATABASE_USER = "xa3uk";
    static final String DATABASE_PASSWORD = "perilrulit1";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("updateEmployee.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Long departmentId = Long.valueOf(req.getParameter("departmentId"));
        Long chiefId = null;
        if (req.getParameter("chiefId").length() > 0) {
            chiefId = Long.valueOf(req.getParameter("chiefId"));
        }
        String name = req.getParameter("name");
        int salary = Integer.parseInt(req.getParameter("salary"));

        Class.forName(DRIVER_DB);
        Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        String sql = "update employee set department_id=?, chief_id=?, name=?, salary=? where id=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setLong(1, departmentId);
        if (chiefId == null) {
            st.setNull(2, Types.NULL);
        } else {
            st.setLong(2, chiefId);
        }
        st.setString(3, name);
        st.setInt(4, salary);
        st.setLong(5, id);
        int execute = st.executeUpdate();
        if (execute > 0) {
            req.setAttribute("isUpdate", Boolean.TRUE);
        }
        req.setAttribute("id", id);
        doGet(req, resp);
    }
}
