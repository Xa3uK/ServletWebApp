package net.servlet.services;

import lombok.SneakyThrows;
import net.servlet.connection.DataBaseConnection;
import net.servlet.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DepartmentManageService {

    @SneakyThrows
    public Department getDepartment(Long id) {
        Connection con = DataBaseConnection.getInstance().getConnection();
        String sql = "select * from department where id = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setLong(1, id);
        ResultSet rs = st.executeQuery();
        Department department = null;
        if (rs.next()) {
            department = new Department(rs.getLong("id"),
                    rs.getString("name"));
        }
        return department;
    }

    @SneakyThrows
    public int deleteDepartment(Long id) {
        Connection con = DataBaseConnection.getInstance().getConnection();
        PreparedStatement st = con.prepareStatement("delete from department where id=?");
        st.setLong(1, id);
        return st.executeUpdate();
    }

    @SneakyThrows
    public Long addDepartment(String name) {
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
        return id;
    }

    @SneakyThrows
    public int updateDepartment(String name, Long id) {
        Connection con = DataBaseConnection.getInstance().getConnection();
        String sql = "update department set name=? where id=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, name);
        st.setLong(2, id);
        return st.executeUpdate();
    }

    @SneakyThrows
    public List<Department> listDepartment() {
        List<Department> departments = new ArrayList<>();
        Connection con = DataBaseConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from department");
        while (rs.next()) {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            departments.add(new Department(id, name));
        }
        return departments.stream()
                .sorted(Comparator.comparing(Department::getId))
                .collect(Collectors.toList());
    }
}