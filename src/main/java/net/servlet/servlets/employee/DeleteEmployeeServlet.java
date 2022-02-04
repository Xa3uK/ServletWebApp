package net.servlet.servlets.employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import net.servlet.connection.DataBaseConnection;
import org.postgresql.Driver;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/deleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("employee/deleteEmloyee.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Class.forName("org.postgresql.Driver");
        Connection con = DataBaseConnection.getInstance().getConnection();
        PreparedStatement st = con.prepareStatement("delete from employee where id=?");
        st.setLong(1, id);
        int execute = st.executeUpdate();
        if (execute > 0) {
            req.setAttribute("isDelete", Boolean.TRUE);
        }
        req.setAttribute("id", id);
        doGet(req, resp);
    }
}
