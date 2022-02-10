package net.servlet.services;

import lombok.SneakyThrows;
import net.servlet.connection.DataBaseConnection;
import net.servlet.entities.Employee;
import net.servlet.entities.EmployeeWithDep;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeManageService {

    @SneakyThrows
    public List<EmployeeWithDep> getEmployeeWithDep() {
        List<EmployeeWithDep> employees = new ArrayList<>();
        String sql = "select e.id, e.chief_id, e.department_id, e.name as emp_name, e.salary, d.name as dep_name from employee e\n" +
                "join department d on e.department_id = d.id";

        Connection con = DataBaseConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Long id = rs.getLong("id");
            Long departmentId = rs.getLong("department_id");
            Long chiefId = rs.getLong("chief_id");
            if (rs.wasNull()) {
                chiefId = null;
            }
            String name = rs.getString("emp_name");
            int salary = rs.getInt("salary");
            String depName = rs.getString("dep_name");
            employees.add(new EmployeeWithDep(id, departmentId, chiefId, name, salary, depName));
        }
        return employees = employees.stream()
                .sorted(Comparator.comparing(Employee::getId))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public Employee getEmployee(Long id) {
        Connection con = DataBaseConnection.getInstance().getConnection();
        String sql = "select * from employee where id = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setLong(1, id);
        ResultSet rs = st.executeQuery();
        Employee employee = null;
        if (rs.next()) {
            Long chiefId = rs.getLong("chief_id");
            if (rs.wasNull()) {
                chiefId = null;
            }
            employee = new Employee(rs.getLong("id"),
                    rs.getLong("department_id"),
                    chiefId,
                    rs.getString("name"),
                    rs.getInt("salary"));
        }
        return employee;
    }

    @SneakyThrows
    public int deleteEmployee(Long id) {
        Connection con = DataBaseConnection.getInstance().getConnection();
        PreparedStatement st = con.prepareStatement("delete from employee where id=?");
        st.setLong(1, id);
        return st.executeUpdate();
    }

    @SneakyThrows
    public Long addEmployee(Long departmentId, Long chiefId, String name, int salary) {
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
        return id;
    }

    @SneakyThrows
    public int updateEmployee(Long departmentId, Long chiefId, String name, int salary, Long id) {
        Connection con = DataBaseConnection.getInstance().getConnection();
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
        return st.executeUpdate();
    }
}