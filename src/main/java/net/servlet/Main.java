package net.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/homework";
        String login = "xa3uk";
        String pass = "perilrulit1";

        try {
            Connection con = DriverManager.getConnection(url, login, pass);
            Statement st = con.createStatement();
           String s = String.valueOf(st.execute("select * from employee"));
            System.out.println(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
