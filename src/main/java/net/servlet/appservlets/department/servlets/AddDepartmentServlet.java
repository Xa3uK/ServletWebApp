package net.servlet.appservlets.department.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import net.servlet.connection.DataBaseConnection;

import java.io.IOException;
import java.sql.*;

public class AddDepartmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("addDepartment.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("departmentName");

        Class.forName("org.postgresql.Driver");
        Connection con = DataBaseConnection.getInstance().getConnection();
        String sql = "INSERT INTO department (name) VALUES (?)";
        PreparedStatement st = con.prepareStatement(sql, new String[]{"id"});
        st.setString(1, name);
        st.executeUpdate();
        Long id = null;
        ResultSet gk = st.getGeneratedKeys();
        if (gk.next()) {
            id = gk.getLong("id");
        }
        req.setAttribute("departmentName", name);
        req.setAttribute("id", id);
        doGet(req, resp);
    }
}
