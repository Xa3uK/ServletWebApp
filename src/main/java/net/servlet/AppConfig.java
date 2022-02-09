package net.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static Properties prop;

    public static void init() {
        String pathToFile = System.getenv("APP_DIR") + "/app.properties";
        try (InputStream input = new FileInputStream(pathToFile)) {
            prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            prop.load(input);

            System.out.println("Properties loaded from " + pathToFile);
            System.out.println(prop.getProperty("db.url"));
            System.out.println(prop.getProperty("db.user"));
            System.out.println(prop.getProperty("db.password"));
            System.out.println(prop.getProperty("db.driver"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}

