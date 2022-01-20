package net.servlet.connection;

import lombok.SneakyThrows;
import org.postgresql.Driver;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DataBaseConnection {
    private static DataBaseConnection instance;
    private static Connection connection;

    @SneakyThrows
    public Connection getConnection(){
        if(connection == null){
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("/Users/xa3uk/Desktop/hillel/servproselyte/src/main/resources/config.properties");
            prop.load(fis);
            String url = prop.getProperty("db.host");
            String login = prop.getProperty("db.login");
            String password = prop.getProperty("db.password");
            connection = DriverManager.getConnection(url, login, password);
        }
        return connection;
    }

    public static DataBaseConnection getInstance(){
        if (instance == null){
            instance = new DataBaseConnection();
        }
        return instance;
    }
}
