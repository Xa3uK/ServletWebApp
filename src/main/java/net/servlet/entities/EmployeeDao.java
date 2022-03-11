package net.servlet.entities;

import net.servlet.connection.DataBaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    Connection connection;
    Statement stmt;
    private int noOfRecords;

    public EmployeeDao() {
    }

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        return DataBaseConnection.getInstance().getConnection();
    }

    public List<Employee> viewAllEmployees(int offset, int noOfRecords) {
        String query = "SELECT * FROM employee LIMIT " + noOfRecords + " OFFSET " + offset;
        List<Employee> list = new ArrayList<Employee>();
        Employee employee = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                employee = new Employee();
                employee.setId(rs.getLong("id"));
                employee.setName(rs.getString("name"));
                employee.setSalary(rs.getInt("salary"));
                list.add(employee);
            }
            rs.close();

            rs = stmt.executeQuery("SELECT count(*) FROM employee");
            if (rs.next())
                this.noOfRecords = rs.getInt(1);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

}
