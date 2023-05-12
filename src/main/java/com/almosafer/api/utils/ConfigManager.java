package com.almosafer.api.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static ConfigManager instance;
    private static final Object lock = new Object();
    private static String propertyFilePath = System.getProperty("user.dir")+
            "\\src\\main\\resources\\config.properties";
    private static String url;
    private static String token;

    //Create a Singleton instance. We need only one instance of Property Manager.
    public static ConfigManager getInstance () {
        if (instance == null) {
            synchronized (lock) {
                instance = new ConfigManager();
                instance.loadData();
            }
        }
        return instance;
    }
    //Get all configuration data and assign to related fields.
    private void loadData() {
        //Declare a properties object
        Properties prop = new Properties();
        //Read configuration.properties file
        try {
            prop.load(new FileInputStream(propertyFilePath));
        } catch (IOException e) {
            System.out.println("Configuration properties file cannot be found");
        }
        //Get properties from configuration.properties
        url = prop.getProperty("baseURL");
        token = prop.getProperty("token");
    }
    public String getURL () {
        return url;
    }
    public String gettoken () {
        return token;
    }

}
