package de.wko.mdb.cli;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Properties;

public class Settings {

    public static final String KEY_LOGIN_USERNAME = "user.name";
    public static final String KEY_LOGIN_PASSWORD = "user.password";
    public static final String KEY_SHADOW_ARCHIVE = "shadow.archive";

    private static Settings ourInstance = new Settings();

    private Properties prop;

    public static Settings getInstance() {
        return ourInstance;
    }

    public Settings() {
        try {
            File propFile = new File("setting.properties");
            propFile.createNewFile();
            InputStream input = new FileInputStream(propFile);

            prop = new Properties();
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void store() {
        try {
            OutputStream output = new FileOutputStream("setting.properties");
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void setProperty(String name, String value) {
        prop.setProperty(name, value);
        store();
    }

    public void unsetProperty(String name) {
        prop.remove(name);
        store();
    }

    public String getProperty(String name) {
        return prop.getProperty(name);
    }

}
