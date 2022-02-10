package net.servlet.connection;

import lombok.SneakyThrows;
import net.servlet.AppConfig;

import java.sql.Connection;
import java.sql.DriverManager;


public class DataBaseConnection {
    private static DataBaseConnection instance;
    private static Connection connection;

    @SneakyThrows
    public Connection getConnection(){
        if(connection == null){
            AppConfig.init();
            String url = AppConfig.getProperty("db.url");
            String login = AppConfig.getProperty("db.login");
            String password = AppConfig.getProperty("db.password");
            String driver = AppConfig.getProperty("db.driver");
            Class.forName(driver);
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
