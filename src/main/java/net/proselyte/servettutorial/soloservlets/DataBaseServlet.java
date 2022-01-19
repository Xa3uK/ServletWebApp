package net.proselyte.servettutorial.soloservlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class DataBaseServlet extends HttpServlet {
    static final String DRIVER_DB = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/homework";
    static final String DATABASE_USER = "xa3uk";
    static final String DATABASE_PASSWORD = "perilrulit1";


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String title = "Database Demo";
        String docType = "<!DOCTYPE html>";

        try {
            Class.forName(DRIVER_DB);
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employee");

            writer.println(docType + "<html><head><title>" + title + "</title></head><body>");
            writer.println("<h1>DEVELOPERS DATA</h1>");
            writer.println("<br/>");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(4);
                int salary = resultSet.getInt(5);

                writer.println("ID: " + id);
                writer.println("First name: " + firstName + "<br/>");
                writer.println("Salary: $" + salary + "<br/>");
            }

            resultSet.close();
            statement.close();
            connection.close();
            writer.println("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}